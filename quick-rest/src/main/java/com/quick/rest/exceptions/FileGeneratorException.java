package com.quick.rest.exceptions;

public class FileGeneratorException extends RuntimeException {

  /**
   * Serial id
   */
  private static final long serialVersionUID = -8659929650580070988L;

  private String code;

  public FileGeneratorException() {
    super();
  }

  public FileGeneratorException(String message) {
    super(message);
  }

  public FileGeneratorException(String message, String code) {
    super(message);
    this.code = code;
  }

  /**
   * Obtiene el código de error
   *
   * @return código de error
   */
  public String getCode() {
    return code;
  }
}
