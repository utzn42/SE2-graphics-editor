package shapes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import facilitators.Colour;
import java.io.Serializable;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "shapeClass"
)
@JsonRootName(value = "attributes")
@JsonIgnoreProperties({"html"})
public abstract class Shape implements Serializable {

  private Colour fillColour;
  private Colour strokeColour;
  private double strokeWidth;
  private double opacity;

  public Shape() {
    this.fillColour = new Colour("#FFFFFF", 0);
    this.strokeColour = new Colour("#000000", 1);
    this.strokeWidth = 1;
    this.opacity = 1;
  }

  public Shape(Colour fillColour, Colour strokeColour, double strokeWidth, double opacity) {
    this.fillColour = fillColour;
    this.strokeColour = strokeColour;
    this.strokeWidth = strokeWidth;
    this.opacity = opacity;
  }

  public Colour getFillColour() {
    return fillColour;
  }

  @JsonSetter
  public void setFillColour(Colour fillColour) {
    this.fillColour = fillColour;
  }

  public void setFillColour(String hex, float opacity) {
    this.fillColour = new Colour(hex, opacity);
  }

  public Colour getStrokeColour() {
    return strokeColour;
  }

  @JsonSetter
  public void setStrokeColour(Colour strokeColour) {
    this.strokeColour = strokeColour;
  }

  public void setStrokeColour(String hex, float opacity) {
    this.strokeColour = new Colour(hex, opacity);
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

  protected String getHTMLAttributes() {
    return "fill=\"" + fillColour.getRgbColour().toString() + "\" "
        + "fill-opacity=\"" + fillColour.getOpacity() + "\" "
        + "stroke=\"" + strokeColour.getRgbColour().toString() + "\" "
        + "stroke-opacity=\"" + strokeColour.getOpacity() + "\" "
        + "stroke-width=\"" + strokeWidth + "\" "
        + "opacity=\"" + opacity + "\"";
  }

  public abstract String getHTML();

}
