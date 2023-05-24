package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests the FileTreeWalkerVisitor class
 */
class FileTreeWalkerVisitorTest {
  String sampleinputsdirectory = "src/test/samplefiles";

  /**
   * Tests that the tree walker class walks through the class
   * and adds the md files to an array list of file information
   */
  @Test
  void getMdFiles() {
    FileTreeWalkerVisitor mfv = new FileTreeWalkerVisitor();

    try {
      Files.walkFileTree(Path.of(sampleinputsdirectory), mfv);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // get list of traversed Markdown file paths
    ArrayList<FileInformation> actualFiles = mfv.getMdFiles();

    // compare both lists
    assertEquals(3, actualFiles.size());
    assertTrue(actualFiles.get(0).fileName.endsWith(".md"));
    assertTrue(actualFiles.get(1).fileName.endsWith(".md"));
    assertTrue(actualFiles.get(2).fileName.endsWith(".md"));
  }
}