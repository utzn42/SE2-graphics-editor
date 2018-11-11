package project;

import shapes.Point;

import java.util.List;
import java.util.Observable;

/**
 * Represents the drawing canvas of the project.
 * It is the project's main model. Includes all {@link Layer}s and their respective
 * {@link shapes.GraphicElement GraphicElement}s.
 * Canvas extends {@link Observable} and can be observed when needed.
 *
 * @see Layer
 */

public class Canvas extends Observable {

    private List<Layer> layers;
    private Point size;
    private ModifyStrategy modifyStrategy;

    /**
     * Creates a new Canvas of default size with no {@link Layer}s.
     * Default {@link ModifyStrategy} is {@link ModifyOverwrite}.
     */
    public Canvas() {

    }

    /**
     * Returns the size of the Canvas.
     *
     * @return The size of the Canvas
     * @see Point
     */
    public Point getSize() {
        return size;
    }

    /**
     * Sets the size of the Canvas.
     *
     * @param size The new size of the Canvas
     * @see Point
     */
    public void setSize(Point size){

    }

    /**
     * Returns the {@link ModifyStrategy} used by the Canvas.
     *
     * @return The {@link ModifyStrategy} used by the Canvas
     */
    public ModifyStrategy getModifyStrategy() {
        return modifyStrategy;
    }

    /**
     * Sets the {@link ModifyStrategy} used by the Canvas.
     *
     * @param modifyStrategy The new {@link ModifyStrategy} to be used
     */
    public void setModifyStrategy(ModifyStrategy modifyStrategy) {

    }

    /**
     * Returns the {@link Layer} with the specified index.
     *
     * @param layerIndex The index of the Layer to get
     * @return The {@link Layer} with the specified index
     */
    public Layer getLayer(int layerIndex) {
        return null;
    }

    /**
     * Adds a new {@link Layer}. The shape type needs to be provided as a parameter.
     *
     * @param shapeType The type of {@link shapes.GraphicElement GraphicElement} that the Layer should hold
     */
    public void addLayer(String shapeType) {

    }

    /**
     * Removes the {@link Layer} with the specified index.
     *
     * @param layerIndex The index of the Layer to remove
     */
    public void removeLayer(int layerIndex) {

    }

    /**
     * Modifies the {@link Layer} with the specified index using the specified attributes.
     *
     * @param layerIndex The index of the Layer to modify
     * @param attributes A JSON String containing the attributes to be modified
     */
    public void modifyLayer(int layerIndex, String attributes) {

    }

    /**
     * Modifies the Canvas using the specified attributes.
     *
     * @param attributes A JSON String containing the attributes to be modified
     */
    public void modifyCanvas(String attributes) {

    }

    /**
     * Returns the HTML code for the Canvas.
     *
     * @return A String containing HTML code for the Canvas
     * @see Layer#getHTML()
     */
    public String getHTML(){
        return null;
    }

    /**
     * Returns a JSON representation of the Canvas.
     * Modifications to the Canvas should use the same attribute names.
     *
     * @return A String containing a JSON representation of the Canvas
     * @see Layer#getJSON()
     */
    public String getJSON() {
        return null;
    }

}
