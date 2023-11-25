public class AVLT implements Tree{
    Node root;
    private String output = "";

    public AVLT(int value) {
        this.root = new Node(value);

    }
    private int avl_height(Node node) {
        return node == null ? 0 : node.getHeight();
    }
    private int max(int a,int b){
        return a<b ? b : a;
    }
    private void update_height(Node node) {
            node.setHeight(max(avl_height(node.getLeft()), avl_height(node.getRight())) + 1);
    }

    public void addElement(int key){
        root = addElement_avl(key, root);
    }
    public Node addElement_avl(int key, Node node)
    {
        if (node == null) return new Node(key);


        if (key < node.getIP())
        {
            output = output.concat(node.getIP() + ": New Node being added with IP:" + key + "\n");
            Node A =addElement_avl(key, node.getLeft());
            node.setLeft(A);
            A.setPrev(node);


        }
        else if (key > node.getIP())
        {
            output = output.concat(node.getIP() + ": New Node being added with IP:" + key + "\n");
            Node A = addElement_avl(key, node.getRight());
            node.setRight(A);
            A.setPrev(node);

        }
        else return node;


        update_height(node);

        return rebalance(node,key);




    }
    private int balance(Node node){
        return ( avl_height(node.getLeft()) - avl_height(node.getRight()));
    }

    public String getOutput_AVL() {
        return output;
    }

    private Node rebalance(Node node, int key) {
        int b = balance(node);

        if (b < -1 )
        {
            if (node.getRight().getIP() < key) {
                output = output.concat("Rebalancing: left rotation"+"\n");
                 return leftRotate(node);
            }
            else {
                node.setRight(rightRotate(node.getRight()));
                output = output.concat("Rebalancing: right-left rotation"+"\n" );
                return leftRotate(node);


            }
        }
        if (b > 1)
        {
            if (node.getLeft().getIP() > key) {
                output = output.concat("Rebalancing: right rotation"+"\n");
                 return rightRotate(node);
            }
            else {
                node.setLeft(leftRotate(node.getLeft()));
                output = output.concat("Rebalancing: left-right rotation"+"\n");
                  return  rightRotate(node);
            }
        }
        return node;
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
    @Override
    public void deleteItem(int key){
        deleteItem_avl(root,key);
    }
    public Node deleteItem_avl(Node node, int key) {

        if (node == null){
            return null;
        }

        if (key < node.getIP())
        {
            Node A =deleteItem_avl(node.getLeft(), key);
            node.setLeft(A);
            A.setPrev(node);


        }
        else if (key > node.getIP())
        {
            Node A =deleteItem_avl(node.getRight(), key);
            node.setRight(A);
            A.setPrev(node);

        }
        else{

            if(node.getLeft()==null && node.getRight()==null){
                output = output.concat(node.getPrev().getIP() + ": Leaf Node Deleted: " + node.getIP());
                if(isRight(node)) node.getPrev().setRight(null);
                else node.getPrev().setLeft(null);
            } else if (node.getLeft()==null || node.getRight()==null) {
                output = output.concat(node.getPrev().getIP() + ": Node with single child Deleted: " + node.getIP());
                if(isRight(node)) node.getPrev().setRight(null);
                else node.getPrev().setLeft(null);

            }else{
                int val = minValue(node.getRight());
                System.out.println(node.getIP());
                output = output.concat(node.getPrev().getIP() + ": Non-Leaf Node deleted; removed: " + node.getIP() + " replaced " + val +"\n");
                node.setIP(val);
                deleteItem_avl(node.getRight(),key);
            }
        }

        update_height(node);

        return rebalance(node,key);



    }
    private Node rightRotate(Node node) {
        Node iki = node.getLeft();
        node.setLeft(iki.getRight());
        iki.setRight(node);
        iki.setPrev(node.getPrev());
        node.setPrev(iki);
        if (iki.getPrev() != null) {
            if (isRight(iki)) iki.getPrev().setRight(iki);
            else iki.getPrev().setLeft(iki);
        } else;
        update_height(node);
        update_height(iki);

        return iki;
    }
    private boolean isRight(Node node){
        if (node.getPrev().getIP() < node.getIP())
            return true;
        return false;
    }

    private Node leftRotate(Node node) {
        Node bir = node.getRight();
        node.setRight(bir.getLeft());
        bir.setLeft(node);
        bir.setPrev(node.getPrev());
        node.setPrev(bir);
            if (bir.getPrev() != null)
            {
                if (isRight(bir)) {
                    bir.getPrev().setRight(bir);
                }
                else {
                    bir.getPrev().setLeft(bir);
                }
            }
            else ;

        update_height(node);
        update_height(bir);

        return bir;
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
