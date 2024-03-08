package estm.dsic.jee.Business.Services;

import java.util.List;

import estm.dsic.jee.Business.Reposistory;
import estm.dsic.jee.dao.UserDao;
import estm.dsic.jee.model.Note;
import estm.dsic.jee.model.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UserServices implements Reposistory<User,Integer> {

    @Inject
    UserDao userDAO;

    public UserServices() {
      
    }

    @Override
    public boolean create(User entity) {
        // TODO Auto-generated method stub
        return userDAO.insertUser(entity);
    }

    @Override
    public User authenticate(User entity) {
        // TODO Auto-generated method stub
        return userDAO.getUser(entity);
    }

    @Override
    public boolean delet(Integer index) {
        // TODO Auto-generated method stub
        return userDAO.deletuser(index);
    }

    @Override
    public boolean active(Integer index) {
        // TODO Auto-generated method stub
        return userDAO.activeuser(index);
    }

    @Override
    public boolean desactiver(Integer index) {
        // TODO Auto-generated method stub
        return userDAO.desactiveruser(index);
    }

    @Override
    public List<User> selectAll() {
        // TODO Auto-generated method stub
        return userDAO.selectAllUsers();
    }

    @Override
    public List<User> SelectNotes(Integer index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SelectNotes'");
    }

    @Override
    public boolean DeletNote(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DeletNote'");
    }

    @Override
    public boolean UpdateNote(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UpdateNote'");
    }





    // private UserDao userDAO;

    

    // @Override
    // public User authenticate(User user) {
    //     return userDAO.getUser(user);
    // }

    // @Override
    // public boolean createUser(User user) {
    //     return userDAO.insertUser(user);
    // }

    // @Override
    // public List<User> selectAllUsers() {
    //     return userDAO.selectAllUsers();
    // }

    // @Override
    // public boolean deletuser(int user_id) {
    //     // TODO Auto- method stub
    //     return userDAO.deletuser(user_id);
    // }
    // @Override
    // public boolean activeuser(int user_id) {
    //     // TODO Auto- method stub
    //     return userDAO.activeuser(user_id);
    // }

    // @Override
    // public boolean desactiveruser(int user_id) {
    //     // TODO Auto-generated method stub
    //     return userDAO.desactiveruser(user_id);
    // }
}
