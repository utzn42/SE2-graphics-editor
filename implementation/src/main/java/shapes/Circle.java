package shapes;

import facilitators.Coordinate;
import facilitators.CoordinateMath;
import shapes.transform.Rotatable;
import shapes.transform.RotationTransformation;
import shapes.transform.Transformation;
import shapes.transform.Translatable;
import shapes.transform.TranslationTransformation;
import shapes.transform.UniformScalable;
import shapes.transform.UniformScaleTransformation;

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
   * Applies a transformation, given as a {@link Transformation}, to the Circle. The Circle class
   * is non-transformable, so the following transformations will cause an error: skew
   *
   * @param transformer The transformation to apply to the Circle.
   */
  @Override
  public void applyTransformation(Transformation transformer) {
    if ((transformer.getScale() != null
        && !(transformer.getScale() instanceof UniformScaleTransformation)) ||
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
    if (transformer.getScale() != null
        && transformer.getScale() instanceof UniformScaleTransformation) {
      scale((UniformScaleTransformation) transformer.getScale());
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
   * Translates the Circle using a {@link TranslationTransformation}.
   *
   * @param transformation The transformation to apply to the Circle.
   */
  @Override
  public void translate(TranslationTransformation transformation) {
    center = CoordinateMath.translateByCoordinate(center, transformation.getTranslation());
  }

  /**
   * Rotates the Circle using a {@link RotationTransformation}.
   *
   * @param transformation The transformation to apply to the Circle.
   */
  @Override
  public void rotate(RotationTransformation transformation) {
    center = CoordinateMath.rotateAroundCoordinate(center, transformation.getRotationAngle(),
        transformation.getRotationCenter());
  }

  /**
   * Scales the circle using a {@link UniformScaleTransformation}.
   *
   * @param transformation The transformation to apply to the Circle.
   */
  @Override
  public void scale(UniformScaleTransformation transformation) {
    radius *= transformation.getScale().getX();
  }

}
