package rest;

import project.Canvas;
import project.RevisionData;

import java.util.Map;

/**
 * Class that uses REST to handle all communication between server and client.
 * This class also holds the {@link Canvas} objects and their {@link RevisionData RevisionData}.
 * Canvas and RevisionData are mapped to a unique ID.
 * The handler sends String replies formatted according to its {@link ReplyFormat} strategy.
 *
 * @see Canvas
 */
public class RestHandler {

    private Map<Long, Canvas> projects;
    private Map<Long, RevisionData> projectRevisions;
    private ReplyFormat replyFormat;

    /**
     * Constructs a new RestHandler with an empty {@link Canvas} list.
     * Standard {@link ReplyFormat} is {@link JsonAndHtmlReply}.
     *
     * @see ReplyFormat
     */
    public RestHandler() {

    }

    /**
     * Returns the current {@link ReplyFormat} of the RestHandler.
     * @return The current {@link ReplyFormat} of the RestHandler.
     *
     * @see ReplyFormat
     */
    public ReplyFormat getReplyFormat() {
        return replyFormat;
    }

    /**
     * Sets the {@link ReplyFormat} for the RestHandler.
     *
     * @param replyFormat The new {@link ReplyFormat} for the RestHandler
     * @see ReplyFormat
     */
    public void setReplyFormat(ReplyFormat replyFormat) {

    }

    /**
     * Creates a new project.
     *
     * @return The full {@link Canvas} of the project,
     *             formatted into a String according to the RestHandler's {@link ReplyFormat}.
     */
    public String newProject() {
        return null;
    }

    /**
     * Loads a project corresponding to the data in the given JSON String.
     *
     * @param json A JSON String representation of the {@link Canvas} to be loaded.
     *             Can include a Canvas ID or a full Canvas.
     * @return The full {@link Canvas} of the project,
     *             formatted into a String according to the RestHandler's {@link ReplyFormat}.
     */
    public String loadProject(String json) {
        return null;
    }

    /**
     * Adds a new {@link project.Layer Layer} to the project's {@link Canvas}.
     * The JSON parameter needs to specify at least the type of {@link shapes.GraphicElement GraphicElement}
     * that the Layer should hold.
     *
     * @param json A JSON String containing parameters for the operation.
     *             Needs to specify at least the type of {@link shapes.GraphicElement GraphicElement}
     *             that the Layer should hold.
     * @return The full {@link Canvas} of the project,
     *             formatted into a String according to the RestHandler's {@link ReplyFormat}.
     */
    public String addLayer(String json) {
        return null;
    }

    /**
     * Deletes a {@link project.Layer Layer} from the project's {@link Canvas}.
     * The JSON parameter needs to specify the number of the Layer to be removed.
     *
     * @param json A JSON String containing parameters for the operation.
     *             Needs to specify the number of the Layer to be removed.
     * @return The full {@link Canvas} of the project,
     *             formatted into a String according to the RestHandler's {@link ReplyFormat}.
     */
    public String deleteLayer(String json) {
        return null;
    }

    /**
     * Modifies a {@link project.Layer Layer} on the project's {@link Canvas}.
     * The JSON parameter needs to specify the number of the Layer to be modified, as well as the attributes of the
     * Layer's {@link shapes.GraphicElement GraphicElement} that should be modified and their respective new values.
     *
     * @param json A JSON String containing parameters for the operation.
     *             Needs to specify the number of the Layer to be modified, as well as the attributes of the
     *             Layer's {@link shapes.GraphicElement GraphicElement} that should be modified
     *             and their respective new values.
     * @return The full {@link Canvas} of the project,
     *             formatted into a String according to the RestHandler's {@link ReplyFormat}.
     */
    public String modifyLayer(String json) {
        return null;
    }

    /**
     * Modifies the project's {@link Canvas}.
     * The JSON parameter needs to specify the attributes of the Canvas to be modified and their respective new values.
     *
     * @param json A JSON String containing parameters for the operation.
     *             Needs to specify the attributes of the Canvas to be modified and their respective new values.
     * @return The full {@link Canvas} of the project,
     *             formatted into a String according to the RestHandler's {@link ReplyFormat}.
     */
    public String modifyCanvas(String json) {
        return null;
    }

}
