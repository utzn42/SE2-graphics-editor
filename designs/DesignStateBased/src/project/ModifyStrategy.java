package project;

/**
 * Interface used by the {@link Canvas} to solve modification conflict between clients sharing the same project.
 * Implementation determines behavior in conflict cases.
 *
 * @see ModifyOverwrite
 * @see ModifyCancel
 * @see ModifyMerge
 */
public interface ModifyStrategy {

    /**
     * Called when the {@link Canvas} encounters a modification conflict.
     * Implementation determines behavior in conflict cases.
     *
     * @param layer The {@link Layer} that is being modified.
     * @param attributes A JSON String containing the attributes to be modified
     * @return <code>true</code> if the Layer had one or more of its attributes modified, <code>false</code> otherwise
     */
    boolean modify(Layer layer, String attributes);

}
