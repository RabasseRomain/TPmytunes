package UserData;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@ManagedBean
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
	@OneToMany(mappedBy = "user")
	private List<Track> Tracks;	
	
	// ----- Constructor ------------------------
	
	public User() {
		// Empty constructor for testing purpose
	}
	
	// ----- ToString ---------------------------
	
	@Override
	public String toString() {
		return "User [id: " + id + ", username: " + username + ", email: " + email + ", password: " + password + ", Tracks: " + Tracks + "]";
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
		return Tracks;
	}

	public void setTracks(List<Track> tracks) {
		Tracks = tracks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}
	
}
