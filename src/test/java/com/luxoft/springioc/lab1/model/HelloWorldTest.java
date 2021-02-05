package com.luxoft.springioc.lab1.model;

import static org.junit.Assert.*;

import org.junit.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.luxoft.springioc.lab1.model.Country;
import com.luxoft.springioc.lab1.model.Person;
import com.luxoft.springioc.lab1.model.UsualPerson;

public class HelloWorldTest {

    protected static final String APPLICATION_CONTEXT_XML_FILE_NAME = "classpath:application-context.xml";

    private UsualPerson expectedPerson;
    private UsualPerson expectedPerson2;

    private AbstractApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new FileSystemXmlApplicationContext(new String[]{APPLICATION_CONTEXT_XML_FILE_NAME});
        expectedPerson = getExpectedPerson();
        expectedPerson2 = getExpectedPerson2();
    }

    @Test
    public void testInitPersonConstructorArg() {
        UsualPerson person = (UsualPerson) context.getBean("person-constructor-arg", Person.class);

        System.out.println("-->" + person.getCountry().getA());
        assertEquals(expectedPerson, person);
        System.out.println(person);
    }

    @Test
    public void testInitPersonAutowiringByName() {
        UsualPerson person = (UsualPerson) context.getBean("person-autowiring-by-name", Person.class);

        System.out.println("-->" + person.getCountry().getA());
        assertEquals(expectedPerson, person);
        System.out.println(person);
    }

    @Test
    public void testInitPersonAutowiringByType() {
        UsualPerson person = (UsualPerson) context.getBean("person-autowiring-by-type", Person.class);

        System.out.println("-->" + person.getCountry().getA());
        assertEquals(expectedPerson2, person);
        System.out.println(person);
    }


    @Test
    public void testInitPersonAutowiringConstructor() {
        UsualPerson person = (UsualPerson) context.getBean("person-autowiring-by-constructor", Person.class);
        System.out.println("-->" + person.getCountry().getA());
        assertEquals(expectedPerson2, person);
        System.out.println(person);
    }

    private UsualPerson getExpectedPerson() {

        UsualPerson person = new UsualPerson();
        person.setAge(35);
        person.setName("John Smith");

        Country country = new Country();
        country.setId(1);
        country.setName("Russia");
        country.setCodeName("RU");

        System.out.println("-->" + country.getA());

        person.setCountry(country);

        return person;

    }

    private UsualPerson getExpectedPerson2() {
        Country country = new Country();
        country.setId(1);
        country.setName("SDSD");
        country.setCodeName("SDSD");

        return new UsualPerson("John Smith", 35, country);
    }

    @After
    public void tearDown() throws Exception {
        if (context != null)
            context.close();
    }
}
