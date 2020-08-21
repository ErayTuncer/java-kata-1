package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.entity.Article;
import org.echocat.kata.java.part1.entity.Author;
import org.echocat.kata.java.part1.entity.Book;
import org.echocat.kata.java.part1.entity.Magazine;
import org.echocat.kata.java.part1.repository.AuthorRepository;
import org.echocat.kata.java.part1.repository.BookRepository;
import org.echocat.kata.java.part1.repository.MagazineRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private MagazineRepository magazineRepository;

    public LibraryService() {
        authorRepository = AuthorRepository.create();
        bookRepository = BookRepository.create(authorRepository);
        magazineRepository = MagazineRepository.create(authorRepository);
    }

    public Article findArticleByISBN(String isbn) {
        Optional<Book> foundBook = bookRepository.findBookByISBN(isbn);
        if (foundBook.isPresent()) {
            return foundBook.get();
        }

        Optional<Magazine> foundMagazine = magazineRepository.findMagazineByISBN(isbn);
        if (foundMagazine.isPresent()) {
            return foundMagazine.get();
        }

        return null;
    }

    public Set<Article> findArticlesByAuthorEmails(List<String> authorEmailList) {
        Set<Article> foundArticles = new HashSet<>();
        Set<Author> authors = authorEmailList.stream().map(authorEmail -> authorRepository.findAuthorByEmail(authorEmail)).collect(Collectors.toSet());
        authors.stream().forEach(author -> {
            foundArticles.addAll(bookRepository.findBooksByAuthor(author));
            foundArticles.addAll(magazineRepository.findMagazinesByAuthor(author));
        });
        return foundArticles;
    }

    public void printAllArticles() {
        Set<Article> articles = new HashSet<>();
        articles.addAll(bookRepository.findAllBooks());
        articles.addAll(magazineRepository.findAllMagazines());

        articles.stream().forEach(article -> System.out.println(article));
    }

}
