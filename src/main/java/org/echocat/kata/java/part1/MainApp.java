package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.entity.Article;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class MainApp {

    public static void main(String[] args) throws IOException {
        LibraryService library = new LibraryService();

        Article article = library.findArticleByISBN("1024-5245-8584");
        Set<Article> articleSet = library.findArticlesByAuthorEmails(Arrays.asList(new String[]{"null-walter@echocat.org", "null-ferdinand@echocat.org"}));

    }

}
