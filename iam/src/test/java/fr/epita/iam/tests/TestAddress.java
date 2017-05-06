/**
 * 
 */
package fr.epita.iam.tests;

import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iam.datamodel.Address;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.AddressDAO;
import fr.epita.iam.services.Dao;
import fr.epita.iam.services.HibernateDAO;
import fr.epita.iam.services.IdentityJDBCDAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;

/**
 * @author Gervaise ALINA
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})

public class TestAddress {
	
	public static final Logger LOGGER = LogManager.getLogger(TestAddress.class);
	
	
	
	@Inject
	AddressDAO daoAddress;
	@Inject
	HibernateDAO daoHibernate;
	
	@Inject
	SessionFactory sf;
	
	@Test
	public void testCreateAddress(){
		// Create identity
		Identity identity = new Identity("123","Thomas Broussard",
									"tbroussard@natsystem.fr");
		daoHibernate.write(identity);
		
		//Create Address 1
		Address address1 = new Address();
		address1.setStreet("14, rue Voltaire");
		address1.setCity("Kremlin-Bicêtre");
		address1.setZipCode("94270");
		address1.setCountry("France");
		daoAddress.write(address1);
		
		//Create Address 1
		Address address2 = new Address();
		address2.setStreet("14, rue Voltaire");
		address2.setCity("Kremlin-Bicêtre");
		address2.setZipCode("94270");
		address2.setCountry("France");
		daoAddress.write(address2);
		
		address1.setIdentity(identity);
		address2.setIdentity(identity);
		daoAddress.update(address1);
		daoAddress.update(address2);
		
		List<Address> results = daoAddress.search(address2);
		Assert.assertTrue(results != null && !results.isEmpty());
		Assert.assertEquals(1, results.size());
		
		Address resultedAddress = results.get(0);
		Assert.assertEquals("Thomas Broussard", resultedAddress.getIdentity().getDisplayName());
		
		results = daoAddress.search(address2);
		Assert.assertTrue(results != null && !results.isEmpty());
		Assert.assertEquals(1, results.size());
		
		resultedAddress = results.get(0);
		Assert.assertEquals("Thomas Broussard", resultedAddress.getIdentity().getDisplayName());
		
		// test that when deleting and address , identity table remains the same
		daoAddress.delete(resultedAddress);
		List<Identity> identities = daoHibernate.search(identity);
		Assert.assertTrue(identities != null && !identities.isEmpty());
		Assert.assertEquals(1, identities.size());
		
		results = daoAddress.search(address1);
		Assert.assertTrue(results != null && !results.isEmpty());
		Assert.assertEquals(1, results.size());
	}

}
