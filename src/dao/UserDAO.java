package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import data.Track;
import data.User;

@Stateless
public class UserDAO{

	// ----- Parameters -------------------------
	
    @PersistenceContext(unitName = "TPmytunes")
    private EntityManager em;
    
	// ----- CRUD -------------------------------
	
    // ----- Create -----
    public void create(User user) {
        em.persist(user);
    }
    
    // ----- Read -----
	public User read(Long id) {
		return em.find(User.class, id);
	}
    
    // ----- Update -----
    public void update(Long id, User newUser) {
    	User oldUser = em.find(User.class, id);
    	oldUser.setUsername(newUser.getUsername ());
    	oldUser.setEmail(newUser.getEmail());
    	oldUser.setPassword(newUser.getPassword());  	
    	//em.merge(oldUser);
    }
    
    // ----- Delete -----
    public void delete(Long id) {
    	em.remove(em.find(User.class, id));
    }
	
	// ----- MISC -------------------------------
    
    // ----- List -----
    @SuppressWarnings("unchecked")
	public List<User> list() {
		return em.createQuery("SELECT u FROM User u").getResultList();
	}
    
    // ----- Add Track to User -----
    public void addT2U(Long idUser, Long idTrack) {
    	User user	= read(idUser);
    	Track track	= em.find(Track.class, idTrack);
    	user.getTracks().add(track);
    	track.getUsers().add(user);
    	//em.merge(user);
    	//em.merge(track);
    }
    
    // ----- Del Track From User -----
    public void delTFU(Long idUser, Long idTrack) {
    	User user 	= read(idUser);
    	Track track = em.find(Track.class, idTrack);
    	user.getTracks().remove(user.getTracks().indexOf(track));
    	track.getUsers().remove(track.getUsers().indexOf(user));
    	//em.merge(user);
    	//em.merge(track);
    }
    
}
