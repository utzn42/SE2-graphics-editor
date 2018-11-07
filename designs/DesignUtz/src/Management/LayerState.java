package Management;


/**
 * Describes one of the three different states a layer object can currently be. Full, filled or empty.
 *
 * It implements the Observer interface.
 *
 * @see Observer
 * @see Layer
 */
public enum LayerState implements Observer {

    FULL,
    FILLED,
    EMPTY;

    public void update(){

    }

}
