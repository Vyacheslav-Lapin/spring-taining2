package lab.model;

import java.util.List;

public interface Person {
    String getFirstName();
    String getLastName();
    int getId();
    Country getCountry();
    int getAge();
    float getHeight();
    boolean isProgrammer();
    List<String> getContacts();
    boolean isBroke();

    default String getName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    default void sayHello(Person person) {
        System.out.printf("Hello, %s, I`m %s%n", person, this);
    }
}