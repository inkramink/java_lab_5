package org.example;

/**
 * Bean class with injectable fields.
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Performs actions using the injected fields.
     */
    public void foo() {
        field1.doSomething();
        System.out.println(); // Add a newline for separation
        field2.doSomeOther();
    }
}