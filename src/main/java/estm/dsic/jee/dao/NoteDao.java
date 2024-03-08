package estm.dsic.jee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import estm.dsic.jee.model.Note;
import estm.dsic.jee.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class NoteDao {

    @Resource(lookup = "jdbc/myDB")    
    private DataSource dataSource;

    static Connection connection;

    public NoteDao() {

    }

    @PostConstruct
    private void initializeConnection() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Select Notes Of User
    public List<Note> selectNotes(int user_id) {
        String query = "SELECT * FROM note WHERE user_id = ?";
        List<Note> notes = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setSubject(rs.getString("subject"));
                note.setBody(rs.getString("body"));
                note.setDate(rs.getString("date"));

                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return notes;
    }

        public boolean insertNote(Note note) {
        String query = "INSERT INTO note (subject, body,date,user_id) VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement insertStatement = connection.prepareStatement(query);
            insertStatement.setString(1, note.getSubject());
            insertStatement.setString(2, note.getBody());
            insertStatement.setString(3, note.getDate());
            insertStatement.setInt(4, note.getUser_id());

            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeletNote(Note note) {
        String query = "delete from note where id=? and user_id=?;";

        try{
            PreparedStatement insertStatement = connection.prepareStatement(query);
            insertStatement.setInt(1, note.getId());
            insertStatement.setInt(2, note.getUser_id());

            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //Update
    public boolean updatenote(Note note) {
        String query = "UPDATE note SET subject = ?, body = ?,date=? WHERE id = ? AND user_id=?";
    
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, note.getSubject());
            stmt.setString(2, note.getBody());
            stmt.setString(3, note.getDate());
            stmt.setInt(4, note.getId());
            stmt.setInt(5,note.getUser_id());
            int rowsAffected = stmt.executeUpdate();
    
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
