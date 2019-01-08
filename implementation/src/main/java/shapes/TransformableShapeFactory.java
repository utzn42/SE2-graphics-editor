package shapes;

/**
 * Factory that can create transformable {@link Shape Shapes}. Transformable shapes can be
 * transformed in any way, including those that need a "transform" attribute appended to the HTML shape tag.
 */
public class TransformableShapeFactory implements ShapeFactory {

  @Override
  public Circle createCircle() {
    return new TransformableCircle();
  }

  @Override
  public Ellipse createEllipse() {
    return new TransformableEllipse();
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
    return new TransformableRegularPolygon();
  }

  @Override
  public Star createStar() {
    return new TransformableStar();
  }

  @Override
  public Text createText() {
    return new TransformableText();
  }

}
