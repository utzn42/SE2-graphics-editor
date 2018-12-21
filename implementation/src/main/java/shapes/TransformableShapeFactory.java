package shapes;

/**
 * Factory that can create transformable {@link Shape Shapes}. Transformable shapes can be
 * transformed in any way, including those that need a "transform" attribute appended to the HTML shape tag.
 */
public class TransformableShapeFactory implements ShapeFactory {

  @Override
  public Circle createCircle() {
    //TODO: Create class shapes.TransformableCircle
    //TODO: Implement shapes.TransformableShapeFactory#createCircle()
    return null;
  }

  @Override
  public Ellipse createEllipse() {
    //TODO: Create class shapes.TransformableEllipse
    //TODO: Implement shapes.TransformableShapeFactory#createEllipse()
    return null;
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
    //TODO: Create class shapes.TransformableRegularPolygoon
    //TODO: Implement shapes.TransformableShapeFactory#createRegularPolygon()
    return null;
  }

  @Override
  public Star createStar() {
    //TODO: Create class shapes.TransformableStar
    //TODO: Implement shapes.TransformableShapeFactory#createStar()
    return null;
  }

  @Override
  public Text createText() {
    //TODO: Create class shapes.TransformableText
    //TODO: Implement shapes.TransformableShapeFactory#createText()
    return null;
  }

}
