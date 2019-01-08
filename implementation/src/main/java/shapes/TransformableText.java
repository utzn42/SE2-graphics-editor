package shapes;

import shapes.transform.Rotatable;
import shapes.transform.ShapeTransformer;
import shapes.transform.UniformScalable;
import shapes.transform.atomic.RotationTransformation;
import shapes.transform.Scalable;
import shapes.transform.atomic.ScaleTransformation;
import shapes.transform.atomic.SkewTransformation;
import shapes.transform.Skewable;
import shapes.transform.Transformation;
import shapes.transform.Translatable;

/**
 * A transformable {@link Text} element.
 * Can be transformed using any {@link Transformation}.
 */
public class TransformableText extends Text implements Translatable, Rotatable, UniformScalable,
    Scalable, Skewable {

  private Transformation leftoverTransformation = new Transformation();

  @Override
  protected String getHTMLAttributes() {
    if (leftoverTransformation.isNull()) {
      return super.getHTMLAttributes();
    } else {
      return super.getHTMLAttributes() + " " + leftoverTransformation.getHTMLAttribute();
    }
  }

  @Override
  public void applyTransformation(Transformation transformation) {

    // Apply directly all transformations applicable to a normal Text element
    Transformation simpleTransformation = ShapeTransformer
        .getLegalTransformationsForShape(new Text(), transformation);
    super.applyTransformation(simpleTransformation);

    // Add the rest to leftover transformations
    leftoverTransformation = ShapeTransformer.getIllegalTransformationsForShape(new Text(), transformation);

  }

  @Override
  public void rotate(RotationTransformation transformation) {
    leftoverTransformation.addTransformation(transformation);
  }

  @Override
  public void scale(ScaleTransformation transformation) {
    leftoverTransformation.addTransformation(transformation);
  }

  @Override
  public void skew(SkewTransformation transformation) {
    leftoverTransformation.addTransformation(transformation);
  }
}