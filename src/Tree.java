public class Tree {
    private TreeNode root;
    private int counter;

    //counter zum searchen
    public void setCounter(int counter) {
        this.counter = counter;
    }

    public TreeNode getRoot() {
        return root;
    }

    //add a Node to an empty place in the sorted tree.
    public void add(Transportation t) {
        if (root == null) {
            root = new TreeNode(t,null,null, true);

        } else {
            TreeNode n = root;
            while (n != null) {
                if (n.isVert()) {
                    if (t.getX() < n.getValue().getX()) {
                        if (n.getLeft() != null) {
                            n = n.getLeft();
                        } else {
                            n.setLeft(new TreeNode(t, null, null, !(n.isVert())));
                            break;
                        }
                    } else if (n.getRight() != null) {
                        n = n.getRight();
                    } else {
                        n.setRight(new TreeNode(t, null, null, !(n.isVert())));
                        break;
                    }
                }else{
                    if (t.getY() < n.getValue().getY()) {
                        if (n.getLeft() != null) {
                            n = n.getLeft();
                        } else {
                            n.setLeft(new TreeNode(t, null, null, !(n.isVert())));
                            break;
                        }
                    } else if (n.getRight() != null) {
                        n = n.getRight();
                    } else {
                        n.setRight(new TreeNode(t, null, null, !(n.isVert())));
                        break;
                    }
                }
            }
        }
    }

    //print the tree in alphabetically order
    public void print(){
        printrecursive(root);
    }

    //prints recursively the tree in alphabetically order
    public void printrecursive(TreeNode root){
                if(root !=null){
                    printrecursive(root.getLeft());
                    System.out.println(root.getValue().getName());
                    printrecursive(root.getRight());
                }
            }

    public int search(TreeNode tree, double x, double y, double radius){
            if (tree != null) {
                if(tree.isVert()) {
                    if (tree.getValue().getX() - radius < tree.getValue().getX()) {
                        search(tree.getLeft(), x, y, radius);
                    }
                    if (tree.getValue().getX() + radius >= tree.getValue().getX()) {
                        search(tree.getRight(), x, y, radius);
                    }
                }else{
                    if (tree.getValue().getY() - radius < tree.getValue().getY()) {
                        search(tree.getLeft(), x, y, radius);
                    }
                    if (tree.getValue().getY() + radius >= tree.getValue().getY()) {
                        search(tree.getRight(), x, y, radius);
                    }
                }
                if(tree.diff(x,y)<radius ){
                    counter++;
                }
            }
        return counter;
    }
 /*
    //returns the total song-length of the tree
    public long getLaenge(){
        return laengeRecursive(root, 0);
    }

    //recursively counts the length of the tree
    public long laengeRecursive(TreeNode current, long sum) {
        if (current != null) {
            return current.getSong().getLaenge()
        + laengeRecursive(current.getLeft(), sum)
        + laengeRecursive(current.getRight(), sum);
        }
        return sum;
    }
    */

    public static void main(String[] args) {

    }
}

