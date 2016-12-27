package org.sakaiproject.contentreview.logic;

import org.junit.Assert;
import org.junit.Before;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.contentreview.impl.turnitin.TurnitinReviewServiceImpl;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

public class TurnitinImplTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final Log log = LogFactory.getLog(TurnitinImplTest.class);
	
	protected String[] getConfigLocations() {
	      // point to the needed spring config files, must be on the classpath
	      // (add component/src/webapp/WEB-INF to the build path in Eclipse),
	      // they also need to be referenced in the maven file
	      return new String[] {"hibernate-test.xml", "spring-hibernate.xml"};
	   }
	
	public void testFileEscape() {
		TurnitinReviewServiceImpl tiiService = new TurnitinReviewServiceImpl();
		String someEscaping = tiiService.escapeFileName("Practical%203.docx", "contentId");
		Assert.assertEquals("Practical_3.docx", someEscaping);
		
		someEscaping = tiiService.escapeFileName("Practical%203%.docx", "contentId");
		Assert.assertEquals("contentId", someEscaping);
		
		someEscaping = tiiService.escapeFileName("Practical3.docx", "contentId");
		Assert.assertEquals("Practical3.docx", someEscaping);
	}
}
