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
 * A transformable {@link Star}.
 * Can be transformed using any {@link Transformation}.
 */
public class TransformableStar extends Star implements Translatable, Rotatable, UniformScalable,
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

    // Apply directly all transformations applicable to a normal Star
    Transformation simpleTransformation = ShapeTransformer
        .getLegalTransformationsForShape(new Star(), transformation);
    super.applyTransformation(simpleTransformation);

    // Add the rest to leftover transformations
    leftoverTransformation = ShapeTransformer.getIllegalTransformationsForShape(new Star(), transformation);

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