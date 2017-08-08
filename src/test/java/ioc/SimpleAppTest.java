package ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static ioc.HelloWorldTest.getExpectedPerson;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleAppTest {

    private static final String CLASSPATH_APPLICATION_CONTEXT_XML =
            "ioc.xml";

    private BeanFactory context =
            new ClassPathXmlApplicationContext(
                    CLASSPATH_APPLICATION_CONTEXT_XML);

    @Test
    void testInitPerson() {
        assertEquals(getExpectedPerson(), context.getBean("person"));
    }
}
