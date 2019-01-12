package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import canvas.Canvas;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.ProjectSerializer;

class ProjectTest {

  private static final Logger projectTestLogger = LoggerFactory
      .getLogger(ProjectTest.class);
  Project projectTest;

  @AfterAll
  static void testSuccess() {
    projectTestLogger.info("All tests have been passed successfully!" + '\n');
  }

  @BeforeEach
  void cleanProjects() {
    ProjectSerializer.deleteProjects();
    projectTestLogger.info("Successfully reset projects.");
    File dir = new File(
        "projects");
    try {
      FileUtils.cleanDirectory(dir);
      projectTestLogger.info("Successfully cleaned project directory." + '\n');
    } catch (IOException e) {
      fail("cleanProjects(): Could not clean project directory. " + e.getMessage());
    }
  }

  @Test
  void loadedProjectTest() {
    projectTestLogger.info("Starting LoadedProject tests...");
    projectTest = new LoadedProject("testID");
    assertEquals("testID", projectTest.getProjectID());
    projectTestLogger.info("ID constructor successfully called.");

    Canvas testCanvas = new Canvas();
    projectTest = new LoadedProject("newTestID", testCanvas);
    assertEquals("newTestID", projectTest.getProjectID());
    projectTestLogger.info("getProjectID() returned correct projectID.");
    try {
      assertEquals(testCanvas, projectTest.getCanvas());
    } catch (Exception e) {
      fail("failed to retrieve projectTest's canvas: " + e.getMessage());
    }
    projectTestLogger.info("getCanvas() returned correct canvas.");
    projectTestLogger.info("ID + Canvas constructor successfully called.");
    projectTestLogger.info("LoadedProject has successfully passed all tests!" + '\n');
  }

  @Test
  void projectProxyTest() {
    projectTestLogger.info("Starting ProjectProxy tests...");
    projectTest = new ProjectProxy("testID");
    projectTestLogger.info("ProjectProxy constructor successfully called.");
    assertEquals("testID", projectTest.getProjectID());
    projectTestLogger.info("getProjectID returned correct projectID.");

    projectTestLogger.info("Loading existing projectID...");
    ProjectSerializer.putProject(new LoadedProject("testID"));
    Map<String, Project> testProjectList = ProjectSerializer.getProjects();
    Canvas test = null;
    try {
      test = projectTest.getCanvas();
    } catch (Exception e) {
      fail("Failed to assign canvas to test variable. " + e.getMessage());
    }

    try {
      assertEquals(test, projectTest.getCanvas());
    } catch (Exception e) {
      fail("Couldn't fetch canvas from projectTest variable.  " + e.getMessage());
    }
    projectTestLogger.info("Successfully loaded existing projectID.");

    projectTestLogger.info("Loading non-existent projectID...");
    projectTest = new ProjectProxy("non-existent testID");
    Throwable exception =
        assertThrows(IOException.class, () -> projectTest.getCanvas());
    assertEquals(
        "projects\\non-existent testID\\non-existent testID.ser (The system cannot find the path specified)",
        exception.getMessage());
    projectTestLogger.info("Successfully provoked exception while loading non-existing project.");
  }
}