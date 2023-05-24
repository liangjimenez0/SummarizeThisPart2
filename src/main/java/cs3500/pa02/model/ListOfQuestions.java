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
   * Determines the number of questions that went from either hard to easy
   * or easy to hard during a study session
   *
   * @param startingCount The number of questions that were hard or easy at the start of the session
   * @param endingCount   The number of questions that were hard or easy at the end of the session
   * @return the number of questions that went from either hard to easy or
   *         easy to hard during a study session
   */
  public int hardOrEasyCount(int startingCount, int endingCount) {

    return Math.max(startingCount - endingCount, 0);
  }

  /**
   * Counts the number of hard questions in a ListOfQuestions
   *
   * @return The number of hard questions in a ListOfQuestions
   */
  public int countHardQuestions() {

    int totalHard = 0;

    for (Question q : this.questions) {
      if (q.getHard()) {
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
      if (q.getEasy()) {
        totalEasy++;
      }
    }

    return totalEasy;
  }

}
