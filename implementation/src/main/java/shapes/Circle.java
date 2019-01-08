package shapes;

import facilitators.Coordinate;
import facilitators.CoordinateMath;
import shapes.transform.Rotatable;
import shapes.transform.atomic.RotationTransformation;
import shapes.transform.Translatable;
import shapes.transform.atomic.TranslationTransformation;
import shapes.transform.UniformScalable;
import shapes.transform.atomic.UniformScaleTransformation;

/**
 * Represents a Circle on the canvas. This class extends {@link Shape} by a center {@link
 * Coordinate} and a radius.
 *
 * @see Shape
 * @see RegularPolygon
 * @see Star
 */
public class Circle extends Shape implements Translatable, UniformScalable {

  private Coordinate center;
  private double radius;

  /**
   * Creates a new Circle with default attributes.
   */
  public Circle() {
    this.center = new Coordinate(50, 50);
    this.radius = 50;
  }

  /**
   * Creates a new Circle with the specified attributes and default {@link Shape} attributes.
   *
   * @param center The center {@link Coordinate} of the Circle.
   * @param radius The radius of the Circle.
   */
  public Circle(Coordinate center, double radius) {
    this.center = center;
    this.radius = radius;
  }

  /**
   * Returns the center {@link Coordinate} of the Circle.
   *
   * @return The center {@link Coordinate} of the Circle.
   */
  @Override
  public Coordinate getCenter() {
    return center;
  }

  /**
   * Sets the center of the Circle.
   *
   * @param center The new center {@link Coordinate} for the Circle.
   */
  public void setCenter(Coordinate center) {
    this.center = center;
  }

  /**
   * Returns the radius of the Circle.
   *
   * @return The radius of the Circle.
   */
  public double getRadius() {
    return radius;
  }

  /**
   * Sets the radius of the Circle.
   *
   * @param radius The new radius for the Circle.
   */
  public void setRadius(double radius) {
    this.radius = radius;
  }

  /**
   * Returns a String representation of the Circle's attributes as chained HTML attributes.
   *
   * @return A String representation of the Circle's attributes as chained HTML attributes.
   * @see Shape#getHTMLAttributes()
   */
  @Override
  protected String getHTMLAttributes() {
    return super.getHTMLAttributes() +
        " cx=\"" + center.getX() + "\"" +
        " cy=\"" + center.getY() + "\"" +
        " r=\"" + radius + "\"";
  }

  /**
   * Returns a String representation of the Circle as an SVG object in HTML.
   *
   * @return A String representation of the Circle as an SVG object in HTML.
   */
  @Override
  public String getHTML() {
    return "<circle " + getHTMLAttributes() + "></circle>";
  }

  /**
   * Translates the Circle using a {@link TranslationTransformation}.
   *
   * @param transformation The transformation to apply to the Circle.
   */
  @Override
  public void translate(TranslationTransformation transformation) {
    center = CoordinateMath.translateByCoordinate(center, transformation.getTranslation());
  }

  /**
   * Scales the circle using a {@link UniformScaleTransformation}.
   *
   * @param transformation The transformation to apply to the Circle.
   */
  @Override
  public void uniformScale(UniformScaleTransformation transformation) {
    radius *= transformation.getScale();
    setStrokeWidth(getStrokeWidth() * transformation.getScale());
    center = CoordinateMath.scaleVector(center, new Coordinate(transformation.getScale(),
        transformation.getScale()), new Coordinate(0, 0));
  }

}
