package estm.dsic.jee.Business.Services;

import java.util.List;

import estm.dsic.jee.Business.Reposistory;
import estm.dsic.jee.dao.NoteDao;
import estm.dsic.jee.dao.UserDao;
import estm.dsic.jee.model.Note;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class NoteServices implements Reposistory<Note,Integer>{
    @Inject
    NoteDao noteDAO;

    public NoteServices(){}

    @Override
    public boolean create(Note entity) {
        // TODO Auto-generated method stub
        return noteDAO.insertNote(entity);
    }
    
    @Override
    public List<Note> SelectNotes(Integer index) {
        // TODO Auto-generated method stub
        return noteDAO.selectNotes(index);
    }

    @Override
    public boolean DeletNote(Note entity) {
        // TODO Auto-generated method stub
        return noteDAO.DeletNote(entity);
    }

    @Override
    public boolean UpdateNote(Note entity) {
        // TODO Auto-generated method stub
        return noteDAO.updatenote(entity);
    }

    @Override
    public Note authenticate(Note entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

    @Override
    public boolean delet(Integer index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delet'");
    }

    @Override
    public boolean active(Integer index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'active'");
    }

    @Override
    public boolean desactiver(Integer index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desactiver'");
    }

    @Override
    public List<Note> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    // @Override
    // public List<Note> SelectNotes(int user_id) {
    //     // TODO Auto-generated method stub
    //     return noteDAO.selectNotes(user_id);
    // }

    // @Override
    // public boolean AjouterNote(Note note) {
    //     // TODO Auto-generated method stub
    //     return noteDAO.insertNote(note);
    // }

    // @Override
    // public boolean DeletNote(Note note) {
    //     // TODO Auto-generated method stub
    //     return noteDAO.DeletNote(note);
    // }

    // @Override
    // public Note UpdateNote(Note note) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'UpdateNote'");
    // }
    
}
