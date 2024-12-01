package org.example;

/**
 * Implementation of {@link SomeOtherInterface} that prints "C".
 */
public class SODoer implements SomeOtherInterface {
    /**
     * {@inheritDoc}
     */
    public void doSomeOther() {
        System.out.print("C");
    }
}