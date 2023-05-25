package cs3500.pa02.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a whiteFile class that is reponsible for writing
 * a file
 */
public class WriteFile {

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
