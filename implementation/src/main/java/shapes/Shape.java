package shapes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import facilitators.Colour;
import facilitators.RGBColour;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "shapeClass"
)
@JsonRootName(value = "attributes")
@JsonIgnoreProperties({"html"})
public abstract class Shape {

  private Colour fillColour;
  private Colour strokeColour;
  private double strokeWidth;
  private double opacity;

  public Shape() {
    this.fillColour = new Colour(new RGBColour("#FFFFFF"), true);
    this.strokeColour = new Colour(new RGBColour("#000000"), false);
    this.strokeWidth = 1;
    this.opacity = 1;
  }

  public Shape(Colour fillColour, Colour strokeColour, double strokeWidth, double opacity) {
    this.fillColour = fillColour;
    this.strokeColour = strokeColour;
    this.strokeWidth = strokeWidth;
    this.opacity = 1;
  }

  public Colour getFillColour() {
    return fillColour;
  }

  @JsonSetter
  public void setFillColour(Colour fillColour) {
    this.fillColour = fillColour;
  }

  public void setFillColour(String fillColour) {
    this.fillColour = new Colour(fillColour);
  }

  public Colour getStrokeColour() {
    return strokeColour;
  }

  @JsonSetter
  public void setStrokeColour(Colour strokeColour) {
    this.strokeColour = strokeColour;
  }

  public void setStrokeColour(String strokeColour) {
    this.strokeColour = new Colour(strokeColour);
  }

  public double getStrokeWidth() {
    return strokeWidth;
  }

  public void setStrokeWidth(double strokeWidth) {
    this.strokeWidth = strokeWidth;
  }

  public double getOpacity() {
    return opacity;
  }

  public void setOpacity(double opacity) {
    this.opacity = opacity;
  }

  public abstract String getHTML();

}
