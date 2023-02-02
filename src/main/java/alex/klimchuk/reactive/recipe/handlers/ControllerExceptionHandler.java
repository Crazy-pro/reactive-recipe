package alex.klimchuk.reactive.recipe.handlers;

import alex.klimchuk.reactive.recipe.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.thymeleaf.exceptions.TemplateInputException;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, TemplateInputException.class})
    public String handleNotFoundException(Exception exception, Model model) {
        log.error("Handling not found exception!", exception.getMessage());
        model.addAttribute("exception", exception);
        return "/errorPage404";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(NumberFormatException exception, Model model) {
        log.error("Handling number format exception!", exception.getMessage());
        model.addAttribute("exception", exception);
        return "/errorPage400";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public String handleWebExchangeBindException(WebExchangeBindException exception, Model model) {
        log.error("Handling web exchange bind exception!", exception.getMessage());
        model.addAttribute("exception", exception);
        return "/errorPage400";
    }

}
