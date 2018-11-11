package project;

/**
 * Class used by the {@link Canvas} to solve modification conflict between clients sharing the same project.
 * This implementation always cancels the latest modification in favor of the earlier one.
 *
 * @see ModifyStrategy
 */
public class ModifyCancel {

    /**
     * Called when the {@link Canvas} encounters a modification conflict.
     * This implementation cancels the modification.
     *
     * @param layer The {@link Layer} that is being modified.
     * @param attributes A JSON String containing the attributes to be modified
     * @return Always <code>false</code> for this implementation
     */
    public boolean modify(Layer layer, String attributes) {
        return false;
    }

}
