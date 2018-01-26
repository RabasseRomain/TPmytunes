package UserData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	@ManyToMany(fetch = FetchType.EAGER)	// cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, 
	private List<User> users = new ArrayList<User>();	
	
	// ----- Constructor ------------------------
	
	public Track() {
		// Empty constructor for testing purpose
	}

	// ----- ToString ---------------------------
	
	@Override
	public String toString() {
		String returnString = "Track " 
								+ "\n\t id: " + id 
								+ "\n\t title: " + title 
								+ "\n\t artist: " + artist 
								+ "\n\t user: \n\t\t";
		for(User user : users) {
			returnString += user.getUsername() + "\n\t\t";
		}
		return returnString;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
