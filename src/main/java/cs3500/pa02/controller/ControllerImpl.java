package cs3500.pa02.controller;

import cs3500.pa02.model.ListOfQuestions;
import cs3500.pa02.model.Question;
import cs3500.pa02.viewer.Viewer;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents the ControllerImpl class that is responsible for navigating
 * the user input in relation to the console output
 */
public class ControllerImpl implements Controller {

  private final Readable input;
  private final Appendable output;

  /**
   * Constructor to represent an instance of ControllerImpl used
   * for testing
   *
   * @param input  A readable input given by the user
   * @param output An appendable output that is appended to throughout the method
   */
  public ControllerImpl(Readable input, Appendable output) {
    this.input = Objects.requireNonNull(input);
    this.output = Objects.requireNonNull(output);
  }

  /**
   * Responsible for running the entire console application by
   * reading and processing the user's input
   */
  @Override
  public void run() throws IOException {
    Viewer viewer = new Viewer(output);
    viewer.showWelcome();

    Scanner scanner = new Scanner(input);
    String filePath = scanner.nextLine();
    SrFile file = new SrFile();
    ListOfQuestions allQuestions = new ListOfQuestions(file.processQuestions(filePath));
    int originalHardQuestions = allQuestions.countHardQuestions();
    int originalEasyQuestions = allQuestions.countEasyQuestions();

    viewer.numberOfQuestions();

    String numberOfQuestions = scanner.nextLine();
    ListOfQuestions randomQuestions =
        new ListOfQuestions(file.chooseQuestions(numberOfQuestions, allQuestions));

    for (Question q : randomQuestions.getQuestions()) {
      viewer.outputQuestion(q);
      String nextMove = scanner.nextLine();
      q.processAnswer(nextMove);

      if (nextMove.equalsIgnoreCase("show answer")) {
        viewer.outputAnswer(q);
        String easyOrHard = scanner.nextLine();
        q.processAnswer(easyOrHard);
      }
    }

    ListOfQuestions remainderQuestions =
        new ListOfQuestions(file.remainderQuestions(randomQuestions, allQuestions));
    int questionCount = randomQuestions.getQuestions().size();
    int newHardQuestions = randomQuestions.countHardQuestions()
        +
        remainderQuestions.countHardQuestions();
    int newEasyQuestions = randomQuestions.countEasyQuestions()
        +
        remainderQuestions.countEasyQuestions();
    int easyToHard = randomQuestions.hardOrEasyCount(originalEasyQuestions, newEasyQuestions);
    int hardToEasy = randomQuestions.hardOrEasyCount(originalHardQuestions, newHardQuestions);

    file.rewriteFile(allQuestions, filePath);
    viewer.showEnd(questionCount, easyToHard, hardToEasy, newHardQuestions, newEasyQuestions);
  }
}
