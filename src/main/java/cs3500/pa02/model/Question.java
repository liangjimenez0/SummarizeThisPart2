package cs3500.pa02.model;

/**
 * Represents a Question class and is used for the hard
 * and easy values used throughout the study session
 */
public class Question {
  String question;
  String answer;
  boolean hard;
  boolean easy;

  /**
   * Used to construct an instance of a Question
   *
   * @param question A string that represents the question
   * @param answer   A string that represents the answer to that question
   */
  public Question(String question, String answer) {
    this.question = question;
    this.answer = answer;
    this.hard = true;
    this.easy = false;
  }

  /**
   * Gets the question of a specific Question
   *
   * @return A string that represents a question
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * Changes the easy value of a question to true
   * and the hard value to false
   */
  public void changeEasy() {
    this.hard = false;
    this.easy = true;
  }

  /**
   * Changes the easy value of a question to false
   * and the hard value to true
   */
  public void changeHard() {
    this.hard = true;
    this.easy = false;
  }

  /**
   * Gets the answer of a specific question
   *
   * @return A string that represents the answer
   */
  public String getAnswer() {
    return this.answer;
  }

  /**
   * Gets the hard value of a specific question
   *
   * @return A boolean that represents the hard value
   */
  public boolean getHard() {
    return this.hard;
  }

  /**
   * Gets the easy value of a specific question
   *
   * @return A boolean that represents the easy value
   */
  public boolean getEasy() {
    return this.easy;
  }

  /**
   * Determines whether a specific question is hard or easy
   *
   * @return A string that represents whether a question is hard or easy
   */
  public String determineLevel() {
    if (this.getHard()) {
      return "Hard";
    } else {
      return "Easy";
    }
  }

  /**
   * Changes the hard or easy value of a question based on
   * whether the user indicates the question is hard or easy
   *
   * @param nextMove A string that represents whether the user thinks the question is hard or easy
   */
  public void processAnswer(String nextMove) {
    if (nextMove.equalsIgnoreCase("easy")) {
      this.changeEasy();
    } else if (nextMove.equalsIgnoreCase("hard")) {
      this.changeHard();
    }
  }

  /**
   * Overrides the equals method for testing purposes
   *
   * @param other The object you are comparing a question to
   * @return The boolean value that determines if two questions are equal
   */
  public boolean equals(Object other) {
    if (!(other instanceof Question that)) {
      return false;
    }

    return this.question.equals(that.question)
        && this.answer.equals(that.answer)
        && this.hard == (that.hard)
        && this.easy == (that.easy);
  }

}
