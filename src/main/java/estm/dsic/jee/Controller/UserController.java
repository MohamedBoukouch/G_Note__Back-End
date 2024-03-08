package estm.dsic.jee.Controller;


import java.util.List;

import javax.sql.DataSource;

import estm.dsic.jee.Business.Services.UserServices;
import estm.dsic.jee.dao.UserDao;
import estm.dsic.jee.model.Note;
import estm.dsic.jee.model.User;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/user")
public class UserController {

   
    

    // private UserDao userDao;
    @Inject
    UserServices userServices;
    User user = new User();

    @Context
    private HttpServletResponse servletResponse;

    @Context
    private HttpServletRequest servletRequest;

    public UserController() {
    }

    //LOGIN
    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(@FormParam("email") String email, @FormParam("password") String password) {
        user.setemail(email);
        user.setPassword(password);
        User auth = userServices.authenticate(user);

        if (auth != null) {
            // String userId = String.valueOf(auth.getid());
                        // Create a cookie named "authToken" with the user's email as the value
            // Cookie cookie = new Cookie("email", email);
            // Set the cookie to expire in a day (adjust as needed)
            // cookie.setMaxAge(24 * 60 * 60); // 24 hours
            // servletResponse.addCookie(cookie);

            //SESSION
            // HttpSession session = servletRequest.getSession();
            // session.setAttribute("user", auth);
            // if(auth.getisadmin()==1){
                return Response.ok(auth).build();
            //     // return "";
            // }else{
            //     // return "";
            //     return Response.ok("Login successful as user"+auth).build();
            // }
            } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Login failed").build();
    }
    }

   // SignUp
   @POST
   @Path("/signup")
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   @Produces(MediaType.TEXT_PLAIN)
   public String signUp(@FormParam("email") String email,@FormParam("password") String password) {

       if(password.isEmpty() || email.isEmpty()){
        return "All fields must be non-empty";
       }else{  
        user.setemail(email);
        user.setPassword(password);

        boolean registered = userServices.create(user);
        if (registered) {
            return "Signup successful";
        } else {
            return "Signup failed";
        }
       }   
   }

 
   @GET
   @Path("/home")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getHomeData() {
       // Retrieve the user session attribute
       HttpSession session = servletRequest.getSession(false);
       if (session != null) {
           User user = (User) session.getAttribute("user");
           if (user != null) {
               // Return the user data in the response body
               return Response.ok(user).build();
           }
       }
       // Return an empty response if no user session is found
       return Response.noContent().build();
   }

      // ALLUSERS
      @POST
      @Path("/selectallusers")
      @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
      @Produces(MediaType.APPLICATION_JSON)
      public List<User> selectallusers() {
        //   initializeDependencies();

        //    user.setid(user_id);
            List<User> users = userServices.selectAll();
            return users;
      }


      //deletuser
      @POST
      @Path("/deletuser")
      @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   @Produces(MediaType.TEXT_PLAIN)
      public boolean deletuser(@FormParam("user_id") int user_id) {
        //   initializeDependencies();

        //    user.setid(user_id);
            boolean  isdeleted= userServices.delet(user_id);
            return isdeleted;
      }

      //Active
      @POST
      @Path("/activeuser")
      @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
      public boolean activeuser(@FormParam("user_id") int user_id) {
        //   initializeDependencies();

        //    user.setid(user_id);
            boolean  isdeleted= userServices.active(user_id);
            return isdeleted;
      }


      //desactiver
      @POST
      @Path("/desactiveruser")
      @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
      public boolean desactiveruser(@FormParam("user_id") int user_id) {
        //   initializeDependencies();

        //    user.setid(user_id);
            boolean  isdeleted= userServices.desactiver(user_id);
            return isdeleted;
      }




}
