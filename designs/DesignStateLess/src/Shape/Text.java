package Shape;

import facilitators.Coordinate;
import facilitators.RGBColour;

/**
 * Represents a textfield. Creates a text out of a string.
 * It extends the Shape class.
 *
 * @see Shape
 */
public class Text extends Shape{

    private Coordinate anchorCoordinate;
    private String displayText;


    /**
     * Default constructor. Creates text field with default text and size.
     */
    public Text(){
        super("polygon", new RGBColour(new int[]{1, 2, 3}));
        this.anchorCoordinate=new Coordinate(10,10);
        this.displayText="Insert Text";

    }

    /**
     * Constructor with available parameters.
     * @param name name of the shape
     * @param colour colour of the shape in RGBColour format
     * @param anchorCoordinate anchor coordinate
     * @param displayText text in String
     */
    public Text(String name, RGBColour colour, Coordinate anchorCoordinate, String displayText){

        super(name, colour);
        this.anchorCoordinate=anchorCoordinate;
        this.displayText=displayText;

    }

    /**
     * Returns anchor coordinate.
     * @return returns anchor in Coordinate format
     */
    public Coordinate getAnchorCoordinate() {
        return anchorCoordinate;
    }

    /**
     * Returns the text of the textfield.
     * @return text in String
     */
    public String getDisplayText() {
        return displayText;
    }

    /**
     * Lets the user set a new anchor coordinate.
     * @param anchorCoordinate anchor coordinate
     */
    public void setAnchorCoordinate(Coordinate anchorCoordinate) {
        this.anchorCoordinate = anchorCoordinate;
    }


    /**
     * Lets the user set a new text to show in the text field.
     * @param displayText text in String
     */
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
}
