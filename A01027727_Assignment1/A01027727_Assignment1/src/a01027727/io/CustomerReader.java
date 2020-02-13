package a01027727.io;

/**
 * Project: A01027727_Assignment1
 * File: CustomerReader.java
 */

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

import a01027727.data.Customer;
import a01027727.data.util.ApplicationException;

/**
 * Read the customer input.
 * 
 * @author Eric Lau, A01027727
 *
 */
public class CustomerReader {

  public static final String RECORD_DELIMITER = ":";
  public static final String FIELD_DELIMITER = "\\|";

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

  private static final Logger LOG = LogManager.getLogger(CustomerReader.class);

  /**
   * private constructor to prevent instantiation
   */
  private CustomerReader() {
  }

  /**
   * reads a .txt file of customers
   * 
   * @return a ArrayList of Customers
   * @throws ApplicationException
   */

  private static ArrayList<String> readFile() throws ApplicationException {

    ArrayList<String> customersList = new ArrayList<>();

    File sourceFile = new File("customers.dat");
    try (BufferedReader input = new BufferedReader(new FileReader(sourceFile))) {

      if (sourceFile.exists()) {
        LOG.debug("Reading customer data");
        String oneLine = input.readLine();

        while ((oneLine = input.readLine()) != null) {
          customersList.add(oneLine);
        }
        input.close();
      } else {
        throw new ApplicationException("File customers.dat does not exsit");
      }
    } catch (IOException e) {
      LOG.error(e.getStackTrace());
    }

    return customersList;

  }

  /**
   * Organizes the data from the .txt file into a HashMap
   * 
   * @return a Map of Customers
   * @throws ApplicationException
   */

  public static Map<Long, Customer> readToMap() throws ApplicationException {
    ArrayList<String> customersList = readFile();

    Map<Long, Customer> customers = new HashMap<>();

    for (int i = 0; i < customersList.size(); i++) {
      String[] rows = customersList.get(i).split(RECORD_DELIMITER);

      for (String row : rows) {
        Customer customer = readCustomerString(row);
        customers.put(customer.getId(), customer);
        LOG.debug(String.format("%s has been added to the Map", customer.getFirstName()));
      }

    }

    return customers;

  }

  /**
   * Parse a Customer data string into a Customer object;
   * 
   * @param row
   * @throws ApplicationException
   */
  private static Customer readCustomerString(String data) throws ApplicationException {
    String[] elements = data.split(FIELD_DELIMITER);

    if (elements.length != Customer.ATTRIBUTE_COUNT) {
      throw new ApplicationException(
              String.format("Expected %d but got %d: %s", Customer.ATTRIBUTE_COUNT, elements.length, Arrays.toString(elements)));
    }

    int index = 0;
    long id = Integer.parseInt(elements[index++]);
    String firstName = elements[index++];
    String lastName = elements[index++];
    String street = elements[index++];
    String city = elements[index++];
    String postalCode = elements[index++];
    String phone = elements[index++];
    // should the email validation be performed here or in the customer class? I'm leaning towards putting it here.
    String emailAddress = elements[index++];
    String yyyymmdd = elements[index];

    int year = Integer.parseInt(yyyymmdd.substring(0, 4));
    int month = Integer.parseInt(yyyymmdd.substring(4, 6));
    int day = Integer.parseInt(yyyymmdd.substring(6, 8));

    return new Customer.Builder(id, phone).setFirstName(firstName).setLastName(lastName).setStreet(street).setCity(city).setPostalCode(postalCode)
            .setEmailAddress(emailAddress).setJoinedDate(year, month, day).build();
  }

}
