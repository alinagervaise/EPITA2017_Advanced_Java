/**
 * 
 */
package fr.epita.iam.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import fr.epita.iam.datamodel.Identity;

/**
 * @author tbrou
 *
 */
@Service
public class HibernateDAO implements Dao<Identity>{
	
	@Inject
	
	SessionFactory sf;
	
	
	
	public void setSessionFactory(SessionFactory sf) {
		this.sf = sf;
	}

	public void write(Identity identity){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(identity);
		transaction.commit();
		session.close();
	}
	
	public void update(Identity identity){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(identity);
		transaction.commit();
		session.close();
	}
	 
	public void delete(Identity identity){
		
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(identity);
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Identity> search(Identity identity){
		Session session = sf.openSession();
		List<Identity> identityList = new ArrayList<Identity>();
		String queryString = "from Identity as identity";
		//String queryString = "from Identity as identity where identity.displayName like :displayName";
		//Query query = session.createQuery(queryString);
		WhereClauseBuilder cBuilder = new WhereClauseBuilder();
		try {
			Query query = cBuilder.getWhereClause(identity, session, "and", "username", "password");
			identityList = query.list();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//query.setParameter("displayName", "%" + identity.getDisplayName()+"%");
		
		session.close();
		return identityList;
	}

	public List<Identity> search(Identity instance, String separator, String... criterias) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
