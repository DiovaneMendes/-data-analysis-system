package br.com.analysis.exception;

public class FactoryException extends Exception {

  public FactoryException(String message) {
    super("Could not generate the model: ".concat(message));
  }
}
