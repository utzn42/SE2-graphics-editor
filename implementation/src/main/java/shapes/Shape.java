package shapes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import facilitators.Colour;
import facilitators.Coordinate;
import java.io.Serializable;
import shapes.transform.Transformation;

/**
 * Represents a shape on the canvas. It defines the basic operations and attributes of a shape.
 * Every shape should extend this class or one of its subclasses.
 * This class holds attributes that are available to any shape: fillColour, strokeColour, strokeWidth and opacity.
 *
 * Subclasses can make use of the classes {@link Colour}, {@link facilitators.RGBColour} and {@link facilitators.Coordinate} to describe their attributes.
 *
 * It implements {@link Serializable} for writing to files.
 *
 * @see Circle
 * @see Line
 * @see Polygon
 * @see Ellipse
 * @see RegularPolygon
 * @see Star
 * @see Text
 */
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

  /**
   * Creates a new Shape with default attributes.
   */
  public Shape() {
    this.fillColour = new Colour("#FFFFFF", 0);
    this.strokeColour = new Colour("#000000", 1);
    this.strokeWidth = 1;
    this.opacity = 1;
  }

  /**
   * Creates a new Shape with the specified attributes.
   *
   * @param fillColour The fill colour of the shape.
   * @param strokeColour The stroke colour of the shape.
   * @param strokeWidth The stroke width of the shape.
   * @param opacity The opacity of the shape. Value should be between 0 and 1.
   */
  public Shape(Colour fillColour, Colour strokeColour, double strokeWidth, double opacity) {
    this.fillColour = fillColour;
    this.strokeColour = strokeColour;
    this.strokeWidth = strokeWidth;
    this.opacity = opacity;
  }

  /**
   * Returns the fill colour of the shape.
   *
   * @return The fill colour of the shape as a {@link Colour} object.
   */
  public Colour getFillColour() {
    return fillColour;
  }

  /**
   * Sets the fill colour of the shape.
   *
   * @param fillColour The new fill colour of the shape as a {@link Colour} object.
   */
  @JsonSetter
  public void setFillColour(Colour fillColour) {
    this.fillColour = fillColour;
  }

  /**
   * Sets the fill colour of the shape.
   *
   * @param hex The hex representation of the new colour (e.g. "#10FFA3").
   * @param opacity The opacity of the new colour. Value should be between 0 and 1.
   */
  public void setFillColour(String hex, float opacity) {
    this.fillColour = new Colour(hex, opacity);
  }

  /**
   * Returns the outline colour of the shape.
   *
   * @return The outline colour of the shape as a {@link Colour} object.
   */
  public Colour getStrokeColour() {
    return strokeColour;
  }

  /**
   * Sets the colour of the shape outline.
   *
   * @param strokeColour The new outline colour of the shape as a {@link Colour} object.
   */
  @JsonSetter
  public void setStrokeColour(Colour strokeColour) {
    this.strokeColour = strokeColour;
  }

  /**
   * Sets the colour of the shape outline.
   *
   * @param hex The hex representation of the new colour (e.g. "#10FFA3").
   * @param opacity The opacity of the new colour. Value should be between 0 and 1.
   */
  public void setStrokeColour(String hex, float opacity) {
    this.strokeColour = new Colour(hex, opacity);
  }

  /**
   * Returns the width of the shape outline.
   *
   * @return The width of the shape outline.
   */
  public double getStrokeWidth() {
    return strokeWidth;
  }

  /**
   * Sets the width of the shape outline.
   *
   * @param strokeWidth The new width of the shape outline.
   */
  public void setStrokeWidth(double strokeWidth) {
    this.strokeWidth = strokeWidth;
  }

  /**
   * Returns the opacity of the shape.
   *
   * @return The opacity of the shape.
   */
  public double getOpacity() {
    return opacity;
  }

  /**
   * Sets the opacity of the shape.
   *
   * @param opacity The new opacity of the shape. Value should be between 0 and 1.
   */
  public void setOpacity(double opacity) {
    this.opacity = opacity;
  }

  /**
   * Applies a transformation, given as a {@link Transformation}, to the shape.
   * The {@link shapes.transform.Translatable Translatable}, {@link shapes.transform.Rotatable Rotatable},
   * {@link shapes.transform.Scalable Scalable} and {@link shapes.transform.Skewable Skewable} interfaces
   * can be used to determine whether a transformation can be applied or not.
   *
   * @param transformer The transformation to apply to the shape.
   */
  public abstract void applyTransformation(Transformation transformer);

  /**
   * Returns a String representation of the Shape's attributes as chained HTML attributes.
   * This function should ideally be extended by every subclass and integrated into {@link #getHTML()}.
   * Return example:
   * 'fill="#FFFFFF" fill-opacity="0.2" stroke="#000000" stroke-opacity="1.0" stroke-width="1" opacity="1.0"'
   *
   * @return A String representation of the Shape's attributes as chained HTML attributes.
   */
  protected String getHTMLAttributes() {
    return "fill=\"" + fillColour.getRgbColour().toString() + "\" "
        + "fill-opacity=\"" + fillColour.getOpacity() + "\" "
        + "stroke=\"" + strokeColour.getRgbColour().toString() + "\" "
        + "stroke-opacity=\"" + strokeColour.getOpacity() + "\" "
        + "stroke-width=\"" + strokeWidth + "\" "
        + "opacity=\"" + opacity + "\"";
  }

  /**
   * Returns a String representation of the Shape as an SVG object in HTML.
   *
   * @return A String representation of the Shape as an SVG object in HTML.
   */
  public abstract String getHTML();

  /**
   * Returns the center of the Shape as a {@link Coordinate}.
   *
   * @return The center of the Shape as a {@link Coordinate}.
   */
  public abstract Coordinate getCenter();

}
