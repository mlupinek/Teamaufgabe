/*
    Aufgabe 3)
*/
public class DLNode {
    private Transportation value;
    private DLNode next, prev;

    public DLNode(Transportation value, DLNode next, DLNode prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public Transportation getValue(){
        return this.value;
    }

    public void setNext(DLNode next) {
        this.next = next;
    }

    public void setPrev(DLNode prev) {
        this.prev = prev;
    }

    public DLNode getNext() {
        return next;
    }

    public DLNode getPrev() {
        return prev;
    }


    public double diff(double x, double y){
        double a = x - this.value.getX();
        double b = y - this.value.getY();
        return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
    }

}

