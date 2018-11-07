package Base;

import java.util.List;

/**
 * The Management.Canvas class is the container in which all layers with shapes are in.
 * It is like the coordinate system for the operations which will take place.
 * It manages all layers and shapes. Every object is "registered" in the canvas.
 *
 * The Management.Canvas class uses the Point class and the Management.Layer class. The relation is an aggregation.
 * The Management.Canvas class cannot exist without Layers and Points.
 * @see Point
 * @see Layer
 *
 */

public class Canvas {

    private List<Layer> layers;
    private Point size;

    /**
     * This is the default constructor.
     */
    public Canvas(){

    }

    /**
     * This method returns all layers.
     * @return returns a list of all layers
     */
    public List<Layer> getLayers() {
        return layers;
    }

    /**
     * Adds a new shape.
     * @param shapeType defines the shape type
     * @param attributes defines the coordinates
     * @return returns a layer
     */
    public Layer addShape(String shapeType, List<String> attributes){
        return null;
    }

    /**
     * Removes a specific layer.
     * @param nr defines the nr of the layer
     */
    public void removeLayer(int nr){

    }

    /**
     * This method returns the size of the canvas.
     * @return returns a point
     */
    public Point getSize() {
        return size;
    }

    /**
     * This method sets the size.
     * @param point defines the specific point
     */
    public void setSize(Point point){
        this.size=point;
    }

    /**
     * Delivers the HTML-Code of the canvas including all layers and shapes.
     * @return returns a String with the HTML-Code
     */
    public String getHTML(){
        return null;
    }



}
