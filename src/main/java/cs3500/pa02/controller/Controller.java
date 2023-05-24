package cs3500.pa02.controller;

import java.io.IOException;

/**
 * Represents the interface Controller
 */
public interface Controller {

  /**
   * Responsible for running the entire console application by
   * reading and processing the user's input
   */
  void run() throws IOException;
}
