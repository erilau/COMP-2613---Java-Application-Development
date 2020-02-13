package a01027727.data;
/**
 * Project: A01027727_Assignment1
 * File: Book.java
 * Date: May 31, 2019
 * Time: 7:21:19 a.m.
 */

/**
 * @author Eric Lau, A01027727
 *
 */
public class Book {

  public static final int ATTRIBUTE_COUNT = 8;

  private long id;
  private String isbn;
  private String author;
  private int originalPublicationYear;
  private String originalTitle;
  private double averageRating;
  private long ratingsCount;
  private String imageUrl;

  public static class Builder {

    private final long id;

    private String isbn;
    private String author;
    private int originalPublicationYear;
    private String originalTitle;
    private double averageRating;
    private long ratingsCount;
    private String imageUrl;

    /**
     * Builder Constructor
     * 
     * @param id
     */

    public Builder(long id) {
      this.id = id;

    }

    /**
     * The isbn to set as a String
     * 
     * @param isbn
     *          as a String
     * @return
     */

    public Builder setIsbn(String isbn) {
      this.isbn = isbn;
      return this;
    }

    /**
     * The author to set as a String
     * 
     * @param author
     * @return
     */

    public Builder setAuthors(String author) {
      this.author = author;
      return this;
    }

    /**
     * The title to set as a String
     * 
     * @param originalTitle
     * @return
     */
    public Builder setOriginalTitle(String originalTitle) {
      this.originalTitle = originalTitle;
      return this;
    }

    /**
     * The publication year to set as an int
     * 
     * @param originalPublicationYear
     * @return
     */

    public Builder setOriginalPublicationYear(int originalPublicationYear) {
      this.originalPublicationYear = originalPublicationYear;
      return this;
    }

    /**
     * The average rating to set as a double
     * 
     * @param averageRating
     * @return
     */

    public Builder setAverageRating(double averageRating) {
      this.averageRating = averageRating;
      return this;
    }

    /**
     * The ratings count to set as a long
     * 
     * @param ratingsCount
     * @return
     */

    public Builder setRatingCount(long ratingsCount) {
      this.ratingsCount = ratingsCount;
      return this;
    }

    /**
     * The image url to set as a String
     * 
     * @param imageUrl
     * @return
     */

    public Builder setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
      return this;
    }

    public Book build() {
      return new Book(this);
    }
  }

  /**
   * The Default Constructor
   * 
   * @param builder
   */
  private Book(Builder builder) {
    id = builder.id;
    isbn = builder.isbn;
    author = builder.author;
    originalPublicationYear = builder.originalPublicationYear;
    originalTitle = builder.originalTitle;
    averageRating = builder.averageRating;
    ratingsCount = builder.ratingsCount;
    imageUrl = builder.imageUrl;
  }

  /**
   * @return the id as a long
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
   * @return the isbn
   */
  public String getIsbn() {
    return isbn;
  }

  /**
   * @param isbn
   *          the isbn to set
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * @return the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * @param author
   *          the author to set
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * @return the originalPublicationYear
   */
  public int getOriginalPublicationYear() {
    return originalPublicationYear;
  }

  /**
   * @param originalPublicationYear
   *          the originalPublicationYear to set
   */
  public void setOriginalPublicationYear(int originalPublicationYear) {
    this.originalPublicationYear = originalPublicationYear;
  }

  /**
   * @return the originalTitle
   */
  public String getOriginalTitle() {
    return originalTitle;
  }

  /**
   * @param originalTitle
   *          the originalTitle to set
   */
  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  /**
   * @return the averageRating
   */
  public double getAverageRating() {
    return averageRating;
  }

  /**
   * @param averageRating
   *          the averageRating to set
   */
  public void setAverageRating(double averageRating) {
    this.averageRating = averageRating;
  }

  /**
   * @return the ratingsCount
   */
  public long getRatingsCount() {
    return ratingsCount;
  }

  /**
   * @param ratingsCount
   *          the ratingsCount to set
   */
  public void setRatingsCount(long ratingsCount) {
    this.ratingsCount = ratingsCount;
  }

  /**
   * @return the imageUrl
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * @param imageUrl
   *          the imageUrl to set
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public String toString() {
    return "Book [id=" + id + ", isbn=" + isbn + ", author=" + author + ", originalPublicationYear=" + originalPublicationYear + ", originalTitle="
            + originalTitle + ", averageRating=" + averageRating + ", ratingsCount=" + ratingsCount + ", imageUrl=" + imageUrl + "]";
  }

}
