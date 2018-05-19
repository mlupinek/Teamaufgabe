import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class CSV2 {

    private Scanner s;
    private List list = new List();
    private Tree tree = new Tree();

    public CSV2() {
        boolean tf = true;
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


    public int anzahl(double x, double y, double radius){
        tree.setCounter(0);
        return tree.search(tree.getRoot(), x,y,radius);
    }

    /*
    public int anzahlBahnhoefe(double radius, double n){
        DLNode dl = this.list.getHead();
        int counter = 0;
        while (dl !=null){
            if (dl.getValue().isIsairport() && anzahl(dl.getValue().getX(), dl.getValue().getY(), radius)[1]>=n){
                counter++;
            }
            dl = dl.getNext();
        }
        return counter;
    }
    */
    public static void main(String[] args) {
        CSV2 csv = new CSV2();
        csv.makeTree();
        //csv.tree.print();
        System.out.println(csv.anzahl(1818.54657, 5813.29982, 100.0));
        System.out.println(csv.anzahl(1818.54657, 5813.29982, 50.0));
        System.out.println(csv.anzahl(1818.54657, 5813.29982, 1000.0));
    }
}
