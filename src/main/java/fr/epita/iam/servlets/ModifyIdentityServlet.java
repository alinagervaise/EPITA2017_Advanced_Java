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


/**
 * @author tbrou
 *
 */

@WebServlet(name="AuthenticationServlet", urlPatterns={"/modify"})
public class ModifyIdentityServlet extends BaseServlet{

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
	
	
	private static final Logger LOGGER = LogManager.getLogger(ModifyIdentityServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the user name from the current session
		HttpSession session = request.getSession();
		String username =  (String)session.getAttribute("username");
		String displayName =  (String)session.getAttribute("displayName");
		String id = request.getParameter("id");
		List<User> results = new ArrayList<User>();
		User user = new User(username, "");
	
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
			
			Identity identity = new Identity("", firstname, email);
			SimpleDateFormat sm = new SimpleDateFormat("dd/mm/yyyy");
			try {
				identity.setBirthdate(sm.parse(birthdate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			identity.setId(Long.parseLong(id));
			identity.setFirstname(firstname);
			identity.setLastname(lastname);
			identityDao.update(identity);
			
			 Address address = new Address();
			  try {
				List<Identity> identitiesFound = identityDao.search(identity, "and", "id");
				Identity result = identitiesFound.get(0);
				if (!result.getAddresses().isEmpty()){
					address = result.getAddresses().stream().findFirst().get();
				}
			  }catch(Exception ex){
				  LOGGER.debug(ex.getMessage());
			  }
			address.setStreet(street);
			address.setCity(city);
			address.setZipCode(zipCode);
			address.setCountry(country);
			
			addressDao.update(address);
			
			identity.setAddress(address);
			identityDao.update(identity);
		
		    RequestDispatcher dispatcher = request.getRequestDispatcher("search-identity.jsp");
		    dispatcher.include(request, response);
			
	}
	
	 @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	               throws IOException, ServletException {
		  String id = request.getParameter("id");
		  Identity identity = new Identity();
		  identity.setId(Long.parseLong(id));
		  Address address = new Address();
		  try {
			List<Identity> results = identityDao.search(identity, "and", "id");
			Identity result = results.get(0);
			if (!result.getAddresses().isEmpty()){
				address = result.getAddresses().stream().findFirst().get();
			}
			request.setAttribute("identity", result);
			request.setAttribute("address", address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  response.setContentType("text/html");
		  request.getRequestDispatcher("modify-identity.jsp").include(request, response);  
	 }


}
