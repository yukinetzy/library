package kz.aitu.restpro.restpro.entitiess;

import kz.aitu.restpro.restpro.dbconnections.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> books;
    private List<LibraryMember> members;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public static void addBookToDB(Book book) {
        String sql = "INSERT INTO book (title, author, isbn, is_available) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getISBN());
            stmt.setBoolean(4, book.isAvailable());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> getAllBooksFromDB() {
        List<Book> result = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Book b = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn")
                );
                boolean available = rs.getBoolean("is_available");
                if (!available) {
                    b.borrow();
                }
                result.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Book getBookByTitleDB(String title) {
        String sql = "SELECT * FROM book WHERE title = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Book b = new Book(
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("isbn")
                    );
                    boolean available = rs.getBoolean("is_available");
                    if (!available) {
                        b.borrow();
                    }
                    return b;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateBookAvailabilityDB(String title, boolean isAvailable) {
        String sql = "UPDATE book SET is_available = ? WHERE title = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, isAvailable);
            stmt.setString(2, title);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBookByTitleDB(String title) {
        String sql = "DELETE FROM book WHERE title = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addMemberToDB(LibraryMember member) {
        String sql = "INSERT INTO library_member (name, age, member_id) VALUES (?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setInt(2, member.getAge());
            stmt.setString(3, member.getMemberID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<LibraryMember> getAllMembersFromDB() {
        List<LibraryMember> result = new ArrayList<>();
        String sql = "SELECT * FROM library_member";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                LibraryMember m = new LibraryMember(
                        rs.getString("name"),
                        rs.getString("member_id"),
                        rs.getInt("age")
                );
                result.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static LibraryMember getMemberByMemberIdDB(String memberId) {
        String sql = "SELECT * FROM library_member WHERE member_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LibraryMember(
                            rs.getString("name"),
                            rs.getString("member_id"),
                            rs.getInt("age")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateMemberDB(String memberId, LibraryMember updated) {
        String sql = "UPDATE library_member SET name=?, age=? WHERE member_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, updated.getName());
            stmt.setInt(2, updated.getAge());
            stmt.setString(3, memberId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMemberDB(String memberId) {
        String sql = "DELETE FROM library_member WHERE member_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, memberId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) { books.add(book); }
    public void addMember(LibraryMember member) { members.add(member); }

    public List<LibraryMember> getMembers() { return members; }

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
