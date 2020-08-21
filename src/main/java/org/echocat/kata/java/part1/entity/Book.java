package org.echocat.kata.java.part1.entity;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class Book implements Article {

    public abstract String title();
    public abstract String description();
    public abstract List<Author> authors();
    public abstract String isbn();

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Article: Book").append("\n")
                .append("  --> Title: ").append(title()).append("\n")
                .append("  --> ISBN: ").append(isbn()).append("\n")
                .append("  --> Description: ").append(description());

        return stringBuilder.toString();
    }

}
