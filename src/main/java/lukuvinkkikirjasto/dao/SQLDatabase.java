package lukuvinkkikirjasto.dao;

import java.sql.*;
import java.util.ArrayList;

import lukuvinkkikirjasto.domain.BookReadingTip;
import lukuvinkkikirjasto.domain.DefaultReadingTip;
import lukuvinkkikirjasto.domain.ReadingTip;

// https://tikape-k20.mooc.fi/sqlite-java
public class SQLDatabase implements Database {
    Connection db;
    Statement s;

    public SQLDatabase(String databaseName) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:"+databaseName);
        Statement s = db.createStatement();
        s.execute("CREATE TABLE IF NOT EXISTS Tips (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR NOT NULL, description VARCHAR, read BOOLEAN)");
        s.execute("CREATE TABLE IF NOT EXISTS Books ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "writer VARCHAR, "
            + "name VARCHAR, "
            + "isbn VARCHAR, "
            + "year VARCHAR, "
            + "read BOOLEAN)");
    }

    @Override
    public void createDefault(String title, String description) throws SQLException {
        PreparedStatement p = db.prepareStatement("INSERT INTO Tips(title, description, read) VALUES (?, ?, ?)");
        p.setString(1, title);
        p.setString(2, description);
        p.setBoolean(3, false);
        p.executeUpdate();
        p.close();
    }

    @Override
    public void createBook(String writer, String name, String isbn, String year) throws SQLException {
        PreparedStatement p = db.prepareStatement("INSERT INTO Books(writer, name, isbn, year, read) VALUES (?, ?, ?, ?, ?)");
        p.setString(1, writer);
        p.setString(2, name);
        p.setString(3, isbn);
        p.setString(4, year);
        p.setBoolean(5, false);
        p.executeUpdate();
        p.close();
    }

    @Override
    public ArrayList<ReadingTip> getTips() throws SQLException {
        ArrayList<ReadingTip> tipList = new ArrayList<>();
        PreparedStatement defaultP = db.prepareStatement("SELECT * FROM Tips");
        ResultSet defaultR = defaultP.executeQuery();
        tipList.addAll(this.parseDefaultTips(defaultR));
        PreparedStatement bookP = db.prepareStatement("SELECT * FROM Books");
        ResultSet bookR = bookP.executeQuery();
        tipList.addAll(this.parseBookTips(bookR));
        return tipList;
    }

    @Override
    public void editHeader(int id, String header) throws SQLException {
        PreparedStatement statement = db.prepareStatement("UPDATE Tips SET title=? Where id=?");
        statement.setString(1, header);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void editDescription(int id, String description) throws SQLException {
        PreparedStatement statement = db.prepareStatement("UPDATE Tips SET description=? Where id=?");
        statement.setString(1, description);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public Boolean containsId(int id) throws SQLException {
        PreparedStatement p = db.prepareStatement("SELECT id FROM Tips WHERE id=?");
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        return r.next();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement p = db.prepareStatement("DELETE FROM Tips WHERE id=?");
        p.setInt(1, id);
        p.executeUpdate();
        p.close();
    }

    @Override
    public void setReadStatus(int id, boolean status) throws SQLException {
        PreparedStatement p = db.prepareStatement("UPDATE Tips SET read=? Where id=?");
        p.setBoolean(1, status);
        p.setInt(2, id);
        p.executeUpdate();
        p.close();
    }

    @Override
    public ArrayList<ReadingTip> getReadOrUnreadTips(boolean read) throws SQLException {
        ArrayList<ReadingTip> tipList = new ArrayList<>();
        PreparedStatement defaultP = db.prepareStatement("SELECT * FROM TIPS WHERE read=?");
        defaultP.setBoolean(1, read);
        ResultSet defaultR = defaultP.executeQuery();
        tipList.addAll(this.parseDefaultTips(defaultR));
        PreparedStatement bookP = db.prepareStatement("SELECT * FROM Books WHERE read=?");
        bookP.setBoolean(1, read);
        ResultSet bookR = bookP.executeQuery();
        tipList.addAll(this.parseBookTips(bookR));
        return tipList;
    }

    public ArrayList<ReadingTip> searchFromTips(String text) throws SQLException {
        ArrayList<ReadingTip> tipList = new ArrayList<>();
        PreparedStatement defaultP = db.prepareStatement("SELECT * FROM TIPS WHERE title LIKE ? OR description LIKE ?");
        defaultP.setString(1, "%" + text + "%");
        defaultP.setString(2, "%" + text + "%");
        ResultSet defaultR = defaultP.executeQuery();
        tipList.addAll(this.parseDefaultTips(defaultR));

        PreparedStatement bookP = db.prepareStatement("SELECT * FROM TIPS WHERE writer LIKE ? OR name LIKE ? OR isbn LIKE ?");
        bookP.setString(1, "%" + text + "%");
        bookP.setString(2, "%" + text + "%");
        bookP.setString(3, "%" + text + "%");
        ResultSet bookR = bookP.executeQuery();
        tipList.addAll(this.parseBookTips(bookR));
        return tipList;
    }

    private ArrayList<ReadingTip> parseDefaultTips(ResultSet r) throws SQLException {
        ArrayList<ReadingTip> tipList = new ArrayList<>();
        while (r.next()) {
            tipList.add(new DefaultReadingTip(
                r.getInt("id"),
                r.getBoolean("read"),
                r.getString("title"),
                r.getString("description")
                ));
        }
        return tipList;
    }

    private ArrayList<ReadingTip> parseBookTips(ResultSet r) throws SQLException {
        ArrayList<ReadingTip> tipList = new ArrayList<>();
        while (r.next()) {
            tipList.add(new BookReadingTip(
                r.getInt("id"),
                r.getBoolean("read"),
                r.getString("writer"),
                r.getString("name"),
                r.getString("isbn"),
                r.getString("year")
                ));
        }
        return tipList;
    }
}
