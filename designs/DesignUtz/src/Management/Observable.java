package Management;

/**
 * This interface manages the interaction between the actual shapes and the layers.
 * It notifies when something has changed (e.g.: A shape has been moved or a new shape has been added).
 *
 * @see Observer
 * @see Shape.Shape
 */
public interface Observable {


    /**
     * Registers new observable objects.
     * @param observer
     */
    void register(Observer observer);

    /**
     * Unregisters observable objects.
     * @param observer
     */
    void unregister(Observer observer);

    /**
     * This method notifies the observer when something has changed.
     */
    void notifyObservers();


}
