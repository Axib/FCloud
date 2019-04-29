package com.example.demo.exception;

import com.example.demo.model.ResultTO;
import com.example.demo.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerAdvice {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO exceptionResponse(Exception e) {
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(500);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        result.setMsg("\r\n"+sw.toString()+"\r\n");
        logger.error(e.getMessage(),e);
        return result;
    }

    @ExceptionHandler(value = AuthException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO authResponse(AuthException ae) {
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(403);
        result.setMsg(ae.getMessage());
        LogUtil.info(ae.getMessage());
        return result;
    }

    /*@ExceptionHandler(value = AuthException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO commonResponse(AuthException ce) {
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(405);
        result.setMsg(ce.getMessage());
        LogUtil.info(ce.getMessage());
        return result;
    }*/
}
