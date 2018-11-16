package management;

/**
 * Represents all possible colors in the RGB range field. Will get assigned to each shape.
 *
 * @see Shape.Shape
 */
public class RGBColour {

    private int[] colour;

    /**
     * The constructor expects the RGB code in int with the desired color.
     * @param colour name of the color in int
     */
    public RGBColour(int[] colour){
        this.colour=colour;
    }


    /**
     * Returns the name of the color.
     * @return returns an int
     */
    public int[] getColour() {
        return colour;
    }

    /**
     * Can change the color to the desired new color received by the parameter.
     * @param colour name of the color in int
     */
    public void setColour(int[] colour) {
        this.colour = colour;
    }
}
