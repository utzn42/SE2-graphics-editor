package canvas;

import shapes.Shape;

import java.util.Observable;
import java.util.Observer;

/**
 * A collection of all the shapes and their information.
 * The ShapeInfo class implements the Observer interface.
 *
 * @see Observer
 * @see Shape
 * @see Layer
 */
public class ShapeInfo implements Observer {

    private Shape[] listOfShapes;
    private Layer[] listOfLayers;

    private ShapeInfo(){

    }


    /**
     * Returns an array of all shapes existing in the canvas.
     * @return returns a Shape array
     */
    public Shape[] getAllShapes(){
        return null;
    }

    /**
     * Updates the whole information about the layers and shapes.
     * @param shape contains the added, deleted or edited shape
     */
    public void update(Shape shape) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
