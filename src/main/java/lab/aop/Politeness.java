package lab.aop;

import lab.model.Person;
import lab.model.Squishee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Politeness {

    @Pointcut("execution(* sellSquishee(..))")
    void sellSquishee() {
    }

    @Before("sellSquishee()")
    public void sayHello(JoinPoint joinPoint) {
        Person person = (Person) joinPoint.getArgs()[0];
        System.out.printf("Hello %s. How are you doing?%n", person.getName());
    }

    @AfterReturning(pointcut = "sellSquishee()",
            returning = "retVal", argNames = "retVal")
    public void askOpinion(Object retVal) {
        Squishee squishee = (Squishee) retVal;
        if (squishee != null) {
            String squisheeName = squishee.getName();
            System.out.printf("Is %s Good Enough?%n", squisheeName);
        }
    }

    @Around("sellSquishee()")
    public Object sayYouAreNotAllowed(ProceedingJoinPoint pjp) throws Throwable {
        Person person = (Person) pjp.getArgs()[0];
        if (person.isBroke()) {
            System.out.println("Hmmm...");
            return null;
        } else
            return pjp.proceed();
    }

    @After("sellSquishee()")
    public void sayGoodBye() {
        System.out.println("Good Bye!");
    }

    @Around("sellSquishee()")
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Hi!");
        Object retVal = pjp.proceed();
        System.out.println("See you!");
        return retVal;
    }
}