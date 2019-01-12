package shapes;

import canvas.CanvasLayer;

/**
 * Factory that can create transformable {@link Shape Shapes}. Transformable shapes can be
 * transformed in any way, including those that need a "transform" attribute appended to the HTML shape tag.
 */
public class TransformableShapeFactory implements ShapeFactory {

  @Override
  public CanvasLayer createShape(long id, ShapeType shapeType) {
    switch(shapeType){
      case CIRCLE: return new CanvasLayer(id, new ShapeWithTransformAttribute(new Circle()));
      case ELLIPSE: return new CanvasLayer(id, new ShapeWithTransformAttribute(new Ellipse()));
      case LINE: return new CanvasLayer(id, new ShapeWithTransformAttribute(new Line()));
      case POLYGON: return new CanvasLayer(id, new ShapeWithTransformAttribute(new Polygon()));
      case REGULAR_POLYGON: return new CanvasLayer(id, new ShapeWithTransformAttribute(new RegularPolygon()));
      case STAR: return new CanvasLayer(id, new ShapeWithTransformAttribute(new Star()));
      case TEXT: return new CanvasLayer(id, new ShapeWithTransformAttribute(new Text()));
      default : return new CanvasLayer(id, new ShapeWithTransformAttribute(new Circle()));
    }
  }

}
