
package estm.dsic.jee.Controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import estm.dsic.jee.Business.Services.NoteServices;
import estm.dsic.jee.model.Note;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/note")
public class NoteController {

   @Inject
    NoteServices noteservices;
    Note note = new Note();

    public NoteController() {
    }


    @POST
    @Path("/select")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Note> SelectNote(@FormParam("user_id") int user_id) {
        // Ensure dependencies are initialized before processing the request
        
        List<Note> notes = noteservices.SelectNotes(user_id);
        return notes;
    }


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String AddNote(@FormParam("subject") String subject,@FormParam("body") String body,@FormParam("user_id") int user_id) {
        // Ensure dependencies are initialized before processing the request
        

        LocalDate currentDate = LocalDate.now();
        
        // Define a custom date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Format the current date using the custom format
        String formattedDate = currentDate.format(formatter);


        note.setSubject(subject);
        note.setBody(body);
        note.setDate(formattedDate);
        note.setUser_id(user_id);

        boolean isadded = noteservices.create(note);

        if(isadded==true){
            return "true";
        }else{
            return "false";
        }
    }


    @POST
    @Path("/delet")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String DeletNote(@FormParam("user_id") int user_id,@FormParam("id") int id) {
        // Ensure dependencies are initialized before processing the request
        

        note.setId(id);
        note.setUser_id(user_id);
        boolean isdeleted = noteservices.DeletNote(note);
        if(isdeleted==true){
            return "is deleted";
        }else{
            return "is not deleted";
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String UpdateNote(@FormParam("subject") String subject,@FormParam("body") String body,@FormParam("user_id") int user_id,@FormParam("id") int id) {
        // Ensure dependencies are initialized before processing the request
        

        LocalDate currentDate = LocalDate.now();
        
        // Define a custom date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Format the current date using the custom format
        String formattedDate = currentDate.format(formatter);


        note.setSubject(subject);
        note.setBody(body);
        note.setDate(formattedDate);
        note.setUser_id(user_id);
        note.setId(id);

        boolean isadded = noteservices.UpdateNote(note);

        if(isadded==true){
            return "true";
        }else{
            return "false";
        }
    }


}
