package UserBack;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import UserData.User;

@ManagedBean
@RequestScoped
public class UserController implements Serializable {

	// ----- Parameters -------------------------
	
    private static final long serialVersionUID = 1L;

    @EJB
    private UserDAO userDao;
    private User user;
	
	// ----- Constructor ------------------------
	
    public UserController() {
        user = new User();
    }
	
	// ----- CRUD Functions ---------------------
	
    public String add() {
    	userDao.add(user);
        return "HomePage";
    }
    
    public List<User> list() {
    	return userDao.list();
    }
    
    public String edit(Long id) {
    	userDao.edit(id, user);
        return "HomePage";
    }
    
    public String delete(Long id) {
    	userDao.delete(id);
        return "HomePage";
    }
		
	// ----- Getters and Setters ----------------
	
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

}