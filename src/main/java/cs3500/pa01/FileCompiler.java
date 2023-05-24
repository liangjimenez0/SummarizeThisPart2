package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the FileCompiler class that is
 * responsible for compiling the file
 */
public class FileCompiler {
  StringBuilder builder = new StringBuilder();

  /**
   * Compiles a given array list into one String Builder
   *
   * @param mdFiles An ArrayList of FileInformation that needs to be compiled into one string
   */
  public void compile(ArrayList<FileInformation> mdFiles) {

    ArrayList<Path> pathList = new ArrayList<>();
    Scanner input = null;

    for (FileInformation p : mdFiles) {
      pathList.add(p.getFilePath());
    }

    for (Path p : pathList) {
      try {
        input = new Scanner(p);
      } catch (IOException e) {
        System.exit(1);
      }

      while (input.hasNext()) {
        builder.append(input.nextLine()).append(System.lineSeparator());
      }
    }
  }

  /**
   * Returns the compiled string of array list
   *
   * @return The compiled string
   */
  public String getCompiled() {
    return builder.toString();
  }
}
