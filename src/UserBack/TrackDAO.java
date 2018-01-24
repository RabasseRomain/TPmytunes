package UserBack;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import UserData.Track;

@Stateless
public class TrackDAO{

	// ----- Parameters -------------------------
	
    @PersistenceContext(unitName = "TPmytunes")
    private EntityManager em;
	
	// ----- CRUD -------------------------------
	
    // ----- Create -----
    public void add(Track track) {
        em.persist(track);
    }

    // ----- Read -----
	public Track read(Long id) {
		return em.find(Track.class, id);
	}

    // ----- Update -----
    public void edit(Long id, Track newTrack) {
    	Track oldTrack = em.find(Track.class, id);
    	oldTrack.setTitle(newTrack.getTitle());
    	oldTrack.setArtist(newTrack.getArtist());
    	oldTrack.setUser(newTrack.getUser());  	
    }
    
    // ----- Delete -----
    public void delete(Long id) {
    	em.remove(em.find(Track.class, id));
    }
    
    // ----- List -----
    @SuppressWarnings("unchecked")
	public List<Track> list() {
		return em.createQuery("SELECT t FROM Track t").getResultList();
	}
    
}