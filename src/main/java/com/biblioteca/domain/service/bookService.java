package com.biblioteca.domain.service;

import com.biblioteca.domain.enuns.Status;

import com.biblioteca.domain.model.authorModel;
import com.biblioteca.domain.model.bookModel;

import com.biblioteca.domain.model.categoryModel;
import com.biblioteca.domain.repository.authorRepository;
import com.biblioteca.domain.repository.bookRepository;
import com.biblioteca.domain.repository.categoryRepository;
import com.biblioteca.dto.request.bookRequestDTO;
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
public class bookService {
    private final bookRepository bookRepository;
    private final authorRepository authorRepository;
    private final categoryRepository categoryRepository;

    public bookService(bookRepository bookRepository,
                       authorRepository authorRepository,
                       categoryRepository categoryRepository) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public bookModel createdBook(bookRequestDTO data) {
        bookModel book = new bookModel();
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

    public Page<bookModel> getAllBook(Pageable pageable, String search) {
        if (search.isBlank()) {
            return this.bookRepository.findAll(pageable);
        }

        Specification<bookModel> spec =
                (root, query, criteriaBuilder) -> {
                    query.distinct(true);
                    Join<bookModel, authorModel> author = root.join("author", JoinType.LEFT);
                    Join<bookModel, categoryModel> category = root.join("category", JoinType.LEFT);

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


    public bookModel getBookById(Long id) {
        Optional<bookModel> book= bookRepository.findById(id);
        if(book.isEmpty()){
            throw new ResourceNotFoundException("The book you're looking for is currently unavailable.");
        }
        return book.get();
    }

    public bookModel deleteBook(Long id) {
        Optional<bookModel> book = this.bookRepository.findById(id);
        if(book.isEmpty()){
            throw new ResourceNotFoundException("The book you're looking for is currently unavailable.");
        }
        bookModel newBook = book.get();
        newBook.setStatus(Status.UNAVAILABLE);
        newBook.setRemoved(true);
        return this.bookRepository.save(newBook);
    }

    public void updateBook(bookRequestDTO data) {
    }


}
