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
public class UserDao {

    @Resource(lookup = "jdbc/myDB")    
    private DataSource dataSource;

    static Connection connection;

    public UserDao() {

    }

    @PostConstruct
    private void initializeConnection() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertUser(User user) {
        String query = "INSERT INTO users (email, password) VALUES (?, ?)";

        try{
            PreparedStatement insertStatement = connection.prepareStatement(query);
            insertStatement.setString(1, user.getemail());
            insertStatement.setString(2, user.getPassword());

            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUser(User user) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ? ";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, user.getemail());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user.setid(rs.getInt("id"));
                user.setemail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setisadmin(rs.getInt("isadmin"));
                user.setiisverify(rs.getInt("isverify"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //select all users
    public List<User> selectAllUsers() {
        String query = "SELECT * FROM users WHERE isadmin = ?";
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, 0);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setid(rs.getInt("id"));
                user.setemail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setiisverify(rs.getInt("isverify"));

                users.add(user);
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
        return users;
    }


    //delet
    
    public boolean deletuser(int userId) {
        String query = "DELETE FROM users WHERE id=?";
    
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
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


    
    //activeuser
    
    public boolean activeuser(int userId) {
        String query = "update users set isverify=1 where id=?";
    
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
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

      //desactivereuser
    
      public boolean desactiveruser(int userId) {
        String query = "update users set isverify=0 where id=?";
    
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
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

    // public boolean getUserByCin(User user) {
    //     String query = "SELECT * FROM users WHERE email = ?";

    //     try {
    //         PreparedStatement preparedStatement = connection.prepareStatement(query);
    //         preparedStatement.setString(1, user.getemail());

    //         ResultSet resultSet = preparedStatement.executeQuery();
    //         if (resultSet.next()) {
    //             return false;
    //         } else {
    //             return insertUser(user);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }
}
