package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import observer.Observer;
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
    ProjectSerializer.deleteProjects();
    persistenceTestLogger.info("Successfully reset projects");
    File dir = new File(
        "projects");
    try {
      FileUtils.cleanDirectory(dir);
      persistenceTestLogger.info("Successfully cleaned project directory." + '\n');
    } catch (IOException e) {
      fail("cleanProjects(): Could not clean project directory. " + e.getMessage());
    }
  }

  @Test
  void idListTest() {
    persistenceTestLogger.info("idListTest() started.");
    persistenceTestLogger.info("Starting IDList creation test...");
    ArrayList<String> idStringList = new ArrayList<String>();
    idStringList.add(new Hasher(0).getHash());
    idStringList.add(new Hasher(1).getHash());
    idStringList.add(new Hasher(2).getHash());

    IDList testIDList = new IDList(idStringList);
    assertEquals(idStringList, testIDList.getIdList());
    persistenceTestLogger
        .info("IDList creation test passed! Starting IDList setting test...");

    testIDList.setIdList(idStringList);
    assertEquals(idStringList, testIDList.getIdList());
    persistenceTestLogger.info("IDList setting test passed! Starting IDList serialization test.");

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
      in.close();
    } catch (Exception e) {
      fail("Error deserializing testIDList. " + e.getMessage());
    }

    persistenceTestLogger.info("IDList object successfully deserialized.");

    assertEquals(idStringList, testIDList.getIdList());
    persistenceTestLogger
        .info("IDLists match! " + '\n' + '\n' + "idListTest() was passed successfully.");

  }

  @Test
  void projectObserverTest() {
    persistenceTestLogger.info("projectObserverTest() started.");
    String loadedProjectHash = new Hasher(0).getHash();
    Project loadedProjectTest = new LoadedProject(loadedProjectHash);
    Observer projectObserverTest = new ProjectObserver();
    projectObserverTest.update(loadedProjectTest);
    assertEquals(loadedProjectTest, ProjectSerializer.getProjects().get(loadedProjectHash));
    persistenceTestLogger
        .info("ProjectObserver successfully updated ProjectSerializer with LoadedProject object.");


  }

  @Test
  void projectSerializerTest() {
    persistenceTestLogger.info("projectSerializerTest() started.");
    testProjectList = ProjectSerializer.getProjects();
    assertTrue(testProjectList.isEmpty());
    persistenceTestLogger.info("isEmpty test was passed! Starting SeedIncrement test..." + '\n');

    try {
      assertEquals(0, ProjectSerializer.getAndIncrementSeed());
    } catch (IOException e) {
      fail("Failed to pass first seed iteration. " + e.getMessage());
    }
    try {
      assertEquals(1, ProjectSerializer.getAndIncrementSeed());
    } catch (IOException e) {
      fail("Failed to pass second seed iteration. " + e.getMessage());
    }
    persistenceTestLogger
        .info("SeedIncrement test was passed! Starting LoadedProject test..." + '\n');

    String loadedProjectHash = new Hasher(0).getHash();
    Project testLoadedProject = new LoadedProject(loadedProjectHash);
    testProjectList.put((loadedProjectHash), testLoadedProject);
    ProjectSerializer.putProject(testLoadedProject);
    Map<String, Project> testProjectList2 = new HashMap<>();
    testProjectList2 = ProjectSerializer.getProjects();

    assertEquals(testProjectList, testProjectList2);
    try {
      assertEquals(testProjectList.get(loadedProjectHash),
          testProjectList2.get(loadedProjectHash));
    } catch (Exception e) {
      fail("Error in ProjectSerializer.getProject(String projectID): " + e.getMessage());
    }
    persistenceTestLogger.info("LoadedProject test passed! Starting ProjectProxy test..." + '\n');

    //finish ProjectProxy test
    Project testProjectProxy = new ProjectProxy(loadedProjectHash);
    persistenceTestLogger.info("ProjectProxy test passed! ");
  }

  @Test
  void serializerTest() {
    persistenceTestLogger.info("serializerTest() started.");
    Throwable exception =
        assertThrows(IllegalArgumentException.class, () -> Serializer.serialize(null, null));
    assertEquals("Name of file to which to serialize object to cannot be null.",
        exception.getMessage());

    exception =
        assertThrows(IllegalArgumentException.class,
            () -> Serializer.serialize(null, "projects/test.txt"));
    assertEquals("Object to be serialized cannot be null.", exception.getMessage());

    String testString = "I am a test object, which is abut to be (de-)serialized.";
    try {
      Serializer.serialize(testString, "projects/testString.txt");
    } catch (IOException e) {
      fail("Failed to serialize test string. " + e.getMessage());
    }

    persistenceTestLogger.info("Object successfully serialized!");

    exception =
        assertThrows(IllegalArgumentException.class,
            () -> Serializer.deserialize(null, null));
    assertEquals("Cannot deserialize from a null filename.", exception.getMessage());

    exception =
        assertThrows(IllegalArgumentException.class,
            () -> Serializer.deserialize("projects/test.txt", null));
    assertEquals("Type of class to be deserialized cannot be null.", exception.getMessage());

    exception =
        assertThrows(ClassCastException.class,
            () -> Serializer.deserialize("projects/testString.txt", Integer.class));
    assertEquals("java.lang.String cannot be cast to java.lang.Integer", exception.getMessage());

    String deserializedTestString = "";

    try {
      deserializedTestString = Serializer
          .deserialize("projects/testString.txt", String.class);
    } catch (Exception e) {
      fail("Failed to deserialize test string. " + e.getMessage());
    }

    persistenceTestLogger.info("Object successfully deserialized!");
    persistenceTestLogger.info("Serializer successfully passed test.");

    assertEquals(testString, deserializedTestString);
  }
}