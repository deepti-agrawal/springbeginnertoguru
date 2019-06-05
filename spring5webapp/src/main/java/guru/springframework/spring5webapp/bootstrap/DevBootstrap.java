package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository  bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initData();
    }

    private void initData(){
        Publisher publisher = new Publisher("Harper Collins","New York");
        publisherRepository.save(publisher);
        Author author = new Author("Deepti","Agrawal");
        Book book = new Book("Domain Driven Design","1234",publisher);
        book.setPublisher(publisher);
        author.getBooks().add(book);
        book.getAuthors().add(author);

        authorRepository.save(author);
        bookRepository.save(book);

        Publisher newPublisher = new Publisher("Harper David","Denver");
        publisherRepository.save(newPublisher);
        Author newAuthor = new Author("Rupesh","Jaiswal");
        Book newBook = new Book("Marketing Driven Design","5678",newPublisher);
        newBook.setPublisher(newPublisher);
        newAuthor.getBooks().add(newBook);
        newBook.getAuthors().add(newAuthor);

        authorRepository.save(newAuthor);
        bookRepository.save(newBook);
    }
}
