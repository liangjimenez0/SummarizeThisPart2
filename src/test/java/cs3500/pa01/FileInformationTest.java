package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the methods in the FileInformation class
 */
class FileInformationTest {
  String sampleinputsdirectory;
  Path pathForArraysMd;
  FileTime arraysMdCreationTime;
  FileTime arraysMdModificationTime;
  FileInformation arraysFile;
  Path pathForVectorsMd;
  FileTime vectorsMdCreationTime;
  FileTime vectorsMdModificationTime;

  /**
   * Sets up the variables for testing
   */
  @BeforeEach
  void setup() {
    sampleinputsdirectory = "src/test/samplefiles";
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
  }

  /**
   * Tests the overriding equals method
   */
  @Test
  void equals() {
    assertNotEquals(arraysFile,
        new FileInformation(pathForArraysMd, "vectors.md", arraysMdModificationTime,
            arraysMdCreationTime));
    assertEquals(arraysFile,
        new FileInformation(pathForArraysMd, "arrays.md", arraysMdModificationTime,
            arraysMdCreationTime));
    assertNotEquals(arraysFile,
        new FileInformation(pathForVectorsMd, "arrays.md", arraysMdModificationTime,
            arraysMdCreationTime));
    assertNotEquals(arraysFile,
        new FileInformation(pathForArraysMd, "arrays.md", vectorsMdModificationTime,
            arraysMdCreationTime));
    assertNotEquals(arraysFile,
        new FileInformation(pathForArraysMd, "arrays.md", arraysMdModificationTime,
            vectorsMdCreationTime));
  }

  /**
   * Tests that getFilePath() returns the file path
   */
  @Test
  void getFilePath() {
    assertEquals(arraysFile.getFilePath(), pathForArraysMd);
  }

  /**
   * Tests that getFileName() returns the file name
   */
  @Test
  void getFileName() {
    assertEquals(arraysFile.getFileName(), "arrays.md");
  }

  /**
   * Tests that getDateModified() returns the date modified
   */
  @Test
  void getDateModified() {
    assertEquals(arraysFile.getDateModified(), arraysMdModificationTime);
  }

  /**
   * Tests that getDateCreated() returns the date created
   */
  @Test
  void getDateCreated() {
    assertEquals(arraysFile.getDateCreated(), arraysMdCreationTime);
  }
}