package shapes;

import facilitators.Coordinate;

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
    setFillColour("#000000", 1);
    setStrokeWidth(0);
  }

  public Text(Coordinate center, String displayText, String font, double fontSize) {
    this.center = center;
    this.displayText = displayText;
    this.font = font;
    this.fontSize = fontSize;
    setFillColour("#000000", 1);
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
  protected String getHTMLAttributes() {
    return super.getHTMLAttributes() +
        " x=\"" + center.getX() + "\"" +
        " y=\"" + center.getY() + "\"" +
        " font-family=\"" + font + "\"" +
        " font-size=\"" + fontSize + "\"" +
        " text-anchor=\"middle\" alignment-baseline=\"middle\"";
  }

  @Override
  public String getHTML() {
    return "<text " + getHTMLAttributes() + ">" + displayText + "</text>";
  }
}
