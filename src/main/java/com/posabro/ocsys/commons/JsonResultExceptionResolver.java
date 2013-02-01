/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.commons;

import com.posabro.ocsys.security.controller.UserController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;

/**
 *
 * @author Carlos Juarez
 */
public class JsonResultExceptionResolver extends AbstractHandlerMethodExceptionResolver {
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

//    @Override
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        return null;
//    }
    /**
     * The idea is to figure out if the request just accept application/json so we could process this exception and
     * generate a json result.
     * 
     * 
     * @param request
     * @param response
     * @param handlerMethod
     * @param ex
     * @return 
     */
    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request,
            HttpServletResponse response,
            HandlerMethod handlerMethod,
            Exception ex) {
        logger.debug("I was about to handle this exception : " + handlerMethod);
        return null;
    }
    
}
