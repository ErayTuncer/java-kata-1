package org.echocat.kata.java.part1.entity;

import org.immutables.value.Value;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Value.Immutable
public abstract class Magazine implements Article {

    public abstract String title();
    public abstract String isbn();
    public abstract Date publishedDate();
    public abstract List<Author> authors();

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Article: Magazine").append("\n")
                .append("  --> Title: ").append(title()).append("\n")
                .append("  --> ISBN: ").append(isbn()).append("\n")
                .append("  --> Published Date: ").append(formatter.format(publishedDate()));

        return stringBuilder.toString();
    }
}
