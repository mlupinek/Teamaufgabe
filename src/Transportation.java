public class Transportation implements Comparable{

    private String name;
    private double x, y;
    private boolean isairport;
    @Override
    public int compareTo(Object o) {
        if(this.x > ((Transportation)o).x){
            return 1;
        }else if (this.x < ((Transportation)o).x){
            return -1;
        }else{
            return 0;
        }
    }

    public Transportation(String name, double x, double y, boolean isairport) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.isairport = isairport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isIsairport() {
        return isairport;
    }

    public void setIsairport(boolean isairport) {
        this.isairport = isairport;
    }
}
