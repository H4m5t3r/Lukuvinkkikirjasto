package lukuvinkkikirjasto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import lukuvinkkikirjasto.domain.ReadingTip;


public interface Database {

    void create(String title, String description) throws SQLException;

    ArrayList<ReadingTip> getTips() throws SQLException;
    
    void editHeader(int id, String header) throws SQLException;

    void editDescription(int id, String description) throws SQLException;

    Boolean containsId(int id) throws SQLException;
    
    void delete(int id) throws SQLException;
    
    void setReadStatusToTrue(int id) throws SQLException;

    ArrayList<ReadingTip> getReadOrUnreadTips(boolean read) throws SQLException;

    ArrayList<ReadingTip> searchFromTips(String text) throws SQLException;
}
