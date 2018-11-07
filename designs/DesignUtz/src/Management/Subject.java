package Management;

/**
 * This interface manages the interaction between the actual shapes and the layers.
 * It notifys when something has changed (e.g.: A shape has been moved or a new shape has been added).
 *
 * @see Observer
 * @see Shape.Shape
 */
public interface Subject {


    /**
     *
     * @param observer
     */
    void register(Observer observer);

    /**
     *
     * @param observer
     */
    void unregister(Observer observer);

    /**
     * This method notifys the observer when something has changed.
     */
    void notify_();


}
