public class BST implements Tree{

    private Node root;


    public BST(int value) {
        this.root = new Node(value);

    }

    private String output = "";

    public String getOutput() {
        return output;
    }

    private int minValue(Node node) {
        while (node.getLeft() != null){
            node = node.getLeft();
        }
        return node.getIP();
    }

    private Node search(Node root, int key){
        if(root.getIP() == key)
            return root;
        else if (root.getIP() < key) {
            return search(root.getRight(), key);
        } else {
            return search(root.getLeft(), key);
        }
    }

    private boolean isRight(Node root){
        if (root.getPrev().getIP() < root.getIP())
            return true;
        return false;
    }


    public void addElement(int key) {

            Node Temp = root;
            Node Temp2 = root;
            while (Temp != null){
                output = output.concat(Temp.getIP() + ": New Node being added with IP:" + key + "\n");
                if (key< Temp.getIP()){
                    Temp2 = Temp;
                    Temp = Temp.getLeft();
                } else if (key > Temp.getIP()) {
                    Temp2 = Temp;
                    Temp = Temp.getRight();
                }
            }
            Node toAdd = new Node(Temp2,null,null,key);
            if (Temp2.getIP() > key)
                Temp2.setLeft(toAdd);
            else Temp2.setRight(toAdd);

        }




    @Override
    public void deleteItem(int key) {
        Node Temp = search(root, key);
        if (Temp == root) {
            root = null;
            return;
        }
        if (Temp.getRight() == null)
        {
            if(Temp.getLeft() == null)
            {
                output = output.concat(Temp.getPrev().getIP() + ": Leaf Node Deleted: " + Temp.getIP() + "\n");
                if (isRight(Temp))
                    Temp.getPrev().setRight(null);
                else Temp.getPrev().setLeft(null);
            }
            else
            {
                Node parent = Temp.getPrev();
                Node child = Temp.getLeft();
                output = output.concat(parent.getIP() + ": Node with single child Deleted: " + Temp.getIP() + "\n");
                parent.setLeft(child);
                child.setPrev(parent);

            }
            return;

        }
        else
        {
            int val = minValue(Temp.getRight());
            Node Temp2 = search(root, val);
            if (Temp2.getRight() != null) {
                Temp2.getRight().setPrev(Temp2.getPrev());
                Temp2.getPrev().setLeft(Temp2.getRight());
                Temp2.setPrev(null);
                Temp2.setRight(null);
            } else {
                Temp2.getPrev().setLeft(null);
                Temp2.setPrev(null);
            }

            output = output.concat(Temp.getPrev().getIP() + ": Non-Leaf Node deleted; removed: " + Temp.getIP() + " replaced " + val +"\n");
            Temp.setIP(val);


        }
    }

    @Override
    public void sendMessage(int sender, int receiver) {
        output = output.concat(sender + ": Sending message to: " + receiver + "\n");
        Node Temp = root;
        while(Temp.getIP() != sender || Temp.getIP()!=receiver)
        {
            if (sender < Temp.getIP() && receiver < Temp.getIP()) {
                Temp = Temp.getLeft();
                continue;
            }

            else if (sender > Temp.getIP() && receiver > Temp.getIP()) {
                Temp = Temp.getRight();
                continue;
            }
            break;

        }
        if (Temp.getIP() == sender){
            while(Temp.getIP()!=receiver){
                if (Temp.getIP() < receiver)
                    Temp = Temp.getRight();
                else Temp = Temp.getLeft();
                if (Temp.getIP() == receiver)
                    output = output.concat(receiver + ": Received message from: " + sender + "\n");
                else output = output.concat(Temp.getIP() + ": Transmission from: "+ Temp.getPrev().getIP() + " receiver: " + receiver + " sender:" + sender + "\n");
            }
        } else if (Temp.getIP() == receiver) {
            Node sen = search(Temp, sender);
            while(sen.getIP()!=receiver){
                if (sen.getPrev().getIP() == receiver)
                    output = output.concat(receiver + ": Received message from: " + sender + "\n");
                else output = output.concat(sen.getPrev().getIP() + ": Transmission from: "+ sen.getIP() + " receiver: " + receiver + " sender:" + sender + "\n");
                sen = sen.getPrev();
            }
        } else {
            Node sen = search(Temp, sender);
            while(sen.getIP()!= Temp.getIP()){
                output = output.concat(sen.getPrev().getIP() + ": Transmission from: "+ sen.getIP() + " receiver: " + receiver + " sender:" + sender + "\n");
                sen = sen.getPrev();
            }
            while(Temp.getIP()!=receiver){
                if (Temp.getIP() < receiver)
                    Temp = Temp.getRight();
                else Temp = Temp.getLeft();
                if (Temp.getIP() == receiver)
                    output = output.concat(receiver + ": Received message from: " + sender+ "\n");
                else output = output.concat(Temp.getIP() + ": Transmission from: "+ Temp.getPrev().getIP() + " receiver: " + receiver + " sender:" + sender + "\n");
            }

        }

    }
}
