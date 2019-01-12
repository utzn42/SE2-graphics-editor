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

  @JsonUnwrapped
  private final Shape shape;

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

  public Shape getShape(){
    return shape;
  }
}
