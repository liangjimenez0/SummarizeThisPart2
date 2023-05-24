package cs3500.pa01;

import cs3500.pa01.sorter.FileSortByCreated;
import cs3500.pa01.sorter.FileSortByModified;
import cs3500.pa01.sorter.FileSortByName;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This is the main driver of the program and delegates
 * the arguments given from the Main class
 */
public class DriverStudyGuide {
  /**
   * Reads through a given file path and accumulates it into an array list,
   * sorts array list based on ordering flag, compiles array list into one string,
   * and outputs headers and important information into output path
   *
   * @param path         A relative or absolute path to a folder (directory) of markdown
   *                     files containing the notes you want to summarize
   * @param orderingFlag A flag to indicate how the summary document should be organized
   * @param outputPath   An output path and filename of where to write the
   *                     study guide your program generates
   * @throws IOException Throws when an I/O error occurs
   */
  public static void driver(Path path, OrderFlag orderingFlag, Path outputPath) throws IOException {

    FileTreeWalkerVisitor reader = new FileTreeWalkerVisitor();
    Files.walkFileTree(path, reader);

    if (orderingFlag.equals(OrderFlag.FILENAME)) {
      reader.getMdFiles().sort(new FileSortByName());
    } else if (orderingFlag.equals(OrderFlag.CREATED)) {
      reader.getMdFiles().sort(new FileSortByCreated());
    } else {
      reader.getMdFiles().sort(new FileSortByModified());
    }

    FileCompiler compiler = new FileCompiler();
    compiler.compile(reader.getMdFiles());

    FileProcessor processor = new FileProcessor();
    processor.processFiles(compiler.getCompiled(), outputPath);
  }
}
