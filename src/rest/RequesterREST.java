package rest;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dao.TrackDAO;
import dao.UserDAO;
import data.Track;
import data.User;

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
    @Path("createU/{username}/{email}/{password}")
    @Produces("text/html")
    public String createUser(@PathParam("username") String username, @PathParam("email") String email, @PathParam("password") String password) {
    	User user = new User();
    	user.setUsername(username);
    	user.setEmail(email);
    	user.setPassword(password);
        userDao.create(user);
        return "User '" + username + "' was created.";
    }
    // ----- Track -----
    @POST
    @Path("createT/{title}/{artist}")
    @Produces("text/html")
    public String createTrack(@PathParam("title") String title, @PathParam("artist") String artist) {
    	Track track = new Track();
    	track.setTitle(title);
    	track.setArtist(artist);
        trackDao.create(track);
        return "Track - " + title + " - was created";
    }
    
    // ----- READ -------------------------------
	
    // ----- User -----
    @GET
    @Path("readU/{id}")
    @Produces("text/html")
    public String readUser(@PathParam("id") Long id) {
    	return userDao.read(id).toString();
    }
    
    // ----- Track -----
    @GET
    @Path("readT/{id}")
    @Produces("text/html")
    public String readTrack(@PathParam("id") Long id) {
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
        userDao.update(id, user);
        return username.toString();
    }
    
    // ----- Track -----
    @PUT
    @Path("updateT/{id}/{title}/{artist}")
    @Produces("text/html")
    public String updateTrack(@PathParam("id") Long id, @PathParam("title") String title, @PathParam("artist") String artist) {
    	Track track = new Track();
    	track.setTitle(title);
    	track.setArtist(artist);
        trackDao.update(id, track);
        return track.toString();
    }
    
	// ----- DELETE -----------------------------
	
    // ----- User -----
    @DELETE
    @Path("deleteU/{id}")
    @Produces("text/html")
    public String deleteUser(@PathParam("id") Long id) {
    	for(Track track : trackDao.list()){
    			deleteTrackFromUser(track.getId(), id);
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
    		deleteTrackFromUser(id, user.getId());
    	}
    	trackDao.delete(id);
        return "Track N°" + id + "  was deleted.";
    }
    
  	// ----- ADD/DEL Track TO/FROM User --------------
	  
    // ----- ADD Track -----
    @PUT
    @Path("addT2U/{idTrack}/{idUser}")
    @Produces("text/html")
    public String addTrackToUser(@PathParam("idTrack") Long idTrack, @PathParam("idUser") Long idUser) {
    	userDao.addT2U(idTrack, idUser);
        return "Track N°" + idTrack + " was added to User N°" + idUser + ".";
    }
    
    // ----- DEL Track -----
    @PUT
    @Path("delTFU/{idTrack}/{idUser}")
    @Produces("text/html")
    public String deleteTrackFromUser(@PathParam("idTrack") Long idTrack, @PathParam("idUser") Long idUser) {
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
