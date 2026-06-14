package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.Book;
import lv.venta.model.BookCondition;
import lv.venta.service.IBookService;
import lv.venta.service.ILibraryDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ILibraryDepartmentService deptService;

    // LIST ALL  →  GET /book/all
    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("books", bookService.selectAll());
        return "show-all-books-page";
    }

    // FILTER BY AUTHOR  →  GET /book/all?author=...
    @GetMapping("/by-author")
    public String byAuthor(@RequestParam String author, Model model) {
        model.addAttribute("books", bookService.selectByAuthor(author));
        model.addAttribute("filter", "Author: " + author);
        return "show-all-books-page";
    }

    // FILTER BY MIN RATING  →  GET /book/by-rating?rating=4.0
    @GetMapping("/by-rating")
    public String byRating(@RequestParam double rating, Model model) {
        model.addAttribute("books", bookService.selectByMinRating(rating));
        model.addAttribute("filter", "Rating ≥ " + rating);
        return "show-all-books-page";
    }

    // FILTER BY CONDITION  →  GET /book/by-condition?condition=GOOD
    @GetMapping("/by-condition")
    public String byCondition(@RequestParam BookCondition condition, Model model) {
        model.addAttribute("books", bookService.selectByCondition(condition));
        model.addAttribute("filter", "Condition: " + condition);
        return "show-all-books-page";
    }

    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) throws Exception {
        model.addAttribute("book", new Book());
        model.addAttribute("departments", deptService.selectAll());
        model.addAttribute("conditions", BookCondition.values());
        return "insert-book-page";
    }

    // SAVE NEW BOOK
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book,
                          BindingResult result,
                          @RequestParam long departmentId,
                          Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("departments", deptService.selectAll());
            model.addAttribute("conditions", BookCondition.values());
            return "insert-book-page";
        }
        book.setLibraryDepartment(deptService.selectById(departmentId));
        bookService.add(book);
        return "redirect:/book/all";
    }

    // SHOW UPDATE FORM
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) throws Exception {
        model.addAttribute("book", bookService.selectById(id));
        model.addAttribute("departments", deptService.selectAll());
        model.addAttribute("conditions", BookCondition.values());
        return "update-book-page";
    }

    // SAVE UPDATE
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable long id,
                             @Valid @ModelAttribute("book") Book updatedBook,
                             BindingResult result,
                             @RequestParam long departmentId,
                             Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("departments", deptService.selectAll());
            model.addAttribute("conditions", BookCondition.values());
            return "update-book-page";
        }
        updatedBook.setLibraryDepartment(deptService.selectById(departmentId));
        bookService.updateById(id, updatedBook);
        return "redirect:/book/all";
    }

    // CHANGE CONDITION
    @PostMapping("/condition/{id}")
    public String changeCondition(@PathVariable long id,
                                  @RequestParam BookCondition condition) throws Exception {
        bookService.changeBookCondition(id, condition);
        return "redirect:/book/all";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        try { bookService.removeById(id); } catch (Exception ignored) {}
        return "redirect:/book/all";
    }
}