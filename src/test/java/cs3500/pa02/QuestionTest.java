package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Question class and respective methods
 */
class QuestionTest {

  Question question1;
  Question question2;
  Question question3;

  /**
   * Sets up the variables used in testing
   */
  @BeforeEach
  void setup() {
    question1 = new Question("What is the rarest blood type?", "AB-Negative");
    question2 = new Question("What sport does Cristiano Ronaldo play?", "Soccer");
    question3 = new Question("How many bones are there in the human body?", "206");
  }

  /**
   * Checks that the getQuestion method gets the question from a Question
   */
  @Test
  void getQuestion() {
    assertEquals("What is the rarest blood type?", question1.getQuestion());
    assertEquals("What sport does Cristiano Ronaldo play?", question2.getQuestion());
    assertEquals("How many bones are there in the human body?", question3.getQuestion());
  }

  /**
   * Checks that the changeEasy method changes the easy value
   * of a Question
   */
  @Test
  void changeEasy() {
    question1.changeEasy();
    question2.changeEasy();

    assertTrue(question1.getEasy());
    assertTrue(question2.getEasy());
    assertFalse(question3.getEasy());
  }

  /**
   * Checks that the changeHard method changes the hard value
   * of a Question
   */
  @Test
  void changeHard() {
    question1.changeHard();
    question2.changeHard();
    question3.changeEasy();

    assertTrue(question1.getHard());
    assertTrue(question2.getHard());
    assertFalse(question3.getHard());
  }

  /**
   * Checks that the getAnswer method gets the answer from a Question
   */
  @Test
  void getAnswer() {
    assertEquals("AB-Negative", question1.getAnswer());
    assertEquals("Soccer", question2.getAnswer());
    assertEquals("206", question3.getAnswer());
  }

  /**
   * Checks that the getHard method gets the hard value from the Question
   */
  @Test
  void getHard() {
    question3.changeEasy();

    assertTrue(question1.getHard());
    assertTrue(question2.getHard());
    assertFalse(question3.getHard());
  }

  /**
   * Checks that the getEasy method gets the easy value from the Question
   */
  @Test
  void getEasy() {
    question3.changeEasy();

    assertFalse(question1.getEasy());
    assertFalse(question2.getEasy());
    assertTrue(question3.getEasy());
  }

  /**
   * Checks that the determineLevel method converts the level
   * of a question into a string
   */
  @Test
  void determineLevel() {
    question3.changeEasy();

    assertEquals("Hard", question1.determineLevel());
    assertEquals("Hard", question2.determineLevel());
    assertEquals("Easy", question3.determineLevel());
  }

  /**
   * Checks that the processAnswer method changes the hard or easy
   * value based on a string
   */
  @Test
  void processAnswer() {

    question1.processAnswer("Easy");
    question2.changeEasy();
    question2.processAnswer("Hard");
    question3.processAnswer("Invalid");

    assertFalse(question1.getHard());
    assertTrue(question1.getEasy());
    assertTrue(question2.getHard());
    assertFalse(question2.getEasy());
    assertTrue(question3.getHard());
    assertFalse(question3.getEasy());
  }

  /**
   * Checks that the equals method overrides equals
   */
  @Test
  void equals() {
    assertEquals(question1, new Question("What is the rarest blood type?", "AB-Negative"));
    assertNotEquals(question2, new Question("What is the rarest blood type?", "AB-Negative"));
    assertNotEquals(question1, new Question("Invalid", "AB-Negative"));
    assertNotEquals(question1, new Question("What is the rarest blood type?", "Invalid"));

    question1.changeEasy();
    assertNotEquals(question1, new Question("What is the rarest blood type?", "AB-Negative"));

    assertFalse(question1.equals("Invalid"));
  }
}