import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class CSV {

    private Scanner s;
    private List list = new List();

    public CSV() {
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

    public int[] anzahl(double x, double y, double radius){
        return list.search(x,y,radius);
    }

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

    public static void main(String[] args) {
        CSV csv = new CSV();
        csv.makeList();
        int [] arr = csv.anzahl(1818.54657,5813.29982, 100.0);
        System.out.println("Bahnhöfe: " + arr[0] + ", Flughäfen: " + arr[1]);

        arr = csv.anzahl(1818.54657,5813.29982, 50.0);
        System.out.println("Bahnhöfe: " + arr[0] + ", Flughäfen: " + arr[1]);

        arr = csv.anzahl(1818.54657,5813.29982, 1000);
        System.out.println("Bahnhöfe: " + arr[0] + ", Flughäfen: " + arr[1]);
        //System.out.println(csv.anzahlBahnhoefe(15.0,20));


    }
}
