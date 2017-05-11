/**
 * 
 */
package fr.epita.iam.services;

import java.util.List;

import fr.epita.iam.datamodel.Identity;

/**
 * @author tbrou
 *
 */
public interface Dao<T> {
	
	public void write(T instance);
	public void delete(T instance);
	public void update(T instance);
	public List<T> search(T instance);
	public List<T> search(T instance, String separator, String ...criterias) throws Exception;

}
