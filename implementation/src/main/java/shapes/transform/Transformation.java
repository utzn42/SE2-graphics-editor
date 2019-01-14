package shapes.transform;

import com.fasterxml.jackson.annotation.JsonValue;
import facilitators.Coordinate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import shapes.transform.atomic.AtomicTransformation;
import shapes.transform.atomic.OriginDecorator;
import shapes.transform.atomic.RotationTransformation;
import shapes.transform.atomic.ScaleTransformation;
import shapes.transform.atomic.SkewTransformation;
import shapes.transform.atomic.TranslationTransformation;
import shapes.transform.atomic.UniformScaleTransformation;

/**
 * Holds attributes of a shape transformation as described in
 * <a href="https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/transform">SVG transform</a>.
 * The transformations are meant to be performed in the order that they are stored in.
 */
public class Transformation implements Serializable {

  private static final long serialVersionUID = 1L;
  private final List<AtomicTransformation> transformations;

  /**
   * Creates a null Transformation.
   */
  public Transformation() {
    transformations = new ArrayList<>();
  }

  /**
   * Creates a Transformation with the provided {@link AtomicTransformation AtomicTransformations}.
   *
   * @param transformations The AtomicTransformations the Transformation should contain.
   */
  public Transformation(List<AtomicTransformation> transformations) {
    this.transformations = transformations;
  }

  /**
   * Returns <code>true</code> if there are no {@link AtomicTransformation AtomicTransformations}
   * in this transformation.
   *
   * @return <code>true</code> if there are no {@link AtomicTransformation AtomicTransformations}
   * in this transformation, <code>false</code> otherwise.
   */
  public boolean isNull() {
    return transformations.isEmpty();
  }

  /**
   * Returns the {@link List} of {@link AtomicTransformation AtomicTransformations} in this
   * transformation.
   *
   * @return The List of AtomicTransformations in this transformation.
   */
  @JsonValue
  public List<AtomicTransformation> getTransformations() {
    return transformations;
  }

  /**
   * Adds an {@link AtomicTransformation} after the other transformations.
   *
   * @param transformation The atomic transformation to add.
   */
  public void addTransformation(AtomicTransformation transformation) {
    transformations.add(transformation);
  }

  /**
   * Adds all {@link AtomicTransformation AtomicTransformations} contained in the given
   * Transformation to this Transformation.
   *
   * @param transformation The Transformation to add to this Transformation.
   */
  public void addTransformations(Transformation transformation) {
    transformations.addAll(transformation.getTransformations());
  }

  /**
   * Removes the {@link AtomicTransformation} with the specified index from this Transformation.
   *
   * @param index The index of the atomic transformation to remove.
   */
  public void removeTransformation(int index) {
    transformations.remove(index);
  }

  /**
   * Removes the given {@link AtomicTransformation} from this Transformation.
   *
   * @param transformation The atomic transformation to remove.
   */
  public void removeTransformation(AtomicTransformation transformation) {
    transformations.remove(transformation);
  }

  /**
   * Removes all {@link AtomicTransformation AtomicTransformations} contained in the given
   * Transformation from this Transformation.
   *
   * @param transformation The Transformation containing the atomic transformations to remove.
   */
  public void removeTransformations(Transformation transformation) {
    transformations.removeAll(transformation.getTransformations());
  }

  /**
   * Adds a {@link TranslationTransformation} after the other transformations.
   *
   * @param translationVector The vector to translate by.
   */
  public void addTranslation(Coordinate translationVector) {
    transformations.add(new TranslationTransformation(translationVector));
  }

  /**
   * Adds a {@link TranslationTransformation} after the other transformations.
   *
   * @param translationX The value to translate by in the x-direction.
   * @param translationY The value to translate by in the y-direction.
   */
  public void addTranslation(double translationX, double translationY) {
    transformations.add(new TranslationTransformation(translationX, translationY));
  }

  /**
   * Adds a {@link RotationTransformation} after the other transformations.
   *
   * @param rotationAngle The angle to rotate by.
   */
  public void addRotation(double rotationAngle) {
    transformations.add(new RotationTransformation(rotationAngle));
  }

  /**
   * Adds a {@link RotationTransformation} wrapped in an {@link OriginDecorator} after the other
   * transformations.
   *
   * @param rotationAngle The angle to rotate by.
   * @param origin The rotation origin.
   */
  public void addRotation(double rotationAngle, Coordinate origin) {
    transformations.add(new OriginDecorator(new RotationTransformation(rotationAngle),
        origin));
  }

  /**
   * Adds a {@link RotationTransformation} wrapped in an {@link OriginDecorator} after the other
   * transformations.
   *
   * @param rotationAngle The angle to rotate by.
   * @param originX The x-coordinate of the rotation origin.
   * @param originY The y-coordinate of the rotation origin.
   */
  public void addRotation(double rotationAngle, double originX, double originY) {
    transformations.add(new OriginDecorator(new RotationTransformation(rotationAngle),
        originX, originY));
  }

  /**
   * Adds a {@link UniformScaleTransformation} after the other transformations.
   *
   * @param scalingFactor The factor to scale by.
   */
  public void addUniformScale(double scalingFactor) {
    transformations.add(new UniformScaleTransformation(scalingFactor));
  }

  /**
   * Adds a {@link UniformScaleTransformation} wrapped in an {@link OriginDecorator} after the other
   * transformations.
   *
   * @param scalingFactor The factor to scale by.
   * @param origin The origin to scale in respect to.
   */
  public void addUniformScale(double scalingFactor, Coordinate origin) {
    transformations.add(new OriginDecorator(new UniformScaleTransformation(scalingFactor), origin));
  }

