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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

/**
 * @author tbrou
 *
 */
@Entity
@Table(name="IAM2017.IDENTITIES")
public class Identity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IDENTITY_ID")
	private long id;
	
	@Column(name="IDENTITIES_UID")
	private String uid;
	
	@Column(name="DISPLAY_NAME")
	private String displayName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ISADMIN")
	private boolean isAdmin;
	
	@OneToMany
	//(fetch=FetchType.EAGER)
	//manytomany
	@JoinTable(name="iam2017.identity_address",
		joinColumns = @JoinColumn( name="identity_id"),
	    inverseJoinColumns = @JoinColumn( name="address_id")
	 )
	private Set<Address> addresses;
	 /**
	 * Default constructor
	 */
	public Identity() {
		
	}
	
	
	/**
	 * @param uid
	 * @param displayName
	 * @param email
	 */
	public Identity(String uid, String displayName, String email) {
		this.uid = uid;
		this.displayName = displayName;
		this.email = email;
	}
	
	public Identity(String username, String password) {
		
		this.username = username;
		this.password = password;
	}
	
	public Identity(String uid, String displayName, String email, String password) {
		super();
		this.uid = uid;
		this.displayName = displayName;
		this.email = email;
		this.password = password;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	public Set<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	public void setAddress(Address address) {
		if (this.addresses == null){
			this.addresses = new HashSet<Address>();
		}
		this.addresses.add(address);
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [uid=" + uid + ", displayName=" + displayName + 
				", email=" + email + ", addresses="+ addresses+"]";
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	

}