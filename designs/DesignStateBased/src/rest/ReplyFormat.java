package rest;

import project.Canvas;

/**
 * Interface used by the {@link RestHandler} to cast a {@link Canvas} into a String for the client.
 *
 * @see JsonAndHtmlReply
 * @see JsonReply
 * @see HtmlReply
 */
public interface ReplyFormat {

    /**
     * Casts a {@link Canvas} into a String for the client.
     *
     * @param canvas The {@link Canvas} to be converted into a String.
     * @return A String representation of the Canvas.
     */
    String cast(Canvas canvas);

}
