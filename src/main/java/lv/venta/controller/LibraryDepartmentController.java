package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.LibraryDepartment;
import lv.venta.service.ILibraryDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/department")
public class LibraryDepartmentController {

    @Autowired
    private ILibraryDepartmentService deptService;

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("departments", deptService.selectAll());
        return "show-all-departments-page";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new LibraryDepartment());
        return "insert-department-page";
    }

    @PostMapping("/add")
    public String addDept(@Valid @ModelAttribute("department") LibraryDepartment dept,
                          BindingResult result) {
        if (result.hasErrors()) return "insert-department-page";
        deptService.add(dept);
        return "redirect:/department/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) throws Exception {
        model.addAttribute("department", deptService.selectById(id));
        return "update-department-page";
    }

    @PostMapping("/update/{id}")
    public String updateDept(@PathVariable long id,
                             @Valid @ModelAttribute("department") LibraryDepartment updated,
                             BindingResult result) throws Exception {
        if (result.hasErrors()) return "update-department-page";
        deptService.updateById(id, updated);
        return "redirect:/department/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteDept(@PathVariable long id) {
        try { deptService.removeById(id); } catch (Exception ignored) {}
        return "redirect:/department/all";
    }
}