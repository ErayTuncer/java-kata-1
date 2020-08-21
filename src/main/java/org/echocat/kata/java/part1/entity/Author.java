package org.echocat.kata.java.part1.entity;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Author {

    public abstract String email();
    public abstract String firstName();
    public abstract String lastName();

}
