/**
 * 
 */
package fr.epita.iam.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.Dao;
import fr.epita.iam.services.HibernateDAO;
import fr.epita.iam.services.IdentityJDBCDAO;

/**
 * @author tbrou
 *
 */


@WebServlet(name="AuthenticationServlet", urlPatterns={"/","/authenticate"})
public class AuthenticationServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	Dao<Identity> dao1;
	
	private static final Logger LOGGER = LogManager.getLogger(AuthenticationServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
	            "classpath*:**/applicationContext*.xml");

		HibernateDAO dao = (HibernateDAO) context.getBean("daoHibernate");
		List<Identity> results;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Identity identity = new Identity(username, password);
		results = (List<Identity>)dao.search(identity);
		if (results == null || results.size()==0){
			String error_msg = "Authentification failure for user "+ username;
			LOGGER.info("Login attempt failure for user {}", username + " "+password);
			response.setContentType("text/html");
			request.getRequestDispatcher("index.html").include(request, response);  
		}
		else {
		    Identity user = results.get(0);
		    LOGGER.info("Authentification success for user {}", username + " "+password);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("user-home.html");
		    if (user.isAdmin()){
		    	dispatcher = request.getRequestDispatcher("admin-home.html");
		    }
		    request.setAttribute("username", user.getDisplayName());
		    dispatcher.include(request, response);
		 
		}
			
			
		
		
	}
	
	 @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	               throws IOException, ServletException {
	      // Set the response message's MIME type
		  response.setContentType("text/html");
	      //response.sendRedirect("index.html");
		  request.getRequestDispatcher("index.html").include(request, response);  
	 }


}
