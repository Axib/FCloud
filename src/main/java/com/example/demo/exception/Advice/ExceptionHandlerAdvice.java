package com.example.demo.exception.Advice;

import com.alibaba.fastjson.JSONException;
import com.example.demo.exception.CustomException;
import com.example.demo.model.ResultTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    public ExceptionHandlerAdvice() {
    }

    private void interfaceProcess(HttpServletRequest request, Exception e) throws Exception {
        if(request.getServletPath().indexOf("/interface/") > 0) {
            throw e;
        }
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO exceptionResponse(HttpServletRequest request, Exception e) throws Exception {
        this.interfaceProcess(request, e);
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(500);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        result.setMsg("\r\n" + sw.toString() + "\r\n");
        logger.error(e.getMessage(), e);
        return result;
    }

    @ExceptionHandler({LoginException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO loginResponse(HttpServletRequest request, LoginException le) throws Exception {
        this.interfaceProcess(request, le);
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(401);
        result.setMsg(le.getMessage());
        return result;
    }

    /*@ExceptionHandler({PollingException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO pollingResponse(HttpServletRequest request, PollingException pe) throws Exception {
        this.interfaceProcess(request, pe);
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(205);
        result.setMsg(pe.getMessage());
        return result;
    }*/

    @ExceptionHandler({AuthException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO authResponse(HttpServletRequest request, AuthException ae) throws Exception {
        this.interfaceProcess(request, ae);
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(403);
        result.setMsg(ae.getMessage());
        return result;
    }

    @ExceptionHandler({CustomException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO commonResponse(HttpServletRequest request, CustomException ce) throws Exception {
        this.interfaceProcess(request, ce);
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(ce.getCode());
        result.setMsg(ce.getMessage());
        return result;
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO httpRequestMethodResponse(HttpServletRequest request, HttpRequestMethodNotSupportedException hrmse) throws Exception {
        this.interfaceProcess(request, hrmse);
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(405);
        result.setMsg(hrmse.getMessage());
        return result;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO httpMessageNotReadableResponse(HttpServletRequest request, HttpMessageNotReadableException hmnre) throws Exception {
        this.interfaceProcess(request, hmnre);
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(400);
        result.setMsg(hmnre.getMessage());
        return result;
    }

    @ExceptionHandler({JSONException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO httpMessageNotReadableResponse(HttpServletRequest request, JSONException je) throws Exception {
        this.interfaceProcess(request, je);
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(400);
        result.setMsg(je.getMessage());
        return result;
    }

    /*@ExceptionHandler({AlipayApiException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO alipayExceptionResponse(HttpServletRequest request, AlipayApiException ae) throws Exception {
        this.interfaceProcess(request, ae);
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(400);
        result.setMsg("[" + ae.getErrCode() + "] " + ae.getErrMsg());
        return result;
    }*/
}
