package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import data.Track;

@Stateless
public class TrackDAO{

	// ----- Parameters -------------------------
	
    @PersistenceContext(unitName = "TPmytunes")
    private EntityManager em;
	
	// ----- CRUD -------------------------------
	
    // ----- Create -----
    public void create(Track track) {
        em.persist(track);
    }

    // ----- Read -----
	public Track read(Long id) {
		return em.find(Track.class, id);
	}

    // ----- Update -----
    public void update(Long id, Track newTrack) {
    	Track oldTrack = em.find(Track.class, id);
    	oldTrack.setTitle(newTrack.getTitle());
    	oldTrack.setArtist(newTrack.getArtist());
    	//em.merge(oldTrack);
    }
    
    // ----- Delete -----
    public void delete(Long id) {
    	em.remove(em.find(Track.class, id));
    }
	
	// ----- MISC -------------------------------
    
    // ----- List -----
    @SuppressWarnings("unchecked")
	public List<Track> list() {
		return em.createQuery("SELECT t FROM Track t").getResultList();
	}
    
}
