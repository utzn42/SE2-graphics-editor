package persistence;

import canvas.Canvas;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.LoadedProject;
import project.Project;
import project.ProjectProxy;

public class ProjectSerializer {

  private static final Logger projectSerializerLogger = LoggerFactory
      .getLogger(ProjectSerializer.class);
  private static final String baseDirectory = "projects";
  private static final String projectIDListFilePath = baseDirectory + "/projectIDList.ser";
  private static final String seedCounterFilePath = baseDirectory + "/seed.ser";

  private static boolean proxyProjects = true;
  private static Map<String, Project> projects = null;
  private static Long seedCounter = null;

  /**
   * Deletes all projects.
   */
  public static void deleteProjects() {
    projects = null;
  }

  /**
   * Returns the path of the directory of the project.
   * @param projectID The ID of the project.
   * @return The path of the directory in {@link String}
   */
  private static String getProjectDirectoryPath(String projectID) {
    return baseDirectory + "/" + projectID;
  }

  /**
   * Returns the path of the file.
   * @param projectID The ID of the project.
   * @return The path of the file in {@link String}
   */
  private static String getProjectFilePath(String projectID) {
    return getProjectDirectoryPath(projectID) + "/" + projectID + ".ser";
  }

  /**
   * Creates the directory where the projects get saved.
   * @param directoryPath The path where the directory should get created.
   */
  private static void createDirectories(String directoryPath) {
    boolean createdDirs = new File(directoryPath).mkdirs();
    if (createdDirs) {
      projectSerializerLogger.info("Created directories for path " + directoryPath + ".");
    } else {
      projectSerializerLogger.info("Did not create any directories.");
    }
  }

  public static void setProxyProjects(boolean val) {
    proxyProjects = val;
  }

  /**
   * Initializes the IDs of the saved projects.
   */
  private static void initializeProjects() {

    createDirectories(baseDirectory);

    projects = new HashMap<>();

    IDList projectIDs;
    try {
      projectIDs = Serializer.deserialize(projectIDListFilePath, IDList.class);
      seedCounter = Serializer.deserialize(seedCounterFilePath, Long.class);
    } catch (IOException | ClassNotFoundException e) {
      projectSerializerLogger.error("Failed to deserialize Project ID list.");
      projectSerializerLogger.info("Creating new Project ID list and setting seed to 0.");
      projectIDs = new IDList();
      seedCounter = 0L;
    }

    if (proxyProjects) {
      projectIDs.getIdList().forEach(id -> projects.put(id, new ProjectProxy(id)));
    } else {
      projectIDs.getIdList().forEach(id -> {
        try {
          projects.put(id, getProject(id));
        } catch (Exception e) {
          projects.put(id, new ProjectProxy(id));
        }
      });
    }

  }

  /**
   * Returns a {@link Map} that maps the ID of each locally stored {@link Project} to the Project
   * itself.
   *
   * @return The Map of Project IDs and Projects.
   */
  public static Map<String, Project> getProjects() {

    if (projects == null) {
      initializeProjects();
    }

    return new HashMap<>(projects);

  }

  /**
   * Fetches a {@link Project} from local storage for a given Project ID.
   *
   * @param projectID The ID of the Project.
   * @return The Project with the given ID.
   * @throws IOException If an I/O error occured.
   * @throws ClassNotFoundException If the {@link Canvas} class is missing.
   */
  public static Project getProject(String projectID) throws IOException, ClassNotFoundException {

    Canvas projectCanvas;
    try {
      projectCanvas = Serializer.deserialize(getProjectFilePath(projectID), Canvas.class);
    } catch (IOException | ClassNotFoundException e) {
      projectSerializerLogger.error("Failed to deserialize Project with ID " + projectID + ".");
      throw e;
    }

    return new LoadedProject(projectID, projectCanvas);

  }

  /**
   * Stores a {@link Project} using its ID as key. Returns <code>true</code> if the Project has been
   * successfully stored.
   *
   * @param project The Project to store.
   * @return <code>true</code> if the Project has been successfully stored, <code>false</code> if
   * an error occured.
   */
  public static boolean putProject(Project project) {

    if (projects == null) {
      initializeProjects();
    }

    String projectID = project.getProjectID();
    createDirectories(getProjectDirectoryPath(projectID));

    try {
      Serializer.serialize(project.getCanvas(), getProjectFilePath(projectID));
      if (!(projects.containsKey(projectID))) {
        List<String> projectIDs = new ArrayList<>(projects.keySet());
        projectIDs.add(projectID);
        IDList idList = new IDList(projectIDs);
        Serializer.serialize(idList, projectIDListFilePath);
      }
    } catch (IOException | ClassNotFoundException e) {
      projectSerializerLogger.error("Failed to serialize Project with ID " + projectID + ".");
      return false;
    }

    projects.put(projectID, project);
    return true;

  }

  /**
   * Returns and increments the current seed for {@link Project} ID generation.
   *
   * @return The current seed for Project ID generation.
   * @throws IOException If an I/O error occured while incrementing the seed.
   */
  public static long getAndIncrementSeed() throws IOException {

    if (seedCounter == null) {
      initializeProjects();
    }

    try {
      Serializer.serialize(seedCounter + 1, seedCounterFilePath);
    } catch (IOException e) {
      projectSerializerLogger.error("Failed to serialize new seed.");
      throw e;
    }

    return seedCounter++;

  }

}