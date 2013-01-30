/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.controller;

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
 *
 * @author Carlos
 */
//@ControllerAdvice
public class ValidationController {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidationController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    List<ObjectError> handleValidation(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        logger.debug("handleValidation : " + errors);
        return ex.getBindingResult().getAllErrors();
    }

    @ExceptionHandler(JpaSystemException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    List<ObjectError> handleException(JpaSystemException ex, HttpServletResponse response) {
        logger.debug("handleException init ex : " + ex.getMessage());
        return Arrays.asList(new ObjectError("", ex.getMessage()));
    }
}
