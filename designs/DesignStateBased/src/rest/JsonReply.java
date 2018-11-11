package rest;

import project.Canvas;

/**
 * Casts a {@link Canvas} into a String for the client.
 * The String is in JSON format and contains only a JSON representation of the Canvas.
 *
 * @see RestHandler
 * @see ReplyFormat
 */
public class JsonReply implements ReplyFormat {

    /**
     * Casts a {@link Canvas} into a String for the client.
     * The String is in JSON format and contains only a JSON representation of the Canvas.
     *
     * @param canvas The {@link Canvas} to be converted into a String.
     * @return A String representation of the Canvas.
     *             The String is in JSON format and contains only a JSON representation of the Canvas.
     */
    public String cast(Canvas canvas) {
        return null;
    }

}
