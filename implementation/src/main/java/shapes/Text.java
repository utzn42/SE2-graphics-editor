package shapes;

import main.facilitators.Coordinate;

public class Text extends Shape {

    private Coordinate center;
    private String displayText;
    private String font;
    private double fontSize;

    public Text() {
        center = new Coordinate(50, 50);
        displayText = "Hello World!";
        font = "Arial";
        fontSize = 12;
        setFillColour("#000000");
        setStrokeWidth(0);
    }

    public Coordinate getCenter() {
        return center;
    }

    public void setCenter(Coordinate center) {
        this.center = center;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public double getFontSize() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public String getHTML() {
        return "<text" +
                " x=\"" + center.getX() + "\"" +
                " y=\"" + center.getY() + "\"" +
                " font-family=\"" + font + "\"" +
                " font-size=\"" + fontSize + "\"" +
                " text-anchor=\"middle\" alignment-baseline=\"middle\"" + // makes x and y the "center" coordinates
                " fill=\"" + getFillColour() + "\"" +
                " stroke=\"" + getStrokeColour() + "\"" +
                " stroke-width=\"" + getStrokeWidth() + "\"" +
                " opacity=\"" + getOpacity() + "\"" +
                ">" + displayText + "</text>";
    }
}
