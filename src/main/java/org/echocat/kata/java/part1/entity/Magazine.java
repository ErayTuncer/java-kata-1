package org.echocat.kata.java.part1.entity;

import org.immutables.value.Value;

import java.util.Date;
import java.util.List;

@Value.Immutable
public abstract class Magazine implements Article {

    public abstract String title();
    public abstract String isbn();
    public abstract Date publishedDate();
    public abstract List<Author> authors();

}
