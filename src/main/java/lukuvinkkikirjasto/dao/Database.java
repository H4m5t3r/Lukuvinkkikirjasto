package lukuvinkkikirjasto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import lukuvinkkikirjasto.domain.ReadingTip;


public interface Database {

    void createDefault(String title, String description) throws SQLException;

    void createBook(String writer, String name, String isbn, String year, String description) throws SQLException;
    
    void createPodcast(String host, String name, String link, String description) throws SQLException;

    void createBlog(String writer, String name, String link, String description) throws SQLException;
    
    void createVideo(String name, String link, String published, String description) throws SQLException;
    
    ArrayList<ReadingTip> getTips(String option) throws SQLException;

    ReadingTip getTip(int id) throws SQLException;
    
    void editHeader(int id, String header) throws SQLException;

    void editField(int id, String field, String text) throws SQLException;

    void editDescription(int id, String description) throws SQLException;

    Boolean containsId(int id) throws SQLException;
    
    void delete(int id) throws SQLException;
    
    void setReadStatus(int id, boolean status) throws SQLException;

    ArrayList<ReadingTip> searchFromTips(String text) throws SQLException;
}
