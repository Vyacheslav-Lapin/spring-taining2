package lab.model.simple;

import lab.model.Bar;
import lab.model.Person;
import lab.model.Squishee;
import org.springframework.stereotype.Component;

@Component
public class ApuBar implements Bar {

    @Override
	public Squishee sellSquishee(Person person)  {
        System.out.println("Here is your Squishee");
        return () -> "Usual Squishee";
    }
}