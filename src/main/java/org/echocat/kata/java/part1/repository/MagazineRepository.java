package org.echocat.kata.java.part1.repository;

import org.echocat.kata.java.part1.entity.Author;
import org.echocat.kata.java.part1.entity.ImmutableMagazine;
import org.echocat.kata.java.part1.entity.Magazine;
import org.echocat.kata.java.part1.tool.CSVFileReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MagazineRepository {

    private final List<Magazine> magazineList = new LinkedList<>();

    public static MagazineRepository create(AuthorRepository authorRepository) {
        MagazineRepository repository = new MagazineRepository();
        repository.readMagazinesFromFile(authorRepository);
        return repository;
    }

    private MagazineRepository() {
        // use create instead
    }

    private void readMagazinesFromFile(AuthorRepository authorRepository) {
        magazineList.clear();
        CSVFileReader.readCSVFile("magazines.csv").forEach(csvLine -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            List<Author> authorList = new LinkedList<>();
            String[] emails = csvLine[2].split(",");
            Arrays.stream(emails).forEach(email -> authorList.add(authorRepository.findAuthorByEmail(email)));

            try {
                magazineList.add(ImmutableMagazine.builder().title(csvLine[0]).isbn(csvLine[1]).addAllAuthors(authorList).publishedDate(dateFormat.parse(csvLine[3])).build());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

}
