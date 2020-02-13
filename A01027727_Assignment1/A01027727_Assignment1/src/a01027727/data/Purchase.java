package a01027727.data;
/**
 * Project: A01027727_Assignment1
 * File: Purchases.java
 * Date: May 31, 2019
 * Time: 7:21:37 a.m.
 */

import java.util.Map;

import a01027727.data.util.ApplicationException;
import a01027727.io.BookReader;
import a01027727.io.CustomerReader;

/**
 * @author Eric Lau, A01027727
 *
 */
public class Purchase {
  public static final int ATTRIBUTE_COUNT = 4;
  private long id;
  private long customerId;
  private long bookId;
  private double price;

  public static class Builder {
    private final long id;
    private final long customerId;
    private final long bookId;

    private double price;

    public Builder(long id, long customerId, long bookId) {
      this.id = id;
      this.customerId = customerId;
      this.bookId = bookId;
    }

    /**
     * The price to set at a double
     * 
     * @param price
     * @return
     */
    public Builder setPrice(double price) {
      this.price = price;
      return this;
    }

    public Purchase build() {
      return new Purchase(this);
    }

  }

  /**
   * The default constructor
   * 
   * @param builder
   */

  private Purchase(Builder builder) {
    id = builder.id;
    customerId = builder.customerId;
    bookId = builder.bookId;
    price = builder.price;
  }

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return the customerId
   */
  public long getCustomerId() {
    return customerId;
  }

  /**
   * @param customerId
   *          the customerId to set
   */
  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  /**
   * @return the bookId
   */
  public long getBookId() {
    return bookId;
  }

  /**
   * @param bookId
   *          the bookId to set
   */
  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  /**
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /**
   * @param price
   *          the price to set
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * This gets the Customer's first name as a String
   * 
   * @return Customer First Name
   * @throws ApplicationException
   */

  public String getCustomerFirstName() throws ApplicationException {
    Map<Long, Customer> customers = CustomerReader.readToMap();
    for (Customer customer : customers.values()) {
      if (customer.getId() == customerId) {
        return customer.getFirstName();
      }
    }
    return null;
  }

  /**
   * This gets the Customer's Last Name as a String
   * 
   * @return Customers Last Name
   * @throws ApplicationException
   */

  public String getCustomerLastName() throws ApplicationException {
    Map<Long, Customer> customers = CustomerReader.readToMap();
    for (Customer customer : customers.values()) {
      if (customer.getId() == customerId) {
        return customer.getLastName();
      }
    }
    return null;
  }

  /**
   * This gets the Book title as a String
   * 
   * @return Book Title
   * @throws ApplicationException
   */

  public String getBookTitle() throws ApplicationException {
    Map<Long, Book> books = BookReader.readToMap();
    for (Book book : books.values()) {
      if (book.getId() == bookId) {
        return book.getOriginalTitle();
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return "Purchase [id=" + id + ", customerId=" + customerId + ", bookId=" + bookId + ", price=" + price + "]";
  }

}
