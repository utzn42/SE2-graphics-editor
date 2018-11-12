package canvas;

import shapes.Shape;

import java.util.Observable;
import java.util.Observer;

/**
 * The Canvas class is the container in which all layers with shapes are in.
 * It is like the coordinate system for the operations which will take place.
 * It manages all layers and shapes. Every object is "registered" in the canvas.
 * A canvas can hold up to 150 shapes.
 *
 * The Canvas class uses the Shape class and the CanvasState class. The relation is a composition.
 * The Canvas class cannot exist without the CanvasState.
 *
 * @see CanvasState
 * @see Shape
 */
public class Canvas implements Observer {

    private CanvasState state;
    private Layer[] data;

    /**
     * This is the default constructor of the Canvas class.
     */
    public Canvas(){

    }

    /**
     * This method lets you print out a String representation of the Canvas object. It overrides the method of Object.java class
     * @return returns a String
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Updates the Canvas with the layers and shapes.
     * @param shape contains the added, deleted or edited shape
     */
    public void update(Shape shape){

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}