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

    @SuppressWarnings("ArgNamesErrorsInspection")
    @Before(value = "sellSquishee() && args(person)", argNames = "person")
    public void sayHello(JoinPoint joinPoint, Person person) {
        System.out.printf("Hello %s. How are you doing?%n", person.getName());
    }

    @AfterReturning(pointcut = "sellSquishee()",
            returning = "squishee", argNames = "squishee")
    public void askOpinion(Squishee squishee) {
        if (squishee != null) {
            String squisheeName = squishee.getName();
            System.out.printf("Is %s Good Enough?%n", squisheeName);
        }
    }

    @SuppressWarnings("ArgNamesErrorsInspection")
    @Around(value = "sellSquishee() && args(person)", argNames = "person")
    public Object sayYouAreNotAllowed(ProceedingJoinPoint pjp, Person person) throws Throwable {
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

    @SuppressWarnings("ArgNamesErrorsInspection")
    @Around(value = "sellSquishee() && args(person)", argNames = "person")
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp, Person person) throws Throwable {
        String personName = person.getName();
        System.out.printf("Hi, %s!%n", personName);
        Object retVal = pjp.proceed();
        System.out.printf("See you, %s!%n", personName);
        return retVal;
    }
}