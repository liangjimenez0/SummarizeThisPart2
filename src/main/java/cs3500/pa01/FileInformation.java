package cs3500.pa01;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * Represents the file information class that keeps track
 * of the file information for each file
 */
public class FileInformation {
  Path filePath;
  String fileName;
  FileTime dateModified;
  FileTime dateCreated;

  /**
   * Used to construct an instance of FileInformation
   *
   * @param filePath     The file path of a file
   * @param fileName     The file name of a file
   * @param dateModified The date a file was modified
   * @param dateCreated  The date a file was created
   */
  public FileInformation(Path filePath, String fileName, FileTime dateModified,
                         FileTime dateCreated) {
    this.filePath = filePath;
    this.fileName = fileName;
    this.dateModified = dateModified;
    this.dateCreated = dateCreated;
  }

  /**
   * Overrides the equals method for FileInformation
   *
   * @param other The other object you are comparing
   * @return Whether this and that object is equal
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof FileInformation that)) {
      return false;
    }

    return this.filePath.equals(that.filePath)
        && this.fileName.equals(that.fileName)
        && this.dateModified.equals(that.dateModified)
        && this.dateCreated.equals(that.dateCreated);

  }

  /**
   * Returns the file path of a file
   *
   * @return The path of the file
   */
  public Path getFilePath() {
    return filePath;
  }

  /**
   * Returns the file name of a file
   *
   * @return The file name of the file
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Returns the date modified of a file
   *
   * @return The date modified of the file
   */
  public FileTime getDateModified() {
    return dateModified;
  }

  /**
   * Returns the date created of a file
   *
   * @return The date created of the file
   */
  public FileTime getDateCreated() {
    return dateCreated;
  }

}
