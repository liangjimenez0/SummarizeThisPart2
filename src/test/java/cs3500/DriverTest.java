package cs3500;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;


/**
 * Tests the methods in the main class
 */
class DriverTest {
  /**
   * Tests the main method for a created order flag
   */
  @Test
  void setUpCreated() {
    String[] args1 = {"src/test", "CREATED", "src/test/study-guide"};

    Driver.main(args1);
    assertTrue(Files.exists(Path.of("src/test/study-guide.md")));
  }

  /**
   * Tests the main method for a modified order flag
   */
  @Test
  void setUpModified() {
    String[] args2 = {"src/test", "MODIFIED", "src/test/study-guide2"};

    Driver.main(args2);
    assertTrue(Files.exists(Path.of("src/test/study-guide2.md")));
  }

  /**
   * Tests the main method for a file name order flag
   */
  @Test
  void setUpName() {
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
  @Test
  void noArguments() {

    String input =
        "Questions.sr"
            +
            System.lineSeparator()
            +
            "4"
            +
            System.lineSeparator()
            +
            "easy"
            +
            System.lineSeparator()
            +
            "hard"
            +
            System.lineSeparator()
            +
            "show answer"
            +
            System.lineSeparator()
            +
            "easy"
            +
            "invalid"
            +
            System.lineSeparator()
            +
            "easy";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    String welcomePage =
        "Choose a SR Question Bank File and Press Enter: \n";
    final String instructions = "Type easy, hard, or show answer and press enter\n";
    final String endPage = "You answered 4 questions.\n";

    Driver.main(new String[0]);

    assertTrue(Files.exists(Path.of("Questions.sr")));
    assertTrue(output.toString().contains(welcomePage));
    assertTrue(output.toString().contains(instructions));
    assertTrue(output.toString().contains(endPage));
  }
}