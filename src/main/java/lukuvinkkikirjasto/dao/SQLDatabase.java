package lukuvinkkikirjasto.dao;

import java.sql.*;
import java.util.ArrayList;
import lukuvinkkikirjasto.domain.ReadingTip;

// https://tikape-k20.mooc.fi/sqlite-java
public class SQLDatabase implements Database {
    Connection db;
    Statement s;

    public SQLDatabase(String databaseName) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:"+databaseName);
        Statement s = db.createStatement();
        s.execute("CREATE TABLE IF NOT EXISTS Tips (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR NOT NULL, description VARCHAR, read BOOLEAN)");
    }

    @Override
    public void create(String title, String description) throws SQLException {
        PreparedStatement p = db.prepareStatement("INSERT INTO Tips(title, description, read) VALUES (?, ?, ?)");
        p.setString(1, title);
        p.setString(2, description);
        p.setBoolean(3, false);
        p.executeUpdate();
        p.close();
    }

    @Override
    public ArrayList<ReadingTip> getTips() throws SQLException {
        ArrayList<ReadingTip> tipList = new ArrayList<>();
        PreparedStatement p = db.prepareStatement("SELECT * FROM Tips");
        ResultSet r = p.executeQuery();
        while (r.next()) {
            tipList.add(new ReadingTip(
                r.getInt("id"),
                r.getString("title"),
                r.getString("description"),
                r.getBoolean("read")
                ));
        }
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
        PreparedStatement p = db.prepareStatement("SELECT * FROM TIPS WHERE read=?");
        p.setBoolean(1, read);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            tipList.add(new ReadingTip(
                    r.getInt("id"),
                    r.getString("title"),
                    r.getString("description"),
                    r.getBoolean("read")
            ));
        }
        return tipList;
    }

    public ArrayList<ReadingTip> searchFromTips(String text) throws SQLException {
        ArrayList<ReadingTip> tipList = new ArrayList<>();
        PreparedStatement p = db.prepareStatement("SELECT * FROM TIPS WHERE title LIKE ? OR description LIKE ?");
        p.setString(1, "%" + text + "%");
        p.setString(2, "%" + text + "%");
        ResultSet r = p.executeQuery();
        while (r.next()) {
            tipList.add(new ReadingTip(
                    r.getInt("id"),
                    r.getString("title"),
                    r.getString("description"),
                    r.getBoolean("read")
            ));
        }
        return tipList;
    }

}
