package estm.dsic.jee.Business;

import java.util.List;

import estm.dsic.jee.model.Note;
import estm.dsic.jee.model.User;

public interface Reposistory<T,I> {
    boolean create(T entity);
    T authenticate(T entity);
    boolean delet(I index) ;
    boolean active(I index);
    boolean desactiver(I index);
    List<T> selectAll();

    //Notes
    List<T> SelectNotes(I index);
    boolean DeletNote(T entity);
    boolean UpdateNote(T entity);

}
