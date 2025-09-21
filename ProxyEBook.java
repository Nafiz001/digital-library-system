/**
 * ProxyEBook class that implements lazy loading pattern
 * Delays loading until display() is called for the first time
 */
public class ProxyEBook implements EBook {
    private String fileName;
    private String title;
    private double sizeInMB;
    private RealEBook realEBook;
    
    public ProxyEBook(String fileName) {
        this.fileName = fileName;
        // Extract title from filename (remove .txt extension)
        this.title = fileName.replace(".txt", "");
        this.sizeInMB = Math.random() * 10 + 1; // Random size between 1-11 MB
        this.realEBook = null; // Not loaded yet
        System.out.println("ProxyEBook created for: " + fileName + " (not loaded yet)");
    }
    
    @Override
    public void display() {
        // Lazy loading: create RealEBook only when display() is called
        if (realEBook == null) {
            System.out.println("First time access - loading real e-book...");
            realEBook = new RealEBook(fileName);
        }
        realEBook.display();
    }
    
    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public double getSize() {
        return sizeInMB;
    }
    
    /**
     * Check if the real e-book has been loaded
     * @return true if loaded, false otherwise
     */
    public boolean isRealEBookLoaded() {
        return realEBook != null;
    }
    
    /**
     * Get access to the real e-book (for demonstration purposes)
     * @return RealEBook instance or null if not loaded
     */
    public RealEBook getRealEBook() {
        return realEBook;
    }
}