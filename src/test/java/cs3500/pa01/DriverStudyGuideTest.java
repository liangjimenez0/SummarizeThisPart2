package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Used to test the methods in Driver
 */
class DriverStudyGuideTest {

  Path outputPath1;
  Path outputPath2;
  Path outputPath3;
  Path directory;

  /**
   * Sets up the variables used in testing
   */
  @BeforeEach
  void setup() {
    outputPath1 = Path.of("src/test/study-guide.md");
    outputPath2 = Path.of("src/test/study-guide2.md");
    outputPath3 = Path.of("src/test/study-guide3.md");
    directory = Path.of("src/test");
  }

  /**
   * Tests the driver method on a date created order flag
   */
  @Test
  void driverCreated() {
    try {
      DriverStudyGuide.driver(directory, OrderFlag.CREATED,
          outputPath1);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertTrue(Files.exists(outputPath1));
  }

  /**
   * Tests the driver method on a date modified order flag
   */
  @Test
  void driverModified() {

    try {
      DriverStudyGuide.driver(directory, OrderFlag.MODIFIED,
          outputPath2);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertTrue(Files.exists(outputPath2));
  }

  /**
   * Tests the driver method on a file name order flag
   */
  @Test
  void driverName() {
    try {
      DriverStudyGuide.driver(directory, OrderFlag.FILENAME,
          outputPath3);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertTrue(Files.exists(outputPath3));
  }
}
