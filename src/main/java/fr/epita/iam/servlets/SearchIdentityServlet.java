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
		String username =  (String)session.getAttribute("username");
		String displayName =  (String)session.getAttribute("displayName");
		List<Identity>identities, resultIdentities;
		List<Address> addresses , resultAddresses;
		resultIdentities = new ArrayList<>();
		resultAddresses = new ArrayList<>();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("search-identity.jsp");
		boolean searchByIdentity = IdentityService.searchByIdentity(request);
		boolean searchByAddress = IdentityService.searchByAddress(request);
		
	    if (!searchByIdentity && !searchByAddress){
	    	System.out.println("POST-----1------------------------"+searchByIdentity + searchByAddress);
			dispatcher.include(request, response);
			return;
	    }
	    else if (searchByAddress){
	    	addresses = IdentityService.findAddress(request, addressDao);
	    	for(Address address : addresses){
	    		resultIdentities.add(address.getIdentity());
	    	}
	    	System.out.println("POST--------------2---------------"+searchByIdentity + searchByAddress+resultIdentities.size());
	    	request.setAttribute("identities", resultIdentities);
	    	dispatcher.include(request, response);
			return;
	    }
	    else if (searchByIdentity){
	    	identities = IdentityService.findIdentity(request, identityDao);
	    	System.out.println("POST--------------3---------------"+searchByIdentity + searchByAddress+identities.size());
	    	for(Identity i : identities){
	    		System.out.println("POST--------------3---------------"+i);
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
		System.out.println("POST--------------4---------------"+searchByIdentity + searchByAddress+resultIdentities.size());
		request.setAttribute("identities", resultIdentities);
		dispatcher.include(request, response);
		
	}

	
	
	 @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	               throws IOException, ServletException {
	      // Set the response message's MIME type
		  System.out.println("GET-----------------------------");
		  response.setContentType("text/html");
		  request.getRequestDispatcher("search-identity.jsp").include(request, response);  
	 }


}
