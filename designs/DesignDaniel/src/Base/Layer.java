package Base;

import Shapes.GraphicElement;

import java.util.List;

/**
 * Is a container for a shape. Can be visible or invisible. It lays under or over other layers.
 * The relation is an aggregation, it cannot exist without a graphic element/shape.
 *
 * @see GraphicElement
 */
public class Layer {

    private GraphicElement element;
    private boolean layerVisible;

    /**
     * Constructor of the class. Here the existing shape will get assigned to the layer.
     * @param shapeType type of the shape
     * @param attributes points/sizes of the shape
     */
    public Layer(String shapeType, List<String> attributes){

    }

    /**
     * Returns the shape.
     * @return returns a GraphicElement
     */
    public GraphicElement getElement() {
        return element;
    }

    /**
     * Returns the HTML-Code of the Layer with the shape.
     * @return returns a String with HTML-Code
     */
    public String getHTML(){
        return null;
    }

    /**
     * Enables/Disables the visibility of the layer.
     */
    public void toggleVisibility(){

    }

}
