package com.biblioteca.domain.service;

import com.biblioteca.domain.enuns.Status;

import com.biblioteca.domain.model.AuthorModel;
import com.biblioteca.domain.model.BookModel;

import com.biblioteca.domain.model.CategoryModel;
import com.biblioteca.domain.repository.AuthorRepository;
import com.biblioteca.domain.repository.BookRepository;
import com.biblioteca.domain.repository.CategoryRepository;
import com.biblioteca.dto.request.BookRequestDTO;
import com.biblioteca.dto.request.BookUpdateDTO;
import com.biblioteca.exception.ResourceNotFoundException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       CategoryRepository categoryRepository) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public BookModel createdBook(BookRequestDTO data) {
        BookModel book = new BookModel();
        if(bookRepository.existsByTitle(data.getTitle())){
            throw  new ResourceNotFoundException("A book with that title already exists.");
        }
        book.setTitle(data.getTitle());
        book.setPages(data.getPages());
        book.setDetails(data.getDetails());
        book.setCreated_at(LocalDateTime.now());
        book.setUpdated_at(LocalDateTime.now());
        book.setStatus(Status.AVAILABLE);
        book.setRemoved(false);
        book.setAuthor(authorRepository.findByNameIgnoreCase(data.getAuthor()).orElseThrow(() -> new ResourceNotFoundException("Author dont exists")));
        book.setCategory(categoryRepository.findByTitleIgnoreCase(data.getCategory()).orElseThrow(() -> new ResourceNotFoundException("Category dont exist")));

        return bookRepository.save(book);

    }

    public Page<BookModel> getAllBook(Pageable pageable, String search) {
        if (search.isBlank()) {
            return this.bookRepository.findAll(pageable);
        }

        Specification<BookModel> spec =
                (root, query, criteriaBuilder) -> {
                    query.distinct(true);
                    Join<BookModel, AuthorModel> author = root.join("author", JoinType.LEFT);
                    Join<BookModel, CategoryModel> category = root.join("category", JoinType.LEFT);

                    String like = "%" + search.toLowerCase() + "%";
                    return criteriaBuilder.or(
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get("title")), like
                            ),
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(author.get("name")), like
                            ),
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(category.get("title")), like
                            )
                    );
                };
        return this.bookRepository.findAll(spec,pageable);
    }


    public BookModel getBookById(Long id) {
        Optional<BookModel> book= bookRepository.findById(id);
        if(book.isEmpty()){
            throw new ResourceNotFoundException("The book you're looking for is currently unavailable.");
        }
        return book.get();
    }

    public BookModel deleteBook(Long id) {
        Optional<BookModel> book = this.bookRepository.findById(id);
        if(book.isEmpty()){
            throw new ResourceNotFoundException("The book you're looking for is currently unavailable.");
        }
        BookModel newBook = book.get();
        newBook.setStatus(Status.UNAVAILABLE);
        newBook.setRemoved(true);
        return this.bookRepository.save(newBook);
    }

    public BookModel updateBook(Long id, BookUpdateDTO data) {
        return this.bookRepository.findById(id).map(book ->{
            book.setTitle(data.getTitle());
            book.setPages(data.getPages());
            book.setDetails(data.getDetails());
            book.setUpdated_at(LocalDateTime.now());
            book.setCategory(this.categoryRepository.findByTitleIgnoreCase(data.getCategory()).orElseThrow(
                    ()-> new ResourceNotFoundException("Category not found.")));
            return this.bookRepository.save(book);

                }

        ).orElseThrow(
                ()-> new ResourceNotFoundException("Book not found"));

    }


}
