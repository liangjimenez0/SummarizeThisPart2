package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.sorter.FileSortByCreated;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing the methods in FileSortByCreated class
 */
class FileSortByCreatedTest {
  private final String sampleinputsdirectory = "src/test/samplefiles";

  Path pathForArraysMd;
  FileTime arraysMdCreationTime;
  FileTime arraysMdModificationTime;
  FileInformation arraysFile;
  Path pathForVectorsMd;
  FileTime vectorsMdCreationTime;
  FileTime vectorsMdModificationTime;
  FileInformation vectorsFile;
  Path pathForFilesMd;
  FileTime fileMdCreationTime;
  FileTime fileMdModificationTime;
  FileInformation fileFile;
  ArrayList<FileInformation> files;

  /**
   * Sets up the variables for testing
   */
  @BeforeEach
  void setup() {
    pathForArraysMd = (Path.of(sampleinputsdirectory + "/arrays.md"));
    arraysMdCreationTime = FileTime.from(Instant.parse("2023-05-14T19:54:04Z"));
    arraysMdModificationTime =
        FileTime.from(Instant.parse("2023-05-14T19:54:33.112556888Z"));
    arraysFile =
        new FileInformation(pathForArraysMd, "arrays.md", arraysMdModificationTime,
            arraysMdCreationTime);

    pathForVectorsMd = (Path.of(sampleinputsdirectory + "/vectors.md"));
    vectorsMdCreationTime = FileTime.from(Instant.parse("2023-05-14T19:54:38Z"));
    vectorsMdModificationTime =
        FileTime.from(Instant.parse("2023-05-14T19:55:22.481421607Z"));
    vectorsFile =
        new FileInformation(pathForVectorsMd, "vectors.md", vectorsMdModificationTime,
            vectorsMdCreationTime);

    pathForFilesMd = (Path.of(sampleinputsdirectory + "/File/file.md"));
    fileMdCreationTime = FileTime.from(Instant.parse("2023-05-15T00:58:50Z"));
    fileMdModificationTime = FileTime.from(Instant.parse("2023-05-15T17:42:25.188519087Z"));
    fileFile = new FileInformation(pathForFilesMd, "file.md", fileMdModificationTime,
        fileMdCreationTime);

    files = new ArrayList<>();
    files.add(arraysFile);
    files.add(fileFile);
    files.add(vectorsFile);
  }

  /**
   * Tests that the method sort sorts files by the
   * date created
   */
  @Test
  void sort() {

    FileTreeWalkerVisitor mfv = new FileTreeWalkerVisitor();

    try {
      Files.walkFileTree(Path.of(sampleinputsdirectory), mfv);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    files.sort(new FileSortByCreated());

    assertEquals(3, files.size());
    assertEquals(files.get(0), arraysFile);
    assertEquals(files.get(1), vectorsFile);
    assertEquals(files.get(2), fileFile);
  }

  /**
   * Tests the method compare between date created
   */
  @Test
  void compare() {
    assertEquals(-1, new FileSortByCreated().compare(arraysFile, vectorsFile));
    assertEquals(0, new FileSortByCreated().compare(arraysFile,
        new FileInformation(pathForArraysMd, "arrays.md", arraysMdModificationTime,
            arraysMdCreationTime)));
    assertEquals(1, new FileSortByCreated().compare(fileFile, vectorsFile));
  }
}