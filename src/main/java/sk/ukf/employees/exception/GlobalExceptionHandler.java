package sk.ukf.employees.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public String handleObjectNotFound(ObjectNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "employees/error/not-found";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneral(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "employees/error/server-error";
    }
}