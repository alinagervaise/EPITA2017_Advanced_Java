/**
 * 
 */
package fr.epita.iam.datamodel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.epita.iam.utils.Constants;

import javax.persistence.JoinColumn;

/**
 * @author tbrou
 *
 */
@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private long id;

	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	
	 @OneToOne
	 @MapsId(value="IDENTITY_ID")
     Identity identity;
	 
	 @ManyToOne
	 @JoinColumn(name="USER_ROLE", nullable=false)
	 private Role role;


	 /**
	 * Default constructor
	 */
	public User() {
		
	}
	
	
	/**
	 * @param username
	 * @param password
	 */
	public User( String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}


	
	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Identity getIdentity() {
		return identity;
	}


	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

		
	public boolean isAdmin(){
		if (this.role == null) {
			return false;
		}
		return (this.role.getId() == Constants.IS_ADMIN);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username+  ", password="+ password+"]";
	}

}