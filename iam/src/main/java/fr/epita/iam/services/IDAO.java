/**
 * 
 */
package fr.epita.iam.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fr.epita.iam.datamodel.Identity;

/**
 * 
 * @author Gervaise ALINA
 * @param T:
 *
 */


public abstract class  IDAO<T> implements Dao<T>{
	
	@Inject
	SessionFactory sf;
	
	
	/*
	public void setSessionFactory(SessionFactory sf) {
		this.sf = sf;
	}
*/
	public void write(T instance){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(instance);
		transaction.commit();
		session.close();
	}
	
	public void update(T instance){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(instance);
		transaction.commit();
		session.close();
	}
	 
	public void delete(T instance){
		
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(instance);
		transaction.commit();
		session.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> search(T instance){
		List<T> identityList = new ArrayList<T>();
		Session session = sf.openSession();
		WhereClauseBuilder queryBuilder = new WhereClauseBuilder();
		Query query;
		try {
			query = queryBuilder.getWhereClause(instance, session);
			identityList = query.list();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.close();
		return identityList;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> search(T instance, String separator, String ...criterias) throws Exception{
		
		if (!(separator.trim().equalsIgnoreCase("and") || separator.trim().equalsIgnoreCase("or")) ){
			throw new  Exception();
		}
		Session session = sf.openSession();
		List<T> resultList = new ArrayList<T>();
		
		try {
			WhereClauseBuilder cBuilder = new WhereClauseBuilder();
			Query query = cBuilder.getWhereClause(instance, session, separator, criterias);
			resultList = query.list();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.close();
		return resultList;
	}
	@SuppressWarnings("unchecked")
	public List<T> search(T instance, String separator, List<String>criterias) throws Exception{
		
		if (!(separator.trim().equalsIgnoreCase("and") || separator.trim().equalsIgnoreCase("or")) ){
			throw new  Exception();
		}
		Session session = sf.openSession();
		List<T> resultList = new ArrayList<T>();
		
		try {
			WhereClauseBuilder cBuilder = new WhereClauseBuilder();
			Query query = cBuilder.getWhereClause(instance, session, separator, criterias);
			resultList = query.list();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.close();
		return resultList;
	}


}
