/**
 * Project: A01027727_Assignment1
 * File: GeneralComparer.java
 * Date: Jun. 2, 2019
 * Time: 2:03:31 p.m.
 */

package a01027727.data.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a01027727.data.Book;
import a01027727.data.Customer;
import a01027727.data.Purchase;

/**
 * @author Eric Lau, A01027727
 *
 */
public class GeneralComparer {

  public static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";

  private static void configureLogging() {

    ConfigurationSource source;

    try {
      source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
      Configurator.initialize(null, source);
    } catch (IOException e) {
      System.out.println(String.format("Can't find the log4j logging configuration file %s", LOG4J_CONFIG_FILENAME));
    }
  }

  static {
    configureLogging();
  }

  private static final Logger LOG = LogManager.getLogger(GeneralComparer.class);

  public static class CustomersByJoinDate implements Comparator<Customer> {

    @Override
    public int compare(Customer customer1, Customer customer2) {
      return customer1.getJoinedDate().compareTo(customer2.getJoinedDate());

    }

  }

  public static class BookByAuthor implements Comparator<Book> {

    @Override
    public int compare(Book book1, Book book2) {
      return book1.getAuthor().compareTo(book2.getAuthor());
    }

  }

  public static class PurchasebyLastName implements Comparator<Purchase> {

    @Override
    public int compare(Purchase purchase1, Purchase purchase2) {
      try {
        return purchase1.getCustomerLastName().compareTo(purchase2.getCustomerLastName());
      } catch (ApplicationException e) {

        LOG.error(e.getMessage());
      }
      return 0;
    }

  }

  public static class PurchasebyBookTitle implements Comparator<Purchase> {

    @Override
    public int compare(Purchase purchase1, Purchase purchase2) {
      try {
        return purchase1.getBookTitle().compareTo(purchase2.getBookTitle());
      } catch (ApplicationException e) {
        LOG.error(e.getMessage());
      }
      return 0;
    }

  }
}
