package persistence;

import observer.Observer;
import project.Project;

/**
 * {@link Observer} that passes any {@link Project} it receives via its {@link #update(Object)} function to the {@link ProjectSerializer} class.
 */
public class ProjectObserver implements Observer {

  /**
   * Stores the received object with the {@link ProjectSerializer} if it is a {@link Project}.
   *
   * @param obj The changed object (does not necessarily need to be the subject itself).
   */
  @Override
  public void update(Object obj) {
    if (obj instanceof Project) {
      ProjectSerializer.putProject((Project) obj);
    }
  }

}
