package kz.aitu.restpro.restpro.entitiess;
import java.util.ArrayList;
import java.util.List;

class Library {
    private String name;
    private List<Book> books;
    private List<LibraryMember> members;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(LibraryMember member) {
        members.add(member);
    }

    public List<LibraryMember> getMembers() {
        return members;
    }

    public void showBooks() {
        System.out.println("Books available in the library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(LibraryMember member, String bookTitle) {
        Book book = searchBookByTitle(bookTitle);
        if (book != null) {
            book.borrow();
        } else {
            System.out.println("The book '" + bookTitle + "' is not available in the library.");
        }
    }

    public void returnBook(LibraryMember member, String bookTitle) {
        Book book = searchBookByTitle(bookTitle);
        if (book != null) {
            book.returnBook();
        } else {
            System.out.println("The book '" + bookTitle + "' is not part of our collection.");
        }
    }

    public void showMembers() {
        System.out.println("Library Members:");
        for (LibraryMember member : members) {
            System.out.println(member);
        }
    }
}