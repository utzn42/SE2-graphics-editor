package persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PersistenceTest {

  private static final Logger persistenceTestLogger = LoggerFactory
      .getLogger(PersistenceTest.class);

  @BeforeEach
  void cleanProjects() {
    File dir = new File(
        "C:\\Users\\utzn\\Desktop\\g2018w_se2_0304\\projects");           // how do I access this generally? Why does it work in persistence ("projects")?!
    try {                                                                                           // Might need new directory structure
      FileUtils.cleanDirectory(dir);
      persistenceTestLogger.info("Successfully cleaned project directory.");
    } catch (IOException e) {
      fail("Could not clean project directory. " + e.getMessage());
    }
  }

  @Test
  void ProjectSerializerTest() {
    ProjectSerializer.getProjects();
    assertTrue(true);
  }
}