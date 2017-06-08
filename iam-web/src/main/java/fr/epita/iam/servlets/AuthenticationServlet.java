/**
 * 
 */
package fr.epita.iam.servlets;

import java.io.IOException;
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

@WebServlet(name="AuthenticationServlet", urlPatterns={"/", "/authenticate"})
public class AuthenticationServlet extends BaseServlet{

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
	
	
	private static final Logger LOGGER = LogManager.getLogger(AuthenticationServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> results = new ArrayList<User>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);
		try {
			results = (List<User>)userDao.search(user, "and", "username", "password");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (results == null || results.isEmpty()){
			String error_msg = "Login attempt failure for user "+ username;
			LOGGER.info("Login attempt failure for user {}", username + " "+password);
			response.setContentType("text/html");
			request.setAttribute("error_msg", error_msg);
			request.getRequestDispatcher("index.jsp").include(request, response);  
		}
		else {
		    user = results.get(0);
		    HttpSession session = request.getSession();
		    session.setAttribute("current_user", user.getIdentity().getId());
		    session.setAttribute("displayName", user.getIdentity().getDisplayName());
		    session.setAttribute("username", username);
		    LOGGER.info("Authentification success for user {}", username + " "+password);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("user-home.jsp");
		    if (user.isAdmin()){
		    	dispatcher = request.getRequestDispatcher("admin-home.jsp");
		    }
		    request.setAttribute("username", user.getIdentity().getDisplayName());
		    dispatcher.include(request, response);
		 
		}
			
	}
	
	 @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	               throws IOException, ServletException {
	      // Set the response message's MIME type
		  response.setContentType("text/html");
		  request.getRequestDispatcher("index.jsp").include(request, response);  
		  //response.sendRedirect("index.jsp");
	 }


}
