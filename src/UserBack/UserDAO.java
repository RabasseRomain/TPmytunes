package UserBack;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import UserData.Track;
import UserData.User;

@Stateless
public class UserDAO{

	// ----- Parameters -------------------------
	
    @PersistenceContext(unitName = "TPmytunes")
    private EntityManager em;
    
	// ----- CRUD -------------------------------
	
    // ----- Create -----
    public void add(User user) {
        em.persist(user);
    }
    
    // ----- Read -----
	public User read(Long id) {
		return em.find(User.class, id);
	}
    
    // ----- Update -----
    public void edit(Long id, User newUser) {
    	User oldUser = em.find(User.class, id);
    	oldUser.setUsername(newUser.getUsername ());
    	oldUser.setEmail(newUser.getEmail());
    	oldUser.setPassword(newUser.getPassword());  	
    }
    
    // ----- Delete -----
    public void delete(Long id) {
    	em.remove(em.find(User.class, id));
    }
	
	// ----- MISC -------------------------------
	
    // ----- Merge -----
    public void merge(User user) {
    	em.merge(user);
    }
    
    // ----- Persist -----
    public void saveU(User user) {
    	em.persist(user);
    }
    
    // ----- List -----
    @SuppressWarnings("unchecked")
	public List<User> list() {
		return em.createQuery("SELECT u FROM User u").getResultList();
	}
    
    // ----- Add Track -----
    public void addT(Long idUser, Long idTrack) {
    	User user	= read(idUser);
    	Track track	= em.find(Track.class, idTrack);
    	user.getTracks().add(track);
    	track.getUsers().add(user);
    }
    
    // ----- Del Track -----
    public void delT(Long idUser, Long idTrack) {
    	User user 	= read(idUser);
    	Track track = em.find(Track.class, idTrack);
    	user.getTracks().remove(user.getTracks().indexOf(track));
    	track.getUsers().remove(track.getUsers().indexOf(user));
    }
    
}
