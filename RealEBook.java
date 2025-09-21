/**
 * RealEBook class that loads the actual book file and displays content
 * Implements the EBook interface
 */
public class RealEBook implements EBook {
    private String fileName;
    private String title;
    private double sizeInMB;
    private String content;
    private boolean isLoaded;
    
    public RealEBook(String fileName) {
        this.fileName = fileName;
        this.isLoaded = false;
        // Extract title from filename (remove .txt extension)
        this.title = fileName.replace(".txt", "");
        this.sizeInMB = Math.random() * 10 + 1; // Random size between 1-11 MB
        System.out.println("RealEBook created for: " + fileName);
    }
    
    /**
     * Load the book content from file
     */
    private void loadFromFile() {
        if (!isLoaded) {
            System.out.println("Loading book content from file: " + fileName);
            // Simulate file loading delay
            try {
                Thread.sleep(1000); // 1 second delay to simulate loading
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Simulate book content
            this.content = "Content of " + title + "\n" +
                          "This is a sample e-book content loaded from " + fileName + "\n" +
                          "The book contains valuable information about various topics.\n" +
                          "This content was loaded from the actual file.";
            
            this.isLoaded = true;
            System.out.println("Book '" + title + "' loaded successfully!");
        }
    }
    
    @Override
    public void display() {
        loadFromFile(); // Load content if not already loaded
        System.out.println("=== Displaying: " + title + " ===");
        System.out.println(content);
        System.out.println("=== End of " + title + " ===\n");
    }
    
    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public double getSize() {
        return sizeInMB;
    }
    
    @Override
    public String getFileFormat() {
        return "PDF";
    }
    
    @Override
    public String getMetadata() {
        return "Author: Sample Author, Year: 2024, Pages: " + (int)(Math.random() * 500 + 100);
    }
    
    public boolean isLoaded() {
        return isLoaded;
    }
}