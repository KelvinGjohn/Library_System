package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.Reader;
import lv.venta.service.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private IReaderService readerService;

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("readers", readerService.selectAll());
        return "show-all-readers-page";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("reader", new Reader());
        return "insert-reader-page";
    }

    @PostMapping("/add")
    public String addReader(@Valid @ModelAttribute("reader") Reader reader,
                            BindingResult result) {
        if (result.hasErrors()) return "insert-reader-page";
        readerService.add(reader);
        return "redirect:/reader/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) throws Exception {
        model.addAttribute("reader", readerService.selectById(id));
        return "update-reader-page";
    }

    @PostMapping("/update/{id}")
    public String updateReader(@PathVariable long id,
                               @Valid @ModelAttribute("reader") Reader updatedReader,
                               BindingResult result) throws Exception {
        if (result.hasErrors()) return "update-reader-page";
        readerService.updateById(id, updatedReader);
        return "redirect:/reader/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteReader(@PathVariable long id) {
        try { readerService.removeById(id); } catch (Exception ignored) {}
        return "redirect:/reader/all";
    }

    // loadHistory for a reader
    @GetMapping("/history/{id}")
    public String loadHistory(@PathVariable long id, Model model) throws Exception {
        model.addAttribute("reader", readerService.selectById(id));
        model.addAttribute("checkouts", readerService.loadHistory(id));
        model.addAttribute("historyMode", true);
        return "show-reader-checkouts-page";
    }

    // Current taken books
    @GetMapping("/current/{id}")
    public String currentBooks(@PathVariable long id, Model model) throws Exception {
        model.addAttribute("reader", readerService.selectById(id));
        model.addAttribute("checkouts", readerService.currentTakenBooks(id));
        model.addAttribute("historyMode", false);
        return "show-reader-checkouts-page";
    }
}