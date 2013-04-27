/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller class that can be used as a parent controller class or as controller advice which is a new feature in Spring 3.2
 * This class is in charge of handle validation by catching exception and processing them
 * 
 * @author Carlos Juarez
 */
//@ControllerAdvice
public class ValidationController {

    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidationController.class);

    /**
     * Handles bean validation JSR 303 every time that a bean validation fails this method is invoked and
     * the exception is handled
     * 
     * @param ex - exception that contains the validation errors
     * @return the list of <code>ObjectError</code>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    List<ObjectError> handleValidation(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        logger.debug("handleValidation : " + errors);
        return ex.getBindingResult().getAllErrors();
    }

    /**
     * Handles service exceptions commonly thrown by service classes every time that one of this exceptions occur
     * this method is invoked and the exception is handled
     * 
     * @param ex - exception thrown
     * @param response - it is not used but required by Spring mapping
     * @return the list of <code>ObjectError</code>
     */
    @ExceptionHandler({JpaSystemException.class,org.springframework.dao.DataIntegrityViolationException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    List<ObjectError> handleException(RuntimeException ex, HttpServletResponse response) {
        logger.debug("handleException init ex : " + ex.getMessage());
        return Arrays.asList(new ObjectError("", ex.getMessage()));
    }
}
