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
public class PostmanRequest {
	
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
    @Produces("application/json")
    public User addUser(@PathParam("username") String username, @PathParam("email") String email, @PathParam("password") String password) {
    	User user = new User();
    	user.setUsername(username);
    	user.setEmail(email);
    	user.setPassword(password);
        userDao.add(user);
        return showUser(username);
    }
    // ----- Track -----
    @POST
    @Path("addT/{title}/{artist}/{user}")
    @Produces("application/json")
    public Track addTrack(@PathParam("title") String title, @PathParam("artist") String artist, @PathParam("userId") Long id) {
    	Track track = new Track();
    	track.setTitle(title);
    	track.setArtist(artist);
    	track.setUser(userDao.read(id));
        trackDao.add(track);
        return showTrack(title);
    }
    
    // ----- READ -------------------------------
	
    // ----- User -----
    @GET
    @Path("showU/{username}")
    @Produces("application/json")
    public User showUser(@PathParam("username") String username) {
    	User userReturn = new User();
        for(User user : userDao.list()) {
        	if(user.getUsername().equals(username)) { userReturn = user; }
        }
        return userReturn;
    }
    
    // ----- Track -----
    @GET
    @Path("showT/{title}")
    @Produces("application/json")
    public Track showTrack(@PathParam("title") String title) {
    	Track track = new Track();
        for(Track trackL : trackDao.list()) {
        	if(track.getTitle().equals(title)) { return track = trackL; }
        }
        return track;
    }
    
    // ----- UPDATE -----------------------------
	
    // ----- User -----
    @PUT
    @Path("updateU/{id}{username}/{email}/{password}")
    @Produces("application/json")
    public User updateUser(@PathParam("id") Long id, @PathParam("username") String username, @PathParam("email") String email, @PathParam("password") String password) {
    	User user = new User();
    	user.setUsername(username);
    	user.setEmail(email);
    	user.setPassword(password);
        userDao.edit(id, user);
        return showUser(username);
    }
    
    // ----- Track -----
    @PUT
    @Path("addT/{id}/{title}/{artist}/{user}")
    @Produces("application/json")
    public Track updateTrack(@PathParam("id") Long id, @PathParam("title") String title, @PathParam("artist") String artist, @PathParam("userId") Long userId) {
    	Track track = new Track();
    	track.setTitle(title);
    	track.setArtist(artist);
    	track.setUser(userDao.read(userId));
        trackDao.edit(id, track);
        return showTrack(title);
    }
	// ----- DELETE -----------------------------
	
    // ----- User -----
    @DELETE
    @Path("deleteU/{id}")
    @Produces("text/html")
    public String deleteUser(@PathParam("id") Long id) {
    	userDao.delete(id);
        return "User was deleted.";
    }
    
    // ----- Track -----
    @DELETE
    @Path("deleteT/{id}")
    @Produces("text/html")
    public String deleteTrack(@PathParam("id") Long id) {
    	trackDao.delete(id);
        return "Track was deleted.";
    }
    
  	// ----- ADD Track TO User -----------------------------
	  
    // ----- Track -----
    @PUT
    @Path("addT2U/{idUser}/{idTrack}")
    @Produces("application/json")
    public String addT2U(@PathParam("idUser") Long idUser, @PathParam("idTrack") Long idTrack) {
    	User user = userDao.read(idUser);
    	user.getTracks().add(trackDao.read(idTrack));
        return trackDao.read(idTrack).getTitle() + " was added to " + user.getUsername();
    }
     
}

