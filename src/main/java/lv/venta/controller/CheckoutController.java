package lv.venta.controller;

import lv.venta.service.IBookService;
import lv.venta.service.ICheckoutService;
import lv.venta.service.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private ICheckoutService checkoutService;

    @Autowired
    private IReaderService readerService;

    @Autowired
    private IBookService bookService;

    // Show all checkouts
    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("checkouts", checkoutService.selectAll());
        return "show-all-checkouts-page";
    }

    // Show only active (not returned) checkouts
    @GetMapping("/active")
    public String showActive(Model model) {
        model.addAttribute("checkouts", checkoutService.selectActiveCheckouts());
        model.addAttribute("activeOnly", true);
        return "show-all-checkouts-page";
    }

    // Show form to checkout a book
    @GetMapping("/new")
    public String showCheckoutForm(Model model) {
        model.addAttribute("readers", readerService.selectAll());
        model.addAttribute("books", bookService.selectAll());
        return "insert-checkout-page";
    }

    // Process checkout
    @PostMapping("/new")
    public String processCheckout(@RequestParam long readerId,
                                  @RequestParam long bookId,
                                  Model model) {
        try {
            checkoutService.checkoutBook(readerId, bookId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("readers", readerService.selectAll());
            model.addAttribute("books", bookService.selectAll());
            return "insert-checkout-page";
        }
        return "redirect:/checkout/active";
    }

    // Return a book
    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable long id) {
        try { checkoutService.returnBook(id); } catch (Exception ignored) {}
        return "redirect:/checkout/active";
    }
}