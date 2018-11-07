package Management;

public class Coordinate {

    private int xCoord;
    private int yCoord;

    public Coordinate(){

    }

    public Coordinate(int xCoord, int yCoord){
        this.xCoord=xCoord;
        this.yCoord=yCoord;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    private void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    private void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setXY(int[] xy){
        setxCoord(xy[0]);
        setyCoord(xy[1]);
    }
}
