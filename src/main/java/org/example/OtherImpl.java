package org.example;

/**
 * Another implementation of {@link SomeInterface} that prints "B".
 */
public class OtherImpl implements SomeInterface {
    /**
     * {@inheritDoc}
     */
    public void doSomething() {
        System.out.print("B");
    }
}