package lukuvinkkikirjasto.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import lukuvinkkikirjasto.dao.Database;

public class ReadingTipService {
    Database database;

    public ReadingTipService(Database database) throws SQLException {
        this.database = database;
    }

    public void add(String header, String description) throws SQLException {
        database.createDefault(header, description);
    }

    public void addBook(String writer, String name, String isbn, String year, String description) throws SQLException {
        database.createBook(writer, name, isbn, year, description);
    }
    
    public void addPodcast(String host, String name, String link, String description) throws SQLException {
        database.createPodcast(host, name, link, description);
    }
    
    public void addBlog(String writer, String name, String link, String description) throws SQLException {
        database.createBlog(writer, name, link, description);
    }    

    public void addVideo(String name, String link, String published, String description) throws SQLException {
        database.createVideo(name, link, published, description);
    }   
        
    public void editHeader(int id, String header) throws SQLException {
        database.editHeader(id, header);
    }

    public void editDescription(int id, String description) throws SQLException {
        database.editDescription(id, description);
    }

    public Boolean tipExists(int id) throws SQLException {
        return database.containsId(id);
    }

    public ArrayList<ReadingTip> getTips() throws SQLException {
        return database.getTips();
    }

    public ArrayList<ReadingTip> getReadOrUnreadTips(boolean read) throws SQLException {
        return database.getReadOrUnreadTips(read);
    }

    public ArrayList<ReadingTip> searchReadingTips(String search) throws SQLException {
        return database.searchFromTips(search);
    }
    
    public void delete(int id) throws SQLException {
        database.delete(id);
    }
    
    public void setReadStatus(int id, boolean status) throws SQLException {
        database.setReadStatus(id, status);
    }
}
