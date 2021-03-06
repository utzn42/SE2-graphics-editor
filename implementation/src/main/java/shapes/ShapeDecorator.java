package shapes;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import facilitators.Colour;
import facilitators.Coordinate;

/**
 * Decorator which lets you decorate {@link Shape}s with transform attributes.
 *
 * @see ShapeWithTransformAttribute
 */
public class ShapeDecorator extends Shape{

  private static final long serialVersionUID = 1L;

  @JsonUnwrapped
  private final Shape shape;

  /**
   * Creates a ShapeDecorator around the given {@link Shape}.
   *
   * @param shape The Shape to wrap in the decorator.
   */
  public ShapeDecorator(Shape shape){
    this.shape = shape;
  }

  @Override
  public String getHTML() {
    return shape.getHTML();
  }

  @Override
  protected String getHTMLAttributes() {
    return shape.getHTMLAttributes();
  }

  @Override
  public Coordinate getCenter() {
    return shape.getCenter();
  }

  @Override
  public Colour getFillColour() {
    return shape.getFillColour();
  }

  @Override
  public Colour getStrokeColour() {
    return shape.getStrokeColour();
  }

  @Override
  public double getOpacity() {
    return shape.getOpacity();
  }

  @Override
  public double getStrokeWidth() {
    return shape.getStrokeWidth();
  }

  @Override
  public void setFillColour(Colour fillColour) {
    shape.setFillColour(fillColour);
  }

  @Override
  public void setFillColour(String hex, float opacity) {
    shape.setFillColour(hex, opacity);
  }

  @Override
  public void setOpacity(double opacity) {
    shape.setOpacity(opacity);
  }

  @Override
  public void setStrokeColour(Colour strokeColour) {
    shape.setStrokeColour(strokeColour);
  }

  @Override
  public void setStrokeColour(String hex, float opacity) {
    shape.setStrokeColour(hex, opacity);
  }

  @Override
  public void setStrokeWidth(double strokeWidth) {
    shape.setStrokeWidth(strokeWidth);
  }

  @Override
  public ShapeType getShapeType() {
    return shape.getShapeType();
  }

  /**
   * Returns the {@link Shape} wrapped by this ShapeDecorator.
   *
   * @return The Shape wrapped by this ShapeDecorator.
   */
  public Shape getShape(){
    return shape;
  }
}
