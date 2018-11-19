package messages;

/**
 * This abstract class holds a projectID to specify to which project/client/user the response belongs.
 * The class gets extended and defined more by {@link ServerResponse}.
 *
 * @see ServerResponse
 */
public abstract class Response {

  String projectID;
}
