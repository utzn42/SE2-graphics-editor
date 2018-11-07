package Management;

/**
 * Represents all possible colors in the RGB range field. Will get assigned to each shape.
 *
 * @see Shape.Shape
 */
public class RGBColour {

    private String colour;

    /**
     * The constructor expects a string with the desired color.
     * @param colour name of the color in String
     */
    public RGBColour(String colour){
        this.colour=colour;
    }

    /**
     * Returns the name of the color.
     * @return returns a String
     */
    public String getColour() {
        return colour;
    }

    /**
     * Can change the color to the desired new color received by the parameter.
     * @param colour name of the color in String
     */
    public void setColour(String colour) {
        this.colour = colour;
    }
}
