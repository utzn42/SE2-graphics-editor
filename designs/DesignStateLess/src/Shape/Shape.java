package Shape;


import canvas.Observable;
import canvas.Observer;
import facilitators.RGBColour;
import transform_strategy.TransformBehaviour;

/**
 * This abstract class describes the main components of a shape. It is then divided into subclasses for each individual shape.
 * It implements {@link Observable}.
 */
public abstract class Shape implements Observable{

    private String name;
    private RGBColour colour;
    private TransformBehaviour transformBehaviour;

    /**
     * Registers new observable objects.
     * @param observer the observer which gets registered
     */
    public void register(Observer observer){

    }

    /**
     * Unregisters observable objects.
     * @param observer the observer which gets unregistered
     */
    public void unregister(Observer observer){

    }

    /**
     * This method notifies the observer when something has changed.
     */
    public void notifyObservers(){

    }

    /**
     * The constructor of the Shape class. It expects a String with the desired name of the shape as well as the colour.
     * @param name name of the shape in String
     * @param colour colour of the Shape in RGBColour
     */
    Shape(String name, RGBColour colour){

    }


    /**
     * This method lets you print out a String representation of the Shape object. It overrides the method of Object.java class
     * @return returns a String
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Moves the shape from one point to another.
     */
    public void move(){

    }
}
