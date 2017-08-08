import lab.model.simple.SimpleCountry;
import lab.model.Person;
import lab.model.simple.SimplePerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldTest {

	private static final String APPLICATION_CONTEXT_XML_FILE_NAME = "application-context.xml";

	private BeanFactory context;

	@BeforeEach
	void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);
	}

	@Test
	void testInitPerson() {
		assertEquals(getExpectedPerson(),
				context.getBean("person"));
	}

	static Person getExpectedPerson() {
		return new SimplePerson(
		        1,
                "John Smith",
                new SimpleCountry(
                        1,
                        "Russia",
                        "RU"),
                35,
                1.78f,
                true,
                Arrays.asList("kjhdfga@kjfg.ru")
        );
	}
}
