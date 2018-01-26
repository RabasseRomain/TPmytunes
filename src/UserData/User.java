package UserData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
//import javax.persistence.CascadeType;
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
public class User implements Serializable {
	
	// ----- Parameters -------------------------
	
	private static final long serialVersionUID = -2097846259608849701L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String username;
	@Column
	private String email;
	@Column
	private String password;
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER) //cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, 
	private List<Track> tracks = new ArrayList<Track>();	
	
	// ----- Constructor ------------------------
	
	public User() {
		// Empty constructor for testing purpose
	}
	
	// ----- ToString ---------------------------
	
	@Override
	public String toString() {
		String returnString = "User " 
								+ "\n\t id: " + id 
								+ "\n\t username: " + username 
								+ "\n\t email: " + email 
								+ "\n\t password: " + password 
								+ "\n\t Tracks: \n\t\t";
		for(Track track : tracks) {
			returnString += track.getTitle() + "\n\t\t";
		}
		return returnString;
	}
	
	// ----- Getters and Setters ----------------
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
