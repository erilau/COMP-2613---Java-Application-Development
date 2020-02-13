/**
 * Project: A01027727_Assignment1
 * File: ApplicationException.java
 * Date: May 31, 2019
 * Time: 7:21:37 a.m.
 */
package a01027727.data.util;

/**
 * @author Eric Lau, A01027727
 *
 */
@SuppressWarnings("serial")
public class ApplicationException extends Exception {

  /**
   * This is a default constructor
   */
  public ApplicationException() {
  }

  /**
   * @param message
   * @param cause
   * @param enableSuppression
   * @param writableStackTrace
   */
  public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  /**
   * @param message
   * @param cause
   */
  public ApplicationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public ApplicationException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public ApplicationException(Throwable cause) {
    super(cause);
  }

}
