package Shape;


import Management.RGBColour;
import Move.MoveBehaviour;

public abstract class Shape implements MoveBehaviour{

    private String name;
    private RGBColour colour;

    Shape(String name, RGBColour colour){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void move(){

    }
}
