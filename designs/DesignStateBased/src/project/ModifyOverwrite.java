package project;

/**
 * Class used by the {@link Canvas} to solve modification conflict between clients sharing the same project.
 * This implementation always overwrites the earlier modification with the latest one.
 *
 * @see ModifyStrategy
 */
public class ModifyOverwrite {

    /**
     * Called when the {@link Canvas} encounters a modification conflict.
     * This implementation disregards the conflict and carries out the modification
     *
     * @param layer The {@link Layer} that is being modified.
     * @param attributes A JSON String containing the attributes to be modified
     * @return <code>true</code> if the Layer had one or more of its attributes modified, <code>false</code> otherwise
     */
    public boolean modify(Layer layer, String attributes) {
        return false;
    }

}
