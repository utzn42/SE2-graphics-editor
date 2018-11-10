package Management;

import Shape.Shape;


/**
 * A collection of all the shapes and their information. There can be a maximum of 150 shapes from a maximum of 3 different layers.
 * The ShapeInfo class implements the Observer interface.
 *
 * @see Observer
 * @see Shape
 * @see Layer
 */
public class ShapeInfo implements Observer{

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


    public void update(Shape shape) {

    }
}
