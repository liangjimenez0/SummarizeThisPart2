package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;


/**
 * Represents a FileTreeWalkerVisitor class that implements FileVisitor
 * and walks through each file, adding it to a list if it is an .md file
 */
public class FileTreeWalkerVisitor implements FileVisitor<Path> {

  private final ArrayList<FileInformation> fileList = new ArrayList<>();

  /**
   * Adds a FileInformation to an array list if it is a md file
   *
   * @param file a reference to the file
   * @param attr the file's basic attributes
   * @return FileVisitResult
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
    if (attr.isRegularFile()) {
      if (file.getFileName().toString().endsWith(".md")) {
        fileList.add(
            new FileInformation(file, file.getFileName().toString(), attr.lastModifiedTime(),
                attr.creationTime()));
      }
    }
    return CONTINUE;
  }

  /**
   * Executes after a directory is completely visited
   *
   * @param dir  a reference to the directory
   * @param exec {@code null} if the iteration of the directory completes without
   *             an error; otherwise the I/O exception that caused the iteration
   *             of the directory to complete prematurely
   * @return FileVisitResult
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {
    System.out.format("Finishing Director: %s%n", dir);
    return CONTINUE;
  }

  /**
   * Executes before a directory is visited
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return FileVisitResult
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir,
                                           BasicFileAttributes attrs) {
    System.out.format("Starting Directory: %s%n", dir);
    return CONTINUE;
  }

  /**
   * Executes if the visitFile method fails
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return FileVisitResult
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    throwsError();
    return CONTINUE;
  }

  /**
   * Throws an error if the visit file fails
   */
  void throwsError() {
    System.err.println("Visit file failed");
  }

  /**
   * Returns the array list of file information with just md files
   * after all the files are visited
   *
   * @return The array list of file information with just md files
   */
  public ArrayList<FileInformation> getMdFiles() {
    return fileList;
  }
}

