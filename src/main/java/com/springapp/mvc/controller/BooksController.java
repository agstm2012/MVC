package com.springapp.mvc.controller;

import com.springapp.mvc.model.Book;
import com.springapp.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class BooksController {
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm";

    @Autowired
    private BookService bookService;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Spring samples");
        return "index";
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String getBooks(Model model) {
        List<Book> books = bookService.getBooks();
        for (Book book : books) {
            if (book.getName() != null && book.getName().length() > 50) {
                book.setName(book.getName().substring(0, 47) + "...");
            }
        }
        model.addAttribute("books", books);
        return "books/books";
    }

    @RequestMapping(value = "books/add", method = RequestMethod.GET)
    public String getAddBook(Model model) {
        model.addAttribute("bookAttribute", new Book());
        return "books/addBook";
    }

    @RequestMapping(value = "books/add", method = RequestMethod.POST)
    public String addBook(@Valid @ModelAttribute("bookAttribute") Book book,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "books/addBook";
        }
        bookService.saveBook(book);
        model.addAttribute("date", new SimpleDateFormat(TIMESTAMP_PATTERN).format(new Date()));
        return "books/addedBook";
    }

    @RequestMapping(value = "books/delete", method = RequestMethod.GET)
    public String deleteBook(@RequestParam(value = "id", required = true) UUID id, Model model) {
        bookService.deleteBook(bookService.getBook(id));
        model.addAttribute("id", id);
        model.addAttribute("date", new SimpleDateFormat(TIMESTAMP_PATTERN).format(new Date()));
        return "books/deletedBook";
    }

    @RequestMapping(value = "books/edit", method = RequestMethod.GET)
    public String editBook(@RequestParam(value = "id", required = true) UUID id, Model model) {
        model.addAttribute("bookAttribute", bookService.getBook(id));
        return "books/editBook";
    }

    @RequestMapping(value = "books/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("bookAttribute") Book book,
            @RequestParam(value = "id", required = true) UUID id,
            BindingResult result, Model model) {
        validator.validate(book, result);

        if (result.hasErrors()) {
            return "books/editBook";
        }

        bookService.updateBook(book);
        model.addAttribute("id", id);
        model.addAttribute("date", new SimpleDateFormat(TIMESTAMP_PATTERN).format(new Date()));
        return "books/editedBook";
    }
}