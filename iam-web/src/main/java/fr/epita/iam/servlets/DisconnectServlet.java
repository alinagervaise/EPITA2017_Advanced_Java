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

@WebServlet(name="DisconnectServlet", urlPatterns={ "/disconnect"})
public class DisconnectServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(DisconnectServlet.class);
	
	
	 @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	               throws IOException, ServletException {
	      // Set the response message's MIME type
		 HttpSession session = request.getSession(); 
         session.invalidate();  
		  request.logout();
		  response.setContentType("text/html");
		  request.setAttribute("logout_msg", "Succcessful logout");
		  request.getRequestDispatcher("index.jsp").include(request, response);  
		  //response.sendRedirect("index.jsp");
	 }


}
