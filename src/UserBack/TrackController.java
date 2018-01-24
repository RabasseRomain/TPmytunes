package UserBack;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import UserData.Track;

@ManagedBean
@RequestScoped
public class TrackController implements Serializable {

	// ----- Parameters -------------------------
	
    private static final long serialVersionUID = 1L;

    @EJB
    private TrackDAO trackDao;
    private Track track;
	
	// ----- Constructor ------------------------
	
    public TrackController() {
        track = new Track();
    }
	
	// ----- CRUD Functions ---------------------
	
    public String add() {
    	trackDao.add(track);
        return "HomePage";
    }
    
    public List<Track> list() {
    	return trackDao.list();
    }
    
    public String edit(Long id) {
    	trackDao.edit(id, track);
        return "HomePage";
    }
    
    public String delete(Long id) {
    	trackDao.delete(id);
        return "HomePage";
    }
		
	// ----- Getters and Setters ----------------
	
    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public TrackDAO getTrackDao() {
        return trackDao;
    }

    public void setTrackDao(TrackDAO trackDao) {
        this.trackDao = trackDao;
    }

}