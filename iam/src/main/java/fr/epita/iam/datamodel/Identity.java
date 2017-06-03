/**
 * 
 */
package fr.epita.iam.datamodel;

import java.util.Date;
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
@Table(name="IDENTITIES")
public class Identity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IDENTITY_ID")
	private long id;
	
	@Column(name="IDENTITY_UID")
	private String uid;
	
	@Column(name="DISPLAY_NAME")
	private String displayName;
	

	@Column(name="FIRSTNAME")
	private String firstname;
	
	@Column(name="LASTNAME")
	private String lastname;
	
	@Column(name="BIRTHDATE")
	private Date birthdate;
	
	@Column(name="EMAIL")
	private String email;
	

	@OneToMany
	@JoinTable(name="identity_address",
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


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public Date getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Identity [id=" + id + ", uid=" + uid + ", displayName=" + displayName + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", birthdate=" + birthdate + ", email=" + email + "]";
	}
	
	

}