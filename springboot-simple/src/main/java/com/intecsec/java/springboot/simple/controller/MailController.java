package com.intecsec.java.springboot.simple.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.mail.MailUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;

@RestController
@RequestMapping("/mail")
public class MailController {

    @RequestMapping("/send")
    public Object send() {
        ArrayList<String> tos = CollUtil.newArrayList(
                "peter.peng@watsons.com.cn");
        String subject = "测试";
        String content = "<h1>邮件来自Hutool测试</h1>";
        String result = MailUtil.send(tos, subject, content, true);
        //String result = MailUtil.send(tos, subject, content, true, FileUtil.file("E:/SCO.txt"));
        return result;
    }

    @GetMapping("/hello")
    public Object hello() {
        return "hello";
    }

}
