package shapes;

import shapes.transform.ShapeTransformer;
import shapes.transform.atomic.RotationTransformation;
import shapes.transform.atomic.SkewTransformation;
import shapes.transform.Transformation;

/**
 * A transformable {@link Ellipse}.
 * Can be transformed using any {@link Transformation}.
 */
public class TransformableEllipse extends Ellipse implements TransformableShape {

  private static final long serialVersionUID = 1L;

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

    // Apply directly all transformations applicable to a normal Ellipse
    Transformation simpleTransformation = ShapeTransformer
        .getLegalTransformationsForShape(new Ellipse(), transformation);
    super.applyTransformation(simpleTransformation);

    // Add the rest to leftover transformations
    leftoverTransformation = ShapeTransformer.getIllegalTransformationsForShape(new Ellipse(), transformation);

  }

  @Override
  public void rotate(RotationTransformation transformation) {
    leftoverTransformation.addTransformation(transformation);
  }

  @Override
  public void skew(SkewTransformation transformation) {
    leftoverTransformation.addTransformation(transformation);
  }

  @Override
  public Transformation getTransformations() {
    return leftoverTransformation;
  }

}
