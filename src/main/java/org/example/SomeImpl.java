package org.example;

/**
 * Implementation of {@link SomeInterface} that prints "A".
 */
public class SomeImpl implements SomeInterface {
    /**
     * {@inheritDoc}
     */
    public void doSomething() {
        System.out.print("A");
    }
}