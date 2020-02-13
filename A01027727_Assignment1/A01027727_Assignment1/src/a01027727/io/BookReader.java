package a01027727.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a01027727.data.Book;
import a01027727.data.util.ApplicationException;

/**
 * Project: A01027727_Assignment1
 * File: BookReader.java
 * Date: Jun. 2, 2019
 * Time: 12:36:01 a.m.
 */

/**
 * @author Eric Lau, A01027727
 *
 */
public class BookReader {
  public static final String COMMA_DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

  public static final String FILE_TO_READ = "books500.csv";
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

  private static final Logger LOG = LogManager.getLogger(BookReader.class);

  /**
   * private constructor to prevent instantiation
   */
  private BookReader() {
  }

  /**
   * reads a .csv file of books
   * 
   * @return a ArrayList of Books
   * @throws ApplicationException
   */

  private static ArrayList<String> readFile() throws ApplicationException {

    ArrayList<String> bookList = new ArrayList<>();

    File sourceFile = new File(FILE_TO_READ);
    try (BufferedReader input = new BufferedReader(new FileReader(sourceFile))) {

      if (sourceFile.exists()) {
        LOG.debug("Reading book data");
        String oneLine = input.readLine();

        while ((oneLine = input.readLine()) != null) {

          bookList.add(oneLine);

        }
        input.close();
      } else {
        throw new ApplicationException("File " + FILE_TO_READ + " does not exsit");
      }
    } catch (IOException e) {
      LOG.error(e.getStackTrace());
    }

    return bookList;

  }

  /**
   * Organizes the data from the .csv file into a HashMap
   * 
   * @return a Map of Books
   * @throws ApplicationException
   */

  public static Map<Long, Book> readToMap() throws ApplicationException {
    ArrayList<String> bookList = readFile();
    Map<Long, Book> books = new HashMap<>();

    for (int i = 0; i < bookList.size(); i++) {
      String[] rows = bookList.get(i).split(COMMA_DELIMITER, -1);
      Book book = readBookString(rows);
      books.put(book.getId(), book);
      LOG.debug(String.format("%s has been added to the Map", book.getAuthor()));

    }

    return books;

  }

  /**
   * Parse a Customer data string into a Customer object;
   * 
   * @param row
   * @throws ApplicationException
   */
  private static Book readBookString(String[] rows) throws ApplicationException {

    if (rows.length != Book.ATTRIBUTE_COUNT) {
      throw new ApplicationException(String.format("Expected %d but got %d: %s", Book.ATTRIBUTE_COUNT, rows.length, Arrays.toString(rows)));
    }

    int index = 0;
    long id = Integer.parseInt(rows[index++]);
    String isbn = rows[index++];
    String author = rows[index++];
    if (author.startsWith("\"")) {
      author = author.substring(0, author.length() - 1).replaceAll("^\"|\"$", "");
    }
    int originalPublicationYear = Integer.parseInt(rows[index++]);
    String originalTitle = rows[index++];
    if (originalTitle.startsWith("\"")) {
      originalTitle = originalTitle.substring(0, originalTitle.length() - 1).replaceAll("^\"|\"$", "");
    }
    double averageRating = Double.parseDouble(rows[index++]);
    long ratingsCount = Integer.parseInt(rows[index++]);
    String imageUrl = rows[index++];

    return new Book.Builder(id).setIsbn(isbn).setAuthors(author).setOriginalPublicationYear(originalPublicationYear).setOriginalTitle(originalTitle)
            .setAverageRating(averageRating).setRatingCount(ratingsCount).setImageUrl(imageUrl).build();
  }
}
