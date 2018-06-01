public class Tree {
    private TreeNode root;
    private int[] counter;

    //counter zum searchen
    public void newCounter() {
        counter = new int[2];
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

    public int[] search(TreeNode tree, double x, double y, double radius) {

        if (tree != null) {
            if (tree.isVert()) {
                if (x - radius <= tree.getValue().getX()) {
                    search(tree.getLeft(), x, y, radius);
                }
                if (x + radius >= tree.getValue().getX()) {
                    search(tree.getRight(), x, y, radius);
                }
            } else {
                if (y - radius <= tree.getValue().getY()) {
                    search(tree.getLeft(), x, y, radius);
                }
                if (y + radius >= tree.getValue().getY()) {
                    search(tree.getRight(), x, y, radius);
                }
            }
            if (tree.diff(x, y) < radius) {
                if (tree.getValue().isIsairport()) {
                    counter[0]++;
                } else {
                    counter[1]++;
                }
            }
        }
        return counter;
    }

}

