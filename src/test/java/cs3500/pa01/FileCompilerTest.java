package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Used to test the methods in FileCompiler
 */
class FileCompilerTest {

  FileTreeWalkerVisitor mfv;
  String sampleinputsdirectory;
  ArrayList<FileInformation> actualFiles;
  FileCompiler compiler;

  /**
   * Sets up the variables used in testing
   */
  @BeforeEach
  void setup() {
    mfv = new FileTreeWalkerVisitor();

    try {
      sampleinputsdirectory = "src/test/samplefiles";
      Files.walkFileTree(Path.of(sampleinputsdirectory), mfv);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    actualFiles = mfv.getMdFiles();
    compiler = new FileCompiler();
  }

  /**
   * Checking the compile method compiles all of the file informations
   * in an array list are compiled into one string
   */
  @Test
  void compile() {
    assertEquals(0, compiler.getCompiled().length());

    compiler.compile(actualFiles);

    assertEquals(1462, compiler.getCompiled().length());
  }
}