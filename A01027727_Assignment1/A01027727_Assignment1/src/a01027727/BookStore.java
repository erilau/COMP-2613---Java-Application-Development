package a01027727;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a01027727.data.Book;
import a01027727.data.Customer;
import a01027727.data.Purchase;
import a01027727.data.util.ApplicationException;
import a01027727.data.util.GeneralComparer;
import a01027727.io.BookReader;
import a01027727.io.BookReport;
import a01027727.io.CustomerReader;
import a01027727.io.CustomerReport;
import a01027727.io.PurchaseReader;
import a01027727.io.PurchaseReport;

/**
 * Project: A01027727_Assignment1
 * File: BookStore.java
 * Date: Jun. 2, 2019
 * Time: 2:51:00 a.m.
 */

/**
 * @author Eric Lau, A01027727
 *
 */

public class BookStore {

  private static final Instant startTime = Instant.now();
  public static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
  public static final String DISPLAY_FORMAT = "%-10s %-15s %-15s %-10s\n";

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

  private static final Logger LOG = LogManager.getLogger(BookStore.class);

  /**
   * @param args
   */
  public static void main(String[] args) {

    LOG.info("Book Store Program Started!");
    LOG.info("Start Time: " + startTime);

    try {
      BookOptions.process(args);
      if (BookOptions.isHelpOptionSet()) {
        List<BookOptions.Value> values = new ArrayList<>(Arrays.asList(BookOptions.Value.values()));
        System.out.format(DISPLAY_FORMAT, "Option", "Long Option", "Has Value", "Description");
        for (BookOptions.Value value : values) {
          System.out.format(DISPLAY_FORMAT, value.getOption(), ("-" + value.getLongOption()), value.isHasArg(), value.getDescription());
        }

      }
      new BookStore().run();
    } catch (ApplicationException e) {
      LOG.error(e.getMessage());
    }

  }

  /**
   * This prints out duration and end time of the program
   */

  public static void printEndTimeAndDuration() {
    Instant endTime = Instant.now();
    LOG.info("End Time: " + endTime);
    LOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
  }

  /**
   * Populate the customers.
   */

  private void run() {
    try {

      Map<Long, Customer> customers = CustomerReader.readToMap();
      Map<Long, Book> books = BookReader.readToMap();
      Map<Long, Purchase> purchases = PurchaseReader.readToMap(BookOptions.getCustomerId());

      List<Customer> sortedCustomers = new ArrayList<>(customers.values());
      List<Book> sortedBooks = new ArrayList<>(books.values());
      List<Purchase> sortedPurchases = new ArrayList<>(purchases.values());

      LOG.info("Generated Data. Ready to Report and Sort");
      CustomerOptionSet(sortedCustomers);
      BooksOptionSet(sortedBooks);
      PurchasesOptionSet(sortedPurchases);

    } catch (ApplicationException e) {
      LOG.error(e.getMessage());
    } finally {
      LOG.info("Book Store Program Finished!");
      printEndTimeAndDuration();
    }
  }

  /**
   * This sorts the book report based on CMD input
   * 
   * @param sortedBooks
   */

  private void BooksOptionSet(List<Book> sortedBooks) {
    if (BookOptions.isBooksOptionSet()) {
      LOG.info("Generating Book Report");

      if (BookOptions.isByAuthorOptionSet()) {
        Collections.sort(sortedBooks, new GeneralComparer.BookByAuthor());
        LOG.info("Book Report Sorted by Author");
      }
      if (BookOptions.isDescendingOptionSet()) {
        Collections.sort(sortedBooks, Collections.reverseOrder(new GeneralComparer.BookByAuthor()));
        LOG.info("Book Report Sorted by Author in Desending Order");
      }
      BookReport.writeToFile(sortedBooks);
      LOG.info("Done!");
    }
  }

  /**
   * This sorts the purchase report based on CMD input
   * 
   * @param sortedPurchases
   * @throws ApplicationException
   */

  private void PurchasesOptionSet(List<Purchase> sortedPurchases) throws ApplicationException {
    if (BookOptions.isPurchasesOptionSet()) {
      LOG.info("Generating Purchase Report");
      if (BookOptions.isByTitleOptionSet()) {
        Collections.sort(sortedPurchases, new GeneralComparer.PurchasebyBookTitle());
        LOG.info("Purchase Report Sorting by Book Title");
        if (BookOptions.isDescendingOptionSet()) {
          Collections.sort(sortedPurchases, Collections.reverseOrder(new GeneralComparer.PurchasebyBookTitle()));
          LOG.info("Purchase Report Sorting by Book Title in Descending Order");
        }
      }

      if (BookOptions.isByLastnameOptionSet()) {
        Collections.sort(sortedPurchases, new GeneralComparer.PurchasebyLastName());
        LOG.info("Purchase Report Sorting by Last Name");
        if (BookOptions.isDescendingOptionSet()) {
          Collections.sort(sortedPurchases, Collections.reverseOrder(new GeneralComparer.PurchasebyLastName()));
          LOG.info("Purchase Report Sorting by Last Name in Descending Order");
        }
      }

      if (BookOptions.isTotalOptionSet()) {
        PurchaseReport.writeToFileWithTotal(sortedPurchases);
        LOG.info("Purchase Report with total");
        return;

      } else {
        PurchaseReport.writeToFile(sortedPurchases);
        LOG.info("Done!");
      }
    }

  }

  /**
   * This sorts the customer report based on CMD input
   * 
   * @param customers
   */

  private void CustomerOptionSet(List<Customer> customers) {
    if (BookOptions.isCustomersOptionSet()) {
      LOG.info("Generating Customer Report");
      if (BookOptions.isByJoinDateOptionSet()) {
        Collections.sort(customers, new GeneralComparer.CustomersByJoinDate());
        LOG.info("Customer Report Sorted by Joined Date");
      }
      if (BookOptions.isDescendingOptionSet()) {
        Collections.sort(customers, Collections.reverseOrder(new GeneralComparer.CustomersByJoinDate()));
        LOG.info("Customer Report Sorted by Joined Date in Desending Order");
      }
      CustomerReport.writeToFile(customers);
      LOG.info("Done!");
    }
  }
}
