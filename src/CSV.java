import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class CSV {

    private Scanner s;
    private List list = new List();
    private Tree tree = new Tree();
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

    public void print1(int[] arr, int x, int y, int r){
        System.out.println("Junctions less than " + r + " units from x=" + x + " y= ");
        System.out.println("Airports: " + arr[1] + ", Trainstations: " + arr[0]);
    }
    public void print2(int[] arr, int r, int n){
        System.out.println("Airports with at least " + n + " Trainstations less than " + r + " units away");
        System.out.println();
    }

    public static void main(String[] args) {
        CSV csv = new CSV();

        System.out.println("List:");
        int [] arr = csv.anzahlList(1818.54657,5813.29982, 100.0);
        System.out.println("Bahnhöfe: " + arr[0] + ", Flughäfen: " + arr[1]);

        arr = csv.anzahlList(1818.54657,5813.29982, 50.0);
        System.out.println("Bahnhöfe: " + arr[0] + ", Flughäfen: " + arr[1]);

        arr = csv.anzahlList(1818.54657,5813.29982, 1000);
        System.out.println("Bahnhöfe: " + arr[0] + ", Flughäfen: " + arr[1]);

        long startTime = System.nanoTime();
        System.out.println(csv.anzahlBahnhoefeList(15,20));
        System.out.println(csv.anzahlBahnhoefeList(20,15));
        long endTime = System.nanoTime();
        double erg = (endTime-startTime)*Math.pow(10,-9);
        System.out.println(erg);

        System.out.println("Tree:");
        arr = csv.anzahlTree(1818.54657,5813.29982, 100.0);
        System.out.println("Bahnhöfe: " + arr[0] + ", Flughäfen: " + arr[1]);

        arr = csv.anzahlTree(1818.54657,5813.29982, 50.0);
        System.out.println("Bahnhöfe: " + arr[0] + ", Flughäfen: " + arr[1]);

        arr = csv.anzahlTree(1818.54657,5813.29982, 1000);
        System.out.println("Bahnhöfe: " + arr[0] + ", Flughäfen: " + arr[1]);

        startTime = System.nanoTime();
        System.out.println(csv.anzahlBahnhoefeTree(15,20));
        System.out.println(csv.anzahlBahnhoefeTree(20,15));
        endTime = System.nanoTime();
        erg = (endTime-startTime)*Math.pow(10,-9);
        System.out.println(erg);
    }
}
