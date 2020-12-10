package lukuvinkkikirjasto.dao;

import java.sql.*;
import java.util.ArrayList;
import lukuvinkkikirjasto.domain.BlogReadingTip;

import lukuvinkkikirjasto.domain.BookReadingTip;
import lukuvinkkikirjasto.domain.DefaultReadingTip;
import lukuvinkkikirjasto.domain.PodcastReadingTip;
import lukuvinkkikirjasto.domain.ReadingTip;
import lukuvinkkikirjasto.domain.VideoReadingTip;

public class SQLDatabase implements Database {

    Connection db;
    Statement s;

    public SQLDatabase(String databaseName) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
        Statement s = db.createStatement();
        s.execute("CREATE TABLE IF NOT EXISTS Tips (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type VARCHAR NOT NULL, "
                + "read BOOLEAN, "
                + "title VARCHAR, "
                + "author VARCHAR, "
                + "isbn VARCHAR, "
                + "published VARCHAR, "
                + "link VARCHAR, "
                + "description VARCHAR)");
    }

    public void createDefault(String title, String description) throws SQLException {
        PreparedStatement p = db.prepareStatement("INSERT INTO Tips(title, description, read, type) VALUES (?, ?, ?, ?)");
        p.setString(1, title);
        p.setString(2, description);
        p.setBoolean(3, false);
        p.setString(4, "default");
        p.executeUpdate();
        p.close();
    }

    @Override
    public void createBook(String writer, String name, String isbn, String year, String description) throws SQLException {
        PreparedStatement p = db.prepareStatement("INSERT INTO Tips(author, title, published, isbn, description, read, type) VALUES (?, ?, ?, ?, ?, ?, ?)");
        p.setString(1, writer);
        p.setString(2, name);
        p.setString(3, year);
        p.setString(4, isbn);
        p.setString(5, description);
        p.setBoolean(6, false);
        p.setString(7, "book");
        p.executeUpdate();
        p.close();
    }
    
    @Override
    public void createPodcast(String host, String name, String link, String description) throws SQLException {
        PreparedStatement p = db.prepareStatement("INSERT INTO Tips(author, title, link, description, read, type) VALUES (?, ?, ?, ?, ?, ?)");
        p.setString(1, host);
        p.setString(2, name);
        p.setString(3, link);
        p.setString(4, description);
        p.setBoolean(5, false);
        p.setString(6, "podcast");
        p.executeUpdate();
        p.close();
    }
    
    @Override
    public void createBlog(String writer, String name, String link, String description) throws SQLException {
        PreparedStatement p = db.prepareStatement("INSERT INTO Tips(author, title, link, description, read, type) VALUES (?, ?, ?, ?, ?, ?)");
        p.setString(1, writer);
        p.setString(2, name);
        p.setString(3, link);
        p.setString(4, description);
        p.setBoolean(5, false);
        p.setString(6, "blog");
        p.executeUpdate();
        p.close();
    }
    
    @Override
    public void createVideo(String name, String link, String published, String description) throws SQLException {
        PreparedStatement p = db.prepareStatement("INSERT INTO Tips(title, link, published, description, read, type) VALUES (?, ?, ?, ?, ?, ?)");
        p.setString(1, name);
        p.setString(2, link);
        p.setString(3, published);
        p.setString(4, description);
        p.setBoolean(5, false);
        p.setString(6, "video");
        p.executeUpdate();
        p.close();
    }

    @Override
    public ArrayList<ReadingTip> getTips(String option) throws SQLException {
        PreparedStatement p;
        if (option.equals("unread")) {
            p = db.prepareStatement("SELECT * FROM Tips WHERE read=false");
        } else if (option.equals("read")) {
            p = db.prepareStatement("SELECT * FROM Tips WHERE read=true");
        } else {
            p = db.prepareStatement("SELECT * FROM Tips");
        }
        return this.parseResult(p.executeQuery());
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
    public void editField(int id, String field, String text) throws SQLException {
        String realField;
        switch (field){
            case "writer":
                realField = "author";
                break;
            case "host":
                realField = "author";
                break;
            case "name":
                realField = "title";
                break;
            case "year":
                realField = "published";
                break;
            case "header":
                realField = "title";
                break;
            default:
                realField = field;
        }
        PreparedStatement statement = db.prepareStatement("UPDATE Tips SET " + realField + "=? Where id=?");
        statement.setString(1, text);
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

    public ArrayList<ReadingTip> searchFromTips(String text) throws SQLException {
        ArrayList<ReadingTip> tipList = new ArrayList<>();
        PreparedStatement p = db.prepareStatement("SELECT * FROM TIPS WHERE "
            + "title LIKE ? "
            + "OR author LIKE ? "
            + "OR isbn LIKE ? "
            + "OR published LIKE ? "
            + "OR link LIKE ? "
            + "OR description LIKE ?");
        p.setString(1, "%" + text + "%");
        p.setString(2, "%" + text + "%");
        p.setString(3, "%" + text + "%");
        p.setString(4, "%" + text + "%");
        p.setString(5, "%" + text + "%");
        p.setString(6, "%" + text + "%");
        return this.parseResult(p.executeQuery());
    }

    @Override
    public ReadingTip getTip(int id) throws SQLException {
        PreparedStatement p = db.prepareStatement("SELECT * FROM Tips WHERE id=?");
        p.setInt(1, id);
        return this.parseResult(p.executeQuery()).get(0);
    }

    private ArrayList<ReadingTip> parseResult(ResultSet r) throws SQLException {
        ArrayList<ReadingTip> tipList = new ArrayList<>();
        while (r.next()) {
            String type = r.getString("type");
            if (type.equals("book")) {
                tipList.add(new BookReadingTip(
                        r.getInt("id"),
                        r.getBoolean("read"),
                        r.getString("author"),
                        r.getString("title"),
                        r.getString("isbn"),
                        r.getString("published"),
                        r.getString("description")
                ));
            } else if (type.equals("podcast")) {
                tipList.add(new PodcastReadingTip(
                        r.getInt("id"),
                        r.getBoolean("read"),
                        r.getString("author"),
                        r.getString("title"),
                        r.getString("link"),
                        r.getString("description")
                ));
            } else if (type.equals("blog")) {
                tipList.add(new BlogReadingTip(
                        r.getInt("id"),
                        r.getBoolean("read"),
                        r.getString("author"),
                        r.getString("title"),
                        r.getString("link"),
                        r.getString("description")
                ));
            } else if (type.equals("video")) {
                tipList.add(new VideoReadingTip(
                        r.getInt("id"),
                        r.getBoolean("read"),
                        r.getString("title"),
                        r.getString("link"),
                        r.getString("published"),                        
                        r.getString("description")
                ));
            } else if (type.equals("default")) {
                tipList.add(new DefaultReadingTip(
                        r.getInt("id"),
                        r.getBoolean("read"),
                        r.getString("title"),
                        r.getString("description")
                ));
            }
            
        }
        return tipList;
    }


}
