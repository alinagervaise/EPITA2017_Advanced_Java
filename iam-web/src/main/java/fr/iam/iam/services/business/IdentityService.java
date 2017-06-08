package fr.iam.iam.services.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import fr.epita.iam.datamodel.Address;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.datamodel.User;
import fr.epita.iam.services.DefaultDAO;

@Service
public  class IdentityService {
	private IdentityService(){
		
	}
	public static boolean searchByIdentity(HttpServletRequest request) {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String birthdate = request.getParameter("birthdate");
		
		if (firstname != null && (!firstname.isEmpty())){
			return true;
		}
		if (lastname != null && (!lastname.isEmpty())){
			return true;
		}
		if (email != null && (!email.isEmpty())){
			return true;
		}
		if (birthdate != null  && (!birthdate.isEmpty())){
			return true;
		}
		return false;
	}
		
	public static boolean searchByAddress(HttpServletRequest request) {
		String street = request.getParameter("street");
		String zipCode = request.getParameter("zipCode");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		
		if (street != null && (!street.isEmpty())){
			return true;
		}
		if (zipCode != null  && (!zipCode.isEmpty())){
			return true;
		}
		if (city != null && (!city.isEmpty())){
			return true;
		}
		if (country != null  && (!country.isEmpty())){
			return true;
		}
		return false;
	}
	public static List<Identity> findIdentity(HttpServletRequest request, DefaultDAO<Identity>identityDao) {
		System.out.println("Request params "+request.getParameterNames());
		String firstname = (String)request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String birthdate = request.getParameter("birthdate");

		List<Identity> identities = new ArrayList<>();
		Identity identity = new Identity("", firstname, email);
		SimpleDateFormat sm = new SimpleDateFormat("dd/mm/yyyy");
		try {
			Date date = (birthdate == null || birthdate.trim().isEmpty())? null :sm.parse(birthdate);
			identity.setBirthdate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		identity.setFirstname(firstname);
		identity.setLastname(lastname);
		
		List<String> args = new ArrayList<>();
		if (firstname !=null && !firstname.isEmpty()){
			args.add("firstname");
		}
		if (lastname !=null && !lastname.isEmpty()){
			args.add("lastname");
		}
		if (email !=null && !email.isEmpty()){
			args.add("email");
		}
		if (birthdate !=null && !birthdate.isEmpty()){
			args.add("birthdate");
		}
		
		try {
			for (String a: args){
			System.out.println("SEARCH--------------"+a);
			}
			System.out.println("SEARCH--------------"+identity);
			identities =  identityDao.search(identity,  "and", args);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (HibernateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return identities;
	}
	
	public static List<Address> findAddress(HttpServletRequest request, DefaultDAO<Address> addressDao) {
		List<Address> addresses = new ArrayList<>();
		String street = request.getParameter("street");
		String zipCode = request.getParameter("zipCode");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		Address address = new Address(street, zipCode, city, country);
		List<String> args = new ArrayList<>();
		if (street !=null && !street.isEmpty()){
			args.add("street");
		}
		if (zipCode !=null && !zipCode.isEmpty()){
			args.add("zipCode");
		}
		if (city !=null && !city.isEmpty()){
			args.add("city");
		}
		if (country !=null && !country.isEmpty()){
			args.add("country");
		}
		
		try {
			addresses =  addressDao.search(address,  "and", args);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (HibernateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return addresses;
	}


}
