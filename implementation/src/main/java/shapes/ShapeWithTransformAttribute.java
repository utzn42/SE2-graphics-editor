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

public class ShapeWithTransformAttribute extends ShapeDecorator implements Rotatable, Scalable, Skewable,
    Translatable,
    UniformScalable {

  @JsonUnwrapped
  @JsonProperty("transformations")
  private Transformation transformation;

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
    return "<" + getShapeType().toString().toLowerCase() + " " + getHTMLAttributes() + "></" + getShapeType().toString().toLowerCase() + ">";
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

  public Transformation getTransformation(){
    return transformation;
  }

}
