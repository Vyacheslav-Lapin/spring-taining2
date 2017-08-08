package lab.aop;


import lab.model.Person;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import lab.model.Squishee;

@Aspect
public class Politeness {

    @Before("execution(* sellSquishee(..))")
    public void sayHello(JoinPoint joinPiont) {
        System.out.printf("Hello %s. How are you doing?%n",
                ((Person) joinPiont.getArgs()[0]).getName());
    }

    @AfterReturning(pointcut = "execution(* sellSquishee(..))",
            returning = "retVal", argNames = "retVal")
    public void askOpinion(Object retVal) {
        System.out.printf("Is %s Good Enough?%n", ((Squishee) retVal).getName());
    }

    public void sayYouAreNotAllowed() {
        System.out.println("Hmmm... \n");
    }

    public void sayGoodBye() {
        System.out.println("Good Bye! \n");
    }

    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Hi!");
        Object retVal = pjp.proceed();
        System.out.println("See you!");
        return retVal;
    }
}