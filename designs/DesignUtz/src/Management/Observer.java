package Management;

import Shape.Shape;

/**
 * This interface checks if something has changed...
 *
 * @see CanvasState
 * @see ShapeInfo
 * @see LayerState
 */
public interface Observer {

    void update(Shape shape);

}
