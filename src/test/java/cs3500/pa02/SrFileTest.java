package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.controller.SrFile;
import cs3500.pa02.model.ListOfQuestions;
import cs3500.pa02.model.Question;
import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test the SrFile class and respective methods
 */
class SrFileTest {

  String samplePath;
  String invalidPath;
  SrFile file;
  ArrayList<Question> listOfQuestions;
  Question question1;
  Question question2;
  Question question3;
  Question question4;
  Question question5;
  Question question6;
  ListOfQuestions questions;
  ListOfQuestions loQuestions;

  /**
   * Sets up the variables before each test
   */
  @BeforeEach
  void setUp() {
    samplePath = "src/test/samplefiles/questions.sr";
    invalidPath = "src/test/invalid";
    file = new SrFile();

    question4 = new Question("What is the rarest blood type?", "AB-Negative");
    question5 = new Question("What sport does Cristiano Ronaldo play?", "Soccer");
    question6 = new Question("How many bones are there in the human body?", "206");
    question3 = new Question("Which country gifted the Statue of Liberty to the US?", "France");
    question2 = new Question("Which house was Harry Potter almost sorted into?", "Slytherin");
    question1 = new Question("Where is the Great Barrier Reef located?", "Australia");
    question1.changeEasy();

    listOfQuestions = new ArrayList<>();

    listOfQuestions.add(question1);
    listOfQuestions.add(question2);
    listOfQuestions.add(question3);
    listOfQuestions.add(question4);
    listOfQuestions.add(question5);
    listOfQuestions.add(question6);

    questions = new ListOfQuestions(listOfQuestions);

    loQuestions = new ListOfQuestions(file.processQuestions(samplePath));
  }

  /**
   * Tests that the processQuestions method creates an array list of questions
   * based on a file
   */
  @Test
  void processQuestions() {
    assertEquals(listOfQuestions, file.processQuestions(samplePath));
    assertEquals(6, file.processQuestions(samplePath).size());
    assertThrows(RuntimeException.class, () -> file.processQuestions("invalid"));
  }

  /**
   * Checks that the chooseQuestions method randomly selects an indicated
   * number of questions from a given list of questions
   */
  @Test
  void chooseQuestions() {
    assertEquals(2, file.chooseQuestions("2", loQuestions).size());
    assertEquals(6, file.chooseQuestions("10", loQuestions).size());
  }

  /**
   * Checks that the remainderQuestions outputs the questions not
   * randomly chosen
   */
  @Test
  void remainderQuestions() {
    ListOfQuestions loQuestions2 = new ListOfQuestions(file.processQuestions(samplePath));

    loQuestions2.getQuestions().remove(0);

    ArrayList<Question> newList = new ArrayList<>();
    newList.add(question1);

    assertEquals(newList, file.remainderQuestions(loQuestions2, loQuestions));
  }

  /**
   * Checks that the rewriteFile method rewrites a new question sr file
   * with updated levels and deletes the old file
   */
  @Test
  void rewriteFile() {

    file.rewriteFile(loQuestions, samplePath);

    File newFile = new File(samplePath);

    assertTrue(newFile.exists());
  }
}