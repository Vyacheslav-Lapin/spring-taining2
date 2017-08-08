package lab.model.simple;

import lab.model.Bar;
import lab.model.Person;
import lab.model.Squishee;

public class ApuBar implements Bar {

    @Override
	public Squishee sellSquishee(Person customer)  {
        System.out.println("Here is your Squishee");
        return () -> "Usual Squishee";
    }
}