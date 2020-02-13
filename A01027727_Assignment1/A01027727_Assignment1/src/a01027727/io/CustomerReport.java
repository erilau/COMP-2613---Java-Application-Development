package a01027727.io;

/**
 * Project: A01027727_Assignment1
 * File: CustomerReport.java
 * Date: Jun. 1, 2019
 * Time: 2:41:01 p.m.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a01027727.data.Customer;
import a01027727.data.util.Common;

/**
 * Prints a formated Customers report.
 * 
 * @author Eric Lau, A01027727
 *
 */
public class CustomerReport {

  public static final String HORIZONTAL_LINE = "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
  public static final String HEADER_FORMAT = "%4s. %-6s %-12s %-12s %-40s %-25s %-12s %-15s %-40s%s     %7s%n";
  public static final String CUSTOMER_FORMAT = "%4d. %06d %-12s %-12s %-40s %-25s %-12s %-15s %-40s %-12s%7d%n";

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

  private static final Logger LOG = LogManager.getLogger(CustomerReport.class);

  /**
   * private constructor to prevent instantiation
   */
  private CustomerReport() {
  }

  /**
   * Write report to a file
   * 
   * @param customers
   */

  public static void writeToFile(Collection<Customer> customers) {
    File output = new File("customers_report.txt");
    try (PrintWriter out = new PrintWriter(new FileOutputStream(output))) {

      if (output.exists()) {
        output.createNewFile();
        LOG.debug("customers_report.txt has been created");
        out.println("Customers Report");
        out.println(HORIZONTAL_LINE);
        out.format(HEADER_FORMAT, "#", "ID", "First name", "Last name", "Street", "City", "Postal Code", "Phone", "Email", "Join Date", "Length");
        out.println(HORIZONTAL_LINE);
        LOG.debug("text file header has been created");
        int i = 0;
        for (Customer customer : customers) {
          LocalDate date = customer.getJoinedDate();
          LocalDate currentDate = LocalDate.now();
          int length = currentDate.getYear() - date.getYear();

          out.format(CUSTOMER_FORMAT, ++i, customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreet(),
                  customer.getCity(), customer.getPostalCode(), customer.getPhone(), customer.getEmailAddress(), Common.DATE_FORMAT.format(date),
                  length);

          LOG.debug(String.format(CUSTOMER_FORMAT, ++i, customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreet(),
                  customer.getCity(), customer.getPostalCode(), customer.getPhone(), customer.getEmailAddress(), Common.DATE_FORMAT.format(date),
                  length));
        }
        out.close();
      }
    } catch (IOException e) {
      LOG.error(e.getMessage());
    }

  }
}