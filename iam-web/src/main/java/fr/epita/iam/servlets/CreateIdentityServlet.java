/**
 * 
 */
package fr.epita.iam.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.iam.datamodel.Address;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.datamodel.User;
import fr.epita.iam.services.DefaultDAO;
import fr.iam.iam.services.business.IdentityService;


/**
 * @author tbrou
 *
 */

@WebServlet(name="AuthenticationServlet", urlPatterns={"/create"})
public class CreateIdentityServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	//IdentityDAO identityDao;
	DefaultDAO<Identity>identityDao;

	@Inject
	//AddressDAO addressDao;
	DefaultDAO<Address>addressDao;
	
	@Inject
	//UserDAO userDao;
	DefaultDAO<User>userDao;
	
	
	private static final Logger LOGGER = LogManager.getLogger(CreateIdentityServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the user name from the current session
		HttpSession session = request.getSession();
		String username =  (String)session.getAttribute("username");
		String displayName =  (String)session.getAttribute("displayName");
		
		List<User> results = new ArrayList<User>();
		User user = new User(username, "");
		try {
			results = (List<User>)userDao.search(user, "and", "username");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (results == null || results.isEmpty()){
			String error_msg = "Could not find user when creating identity "+ username;
			LOGGER.debug(error_msg);
			response.setContentType("text/html");
			//request.setAttribute("error_msg", error_msg);
			//request.getRequestDispatcher("index.jsp").include(request, response);  
		}
		else {
		    user = results.get(0);
		    System.out.println("Request params "+request.getParameterNames());
		    String firstname = (String)request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String birthdate = request.getParameter("birthdate");
			String street = request.getParameter("street");
			String zipCode = request.getParameter("zipCode");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			
			Identity identity = new Identity("", displayName, email);
			SimpleDateFormat sm = new SimpleDateFormat("dd/mm/yyyy");
			try {
				if (birthdate == null || birthdate.isEmpty()){
					identity.setBirthdate(null);
				}
				else{
					identity.setBirthdate(sm.parse(birthdate));
				}
			} catch (ParseException e) {
				identity.setBirthdate(null);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			identity.setFirstname(firstname);
			identity.setLastname(lastname);
			identityDao.write(identity);
			
			if ( IdentityService.searchByAddress(request)){
			Address address = new Address(street, zipCode, city, country);
			addressDao.write(address);
			
			address.setIdentity(identity);
			addressDao.update(address);
			//identity.setAddress(address);
			identityDao.update(identity);
			}
		
		    RequestDispatcher dispatcher = request.getRequestDispatcher("search-identity.jsp");
		    dispatcher.include(request, response);
		}	
	}
	
	 @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	               throws IOException, ServletException {
	      // Set the response message's MIME type
		  response.setContentType("text/html");
		  request.getRequestDispatcher("create-identity.jsp").include(request, response);  
	 }


}
