package shapes.transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import shapes.Shape;
import shapes.transform.atomic.AtomicTransformation;
import shapes.transform.atomic.AtomicTransformationDecorator;
import shapes.transform.atomic.OriginDecorator;
import shapes.transform.atomic.RotationTransformation;
import shapes.transform.atomic.ScaleTransformation;
import shapes.transform.atomic.SkewTransformation;
import shapes.transform.atomic.TranslationTransformation;
import shapes.transform.atomic.UniformScaleTransformation;

/**
 * Class used for {@link Shape} transformations.
 *
 * @see Transformation
 */
public class ShapeTransformer {

  /**
   * Transforms a {@link Shape} using a {@link Transformation}.
   *
   * @param shape The shape to transform.
   * @param transformation The transformation to apply.
   * @throws IllegalArgumentException If the transformation cannot fully be applied to the shape.
   */
  public static void transformShape(Shape shape, Transformation transformation) throws IllegalArgumentException {

    if (!getIllegalTransformationsForShape(shape, transformation).isNull()) {
      throw new IllegalArgumentException("Transformation could not be applied to the shape!");
    }

    List<AtomicTransformation> unpackedTransformations = new ArrayList<>();
    transformation.getTransformations().forEach(atomicTransformation ->
        unpackedTransformations.addAll(unpackDecorators(atomicTransformation))
    );

    unpackedTransformations.forEach(atomicTransformation -> {

      assert (!(atomicTransformation instanceof AtomicTransformationDecorator));

      switch (atomicTransformation.getType()) {

        case TRANSLATION:
          assert (shape instanceof Translatable
              && atomicTransformation instanceof TranslationTransformation);
          ((Translatable) shape).translate((TranslationTransformation) atomicTransformation);
          break;

        case ROTATION:
          assert (shape instanceof Rotatable
              && atomicTransformation instanceof RotationTransformation);
          ((Rotatable) shape).rotate((RotationTransformation) atomicTransformation);
          break;

        case UNIFORM_SCALE:
          assert (shape instanceof UniformScalable
              && atomicTransformation instanceof UniformScaleTransformation);
          ((UniformScalable) shape).uniformScale((UniformScaleTransformation) atomicTransformation);
          break;

        case SCALE:
          assert (shape instanceof Scalable
              && atomicTransformation instanceof ScaleTransformation);
          ((Scalable) shape).scale((ScaleTransformation) atomicTransformation);
          break;

        case SKEW:
          assert (shape instanceof Skewable
              && atomicTransformation instanceof SkewTransformation);
          ((Skewable) shape).skew((SkewTransformation) atomicTransformation);

      }

    });

  }

  /**
   * Given a {@link Shape} and a {@link Transformation}, returns a transformation containing
   * all the transformations that cannot be applied to the shape.
   *
   * @param shape The shape to test the transformation on.
   * @param transformation The transformation to test.
   * @return The non-applicable transformations.
   */
  public static Transformation getIllegalTransformationsForShape(Shape shape, Transformation transformation) {

    List<AtomicTransformation> illegalTransformations = new ArrayList<>();

    transformation.getTransformations().forEach(atomicTransformation -> {

      // Note: To keep the transformation order intact, one illegal transformation means that the
      //       following transformations have to be considered illegal as well.
      if (!illegalTransformations.isEmpty()) {
        illegalTransformations.add(atomicTransformation);
        return;
      }

      // Note: Since OriginDecorator adds additional Translations to a transformation, it needs the
      //       Shape to be Translatable
      if (atomicTransformation instanceof OriginDecorator && !(shape instanceof Translatable)) {
        illegalTransformations.add(atomicTransformation);
        return;
      }

      switch(atomicTransformation.getType()) {

        case TRANSLATION:
          if (!(shape instanceof Translatable)) {
            illegalTransformations.add(atomicTransformation);
          }
          break;

        case ROTATION:
          if(!(shape instanceof Rotatable)) {
            illegalTransformations.add(atomicTransformation);
          }
          break;

        case UNIFORM_SCALE:
          if (!(shape instanceof UniformScalable)) {
            illegalTransformations.add(atomicTransformation);
          }
          break;

        case SCALE:
          if (!(shape instanceof Scalable)) {
            illegalTransformations.add(atomicTransformation);
          }
          break;

        case SKEW:
          if (!(shape instanceof Skewable)) {
            illegalTransformations.add(atomicTransformation);
          }

      }

    });

    return new Transformation(illegalTransformations);

  }

  /**
   * Given a {@link Shape} and a {@link Transformation}, returns a transformation containing
   * only the transformations that can be applied to the shape.
   *
   * @param shape The shape to test the transformation on.
   * @param transformation The transformation to test.
   * @return The applicable transformations.
   */
  public static Transformation getLegalTransformationsForShape(Shape shape, Transformation transformation) {

    Transformation illegalTransformations = getIllegalTransformationsForShape(shape, transformation);

    List<AtomicTransformation> legalTransformations = new ArrayList<>(transformation.getTransformations());
    legalTransformations.removeAll(illegalTransformations.getTransformations());

    return new Transformation(legalTransformations);

  }

  /**
   * Unpacks all pre- and postprocessing transformations from an {@link AtomicTransformation}
   * and returns the result as a {@link List} of AtomicTransformations.
   *
   * @param transformation The transformation to unpack.
   * @return The unpacked transformation as a List of AtomicTransformations.
   */
  private static List<AtomicTransformation> unpackDecorators(AtomicTransformation transformation) {

    // Nothing to unpack if not a decorator
    if (!(transformation instanceof AtomicTransformationDecorator)) {
      return Collections.singletonList(transformation);
    }

    if (transformation instanceof OriginDecorator) {
      return unpackOriginDecorator((OriginDecorator) transformation);
    }

    throw new RuntimeException("Failed to unpack: Unexpected subclass of OriginDecorator");

  }

  /**
   * Unpacks an {@link OriginDecorator} into its preprocessing, main and postprocessing
   * transformations and returns them as a {@link List}.
   *
   * @param originDecorator The decorator to unpack.
   * @return The unpacked decorator as a List of AtomicTransformations.
   */
  private static List<AtomicTransformation> unpackOriginDecorator(OriginDecorator originDecorator) {

    List<AtomicTransformation> unpackedTransformations = new ArrayList<>();

    unpackedTransformations.add(originDecorator.getBeforeTransformation());
    unpackedTransformations.addAll(unpackDecorators(originDecorator.getTransformation()));
    unpackedTransformations.add(originDecorator.getAfterTransformation());

    return unpackedTransformations;

  }


}
