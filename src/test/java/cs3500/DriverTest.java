package cs3500;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;


/**
 * Tests the methods in the main class
 */
class DriverTest {
  /**
   * Tests the main method for a created order flag
   *
   * @throws IOException Throws when an I/O error occurs
   */
  @Test
  void setUpCreated() throws IOException {
    String[] args1 = {"src/test", "CREATED", "src/test/study-guide"};

    Driver.main(args1);
    assertTrue(Files.exists(Path.of("src/test/study-guide.md")));
  }

  /**
   * Tests the main method for a modified order flag
   *
   * @throws IOException Throws when an I/O error occurs
   */
  @Test
  void setUpModified() throws IOException {
    String[] args2 = {"src/test", "MODIFIED", "src/test/study-guide2"};

    Driver.main(args2);
    assertTrue(Files.exists(Path.of("src/test/study-guide2.md")));
  }

  /**
   * Tests the main method for a file name order flag
   *
   * @throws IOException Throws when an I/O error occurs
   */
  @Test
  void setUpName() throws IOException {
    String[] args3 = {"src/test", "FILENAME", "src/test/study-guide3"};

    Driver.main(args3);
    assertTrue(Files.exists(Path.of("src/test/study-guide3.md")));
  }

  /**
   * Tests the main method when it does not properly set up
   */
  @Test
  void error() {
    String[] invalidArgs = {"/#bsdajk", "INVALID_FLAG", "/#bsdajk"};
    assertThrows(IllegalArgumentException.class, () -> Driver.main(invalidArgs));
  }

  /**
   * Tests the main method when there are no arguments given
   */
  void noArguments() {
    Driver.main(new String[0]);

    assertTrue(Files.exists(Path.of("src/test/java.sr")));
  }
}