package shapes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import shapes.transform.Rotatable;
import shapes.transform.Scalable;
import shapes.transform.ShapeTransformer;
import shapes.transform.Skewable;
import shapes.transform.Transformation;
import shapes.transform.Translatable;
import shapes.transform.UniformScalable;
import shapes.transform.atomic.RotationTransformation;
import shapes.transform.atomic.ScaleTransformation;
import shapes.transform.atomic.SkewTransformation;
import shapes.transform.atomic.TranslationTransformation;
import shapes.transform.atomic.UniformScaleTransformation;

/**
 * Lets you decorate {@link Shape}s with transform attributes.
 *
 * @see ShapeDecorator
 */
public class ShapeWithTransformAttribute extends ShapeDecorator implements Rotatable, Scalable,
    Skewable,
    Translatable,
    UniformScalable {

  private static final long serialVersionUID = 1L;

  @JsonUnwrapped
  @JsonProperty("transformations")
  private Transformation transformation;

  /**
   * Creates a ShapeWithTransformAttribute decorator around the given {@link Shape}.
   *
   * @param shape The Shape to wrap in the decorator.
   */
  public ShapeWithTransformAttribute(Shape shape) {
    super(shape);
    transformation = new Transformation();
  }

  @Override
  public void rotate(RotationTransformation transformation) {
    this.transformation.addTransformation(transformation);
  }

  @Override
  public void scale(ScaleTransformation transformation) {
    this.transformation.addTransformation(transformation);
  }

  @Override
  public void skew(SkewTransformation transformation) {
    this.transformation.addTransformation(transformation);
  }

  @Override
  public void translate(TranslationTransformation transformation) {
    this.transformation.addTransformation(transformation);
  }

  @Override
  public void uniformScale(UniformScaleTransformation transformation) {
    this.transformation.addTransformation(transformation);
  }


  @Override
  protected String getHTMLAttributes() {
    if (transformation.isNull()) {
      return super.getHTMLAttributes();
    } else {
      return super.getHTMLAttributes() + " " + transformation.getHTMLAttribute();
    }
  }

  @Override
  public String getHTML() {
    switch (getShapeType()) {
      case REGULAR_POLYGON:
      case STAR:
        return "<polygon " + getHTMLAttributes() + "></polygon>";
      case TEXT:
        return "<text " + getHTMLAttributes() + ">" + ((Text) getShape()).getDisplayText() + "</text>";
      case LINE:
        return "<polyline " + getHTMLAttributes() + "></polyline>";
      default:
        return "<" + getShapeType().toString().toLowerCase() + " " + getHTMLAttributes() + "></"
            + getShapeType().toString().toLowerCase() + ">";
    }
  }

  @Override
  public void applyTransformation(Transformation transformation) {

    // Apply directly all transformations applicable to a normal Shape of that type
    Transformation simpleTransformation = ShapeTransformer
        .getLegalTransformationsForShape(getShape(), transformation);
    getShape().applyTransformation(simpleTransformation);

    // Add the rest to leftover transformations
    this.transformation = ShapeTransformer
        .getIllegalTransformationsForShape(getShape(), transformation);

  }

  /**
   * Returns the Transformation that has to be applied to the contained Shape through an HTML "transform" attribute.
   *
   * @return The Transformation that has to be applied to the contained Shape through an HTML "transform" attribute.
   */
  public Transformation getTransformation() {
    return transformation;
  }

  /**
   * Resets the Transformation to a null Transformation.
   */
  public void clearTransformation() {
    transformation = new Transformation();
  }

}
