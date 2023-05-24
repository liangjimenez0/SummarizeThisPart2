package cs3500.pa01.sorter;

import cs3500.pa01.FileInformation;
import java.util.Comparator;

/**
 * Represents the FileSortByName class that is responsible
 * for sorting a list of files by the file name
 */
public class FileSortByName extends FileSort
    implements Comparator<FileInformation> {
  /**
   * Compares the file name of two objects
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return An integer based on the comparison of the two objects
   */
  @Override
  public int compare(FileInformation o1, FileInformation o2) {
    return o1.getFileName().compareTo(o2.getFileName());
  }
}
