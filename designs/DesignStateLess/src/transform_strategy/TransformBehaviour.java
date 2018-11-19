package transform_strategy;

/**
 * This interface combines the different transformbehaviours of the different shapes.
 *
 * @see LineTransform
 * @see CircleTransform
 * @see EllipseTransform
 * @see PolygonTransform
 * @see CantTransform
 */
public interface TransformBehaviour {

    /**
     * Moves the shape from one point to another.
     */
    void transform();

}
