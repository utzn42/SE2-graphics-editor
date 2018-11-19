package messages;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * This class will get useful later on in the project.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestAddLayer {

  //TODO: Add a parameter for the new layer's position

  @JsonCreator()
  RequestAddLayer() {

  }

}
