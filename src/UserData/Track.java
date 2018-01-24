package UserData;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@ManagedBean
@Entity
@Table
public class Track implements Serializable {

	// ----- Parameters -------------------------
	
	private static final long serialVersionUID = -8345584659457255567L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String title;
	@Column
	private String artist;
	@ManyToOne
	private User user;	
	
	// ----- Constructor ------------------------
	
	public Track() {
		// Empty constructor for testing purpose
	}

	// ----- ToString ---------------------------
	
	@Override
	public String toString() {
		return "Track [id: " + id + ", title: " + title + ", artist: " + artist + ", user: " + user + "]";
	}
	
	// ----- Getters and Setters ----------------
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

}
