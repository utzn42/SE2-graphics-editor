package shapes;

import shapes.transform.ShapeTransformer;
import shapes.transform.atomic.RotationTransformation;
import shapes.transform.atomic.ScaleTransformation;
import shapes.transform.atomic.SkewTransformation;
import shapes.transform.Transformation;

/**
 * A transformable {@link RegularPolygon}.
 * Can be transformed using any {@link Transformation}.
 */
public class TransformableRegularPolygon extends RegularPolygon implements TransformableShape {

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

    // Apply directly all transformations applicable to a normal RegularPolygon
    Transformation simpleTransformation = ShapeTransformer
        .getLegalTransformationsForShape(new RegularPolygon(), transformation);
    super.applyTransformation(simpleTransformation);

    // Add the rest to leftover transformations
    leftoverTransformation = ShapeTransformer.getIllegalTransformationsForShape(new RegularPolygon(), transformation);

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

  @Override
  public Transformation getTransformations() {
    return leftoverTransformation;
  }

}