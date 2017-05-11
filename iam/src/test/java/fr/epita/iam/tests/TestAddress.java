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
import java.util.Set;

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
		
		//Create Address 2
		Address address2 = new Address();
		address2.setStreet("16, rue Voltaire");
		address2.setCity("Kremlin-Bicêtre");
		address2.setZipCode("94270");
		address2.setCountry("France");
		daoAddress.write(address2);
		
		identity.setAddress(address1);
		identity.setAddress(address2);
		daoHibernate.update(identity);
		
		List<Identity> results = daoHibernate.search(identity);
		Assert.assertTrue(results != null && !results.isEmpty());
		Assert.assertEquals(1, results.size());
		
		Identity result = results.get(0);
		Assert.assertEquals("Thomas Broussard", result.getDisplayName());
		
		Set<Address> i_Addresses = result.getAddresses();
		Assert.assertTrue(i_Addresses  != null && !i_Addresses.isEmpty());
		LOGGER.info("---------"+i_Addresses);
		Assert.assertEquals(2, i_Addresses.size());
		
	
	}

}
