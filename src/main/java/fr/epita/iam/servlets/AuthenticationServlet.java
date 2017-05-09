/**
 * 
 */
package fr.epita.iam.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("usernmae");
		String password = req.getParameter("password");
		Identity identity = new Identity("123","Thomas","email@gmail.com");
		ApplicationContext context = new ClassPathXmlApplicationContext(
	            "classpath*:**/applicationContext*.xml");

		HibernateDAO dao = (HibernateDAO) context.getBean("daoHibernate");
		
		//IdentityJDBCDAO dao = (IdentityJDBCDAO) context.getBean("dao");
		List<Identity> results;
			results = (List<Identity>)dao.search(identity);
			LOGGER.info("tried to authenticate with this login {}", username + " "+password);
			LOGGER.info("tried to authenticate with this login {}", results.get(0));
		
		
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
