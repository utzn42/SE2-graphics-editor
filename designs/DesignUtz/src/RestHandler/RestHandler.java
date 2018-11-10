package RestHandler;

/**
 * This class is the actual interface between the client and the server. The reqeuests get to the RestHandler class and return the desired json object with the parameters for javascript.
 */
public class RestHandler {

    /**
     * This method runs if the user presses the "add item" button.
     * @param json the request from the client
     * @return returns String with parameters for the new added shape
     */
    public String addShape(String json){
        return null;
    }

    /**
     * This method runs if the user edited the shape in any way and submitted the changes.
     * @param json the request from the client
     * @return returns String with parameters for the changes
     */
    public String editShape(String json){
        return null;
    }

    /**
     * This method runs if the user wants to move the shape in any direction.
     * @param json the request from the client
     * @return returns String with parameters for the changes
     */
    public String moveShape(String json){
        return null;
    }

    /**
     * This method runs if the user wants to delete any shape.
     * @param json the request from the client
     * @return returns String with acknowledgement
     */
    public String deleteShape(String json){
        return null;
    }

}
