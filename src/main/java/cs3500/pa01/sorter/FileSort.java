package cs3500.pa01.sorter;

import cs3500.pa01.FileInformation;

/**
 * Represents an abstract class for each type of file sort
 */
public abstract class FileSort {

  /**
   * Compare method used in each of the file sortings
   *
   * @return Integer based on comparison
   */
  abstract int compare(FileInformation o1, FileInformation o2);
}
