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

@Path("MyTunes")
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
    	System.out.println("Creating User " + username);
    	User user = new User();
    	user.setUsername(username);
    	user.setEmail(email);
    	user.setPassword(password);
        userDao.add(user);
        return "User - " + username + " - was created.";
    }
    // ----- Track -----
    @POST
    @Path("addT/{title}/{artist}")
    @Produces("text/html")
    public String addTrack(@PathParam("title") String title, @PathParam("artist") String artist) {
    	System.out.println("Creating Track " + title);
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
    	System.out.println("Retrieving User N°" + id);
    	User user = userDao.read(id);
        return user.toString();
    }
    
    // ----- Track -----
    @GET
    @Path("showT/{id}")
    @Produces("text/html")
    public String showTrack(@PathParam("id") Long id) {
    	System.out.println("Retrieving Track N°" + id);
    	Track track = trackDao.read(id);
        return track.toString();
    }
    
    // ----- UPDATE -----------------------------
	
    // ----- User -----
    @PUT
    @Path("updateU/{id}{username}/{email}/{password}")
    @Produces("text/html")
    public String updateUser(@PathParam("id") Long id, @PathParam("username") String username, @PathParam("email") String email, @PathParam("password") String password) {
    	System.out.println("Updating User N°" + id);
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
    	System.out.println("Updating Track N°" + id);
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
    	System.out.println("Deleting User N°" + id);
    	userDao.delete(id);
        return "User N°" + id + " was deleted.";
    }
    
    // ----- Track -----
    @DELETE
    @Path("deleteT/{id}")
    @Produces("text/html")
    public String deleteTrack(@PathParam("id") Long id) {
    	System.out.println("Deleting Track N°" + id);
    	trackDao.delete(id);
        return "Track N°" + id + "  was deleted.";
    }
    
  	// ----- ADD/DEL Track TO/FROM User --------------
	  
    // ----- ADD Track -----
    @PUT
    @Path("addT2U/{idTrack}/{idUser}")
    @Produces("text/html")
    public String addT2U(@PathParam("idTrack") Long idTrack, @PathParam("idUser") Long idUser) {
    	System.out.println("Retrieving Track N°" + idTrack + " and User N°" +  idUser);
    	User user	= userDao.read(idUser);
    	Track track	= trackDao.read(idTrack);
    	System.out.println("Adding Track N°" + idTrack + ": '" + track.getTitle() + "' to User N°" +  idUser + ": '" + user.getUsername() + "'");
    	user.getTracks().add(track);
    	System.out.println("Adding  User N°" +  idUser + ": '" + user.getUsername() + "' to Track N°" + idTrack + ": '" + track.getTitle() + "'");
    	track.getUsers().add(user);
    	userDao.merge(user);
    	trackDao.merge(track);
        return track.getTitle() + " was added to " + user.getUsername();
    }
    
    // ----- DEL Track -----
    @DELETE
    @Path("delT2U/{idTrack}/{idUser}")
    @Produces("text/html")
    public String delT2U(@PathParam("idTrack") Long idTrack, @PathParam("idUser") Long idUser) {
    	System.out.println("Retrieving Track N°" + idTrack + " and User N°" +  idUser);
    	User user 	= userDao.read(idUser);
    	Track track = trackDao.read(idTrack);
    	System.out.println("Deleting Track N°" + idTrack + ": '" + track.getTitle() + "' from User N°" +  idUser + ": '" + user.getUsername() + "'");
    	user.getTracks().remove(track);
    	System.out.println("Deleting  User N°" +  idUser + ": '" + user.getUsername() + "' from Track N°" + idTrack + ": '" + track.getTitle() + "'");
    	track.getUsers().remove(user);
    	userDao.merge(user);
    	trackDao.merge(track);
        return track.getTitle() + " was removed from " + user.getUsername();
    }   
    
    // ----- ADD/DEL User TO/FROM Track --------------
	  
    // ----- ADD User -----
    @PUT
    @Path("addU2T/{idUser}/{idTrack}")
    @Produces("text/html")
    public String addU2T(@PathParam("idUser") Long idUser, @PathParam("idTrack") Long idTrack) {
    	System.out.println("Retrieving User N°" + idUser + " and Track N°" +  idTrack);
    	User user	= userDao.read(idUser);
    	Track track	= trackDao.read(idTrack);
    	System.out.println("Adding  User N°" +  idUser + ": '" + user.getUsername() + "' to Track N°" + idTrack + ": '" + track.getTitle() + "'");
    	user.getTracks().add(track);
    	System.out.println("Adding Track N°" + idTrack + ": '" + track.getTitle() + "' to User N°" +  idUser + ": '" + user.getUsername() + "'");
    	track.getUsers().add(user);
    	userDao.merge(user);
    	trackDao.merge(track);
        return user.getUsername() + " was added to " + track.getTitle();
    }
    
    // ----- DEL User -----
    @DELETE
    @Path("delU2T/{idUser}/{idTrack}")
    @Produces("text/html")
    public String delU2T(@PathParam("idUser") Long idUser, @PathParam("idTrack") Long idTrack) {
    	System.out.println("Retrieving User N°" +  idUser + " and Track N°" + idTrack);
    	User user 	= userDao.read(idUser);
    	Track track = trackDao.read(idTrack);
    	System.out.println("Deleting  User N°" +  idUser + ": '" + user.getUsername() + "' from Track N°" + idTrack + ": '" + track.getTitle() + "'");
    	user.getTracks().remove(track);
    	System.out.println("Deleting Track N°" + idTrack + ": '" + track.getTitle() + "' from User N°" +  idUser + ": '" + user.getUsername() + "'");
    	track.getUsers().remove(user);
    	userDao.merge(user);
    	trackDao.merge(track);
        return user.getUsername() + " was removed from " + track.getTitle();
    }   
  
  	// ----- LIST -------------------------------
	  
    // ----- User -----
    @GET
    @Path("listU")
    @Produces("text/html")
    public String listUser() {
    	System.out.println("Listing Users");
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
    	System.out.println("Listing Tracks");
    	String trackList = "";
    	for(Track track : trackDao.list()) {
    		trackList += track.toString();
    		trackList += "\n";
    	}
        return trackList;
    }
    
}
