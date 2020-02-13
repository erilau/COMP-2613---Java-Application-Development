package a01027727.io;

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

import a01027727.data.Book;

/**
 * Project: A01027727_Assignment1
 * File: BookReport.java
 * Date: Jun. 2, 2019
 * Time: 2:15:49 a.m.
 */

/**
 * @author Eric Lau, A01027727
 *
 */
public class BookReport {
  public static final String HORIZONTAL_LINE = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
  public static final String HEADER_FORMAT = "%8s %-12s %-40s %-40s %4s %6s %13s %-60s%n";
  public static final String CUSTOMER_FORMAT = "%08d %-12s %-40s %-40s %4d %6.3f %13d %-60s%n";

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

  private static final Logger LOG = LogManager.getLogger(BookReport.class);

  /**
   * private constructor to prevent instantiation
   */
  private BookReport() {
  }

  /**
   * Write report to a file
   * 
   * @param books
   */

  public static void writeToFile(Collection<Book> books) {
    File output = new File("book_report.txt");
    try (PrintWriter out = new PrintWriter(new FileOutputStream(output))) {

      if (output.exists()) {
        output.createNewFile();
        LOG.debug("book_report.txt has been created");
        out.println("Book Report");
        out.println(HORIZONTAL_LINE);
        out.format(HEADER_FORMAT, "ID", "ISBN", "Authors", "Title", "Year", "Ratings", "Ratings Count", "Image URL");
        out.println(HORIZONTAL_LINE);
        LOG.debug("text file header has been created");

        for (Book book : books) {
          if (book.getOriginalTitle().length() > 35) {
            book.setOriginalTitle(book.getOriginalTitle().substring(0, 34) + "...");
          }

          if (book.getAuthor().length() > 30) {
            book.setAuthor(book.getAuthor().substring(0, 29) + "...");
          }
          out.format(CUSTOMER_FORMAT, book.getId(), book.getIsbn(), book.getAuthor(), book.getOriginalTitle(), book.getOriginalPublicationYear(),
                  book.getAverageRating(), book.getRatingsCount(), book.getImageUrl());

          LOG.debug(String.format(CUSTOMER_FORMAT, book.getId(), book.getIsbn(), book.getAuthor(), book.getOriginalTitle(),
                  book.getOriginalPublicationYear(), book.getAverageRating(), book.getRatingsCount(), book.getImageUrl()));
        }
        out.close();
      }
    } catch (IOException e) {
      LOG.error(e.getMessage());
    }

  }
}
