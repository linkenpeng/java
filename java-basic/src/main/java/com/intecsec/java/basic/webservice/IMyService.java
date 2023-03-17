package com.intecsec.java.basic.webservice;

import com.intecsec.java.vo.Students;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Peter.Peng
 * Created on 2023/3/17 14:22
 */
@WebService
public interface IMyService {

    @WebResult(name = "students")
    // @WebMethod(operationName = "list")
    public List<Students> list(@WebParam(header = true, name="auth") String auth);
}
