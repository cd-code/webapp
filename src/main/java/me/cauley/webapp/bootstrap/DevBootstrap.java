package me.cauley.webapp.bootstrap;

import me.cauley.webapp.model.Author;
import me.cauley.webapp.model.Book;
import me.cauley.webapp.model.Publisher;
import me.cauley.webapp.repositories.AuthorRepository;
import me.cauley.webapp.repositories.BookRepository;
import me.cauley.webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData(){

        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher turing = new Publisher("Turing", "Turing address");
        Book ddd = new Book("Domain Driven Design", "1234", turing);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(turing);
        bookRepository.save(ddd);


        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher huazhang = new Publisher("HuaZhang", "HuaZhang address");
        Book noEJB = new Book("J2EE Development without EJB", "23444", huazhang );
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        publisherRepository.save(huazhang);
        bookRepository.save(noEJB);
    }
}
