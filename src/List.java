public class List{
    //objectvariables
    private DLNode head, last;

    public List(){
        head = null;
        last = null;
    }

    //add a Song to the end of the Playlist
    public void add(String [] arr){
        boolean tf = true;
        if(arr[3].equals("TRAINSTATION")){
            tf= !tf;
        }
        if(head==null){
            head = last = new DLNode(new Transportation(arr[0],Double.parseDouble(arr[1]),Double.parseDouble(arr[2]),tf),null,null);
        } else {
            DLNode l = last;
            DLNode a = new DLNode(new Transportation(arr[0],Double.parseDouble(arr[1]),Double.parseDouble(arr[2]),tf),null,l);
            last.setNext(a);
            last = a;
        }
    }

    //print the songs of a playlist
    public void print(){
        for(DLNode x = head; x!=null; x = x.getNext()) {
            System.out.println(x.getValue().getName());
        }
    }

    //return Anzahl an Flughäfen/Bahnhöfe im Radius um x,y.
    public int[] search(double x, double y, double radius){
        int [] sum = new int[2];
        DLNode a = head;
        while (a!=null){
            if(a.diff(x,y)<radius){
                if(a.getValue().isIsairport()){
                    sum[0]++;
                }else{
                    sum[1]++;
                }
            }
            a = a.getNext();
        }
        return sum;
    }

    public DLNode getHead() {
        return head;
    }
}
