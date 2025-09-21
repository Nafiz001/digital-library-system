/**
 * Interface for EBook with display method
 * Part of Digital Library System implementation
 */
public interface EBook {
    /**
     * Display the content of the e-book
     */
    void display();
    
    /**
     * Get the title of the e-book
     * @return title of the book
     */
    String getTitle();
    
    /**
     * Get the size of the e-book file
     * @return size in MB
     */
    double getSize();
    
    /**
     * Get the file format of the e-book
     * @return file format (PDF, EPUB, etc.)
     */
    String getFileFormat();
    
    /**
     * Get additional metadata about the book
     * @return metadata string
     */
    String getMetadata();
}

