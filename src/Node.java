public class Node {
    private Node prev;
    private Node right;
    private Node left;
    private int IP;



    private int height = 1;


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node(int IP) {
        this.IP = IP;
        this.right = null;
        this.left = null;
        this.prev = null;
    }

    public Node(Node prev, Node right, Node left, int IP) {
        this.prev = prev;
        this.right = right;
        this.left = left;
        this.IP = IP;
    }

    public void setIP(int IP) {
        this.IP = IP;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }

    public int getIP() {
        return IP;
    }


}
