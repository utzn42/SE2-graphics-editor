package Management;

import Shape.Shape;

/**
 * The observers update is called when the subject changes.
 *
 * @see Canvas
 * @see ShapeInfo
 * @see Layer
 */
public interface Observer {

    /**
     * Updates the Canvas, Layer respectively the shapes.
     * @param shape contains the added, deleted or edited shape
     */
    void update(Shape shape);

}
