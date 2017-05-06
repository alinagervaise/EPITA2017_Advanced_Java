/**
 * 
 */
package fr.epita.iam.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author tbrou
 *
 */
public class TestLog4j2Usage {

	private static final Logger LOGGER = LogManager.getLogger(TestLog4j2Usage.class);
	
	
	@Test
	public void testLog4J2(){
		LOGGER.info("this is an info message with parameter : {}", "parameter");
		
	}
	
}