  /**
   * Adds a {@link UniformScaleTransformation} wrapped in an {@link OriginDecorator} after the other
   * transformations.
   *
   * @param scalingFactor The factor to scale by.
   * @param originX The x-coordinate of the origin to scale in respect to.
   * @param originY The y-coordinate of the origin to scale in respect to.
   */
  public void addUniformScale(double scalingFactor, double originX, double originY) {
    transformations.add(new OriginDecorator(new UniformScaleTransformation(scalingFactor), originX,
        originY));
  }

  /**
   * Adds a {@link ScaleTransformation} after the other transformations.
   *
   * @param scalingFactors The factors to scale by in the x- and y-directions.
   */
  public void addScale(Coordinate scalingFactors) {
    transformations.add(new ScaleTransformation(scalingFactors));
  }

  /**
   * Adds a {@link ScaleTransformation} after the other transformations.
   *
   * @param scalingFactorX The factor to scale by in the x-direction.
   * @param scalingFactorY The factor to scale by in the y-direction.
   */
  public void addScale(double scalingFactorX, double scalingFactorY) {
    transformations.add(new ScaleTransformation(scalingFactorX, scalingFactorY));
  }

  /**
   * Adds a {@link ScaleTransformation} wrapped in an {@link OriginDecorator} after the other
   * transformations.
   *
   * @param scalingFactors The factors to scale by in the x- and y-directions.
   * @param origin The origin to scale in respect to.
   */
  public void addScale(Coordinate scalingFactors, Coordinate origin) {
    transformations.add(new OriginDecorator(new ScaleTransformation(scalingFactors), origin));
  }

  /**
   * Adds a {@link ScaleTransformation} wrapped in an {@link OriginDecorator} after the other
   * transformations.
   *
   * @param scalingFactorX The factor to scale by in the x-direction.
   * @param scalingFactorY The factor to scale by in the y-direction.
   * @param originX The x-coordinate of the origin to scale in respect to.
   * @param originY The y-coordinate of the origin to scale in respect to.
   */
  public void addScale(double scalingFactorX, double scalingFactorY, double originX, double originY) {
    transformations.add(new OriginDecorator(new ScaleTransformation(scalingFactorX, scalingFactorY),
        originX, originY));
  }

  /**
   * Adds a {@link SkewTransformation} using the x-axis after the other transformations.
   *
   * @param skewAngle The angle to skew by.
   */
  public void addSkewX(double skewAngle) {
    transformations.add(new SkewTransformation(skewAngle, "x"));
  }

  /**
   * Adds a {@link SkewTransformation} using the x-axis wrapped in an {@link OriginDecorator} after
   * the other transformations.
   *
   * @param skewAngle The angle to skew by.
   * @param origin The origin to skew in respect to.
   */
  public void addSkewX(double skewAngle, Coordinate origin) {
    transformations.add(new OriginDecorator(new SkewTransformation(skewAngle, "x"),
        origin));
  }

  /**
   * Adds a {@link SkewTransformation} using the x-axis wrapped in an {@link OriginDecorator} after
   * the other transformations.
   *
   * @param skewAngle The angle to skew by.
   * @param originX The x-coordinate of the origin to skew in respect to.
   * @param originY The y-coordinate of the origin to skew in respect to.
   */
  public void addSkewX(double skewAngle, double originX, double originY) {
    transformations.add(new OriginDecorator(new SkewTransformation(skewAngle, "x"),
        originX, originY));
  }

  /**
   * Adds a {@link SkewTransformation} using the y-axis after the other transformations.
   *
   * @param skewAngle The angle to skew by.
   */
  public void addSkewY(double skewAngle) {
    transformations.add(new SkewTransformation(skewAngle, "y"));
  }

  /**
   * Adds a {@link SkewTransformation} using the y-axis wrapped in an {@link OriginDecorator} after
   * the other transformations.
   *
   * @param skewAngle The angle to skew by.
   * @param origin The origin to skew in respect to.
   */
  public void addSkewY(double skewAngle, Coordinate origin) {
    transformations.add(new OriginDecorator(new SkewTransformation(skewAngle, "y"),
        origin));
  }

  /**
   * Adds a {@link SkewTransformation} using the y-axis wrapped in an {@link OriginDecorator} after
   * the other transformations.
   *
   * @param skewAngle The angle to skew by.
   * @param originX The x-coordinate of the origin to skew in respect to.
   * @param originY The y-coordinate of the origin to skew in respect to.
   */
  public void addSkewY(double skewAngle, double originX, double originY) {
    transformations.add(new OriginDecorator(new SkewTransformation(skewAngle, "y"),
        originX, originY));
  }

  /**
   * Returns an HTML "transform" attribute representing the transformation.
   *
   * @return An HTML "transform" attribute representing the transformation.
   */
  public String getHTMLAttribute() {

    StringJoiner stringJoiner = new StringJoiner(" ", "transform=\"", "\"");

    // Note: Transformations are performed from right to left, so the order needs to be reversed.
    List<AtomicTransformation> reversedTransformations = new ArrayList<>(transformations);
    Collections.reverse(reversedTransformations);

    reversedTransformations.forEach(transformation ->
        stringJoiner.add(transformation.getHTMLAttribute())
    );

    return stringJoiner.toString();

  }

}
