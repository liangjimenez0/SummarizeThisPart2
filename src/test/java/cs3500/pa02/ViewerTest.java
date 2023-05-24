package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.model.Question;
import cs3500.pa02.viewer.Viewer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Viewer class and respective methods
 */
class ViewerTest {

  Viewer viewer;
  Appendable output;

  /**
   * Sets up the variables before each test
   */
  @BeforeEach
  void setUp() {
    output = new StringBuilder();
    viewer = new Viewer(output);
  }

  /**
   * Checks that the showWelcome method outputs to the console
   *
   * @throws IOException Throws if an error occurs
   */
  @Test
  void showWelcome() throws IOException {
    viewer.showWelcome();
    assertEquals("Welcome!" + System.lineSeparator()
            +
            "Choose a SR Question Bank File and Press Enter: "
            +
            System.lineSeparator(),
        output.toString());
  }

  /**
   * Checks that the numberOfQuestions method outputs to the console
   *
   * @throws IOException Throws if an error occurs
   */
  @Test
  void numberOfQuestions() throws IOException {
    viewer.numberOfQuestions();

    assertEquals("How many questions would you like to answer today?"
            +
            System.lineSeparator()
            +
            "Press enter to start study session once indicated" + System.lineSeparator(),
        output.toString());
  }

  /**
   * Checks that the outputQuestion method outputs to the console
   *
   * @throws IOException Throws if an error occurs
   */
  @Test
  void outputQuestion() throws IOException {
    viewer.outputQuestion(new Question("What is my name?", "Liang"));

    assertEquals("What is my name?"
            +
            System.lineSeparator()
            +
            "Type easy, hard, or show answer and press enter"
            +
            System.lineSeparator(),
        output.toString());
  }

  /**
   * Checks that the outputAnswer method outputs to the console
   *
   * @throws IOException Throws if an error occurs
   */
  @Test
  void outputAnswer() throws IOException {
    viewer.outputAnswer(new Question("What is my name?", "Liang"));

    assertEquals("Liang"
        +
        System.lineSeparator()
        +
        "Type easy or hard and press enter"
        +
        System.lineSeparator(), output.toString());
  }

  /**
   * Checks that the showEnd method outputs to the console
   *
   * @throws IOException Throws if an error occurs
   */
  @Test
  void showEnd() throws IOException {
    viewer.showEnd(1, 2, 3, 4, 5);

    assertEquals(
        "No more questions left to answer!"
            +
            System.lineSeparator()
            +
            "You answered 1 questions."
            +
            System.lineSeparator()
            +
            "2 questions went from easy to hard."
            +
            System.lineSeparator()
            +
            "3 questions went from hard to easy."
            +
            System.lineSeparator()
            +
            "Current Counts in Question Bank: "
            +
            System.lineSeparator()
            +
            "4 Hard Questions"
            +
            System.lineSeparator()
            +
            "5 Easy Questions", output.toString());
  }
}