/**
 * 
 */
package fr.epita.iam.services;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author tbrou
 *
 */
public class WhereClauseBuilder {
	
	private static final Logger LOGGER = LogManager.getLogger(WhereClauseBuilder.class);

	
	
	public Query getWhereClause(Object obj, Session session) throws IllegalArgumentException, IllegalAccessException{
		return getWhereClause(obj, session, "and");
		
	}
	
	public Query getWhereClause(Object obj, Session session, String separator) throws IllegalArgumentException, IllegalAccessException{
		String queryString = "";
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			String name = field.getName();
			Object fieldValue = field.get(obj);
			String fieldWhereclause = name +" =  :" +name;
			values.put(name, fieldValue);
			queryString += " "+separator+ " "  + fieldWhereclause;
		}
		if (!queryString.isEmpty()){
			queryString = "from " + obj.getClass().getSimpleName() + " where 1=1 " + queryString;
		}
		Query query = session.createQuery(queryString);
		String[] namedParameters = query.getNamedParameters();
		for (String parameter : namedParameters) {
			query.setParameter(parameter, values.get(parameter));
		}
		
		
		return query;
		
		
	}
	public Query getWhereClause(Object obj, Session session, String separator, String ... properties) throws IllegalArgumentException, IllegalAccessException{
		String queryString = "";
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		
		for (String property : properties){
			if (property == null || property.isEmpty()){
				continue;
			}
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				String name = field.getName();
	
				if (property.trim().equalsIgnoreCase(name)){
					Object fieldValue = field.get(obj);
					String fieldWhereclause = name +" =  :" +name;
					if (values.isEmpty()){
						queryString += " "  + fieldWhereclause;
					}
					else{
						queryString += " "+separator+ " "  + fieldWhereclause;
					}
					values.put(name, fieldValue);
					
					
				}
			}
		}
		if (!queryString.isEmpty()){
			queryString = "from " + obj.getClass().getSimpleName() + " where  " + queryString;
		}
		Query query = session.createQuery(queryString);
		String[] namedParameters = query.getNamedParameters();
		for (String parameter : namedParameters) {
			query.setParameter(parameter, values.get(parameter));
		}
		return query;
		
		
	}
	
	public Query getWhereClause(Object obj, Session session, String separator, List<String> properties) throws IllegalArgumentException, IllegalAccessException{
		String queryString = "";
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		
		for (String property : properties){
			if (property == null || property.isEmpty()){
				continue;
			}
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				String name = field.getName();
				if (property.trim().equalsIgnoreCase(name)){
					Object fieldValue = field.get(obj);
					String fieldWhereclause;
					if (field.get(obj) != null  && field.get(obj).getClass().equals(String.class)){
						fieldWhereclause =  "Lower("+name+") = Lower(:" +name+")";
						
					}
					else{
						System.out.println("WHERE-------------------------"+field.get(obj)+field.get(obj).getClass());
						fieldWhereclause =  name+" = :" +name;
					}
					if (values.isEmpty()){
						queryString += " "  + fieldWhereclause;
					}
					else{
						queryString += " "+separator+ " "  + fieldWhereclause;
					}
					values.put(name, fieldValue);
		
				}
			}
		}
		if (!queryString.isEmpty()){
			queryString = "from " + obj.getClass().getSimpleName() + " where  " + queryString;
		}
		Query query = session.createQuery(queryString);
		String[] namedParameters = query.getNamedParameters();
		for (String parameter : namedParameters) {
			
			query.setParameter(parameter, values.get(parameter));
		}
		return query;
		
		
	}

}
