package com.intecsec.java.basic.webservice;

import com.intecsec.java.vo.Students;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter.Peng
 * Created on 2023/3/17 14:24
 */
@WebService(endpointInterface = "com.intecsec.java.basic.webservice.IMyService",
targetNamespace = "http://www.webservice.com")
public class MyServiceImpl implements IMyService {
    private static List<Students> students = new ArrayList<>();

    public MyServiceImpl() {
        students.add(new Students(1, "sam", 18));
    }

    @Override
    public List<Students> list(String auth) {
        System.out.println(auth);
        return students;
    }

}
