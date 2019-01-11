package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import facilitators.Hasher;
import java.io.File;
import java.io.IOException;
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
        "C:\\Users\\utzn\\Desktop\\g2018w_se2_0304\\projects");                           // how do I access this generally? Why does it work in persistence ("projects")?!
    try {                                                                                           // Might need new directory structure
      FileUtils.cleanDirectory(dir);
      persistenceTestLogger.info("Successfully cleaned project directory.");
    } catch (IOException e) {
      fail("Could not clean project directory. " + e.getMessage());
    }
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