package kz.aitu.restpro.restpro.entitiess;
import java.util.Objects;

class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isAvailable;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("The book '" + title + "' has been borrowed.");
        } else {
            System.out.println("The book '" + title + "' is currently unavailable.");
        }
    }

    public void returnBook() {
        isAvailable = true;
        System.out.println("The book '" + title + "' has been returned.");
    }

    @Override
    public String toString() {
        return "Book: " + title + ", Author: " + author + ", ISBN: " + ISBN + ", Available: " + (isAvailable ? "Yes" : "No");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(title, book.title) && Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, ISBN);
    }
}
