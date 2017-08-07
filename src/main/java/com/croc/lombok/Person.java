package com.croc.lombok;

import lombok.Builder;
import lombok.SneakyThrows;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j2;

@Builder
@Log4j2
public class Person {
    private int age;
    private String firstName;
    private String lastName;

    @SneakyThrows
    public static void main(String[] args) {
//        Person person = new Person();
        Person person2 = new Person(0, "", "");

//        person2.getAge();
        person2.firstName = "sfsd";

        var person3 = Person.builder()
                .age(4)
                .firstName("")
                .lastName("")
                .build();

        log.info(person3);

        Class.forName("com.croc.lombok.Person");
    }
}
