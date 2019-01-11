package shapes;

import canvas.CanvasLayer;

/**
 * Factory that can create non-transformable {@link Shape Shapes}. Non-transformable shapes can only
 * be transformed in ways that do not need a "transform" attribute appended to the HTML shape tag.
 */
public class NonTransformableShapeFactory implements ShapeFactory {

  @Override
  public CanvasLayer createShape(ShapeType shapeType) {
    switch(shapeType){
      case CIRCLE: return new CanvasLayer(new Circle());
      case ELLIPSE: return new CanvasLayer(new Ellipse());
      case LINE: return new CanvasLayer(new Line());
      case POLYGON: return new CanvasLayer(new Polygon());
      case REGULAR_POLYGON: return new CanvasLayer(new RegularPolygon());
      case STAR: return new CanvasLayer(new Star());
      case TEXT: return new CanvasLayer(new Text());
      default : return new CanvasLayer(new Circle());
    }
  }

}
