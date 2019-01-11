package shapes;

import canvas.CanvasLayer;

/**
 * Factory that can create transformable {@link Shape Shapes}. Transformable shapes can be
 * transformed in any way, including those that need a "transform" attribute appended to the HTML shape tag.
 */
public class TransformableShapeFactory implements ShapeFactory {

  @Override
  public CanvasLayer createShape(ShapeType shapeType) {
    switch(shapeType){
      case CIRCLE: return new CanvasLayer(new ShapeWithTransformAttribute(new Circle()));
      case ELLIPSE: return new CanvasLayer(new ShapeWithTransformAttribute(new Ellipse()));
      case LINE: return new CanvasLayer(new ShapeWithTransformAttribute(new Line()));
      case POLYGON: return new CanvasLayer(new ShapeWithTransformAttribute(new Polygon()));
      case REGULAR_POLYGON: return new CanvasLayer(new ShapeWithTransformAttribute(new RegularPolygon()));
      case STAR: return new CanvasLayer(new ShapeWithTransformAttribute(new Star()));
      case TEXT: return new CanvasLayer(new ShapeWithTransformAttribute(new Text()));
      default : return new CanvasLayer(new ShapeWithTransformAttribute(new Circle()));
    }
  }

}
