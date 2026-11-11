package ru.chepusov.NauJava.controller;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*
@ControllerAdvice
public class ExceptionControllerAdvice
{
   @ExceptionHandler(java.lang.Exception.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public Exception exception(java.lang.Exception e)
   {
       return Exception.create(e);
   }

   @ExceptionHandler(ResourceNotFoundException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.NOT_FOUND)
   public Exception exception(ResourceNotFoundException e)
   {
       return Exception.create(e);
   }
}
 */
@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(java.lang.Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Exception exception(java.lang.Exception e) {
        return Exception.create(e);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Exception exception(ResourceNotFoundException e) {
        return Exception.create(e);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    //@ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String userNotFoundException(UsernameNotFoundException e) {
        String message = e.getMessage();
        return "register";
    }



}
