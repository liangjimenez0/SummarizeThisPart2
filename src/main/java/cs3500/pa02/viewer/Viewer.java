package cs3500.pa02.viewer;

import cs3500.pa02.model.Question;
import java.io.IOException;

/**
 * Represents the Viewer class which is responsible for outputting
 * strings to the console
 */
public class Viewer {
  private final Appendable output;

  public Viewer(Appendable output) {
    this.output = output;
  }

  /**
   * Outputs the starting welcome page to the console
   */
  public void showWelcome() throws IOException {
    output.append("Welcome!");
    output.append("\n");
    output.append("Choose a SR Question Bank File and Press Enter: ");
    output.append("\n");
  }

  /**
   * Asks the user how many questions they would like to answer in the console
   */
  public void numberOfQuestions() throws IOException {
    output.append("How many questions would you like to answer today?");
    output.append("\n");
    output.append("Press enter to start study session once indicated");
    output.append("\n");
  }

  /**
   * Outputs a given question into the console
   *
   * @param question A random question from a set of questions
   */
  public void outputQuestion(Question question) throws IOException {
    output.append(question.getQuestion());
    output.append("\n");
    output.append("Type easy, hard, or show answer and press enter");
    output.append("\n");
  }

  /**
   * Outputs an answer to a given question into the console
   *
   * @param question A random question from a set of questions
   */
  public void outputAnswer(Question question) throws IOException {
    output.append(question.getAnswer());
    output.append("\n");
    output.append("Type easy or hard and press enter");
    output.append("\n");
  }

  /**
   * Outputs the final page to the user in the console representing
   * the statistics from the study sesion
   *
   * @param totalQuestions The total number of questions answered for that session
   * @param easyToHard     The total number of questions that changed from easy to hard
   * @param hardToEasy     The total number of questions that changed from hard to easy
   * @param hardQuestions  The updated total number of hard questions in the question bank
   * @param easyQuestions  The updated total number of easy questions in the question bank
   */
  public void showEnd(int totalQuestions, int easyToHard, int hardToEasy, int hardQuestions,
                      int easyQuestions) throws IOException {
    output.append("No more questions left to answer!");
    output.append("\n");
    output.append("You answered ").append(String.valueOf(totalQuestions)).append(" questions.");
    output.append("\n");
    output.append(String.valueOf(easyToHard)).append(" questions went from easy to hard.");
    output.append("\n");
    output.append(String.valueOf(hardToEasy)).append(" questions went from hard to easy.");
    output.append("\n");
    output.append("Current Counts in Question Bank: ");
    output.append("\n");
    output.append(String.valueOf(hardQuestions)).append(" Hard Questions");
    output.append("\n");
    output.append(String.valueOf(easyQuestions)).append(" Easy Questions");
  }

  /**
   * Outputs a message to the console if the input in invalid
   *
   * @throws IOException Throws if an error occurs
   */
  public void incorrectOutput() throws IOException {
    output.append("You made a typo.").append(System.lineSeparator())
        .append("Please type hard, easy or show answer and press enter to continue.")
        .append(System.lineSeparator());
  }
}
