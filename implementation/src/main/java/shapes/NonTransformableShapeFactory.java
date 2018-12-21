package shapes;

/**
 * Factory that can create non-transformable {@link Shape Shapes}. Non-transformable shapes can only
 * be transformed in ways that do not need a "transform" attribute appended to the HTML shape tag.
 */
public class NonTransformableShapeFactory implements ShapeFactory {

  @Override
  public Circle createCircle() {
    return new Circle();
  }

  @Override
  public Ellipse createEllipse() {
    return new Ellipse();
  }

  @Override
  public Line createLine() {
    return new Line();
  }

  @Override
  public Polygon createPolygon() {
    return new Polygon();
  }

  @Override
  public RegularPolygon createRegularPolygon() {
    return new RegularPolygon();
  }

  @Override
  public Star createStar() {
    return new Star();
  }

  @Override
  public Text createText() {
    return new Text();
  }
}
