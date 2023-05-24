package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.controller.Controller;
import cs3500.pa02.controller.ControllerImpl;
import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

/**
 * Tests the ControllerImp class and respective methods
 */
class ControllerImplTest {
  /**
   * Checks that the run method properly outputs things to the console
   * based on the given input
   *
   * @throws IOException Thrown when an error occurs during this method
   */
  @Test
  void run() throws IOException {
    String string =
        "Questions.sr"
            +
            System.lineSeparator()
            +
            "2"
            +
            System.lineSeparator()
            +
            "easy"
            +
            System.lineSeparator()
            +
            "hard";
    Readable input = new StringReader(string);
    Appendable output = new StringBuilder();
    Controller controller = new ControllerImpl(input, output);
    String welcomePage =
        "Choose a SR Question Bank File and Press Enter: \n";
    final String instructions = "Type easy, hard, or show answer and press enter\n";
    final String endPage = "You answered 2 questions.\n";

    assertEquals("", output.toString());
    controller.run();
    assertTrue(output.toString().contains(welcomePage));
    assertTrue(output.toString().contains(instructions));
    assertTrue(output.toString().contains(endPage));
  }
}