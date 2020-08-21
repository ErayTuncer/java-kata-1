package org.echocat.kata.java.part1.repository;

import org.echocat.kata.java.part1.entity.Author;
import org.echocat.kata.java.part1.entity.ImmutableAuthor;
import org.echocat.kata.java.part1.tool.CSVFileReader;

import java.util.LinkedList;
import java.util.List;

public class AuthorRepository {

    private final List<Author> authorList = new LinkedList<>();

    public static AuthorRepository create() {
        AuthorRepository repository = new AuthorRepository();
        repository.readAuthorsFromFile();
        return repository;
    }

    private AuthorRepository() {
        // use create instead
    }

    private void readAuthorsFromFile() {
        this.authorList.clear();
        CSVFileReader.readCSVFile("authors.csv").forEach(csvLine -> {
            this.authorList.add(ImmutableAuthor.builder().email(csvLine[0]).firstName(csvLine[1]).lastName(csvLine[2]).build());
        });
    }
    
    public Author findAuthorByEmail(String authorEmail) {
        return authorList.stream().filter(author -> author.email().equals(authorEmail)).findAny().orElseThrow(() -> new RuntimeException("Author Email is not found"));
    }

}
