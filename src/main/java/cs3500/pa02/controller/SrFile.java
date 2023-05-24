package cs3500.pa02.controller;

import cs3500.pa02.model.ListOfQuestions;
import cs3500.pa02.model.Question;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a SrFile class that is responsible for working with
 * the user input in relation to reading and writing the files
 */
public class SrFile {

  /**
   * Creates an array list of questions based on reading
   * through a file
   *
   * @param srPath A string that represents a file path
   * @return An array list of Questions
   */
  public ArrayList<Question> processQuestions(String srPath) {
    File questionBank = new File(srPath);
    ArrayList<Question> allQuestions = new ArrayList<>();

    try {
      Scanner input = new Scanner(questionBank);

      while (input.hasNextLine()) {
        String line = input.nextLine();
        String line2 = input.nextLine();

        Question question = null;

        if (line.contains(":::")) {
          String[] split = line.split(":::");
          question = new Question(split[0], split[1]);

          if (line2.contains("Hard")) {
            question.changeHard();
          } else {
            question.changeEasy();
          }
        }

        allQuestions.add(question);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    return allQuestions;
  }

  /**
   * Generates an random array list of questions from a given list of questions
   * that is the same length as the given amount
   *
   * @param amount       The length of the array list
   * @param allQuestions A list of questions that represents all the possible questions
   * @return A random list of questions from the entire list
   */
  public ArrayList<Question> chooseQuestions(String amount, ListOfQuestions allQuestions) {

    Random rand = new Random();
    int size = Integer.parseInt(amount);
    ArrayList<Question> randomQuestion = new ArrayList<>();
    ArrayList<Question> allQuestionsCopy = new ArrayList<>(allQuestions.getQuestions());

    if (size > allQuestions.getQuestions().size()) {
      randomQuestion.addAll(allQuestions.getQuestions());
    } else {
      for (int i = 0; i < size; i++) {
        int index = rand.nextInt(allQuestionsCopy.size() - 1);
        Question currentQuestion = allQuestionsCopy.get(index);

        if (currentQuestion.getHard()) {
          randomQuestion.add(currentQuestion);
          allQuestionsCopy.remove(index);
        }
      }
    }

    while (randomQuestion.size() < size && !(size > allQuestions.getQuestions().size())) {
      int index = rand.nextInt(allQuestionsCopy.size() - 1);
      randomQuestion.add(allQuestionsCopy.get(index));
      allQuestionsCopy.remove(index);
    }

    return randomQuestion;
  }

  /**
   * Returns an array list of questions that were not randomly selected
   * by the chooseQuestions method
   *
   * @param randomQuestions The list of random questions
   * @param allQuestions    The list of all questions
   * @return An array list of questions that were not in the random questions but in all
   */
  public ArrayList<Question> remainderQuestions(ListOfQuestions randomQuestions,
                                                ListOfQuestions allQuestions) {

    ArrayList<Question> remainders = new ArrayList<>();

    for (Question q : allQuestions.getQuestions()) {
      if (!randomQuestions.getQuestions().contains(q)) {
        remainders.add(q);
      }
    }

    return remainders;
  }

  /**
   * Rewrites the question file to include the new hard and easy values
   * and deletes the old file
   *
   * @param allQuestions A list of all the questions
   * @param srPath       The string of the file path of the original file
   */
  public void rewriteFile(ListOfQuestions allQuestions, String srPath) {
    try {
      StringBuilder builder = new StringBuilder();

      for (Question q : allQuestions.getQuestions()) {
        builder.append(q.getQuestion()).append(":::").append(q.getAnswer())
            .append(System.lineSeparator()).append(q.determineLevel())
            .append(System.lineSeparator());
      }

      File placeHolder = new File("placeHolder.sr");
      File oldFile = new File(srPath);
      Scanner input = new Scanner(builder.toString());
      BufferedWriter writer = new BufferedWriter(new FileWriter(placeHolder));

      while (input.hasNextLine()) {
        String line = input.nextLine();

        writer.write(line + System.lineSeparator());
      }

      writer.close();
      boolean deleted = oldFile.delete();
      boolean renamed = placeHolder.renameTo(oldFile);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
