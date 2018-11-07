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
    private Shape[] layer1Shapes;
    private Shape[] layer2Shapes;
    private Shape[] layer3Shapes;

    /**
     * The constructor expects a list of shapes for each 3 layers.
     *
     * @param layer1Shapes array of shapes from layer 1
     * @param layer2Shapes array of shapes from layer 2
     * @param layer3Shapes array of shapes from layer 3
     */
    public ShapeInfo(Shape[] layer1Shapes, Shape[] layer2Shapes, Shape[] layer3Shapes){

    }

    /**
     * Returns an array of all shapes existing in the canvas.
     * @return returns a Shape array
     */
    public Shape[] getAllShapes(){
        return null;
    }

    /**
     * Returns all shapes from layer 1.
     * @return returns a Shape array
     */
    public Shape[] getLayer1Shapes(){
        return null;
    }

    /**
     * Returns all shapes from layer 2.
     * @return returns a Shape array
     */
    public Shape[] getLayer2Shapes(){
        return null;
    }

    /**
     * Returns all shapes from layer 3.
     * @return returns a Shape array
     */
    public Shape[] getLayer3Shapes(){
        return null;
    }


    public void update() {

    }
}
