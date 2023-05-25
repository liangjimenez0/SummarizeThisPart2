package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.model.ListOfQuestions;
import cs3500.pa02.model.Question;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the ListOfQuestions class and respective methods
 */
class ListOfQuestionsTest {

  ArrayList<Question> listOfQuestions;
  Question question1;
  Question question2;
  Question question3;
  ListOfQuestions questions;

  /**
   * Sets up the variables used in testing
   */
  @BeforeEach
  void setUp() {
    question1 = new Question("What is the rarest blood type?", "AB-Negative");
    question2 = new Question("What sport does Cristiano Ronaldo play?", "Soccer");
    question3 = new Question("How many bones are there in the human body?", "206");
    listOfQuestions = new ArrayList<>();

    listOfQuestions.add(question1);
    listOfQuestions.add(question2);
    listOfQuestions.add(question3);

    questions = new ListOfQuestions(listOfQuestions);
  }

  /**
   * Checks that the getQuestions method gets the questions of
   * a list of questions
   */
  @Test
  void getQuestions() {
    assertEquals(listOfQuestions, questions.getQuestions());
  }

  /**
   * Tests that the countHardQuestions method counts the hard questions of a list of questions
   */
  @Test
  void countHardQuestions() {
    assertEquals(3, questions.countHardQuestions());

    question1.changeEasy();

    assertEquals(2, questions.countHardQuestions());
  }

  /**
   * Tests that the countEasyQuestions method counts the easy questions of a list of questions
   */
  @Test
  void countEasyQuestions() {
    assertEquals(0, questions.countEasyQuestions());

    question1.changeEasy();

    assertEquals(1, questions.countEasyQuestions());
  }

  /**
   * Tests that the hasNoHard method changes its boolean value
   * based on whether there are any hard questions in a list of questions
   */
  @Test
  void hasNoHard() {
    assertFalse(questions.hasNoHard());

    questions.getQuestions().get(0).changeEasy();
    questions.getQuestions().get(1).changeEasy();
    questions.getQuestions().get(2).changeEasy();

    assertTrue(questions.hasNoHard());
  }
}