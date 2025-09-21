import java.util.*;

/**
 * Main class to simulate the Digital Library System
 * Demonstrates lazy loading behavior with multiple students accessing e-books
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Digital Library System Simulation ===\n");
        
        // Create a digital library with various e-books using ProxyEBook (lazy loading)
        Map<String, EBook> library = new HashMap<>();
        
        // Add e-books to the library using Proxy pattern
        library.put("java", new ProxyEBook("Java_Programming.txt"));
        library.put("python", new ProxyEBook("Python_Basics.txt"));
        library.put("algorithms", new ProxyEBook("Data_Structures_And_Algorithms.txt"));
        library.put("webdev", new ProxyEBook("Web_Development.txt"));
        library.put("ai", new ProxyEBook("Artificial_Intelligence.txt"));
        
        System.out.println("Digital Library initialized with " + library.size() + " e-books\n");
        
        // Simulate multiple students accessing e-books
        simulateStudentAccess("Alice", library, Arrays.asList("java", "python"));
        simulateStudentAccess("Bob", library, Arrays.asList("algorithms", "java"));
        simulateStudentAccess("Carol", library, Arrays.asList("webdev", "ai", "python"));
        simulateStudentAccess("David", library, Arrays.asList("java")); // Already loaded
        
        // Show library statistics
        showLibraryStatistics(library);
        
        // Demonstrate direct comparison between RealEBook and ProxyEBook
        System.out.println("\n=== Direct Comparison Demo ===");
        demonstrateLoadingBehavior();
    }
    
    /**
     * Simulate a student accessing e-books from the library
     */
    private static void simulateStudentAccess(String studentName, Map<String, EBook> library, List<String> requestedBooks) {
        System.out.println("--- Student " + studentName + " accessing e-books ---");
        
        for (String bookKey : requestedBooks) {
            EBook book = library.get(bookKey);
            if (book != null) {
                System.out.println(studentName + " requests: " + book.getTitle() + " (" + String.format("%.1f", book.getSize()) + " MB)");
                
                // Check if it's a ProxyEBook and show loading status
                if (book instanceof ProxyEBook) {
                    ProxyEBook proxy = (ProxyEBook) book;
                    if (!proxy.isRealEBookLoaded()) {
                        System.out.println("  -> Lazy loading will occur on first display...");
                    } else {
                        System.out.println("  -> E-book already loaded in memory");
                    }
                }
                
                book.display();
                
                // Add delay between accesses
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println("Book not found: " + bookKey);
            }
        }
        System.out.println();
    }
    
    /**
     * Show statistics about loaded vs unloaded books
     */
    private static void showLibraryStatistics(Map<String, EBook> library) {
        System.out.println("=== Library Loading Statistics ===");
        int loadedBooks = 0;
        int totalBooks = library.size();
        
        for (Map.Entry<String, EBook> entry : library.entrySet()) {
            EBook book = entry.getValue();
            if (book instanceof ProxyEBook) {
                ProxyEBook proxy = (ProxyEBook) book;
                System.out.println("📚 " + book.getTitle() + ": " + 
                    (proxy.isRealEBookLoaded() ? "✅ Loaded" : "⏳ Not loaded"));
                if (proxy.isRealEBookLoaded()) {
                    loadedBooks++;
                }
            }
        }
        
        System.out.println("\nSummary: " + loadedBooks + "/" + totalBooks + " books loaded in memory");
        System.out.println("Memory efficiency: " + String.format("%.1f", (double)(totalBooks - loadedBooks) / totalBooks * 100) + "% of books remain unloaded\n");
    }
    
    /**
     * Demonstrate the difference between RealEBook and ProxyEBook loading behavior
     */
    private static void demonstrateLoadingBehavior() {
        System.out.println("Creating RealEBook (immediate loading):");
        RealEBook realBook = new RealEBook("Immediate_Load_Book.txt");
        System.out.println("RealEBook created. Is loaded: " + realBook.isLoaded());
        
        System.out.println("\nCreating ProxyEBook (lazy loading):");
        ProxyEBook proxyBook = new ProxyEBook("Lazy_Load_Book.txt");
        System.out.println("ProxyEBook created. Real book loaded: " + proxyBook.isRealEBookLoaded());
        
        System.out.println("\nNow calling display() on ProxyEBook:");
        proxyBook.display();
        System.out.println("After display() call. Real book loaded: " + proxyBook.isRealEBookLoaded());
    }
}