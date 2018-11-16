package management;

import Shape.Shape;

/**
 * A layer is the container of one or more shapes. One layer can hold up to 50 shapes.
 * A layer can lay over or under other layers.
 *
 * The relation is an aggregation, it cannot exist without a the LayerState.
 *
 * @see LayerState
 * @see Observer
 */
public class Layer implements Observer{

    private LayerState state;
    private Shape[] data;

    /**
     * The constructor of the Layer Class. It expects one or more shapes, which then get assigned to the layer.
     * @param shape one ore more shapes
     */
    public Layer(Shape[] shape){

    }

    /**
     * Default constructor.
     */
    public Layer(){

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
     * Updates the Layer with the shapes.
     * @param shape contains the added, deleted or edited shape
     */
    public void update(Shape shape){

    }
}
