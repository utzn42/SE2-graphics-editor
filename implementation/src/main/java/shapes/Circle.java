package shapes;

import facilitators.Coordinate;
import shapes.transform.Rotatable;
import shapes.transform.Rotator;
import shapes.transform.Scalable;
import shapes.transform.Scaler;
import shapes.transform.ShapeTransformer;
import shapes.transform.Translatable;
import shapes.transform.Translater;
import shapes.transform.UniformScalable;
import shapes.transform.UniformScaler;

/**
 * Represents a Circle on the canvas. This class extends {@link Shape} by a center {@link
 * Coordinate} and a radius.
 *
 * @see Shape
 * @see RegularPolygon
 * @see Star
 */
public class Circle extends Shape implements Translatable, Rotatable, UniformScalable {

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
   * Applies a transformation, given as a {@link ShapeTransformer}, to the Circle. The Circle class
   * is non-transformable, so the following transformations will cause an error: skew
   *
   * @param transformer The transformation to apply to the Circle.
   */
  @Override
  public void applyTransformation(ShapeTransformer transformer) {
    if (transformer.getScale() != null ||
        transformer.getSkew() != null) {
      throw new IllegalArgumentException(
          "Cannot add transform attribute to non-transformable Shape!");
    }
    if (transformer.getTranslation() != null) {
      translate(transformer.getTranslation());
    }
    if (transformer.getRotation() != null) {
      rotate(transformer.getRotation());
    }
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
   * Translates the Circle using a {@link Translater}.
   *
   * @param translater The translation to apply to the Circle.
   */
  @Override
  public void translate(Translater translater) {
    double newX = center.getX() + translater.getTranslation().getX();
    double newY = center.getY() + translater.getTranslation().getY();

    center = new Coordinate(newX, newY);
  }

  /**
   * Rotates the Circle using a {@link Rotator}.
   *
   * @param rotator The rotation to apply to the Circle.
   */
  @Override
  public void rotate(Rotator rotator) {
    //TODO: Implement shapes.Circle#rotate(Rotator)
  }

  /**
   * Scales the circle using a {@link UniformScaler}.
   *
   * @param scaler The scaler to apply to the Circle.
   */
  @Override
  public void scale(UniformScaler scaler) {
    //TODO: Implement shapes.Circle#scale(UniformScaler)
  }
}
