package canvas;

import shapes.Shape;

import java.util.List;
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

    private List<Shape> listOfShapes;
    private List<Layer> listOfLayers;

    private ShapeInfo() {

    }

    /**
     * Returns an array of all shapes existing in the canvas.
     * @return returns a Shape array
     */
    public List<Shape> getAllShapes(){
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
