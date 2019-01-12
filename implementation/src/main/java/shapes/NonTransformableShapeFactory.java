package shapes;

import canvas.CanvasLayer;

/**
 * Factory that can create non-transformable {@link Shape Shapes}. Non-transformable shapes can only
 * be transformed in ways that do not need a "transform" attribute appended to the HTML shape tag.
 */
public class NonTransformableShapeFactory implements ShapeFactory {

  @Override
  public CanvasLayer createShape(long id, ShapeType shapeType) {
    switch(shapeType){
      case CIRCLE: return new CanvasLayer(id, new Circle());
      case ELLIPSE: return new CanvasLayer(id, new Ellipse());
      case LINE: return new CanvasLayer(id, new Line());
      case POLYGON: return new CanvasLayer(id, new Polygon());
      case REGULAR_POLYGON: return new CanvasLayer(id, new RegularPolygon());
      case STAR: return new CanvasLayer(id, new Star());
      case TEXT: return new CanvasLayer(id, new Text());
      default : return new CanvasLayer(id, new Circle());
    }
  }

}
