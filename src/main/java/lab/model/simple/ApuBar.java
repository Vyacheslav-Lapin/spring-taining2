package lab.model.simple;

import lab.model.Bar;
import lab.model.CustomerBrokenException;
import lab.model.Person;
import lab.model.Squishee;

public class ApuBar implements Bar {

	public Squishee sellSquishee(Person customer)  {
        if (customer.isBroke()){
            throw new CustomerBrokenException();
        }

        System.out.println("Here is your Squishee");

        return () -> "Usual Squishee";
    }
}