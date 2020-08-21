package org.echocat.kata.java.part1.entity;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class Book {

    public abstract String title();
    public abstract String description();
    public abstract List<Author> authors();
    public abstract String isbn();

}
