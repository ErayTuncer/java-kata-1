package org.echocat.kata.java.part1.tool;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.echocat.kata.java.part1.repository.AuthorRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public abstract class CSVFileReader {

    public static List<String[]> readCSVFile(String fileName) {
        InputStream inputStream = AuthorRepository.class.getResourceAsStream(String.format("/org/echocat/kata/java/part1/data/%s", fileName));
        Reader reader = new InputStreamReader(inputStream);

        final CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();
        final CSVReader csvReader =
                new CSVReaderBuilder(reader)
                        .withSkipLines(1)
                        .withCSVParser(parser)
                        .build();

        List<String[]> list;
        try {
            list = csvReader.readAll();
            inputStream.close();
            reader.close();
            csvReader.close();
            return list;
        } catch (IOException e) {
            return new LinkedList<>();
        }

    }

}
