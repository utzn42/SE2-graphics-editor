package Management;

/**
 * Describes one of the three different states a canvas object can currently be. Full, filled or empty.
 *
 * It implements the Observer interface.
 *
 * @see Observer
 * @see Canvas
 */
public enum CanvasState implements Observer{
    FULL,
    FILLED,
    EMPTY;



    public void update(){

    }



}
