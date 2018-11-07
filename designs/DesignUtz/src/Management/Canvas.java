package Management;

import Shape.Shape;

/**
 * The Management.Canvas class is the container in which all layers with shapes are in.
 * It is like the coordinate system for the operations which will take place.
 * It manages all layers and shapes. Every object is "registered" in the canvas.
 *
 * The Management.Canvas class uses the Point class and the Management.Layer class. The relation is an aggregation.
 * The Management.Canvas class cannot exist without Layers and Points.
 *
 *
 */
public class Canvas {

    private CanvasState state;
    private Shape[] data;

    public Canvas(){

    }

    @Override
    public String toString() {
        return super.toString();
    }
}