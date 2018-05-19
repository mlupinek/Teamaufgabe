/*
    Aufgabe 3)
*/
public class TreeNode {
    private Transportation value;
    private TreeNode left, right;
    private boolean vert;

    public TreeNode(Transportation value, TreeNode left, TreeNode right, boolean vertical) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.vert = vertical;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public TreeNode getLeft() {
        return left;
    }
    public boolean isVert() {
        return vert;
    }

    public Transportation getValue() {
        return value;
    }
    public double diff(double x, double y){
        double a = x - this.value.getX();
        double b = y - this.value.getY();
        return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
    }

}

