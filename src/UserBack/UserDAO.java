package UserBack;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    // ----- List -----
    @SuppressWarnings("unchecked")
	public List<User> list() {
		return em.createQuery("SELECT u FROM User u").getResultList();
	}
}