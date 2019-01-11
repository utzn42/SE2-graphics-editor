package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import facilitators.Hasher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.LoadedProject;
import project.Project;
import project.ProjectProxy;

class PersistenceTest {

  private static final Logger persistenceTestLogger = LoggerFactory
      .getLogger(PersistenceTest.class);
  private static Map<String, Project> testProjectList = new HashMap<>();

  /**
   * Deletes files within projects directory in order to be able to work in a clean, standardized
   * environment.
   */
  @BeforeEach
  void cleanProjects() {
    File dir = new File(
        "projects");
    try {
      FileUtils.cleanDirectory(dir);
      persistenceTestLogger.info("Successfully cleaned project directory.");
    } catch (IOException e) {
      fail("Could not clean project directory. " + e.getMessage());
    }
  }

  @Test
  void idListTest() {
    persistenceTestLogger.info("Starting IDList creation test..." + '\n');
    ArrayList<String> idStringList = new ArrayList<String>();
    idStringList.add(new Hasher(0).getHash());
    idStringList.add(new Hasher(1).getHash());
    idStringList.add(new Hasher(2).getHash());

    IDList testIDList = new IDList(idStringList);
    assertEquals(idStringList, testIDList.getIdList());
    persistenceTestLogger
        .info("IDList creation test passed! Starting IDList serialization test..." + '\n');

    String comparatorListPath = "projects/idListArray.ser";
    String testObjectListPath = "projects/idListObject.ser";

    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(comparatorListPath));
      oos.writeObject(idStringList);
      oos.close();
    } catch (IOException e) {
      fail("Error serializing idStringList. " + e.getMessage());
    }

    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(testObjectListPath));
      oos.writeObject(testIDList);
      oos.close();
    } catch (IOException e) {
      fail("Error serializing testIDList. " + e.getMessage());
    }

    persistenceTestLogger.info("Both lists serialized. Attempting to deserialize...");

    try {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(comparatorListPath));
      idStringList = (ArrayList<String>) in.readObject();
      in.close();
    } catch (Exception e) {
      fail("Error deserializing idStringList. " + e.getMessage());
    }

    persistenceTestLogger.info("IDList array successfully deserialized.");

    try {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(testObjectListPath));
      testIDList = (IDList) in.readObject();
    } catch (Exception e) {
      fail("Error deserializing testIDList. " + e.getMessage());
    }

    persistenceTestLogger.info("IDList object successfully deserialized.");

    assertEquals(idStringList, testIDList.getIdList());
    persistenceTestLogger
        .info("IDLists match! " + '\n' + '\n' + "idListTest() was passed completely.");

  }

  @Test
  void ProjectSerializerTest() {
    testProjectList = ProjectSerializer.getProjects();
    assertTrue(testProjectList.isEmpty());
    persistenceTestLogger.info("isEmpty() test was passed! Starting LoadedProject test..." + '\n');

    String loadedProjectHash = new Hasher(0).getHash();
    Project testLoadedProject = new LoadedProject(loadedProjectHash);
    testProjectList.put((loadedProjectHash), testLoadedProject);
    ProjectSerializer.putProject(testLoadedProject);
    assertEquals(testProjectList, ProjectSerializer.getProjects());
    persistenceTestLogger.info("LoadedProject test passed! Starting ProjectProxy test..." + '\n');

    Project testProjectProxy = new ProjectProxy(loadedProjectHash);

  }
}