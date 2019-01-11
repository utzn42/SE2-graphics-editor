package shapes;

import facilitators.Coordinate;

public class ShapeDecorator extends Shape{

  Shape shape;

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
  public ShapeType getShapeType() {
    return shape.getShapeType();
  }

  public Shape getShape(){
    return shape;
  }
}
