package move;

/**
 * This interface combines the different movebehaviours of the different shapes.
 *
 * @see LineMove
 * @see CircleMove
 * @see EllipseMove
 * @see PolygonMove
 * @see CantMove
 */
public interface MoveBehaviour {

    /**
     * Moves the shape from one point to another.
     */
    void move();

}
