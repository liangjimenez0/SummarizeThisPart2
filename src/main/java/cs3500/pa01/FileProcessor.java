package cs3500.pa01;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the FileProcessor class that is responsible
 * for processing a file into a study guide with just the headers
 * and important information
 */
public class FileProcessor {
  /**
   * Reads through a string and outputs a line if either it contains
   * a # or is surrounded in [[""]] without the double brackets and with a bullet point
   * to the given file path, representing the study guide.
   * If strings surrounded in double brackets contain ":::" they are placed
   * into an sr file which represents the question bank.
   *
   * @param compiledFile The compiled string of all the content from all the files
   * @param mdFile       The path of where the study guide should be located
   */
  public void processFiles(String compiledFile, Path mdFile) {
    File studyGuide = new File(mdFile.toString() + ".md");
    File questions = new File(mdFile.toString() + ".sr");

    try {
      Scanner input = new Scanner(compiledFile);
      BufferedWriter writer = new BufferedWriter(new FileWriter(studyGuide));
      BufferedWriter writerQuestions = new BufferedWriter(new FileWriter(questions));
      Pattern pattern = Pattern.compile("\\[\\[(.*?)]]");

      boolean firstLine = true;

      while (input.hasNextLine()) {
        String line = input.nextLine();
        Matcher matcher = pattern.matcher(line);

        if (line.startsWith("#")) {
          if (!firstLine) {
            writer.newLine();
          } else {
            firstLine = false;
          }
          writer.write(line + System.lineSeparator());
        } else if (matcher.find()) {
          if (matcher.group(1).contains(":::")) {
            writerQuestions.write(matcher.group(1) + System.lineSeparator());
            writerQuestions.write("Hard" + System.lineSeparator());
          } else {
            writer.write("- " + matcher.group(1) + System.lineSeparator());
          }
        }
      }

      input.close();
      writer.close();
      writerQuestions.close();

      if (questions.length() == 0) {
        boolean delete = questions.delete();
      }

    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
