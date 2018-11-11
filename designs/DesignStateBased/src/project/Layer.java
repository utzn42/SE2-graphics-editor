package project;

import shapes.GraphicElement;

/**
 * Represents a layer in the {@link Canvas} and acts as a wrapper for a {@link GraphicElement}.
 * A Layer can be visible or invisible.
 *
 * @see Canvas
 * @see GraphicElement
 */
public class Layer {

    private GraphicElement graphicElement;
    private boolean visible;

    /**
     * Creates a new Layer. The shape type needs to be provided as a parameter.
     *
     * @param shapeType The type of {@link GraphicElement} that the Layer should hold
     */
    public Layer(String shapeType) {

    }

    /**
     * Returns the {@link GraphicElement} that the Layer holds.
     *
     * @return The {@link GraphicElement} that the Layer holds
     */
    public GraphicElement getGraphicElement() {
        return graphicElement;
    }

    /**
     * Sets a new {@link GraphicElement} for the Layer to hold.
     *
     * @param graphicElement The new {@link GraphicElement} for the Layer to hold.
     */
    public void setGraphicElement(GraphicElement graphicElement) {

    }

    /**
     * Returns <code>true</code> if the Layer is visible.
     *
     * @return <code>true</code> if the Layer is visible, <code>false</code> otherwise
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the Layer's visibility.
     *
     * @param visible <code>true</code> to make the Layer visible, <code>false</code> otherwise
     */
    public void setVisible(boolean visible) {

    }

    /**
     * Returns the HTML code for the Layer.
     *
     * @return A String containing HTML code for the Layer
     * @see GraphicElement#getHTML()
     */
    public String getHTML(){
        return null;
    }

    /**
     * Returns a JSON representation of the Layer.
     * Modifications to the Layer should use the same attribute names.
     *
     * @return A String containing a JSON representation of the Layer
     * @see GraphicElement#getJSON()
     */
    public String getJSON() {
        return null;
    }

    /**
     * Modifies the Layer using the specified attributes.
     *
     * @param attributes A JSON String containing the attributes to be modified
     * @return <code>true</code> if the Layer had one or more of its attributes modified, <code>false</code> otherwise
     * @see GraphicElement#modify(String)
     */
    public boolean modify(String attributes) {
        return false;
    }

}
