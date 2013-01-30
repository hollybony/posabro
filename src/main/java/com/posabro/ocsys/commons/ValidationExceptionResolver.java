/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.commons;

import com.posabro.ocsys.security.controller.UserController;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 *
 * @author Carlos
 */
public class ValidationExceptionResolver extends AbstractHandlerExceptionResolver {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    public ValidationExceptionResolver() {
        ExceptionHandlerExceptionResolver a;
        super.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

//    @Override
//    protected boolean shouldApplyTo(HttpServletRequest request, Object handler) {
//        org.springframework.web.servlet.DispatcherServlet a;
//        return true;
//    }
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        }else if(ex instanceof JpaSystemException
        if (ex instanceof MethodArgumentNotValidException) {
            logger.debug("handling MethodArgumentNotValidException : " + handler);
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;
//        ModelAndViewContainer mavContainer = new ModelAndViewContainer();
            response.setStatus(400);
            // to be picked up by the RedirectView
//			webRequest.getRequest().setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, this.responseStatus);
//        mavContainer.setRequestHandled(false);
            HashMap<String, Object> objects = new HashMap<String, Object>();
            objects.put("errors", manve.getBindingResult().getAllErrors());
            ModelAndView mav = new ModelAndView().addAllObjects(objects);
            return mav;
        } else {
            return null;
        }
    }
    
}
