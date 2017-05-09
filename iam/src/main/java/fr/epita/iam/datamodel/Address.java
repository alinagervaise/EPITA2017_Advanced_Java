package fr.epita.iam.datamodel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="iam2017.Address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADDRESS_ID")
	private long addressId;
	
	@Column(name="STREET")
	private String street;
	
	@Column(name="ZIPCODE")
	private String zipCode;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="COUNTRY")
	private String country;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	private Identity identity;
	public Address(){
		
	}
    public Address(long id, String street, String zipCode, String city, String country) {
		super();
		this.addressId = id;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}
	/**
     * 
     * @return
     */
	public long getAddressId() {
		return addressId;
	}
	/**
	 * 
	 * @param addressId
	 */
	public void setAddressId(long id) {
		this.addressId = id;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + (int) (addressId ^ (addressId >>> 32));
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (addressId != other.addressId)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", zipCode=" + zipCode + ", city=" + city + ", country="
				+ country + "]";
	}
	
	
	

}
