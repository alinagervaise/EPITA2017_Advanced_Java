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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

/**
 * @author tbrou
 *
 */
@Entity
@Table(name="ROLE")
public class Role {
	
	@Id
	@Column(name="ROLE_ID", unique=true)
	private long id;

	@Column(name="TITLE")
	private String title;
	
	
	
	@ManyToMany
    @JoinTable(name="ROLE_PERMISSION")
    public Set<Permission> permissions;
	
	 @OneToMany
	 @JoinColumn(name="USER_ROLE") 
	 public Set<User> users;
	
	 /**
	 * Default constructor
	 */
	public Role() {
		
	}
	
	
	/**
	 * @param title
	 */
	public Role(long id, String title) {
		this.id = id;
		this.title = title;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}


	public Set<Permission> getPermissions() {
		return permissions;
	}


	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}


	public void setTitle(String title) {
		this.title = title;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", title=" + title + "]";
	}
	

}