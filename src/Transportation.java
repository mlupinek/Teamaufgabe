public class Transportation {

    private String name;
    private double x, y;
    private boolean isairport;

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
