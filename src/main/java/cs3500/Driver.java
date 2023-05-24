package cs3500;

import cs3500.pa01.DriverStudyGuide;
import cs3500.pa01.OrderFlag;
import cs3500.pa02.controller.Controller;
import cs3500.pa02.controller.ControllerImpl;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents the main class which takes in the arguments from the user
 */
public class Driver {
  /**
   * Takes in a users' arguments and converts them to File Paths and Ordering Flags.
   * Delegates these arguments to the Driver class.
   * If there are no arguments, a study session is started.
   *
   * @param args Arguments from the user that represent path, orderingFlag, and outputPath
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;
      Controller controller = new ControllerImpl(input, output);

      try {
        controller.run();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    } else if (args.length == 3) {
      Path path;

      try {
        path = Path.of(args[0]);
      } catch (InvalidPathException e) {
        throw new IllegalArgumentException();
      }

      OrderFlag orderingFlag;
      Path outputPath;

      switch (args[1]) {
        case "FILENAME" -> orderingFlag = OrderFlag.FILENAME;
        case "CREATED" -> orderingFlag = OrderFlag.CREATED;
        case "MODIFIED" -> orderingFlag = OrderFlag.MODIFIED;
        default -> throw new IllegalArgumentException();
      }

      try {
        outputPath = Path.of(args[2]);
      } catch (InvalidPathException e) {
        throw new IllegalArgumentException();
      }

      try {
        DriverStudyGuide.driver(path, orderingFlag, outputPath);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}