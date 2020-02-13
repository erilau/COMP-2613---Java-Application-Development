package a01027727.io;
/**
 * Project: A01027727_Assignment1
 * File: PurchaseReport.java
 * Date: Jun. 2, 2019
 * Time: 2:51:00 a.m.
 */

/**
 * @author Eric Lau, A01027727
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a01027727.data.Purchase;
import a01027727.data.util.ApplicationException;

public class PurchaseReport {
  public static final String HORIZONTAL_LINE = "-------------------------------------------------------------------------------------------------------------------------";
  public static final String HEADER_FORMAT = "%-24s %-80s %2s%n";
  public static final String CUSTOMER_FORMAT = "%-24s %-80s $%.2f%n";

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

  private static final Logger LOG = LogManager.getLogger(PurchaseReport.class);

  /**
   * private constructor to prevent instantiation
   */
  private PurchaseReport() {
  }

  /**
   * Write report to a file
   * 
   * @param purchases
   * @throws ApplicationException
   */

  public static void writeToFile(Collection<Purchase> purchases) throws ApplicationException {
    File output = new File("purchases_report.txt");
    try (PrintWriter out = new PrintWriter(new FileOutputStream(output))) {

      if (output.exists()) {
        output.createNewFile();
        LOG.debug("purchase_report.txt has been created");
        out.println("Purchase Report");
        out.println(HORIZONTAL_LINE);
        out.format(HEADER_FORMAT, "Customer", "Book Title", "Price");
        out.println(HORIZONTAL_LINE);
        LOG.debug("text file header has been created");

        for (Purchase purchase : purchases) {

          out.format(CUSTOMER_FORMAT, purchase.getCustomerFirstName() + " " + purchase.getCustomerLastName(), purchase.getBookTitle(),
                  purchase.getPrice());
          LOG.debug(String.format(CUSTOMER_FORMAT, purchase.getCustomerFirstName() + " " + purchase.getCustomerLastName(), purchase.getBookTitle(),
                  purchase.getPrice()));
        }
        out.close();
      }
    } catch (IOException e) {
      LOG.error(e.getMessage());
    }

  }

  /**
   * This writes report to a file with the total value
   * 
   * @param purchases
   * @throws ApplicationException
   */

  public static void writeToFileWithTotal(Collection<Purchase> purchases) throws ApplicationException {
    File output = new File("purchases_report.txt");
    try (PrintWriter out = new PrintWriter(new FileOutputStream(output))) {

      if (output.exists()) {
        output.createNewFile();
        LOG.debug("purchases_report.txt has been created");
        out.println("Purchase Report");
        out.println(HORIZONTAL_LINE);
        out.format(HEADER_FORMAT, "Customer", "Book Title", "Price");
        out.println(HORIZONTAL_LINE);
        LOG.debug("text file header has been created");
        double total = 0;
        LOG.debug("Purchase Report with Total Value");
        for (Purchase purchase : purchases) {

          out.format(CUSTOMER_FORMAT, purchase.getCustomerFirstName() + " " + purchase.getCustomerLastName(), purchase.getBookTitle(),
                  purchase.getPrice());

          total += purchase.getPrice();

          LOG.debug(String.format(CUSTOMER_FORMAT, purchase.getCustomerFirstName() + " " + purchase.getCustomerLastName(), purchase.getBookTitle(),
                  purchase.getPrice()));
        }
        out.format("\nValue of Purchases: $%.2f", total);
        LOG.debug(String.format("\nValue of Purchases: $%.2f", total));
        out.close();
      }
    } catch (IOException e) {
      LOG.error(e.getMessage());
    }

  }
}
