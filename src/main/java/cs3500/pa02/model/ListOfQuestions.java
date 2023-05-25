package cs3500.pa02.model;

import java.util.ArrayList;

/**
 * Represents a ListOfQuestions that is responsible for
 * counting the stats throughout the study session
 */
public class ListOfQuestions {
  ArrayList<Question> questions;

  /**
   * Used to construct an instance of a ListOfQuestions
   *
   * @param questions An array list of questions
   */
  public ListOfQuestions(ArrayList<Question> questions) {
    this.questions = questions;
  }

  /**
   * Used to get the questions from a ListOfQuestions
   *
   * @return The questions of a list of questions
   */
  public ArrayList<Question> getQuestions() {
    return this.questions;
  }

  /**
   * Counts the number of hard questions in a ListOfQuestions
   *
   * @return The number of hard questions in a ListOfQuestions
   */
  public int countHardQuestions() {

    int totalHard = 0;

    for (Question q : this.questions) {
      if (q.getHard() && !q.getEasy()) {
        totalHard++;
      }
    }

    return totalHard;

  }

  /**
   * Counts the number of easy questions in a ListOfQuestions
   *
   * @return The number of easy questions in a ListOfQuestions
   */
  public int countEasyQuestions() {

    int totalEasy = 0;

    for (Question q : this.questions) {
      if (q.getEasy() && !q.getHard()) {
        totalEasy++;
      }
    }

    return totalEasy;
  }

  /**
   * Determines if a ListOfQuestions has any questions
   * with the difficulty level of hard
   *
   * @return A boolean value
   */
  public boolean hasNoHard() {

    boolean hasNoHard = true;

    for (Question q : this.questions) {
      if (q.getHard()) {
        hasNoHard = false;
        break;
      }
    }

    return hasNoHard;
  }
}
