package project;

/**
 * Class used by the {@link Canvas} to solve modification conflict between clients sharing the same project.
 * This implementation merges as many modifications as possible from the latest modification into the earlier one
 * and discards the rest.
 *
 * @see ModifyStrategy
 */
public class ModifyMerge {

    /**
     * Called when the {@link Canvas} encounters a modification conflict.
     * This implementation tries to merge the modifications where possible.
     *
     * @param layer The {@link Layer} that is being modified.
     * @param attributes A JSON String containing the attributes to be modified
     * @return <code>true</code> if the Layer had one or more of its attributes modified, <code>false</code> otherwise
     */
    public boolean modify(Layer layer, String attributes) {
        return false;
    }

}
