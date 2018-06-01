import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CSV {

    private Scanner s;
    private List list = new List();
    private Tree tree = new Tree();
    private Transportation [] arr1 = new Transportation[94068];
    private TreeNode root = null;
    private int count;

    public CSV() {
        newScanner();
        makeList();
        newScanner();
        makeTree();
    }

    public void newScanner(){
        try {
            s = new Scanner(
                    new File(System.getProperty("user.dir") +
                            "/data/junctions.csv"), "UTF-8") ;

            // Benutzen Sie das Scanner-Objekt s hier
            s.useDelimiter(";");

        } catch(FileNotFoundException e){
            // junctions.csv wurde nicht gefunden
            System.exit(1);
        }
    }
    public void makeList(){
        while(s.hasNextLine()){
            String [] arr = s.nextLine().split(";");
            list.add(arr);
        }
    }
    public void makeTree(){
        while(s.hasNextLine()){
            String [] arr = s.nextLine().split(";");
            boolean tf = true;
            if(arr[3].equals("TRAINSTATION")){
                tf= !tf;
            }
            Transportation a = new Transportation(arr[0],Double.parseDouble(arr[1]),Double.parseDouble(arr[2]),tf);
            tree.add(a);
        }
    }

    public int[] anzahlList(double x, double y, double radius){
        return list.search(x,y,radius);
    }

    public int[] anzahlTree(double x, double y, double radius){
        tree.newCounter();
        return tree.search(tree.getRoot(), x,y,radius);
    }

    public int anzahlBahnhoefeList(double radius, double n){
        DLNode dl = this.list.getHead();
        int counter = 0;
        while (dl !=null){
            if (dl.getValue().isIsairport() && anzahlList(dl.getValue().getX(), dl.getValue().getY(), radius)[1]>=n){
                counter++;
            }
            dl = dl.getNext();
        }
        return counter;
    }
    public int anzahlBahnhoefeTree(double radius, double n){
        count = 0;
        TreeNode tn = tree.getRoot();
        searchTrain(radius, n, tn);
        return count;
    }
    public void searchTrain(double radius, double n, TreeNode tn){
        if(tn != null) {
            searchTrain(radius, n, tn.getLeft());
            searchTrain(radius, n, tn.getRight());
            if (tn.getValue().isIsairport()){
                if (anzahlTree(tn.getValue().getX(), tn.getValue().getY(), radius)[1] >= n) {
                    count++;
                }
            }
        }
    }

    private Transportation [] changeXY(Transportation [] a){
        double x,y;
        for(int i = 0; i < a.length; i++){
            x = a[i].getX();
            y = a[i].getY();
            a[i].setY(x);
            a[i].setX(y);
        }
        return a;
    }

    public TreeNode makeTreeBalanced(Transportation [] a, boolean vert){
        TreeNode root = null;
        a = changeXY(a);
        Arrays.sort(a);


        if(vert!=true){
            a=changeXY(a);
            root = new TreeNode(a[a.length/2],null,null,vert);
            a=changeXY(a);
        }else{
            root = new TreeNode(a[a.length/2],null,null,vert);
        }


        TreeNode left = null;
        TreeNode right = null;
        if(a.length > 2){

            left = makeTreeBalanced(Arrays.copyOfRange(a,0,(a.length/2)),!vert);
            root.setLeft(left);
            right = makeTreeBalanced(Arrays.copyOfRange(a,(a.length/2+1), a.length),!vert);
            root.setRight(right);

        }else{

            if(a.length == 2){
                if(vert!=true){
                    a=changeXY(a);
                    root.setLeft(new TreeNode(a[0],null,null,vert));
                    a=changeXY(a);
                }else{
                    root.setLeft(new TreeNode(a[0],null,null,vert));
                }

            }

        }
        return root;



    }

    public int minDepth(TreeNode tn) {
        int a = 0;
        int b = 0;
        if (tn.getLeft() != null) {
            a = minDepth(tn.getLeft());
        }
        if (tn.getRight() != null) {
            b = minDepth(tn.getRight());
        }
        return (a<b?a:b)+1;
    }


    public int maxDepth(TreeNode tn) {
        int a = 0;
        int b = 0;
        if (tn.getLeft() != null) {
            a = maxDepth(tn.getLeft());
        }
        if (tn.getRight() != null) {
            b = maxDepth(tn.getRight());
        }
        return (a>b?a:b)+1;
    }

    public void print1(CSV csv, double x, double y, double r){
        System.out.println("Junctions less than " + r + " units from x=" + x + " y=" + y);
        int [] arr = csv.anzahlList(x,y,r);
        System.out.println("\t> Airports: " + arr[1] + "\tTrainstations: " + arr[0]);

    }
    public void print2(CSV csv, int t, double r){
        System.out.println("Airports with at least " + t + " trainstations less than " + r + " units away");
        long startTime = System.nanoTime();
        System.out.println("\t> " + csv.anzahlBahnhoefeList(r,t));
        long endTime = System.nanoTime();
        double erg = (endTime-startTime)*Math.pow(10,-9);
        System.out.println("Time: " + erg + "s");
    }
    public void print3(CSV csv, double x, double y, double r){
        System.out.println("Junctions less than " + r + " units from x=" + x + " y=" + y);
        int [] arr = csv.anzahlTree(x,y,r);
        System.out.println("\t> Airports: " + arr[1] + "\tTrainstations: " + arr[0]);

    }
    public void print4(CSV csv, int t, double r){
        System.out.println("Airports with at least " + t + " trainstations less than " + r + " units away");
        long startTime = System.nanoTime();
        System.out.println("\t> " + csv.anzahlBahnhoefeTree(r,t));
        long endTime = System.nanoTime();
        double erg = (endTime-startTime)*Math.pow(10,-9);
        System.out.println("Time: " + erg + "s");
    }

    public static void main(String[] args) {
        CSV csv = new CSV();
        csv.print1(csv,0,0,575.0);
        csv.print1(csv,1818.54657,5813.29982,100.0);
        csv.print2(csv,5,1);
        csv.print2(csv,20,15);
        System.out.println("---------------");
        csv.print3(csv,0,0,575.0);
        csv.print3(csv,1818.54657,5813.29982,100.0);
        csv.print4(csv,5,1);
        csv.print4(csv,20,15);
    }
}
