package UserFront;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import UserData.User;
import UserData.Track;
import UserBack.UserDAO;
import UserBack.TrackDAO;

@Path("MyTunesDB")
public class RequesterREST {
	
	@EJB
    private UserDAO userDao;
	@EJB
    private TrackDAO trackDao;
 
	// ----- TEST -------------------------------
	
    @GET
    @Path("hello/{username}")
    @Produces("text/html")
    public String hello(@PathParam("username") String username) {
        return "Hello " + username;
    }	
    
	// ----- CREATE -----------------------------
	
    // ----- User -----
    @POST
    @Path("addU/{username}/{email}/{password}")
    @Produces("text/html")
    public String addUser(@PathParam("username") String username, @PathParam("email") String email, @PathParam("password") String password) {
    	User user = new User();
    	user.setUsername(username);
    	user.setEmail(email);
    	user.setPassword(password);
        userDao.add(user);
        return "User '" + username + "' was created.";
    }
    // ----- Track -----
    @POST
    @Path("addT/{title}/{artist}")
    @Produces("text/html")
    public String addTrack(@PathParam("title") String title, @PathParam("artist") String artist) {
    	Track track = new Track();
    	track.setTitle(title);
    	track.setArtist(artist);
        trackDao.add(track);
        return "Track - " + title + " - was created";
    }
    
    // ----- READ -------------------------------
	
    // ----- User -----
    @GET
    @Path("showU/{id}")
    @Produces("text/html")
    public String showUser(@PathParam("id") Long id) {
    	return userDao.read(id).toString();
    }
    
    // ----- Track -----
    @GET
    @Path("showT/{id}")
    @Produces("text/html")
    public String showTrack(@PathParam("id") Long id) {
    	return trackDao.read(id).toString();
    }
    
    // ----- UPDATE -----------------------------
	
    // ----- User -----
    @PUT
    @Path("updateU/{id}{username}/{email}/{password}")
    @Produces("text/html")
    public String updateUser(@PathParam("id") Long id, @PathParam("username") String username, @PathParam("email") String email, @PathParam("password") String password) {
    	User user = new User();
    	user.setUsername(username);
    	user.setEmail(email);
    	user.setPassword(password);
        userDao.edit(id, user);
        return username.toString();
    }
    
    // ----- Track -----
    @PUT
    @Path("addT/{id}/{title}/{artist}")
    @Produces("text/html")
    public String updateTrack(@PathParam("id") Long id, @PathParam("title") String title, @PathParam("artist") String artist) {
    	Track track = new Track();
    	track.setTitle(title);
    	track.setArtist(artist);
        trackDao.edit(id, track);
        return track.toString();
    }
    
	// ----- DELETE -----------------------------
	
    // ----- User -----
    @DELETE
    @Path("deleteU/{id}")
    @Produces("text/html")
    public String deleteUser(@PathParam("id") Long id) {
    	for(Track track : trackDao.list()){
    			delT2U(track.getId(), id);
    	}
    	userDao.delete(id);
        return "User N°" + id + " was deleted.";
    }
    
    // ----- Track -----
    @DELETE
    @Path("deleteT/{id}")
    @Produces("text/html")
    public String deleteTrack(@PathParam("id") Long id) {
    	for(User user : userDao.list()) {
    		delT2U(id, user.getId());
    	}
    	trackDao.delete(id);
        return "Track N°" + id + "  was deleted.";
    }
    
  	// ----- ADD/DEL Track TO/FROM User --------------
	  
    // ----- ADD Track -----
    @PUT
    @Path("addT2U/{idTrack}/{idUser}")
    @Produces("text/html")
    public String addT2U(@PathParam("idTrack") Long idTrack, @PathParam("idUser") Long idUser) {
    	userDao.addT2U(idTrack, idUser);
        return "Track N°" + idTrack + " was added to User N°" + idUser + ".";
    }
    
    // ----- DEL Track -----
    @PUT
    @Path("delTFU/{idTrack}/{idUser}")
    @Produces("text/html")
    public String delT2U(@PathParam("idTrack") Long idTrack, @PathParam("idUser") Long idUser) {
    	userDao.addT2U(idTrack, idUser);
        return "Track N°" + idTrack + " was removed from User N°" + idUser + ".";
    }      
  
  	// ----- LIST -------------------------------
	  
    // ----- User -----
    @GET
    @Path("listU")
    @Produces("text/html")
    public String listUser() {
    	String userList = "";
    	for(User user : userDao.list()) {
    		userList += user.toString();
    		userList += "\n";
    	}
        return userList;
    }
    
    // ----- Track -----
    @GET
    @Path("listT")
    @Produces("text/html")
    public String listTrack() {
    	String trackList = "";
    	for(Track track : trackDao.list()) {
    		trackList += track.toString();
    		trackList += "\n";
    	}
        return trackList;
    }
    
}
