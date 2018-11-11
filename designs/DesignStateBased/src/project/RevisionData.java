package project;

import java.util.List;
import java.util.Observer;
import java.util.Observable;

/**
 * Tracks the revision history of a {@link Canvas} and its {@link Layer}s to resolve issues with multiple clients.
 * Implements {@link Observer} to listen to Canvas updates and increments revision numbers on update.
 *
 * @see Canvas
 * @see Layer
 */

public class RevisionData implements Observer {

    private long canvasRevision;
    private List<Long> layerRevision;

    /**
     * Creates an empty RevisionData object observing a {@link Canvas}.
     *
     * @param canvas The {@link Canvas} to observe
     */
    public RevisionData(Canvas canvas) {

    }

    /**
     * Returns the current revision number of the observed {@link Canvas}.
     *
     * @return The current revision number of the observed {@link Canvas}.
     */
    public long getCanvasRevision() {
        return canvasRevision;
    }

    /**
     * Returns the current revision numbers of the {@link Layer}s of the observed {@link Canvas}.
     *
     * @return The current revision numbers of the {@link Layer}s of the observed {@link Canvas}
     */
    public List<Long> getLayerRevision() {
        return layerRevision;
    }

    /**
     * Called when a {@link Layer} of the observed {@link Canvas} is modified.
     * Increments the Canvas revision number and the modified Layer's revision number.
     *
     * @param canvas The observed {@link Canvas}
     * @param layer The modified {@link Layer}
     */
    public void update(Observable canvas, Object layer) {

    }

}
