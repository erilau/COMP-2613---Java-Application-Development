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

import a01027727.data.Purchase;
import a01027727.data.util.ApplicationException;

/**
 * Project: A01027727_Assignment1
 * File: PurchaseReader.java
 * Date: Jun. 2, 2019
 * Time: 2:51:00 a.m.
 */

/**
 * @author Eric Lau, A01027727
 *
 */
public class PurchaseReader {
  public static final String COMMA_DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

  public static final String FILE_TO_READ = "purchases.csv";
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

  private static final Logger LOG = LogManager.getLogger(PurchaseReader.class);

  /**
   * private constructor to prevent instantiation
   */
  private PurchaseReader() {
  }

  /**
   * reads a .csv file of books
   * 
   * @return a ArrayList of Customers
   * @throws ApplicationException
   */

  private static ArrayList<String> readFile() throws ApplicationException {

    ArrayList<String> purchaseList = new ArrayList<>();

    File sourceFile = new File(FILE_TO_READ);
    try (BufferedReader input = new BufferedReader(new FileReader(sourceFile))) {

      if (sourceFile.exists()) {
        LOG.debug("Reading purchase data");
        String oneLine = input.readLine();

        while ((oneLine = input.readLine()) != null) {

          purchaseList.add(oneLine);

        }
        input.close();
      } else {
        throw new ApplicationException("File " + FILE_TO_READ + " does not exsit");
      }
    } catch (IOException e) {
      LOG.error(e.getStackTrace());
    }

    return purchaseList;

  }

  /**
   * Organizes the data from the .csv file into a HashMap
   * 
   * @return a Map of Purchases
   * @throws ApplicationException
   */

  public static Map<Long, Purchase> readToMap(String filterCustomerId) throws ApplicationException {
    ArrayList<String> purchaseList = readFile();
    Map<Long, Purchase> purchases = new HashMap<>();
    if (filterCustomerId == null) {
      for (int i = 0; i < purchaseList.size(); i++) {
        String[] rows = purchaseList.get(i).split(COMMA_DELIMITER, -1);
        Purchase purchase = readPurchaseString(rows);
        purchases.put(purchase.getId(), purchase);
        LOG.debug(String.format("%s has been added to the Map", purchase.getCustomerId()));

      }
    } else {

      for (int i = 0; i < purchaseList.size(); i++) {

        String[] rows = purchaseList.get(i).split(COMMA_DELIMITER, -1);
        Purchase purchase = readPurchaseString(rows);
        if (purchase.getCustomerId() == Long.parseLong(filterCustomerId)) {
          purchases.put(purchase.getId(), purchase);
          LOG.debug(String.format("%s has been added to the Map", purchase.getCustomerId()));

        }

      }
    }

    return purchases;

  }

  /**
   * Parse a Purchase data string into a Purchase object;
   * 
   * @param row
   * @throws ApplicationException
   */
  private static Purchase readPurchaseString(String[] rows) throws ApplicationException {

    if (rows.length != Purchase.ATTRIBUTE_COUNT) {
      throw new ApplicationException(String.format("Expected %d but got %d: %s", Purchase.ATTRIBUTE_COUNT, rows.length, Arrays.toString(rows)));
    }

    int index = 0;
    long id = Integer.parseInt(rows[index++]);
    long customerId = Integer.parseInt(rows[index++]);
    long bookId = Integer.parseInt(rows[index++]);
    double price = Double.parseDouble(rows[index++]);

    return new Purchase.Builder(id, customerId, bookId).setPrice(price).build();

  }
}
