package canvas;

import shapes.Shape;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * A layer is the container of one or more shapes. One layer can hold up to 50 shapes.
 * A layer can lay over or under other layers.
 *
 * The relation is an aggregation, it cannot exist without a the LayerState.
 *
 * @see LayerState
 * @see Observer
 */
public class Layer implements Observer {

    private LayerState state;
    private List<Shape> shapes;
    private boolean visible;

    /**
     * Default constructor.
     */
    public Layer() {

    }

    /**
     * The constructor of the Layer Class. It expects one or more shapes, which then get assigned to the layer.
     * @param shapes one ore more shapes
     */
    public Layer(List<Shape> shapes) {

    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
