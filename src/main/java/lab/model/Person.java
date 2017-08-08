package lab.model;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public interface Person {
    @SneakyThrows
    default void setName(String name) {
        Field field = this.getClass().getField("name");
        field.setAccessible(true);
        field.set(this, name);
    }

    String getFirstName();
    String getLastName();

    default String getName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    default void sayHello(Person person) {
        System.out.printf("Hello, %s, I`m $s%n", person, this);
    }
}