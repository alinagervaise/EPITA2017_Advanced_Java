/**
 * 
 */
package fr.epita.iam.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import fr.epita.iam.datamodel.Address;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.datamodel.User;
import fr.epita.iam.services.DefaultDAO;
import fr.epita.iam.services.WhereClauseBuilder;
import fr.iam.iam.services.business.IdentityService;


/**
 * @author tbrou
 *
 */

@WebServlet(name="AuthenticationServlet", urlPatterns={"/search"})
public class SearchIdentityServlet extends BaseServlet{

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
	
	@Inject
	SessionFactory sf;
	
	private static final Logger LOGGER = LogManager.getLogger(SearchIdentityServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the user name from the current session
		HttpSession session = request.getSession();
		//System.out.println("-------------------------------"+request.getHeader("referer"));
		List<Identity>identities, resultIdentities;
		List<Address> addresses , resultAddresses;
		resultIdentities = new ArrayList<>();
		resultAddresses = new ArrayList<>();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("search-identity.jsp");
		boolean searchByIdentity = IdentityService.searchByIdentity(request);
		boolean searchByAddress = IdentityService.searchByAddress(request);
		String error_msg ;
	    if (!searchByIdentity && !searchByAddress){
	    	error_msg = "Please enter a search criteria";
			LOGGER.debug(error_msg);
			request.setAttribute("error_msg", error_msg);
			dispatcher.include(request, response);
			return;
	    }
	    else if (searchByAddress){
	    	addresses = IdentityService.findAddress(request, addressDao);
	    	for(Address address : addresses){
	    		resultIdentities.add(address.getIdentity());
	    	}
	    	if (resultIdentities.isEmpty()){
	    		error_msg = "Could not find result for your search";
	    		LOGGER.debug(error_msg);
				request.setAttribute("error_msg", error_msg);
	    	}
			
	    	request.setAttribute("identities", resultIdentities);
	    	dispatcher.include(request, response);
			return;
	    }
	    else if (searchByIdentity){
	    	identities = IdentityService.findIdentity(request, identityDao);
	    	System.out.println("-------------------------------"+identities.size());
	    	if (identities.isEmpty()){
	    		error_msg = "Could not find result for your search";
	    		LOGGER.debug(error_msg);
				request.setAttribute("error_msg", error_msg);
	    	}
			
	    	request.setAttribute("identities", identities);
	    	dispatcher.include(request, response);
			return;
	    }
	    identities = IdentityService.findIdentity(request, identityDao);
	    addresses = IdentityService.findAddress(request, addressDao);
		for (Identity identity : identities){
			Set<Address> iAddresses = identity.getAddresses();
			for (Address address: addresses){
				if (!iAddresses.contains(address)){
					resultIdentities.add(identity);
				}
			}
		}
		if (resultIdentities.isEmpty()){
    		error_msg = "Could not find result for your search";
    		LOGGER.debug(error_msg);
			request.setAttribute("error_msg", error_msg);
    	}
		
		request.setAttribute("identities", resultIdentities);
		dispatcher.include(request, response);
		
	}

	
	
	 @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	               throws IOException, ServletException {
	      // Set the response message's MIME type
		  response.setContentType("text/html");
		  request.getRequestDispatcher("search-identity.jsp").include(request, response);  
	 }


}
