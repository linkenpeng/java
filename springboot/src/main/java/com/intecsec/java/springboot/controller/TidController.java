package com.intecsec.java.springboot.controller;

import com.google.common.collect.Lists;
import com.intecsec.java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * VM params: -javaagent:D:/components/skywalking-agent/skywalking-agent.jar
 * env: SW_AGENT_COLLECTOR_BACKEND_SERVICES=SkyWalkingServerIp:port
 *
 *
 * Created by Peter.Peng
 * Created on 2023/2/1 17:15
 */
@RestController()
@RequestMapping("/tid")
@Slf4j
public class TidController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/get")
    public String getById(@RequestHeader HttpHeaders headers) {
        // 从HTTP Header 信息中获取skywalking的tid
        String sw8 = headers.getFirst("sw8");
        log.info("从skywalking获取到sw8原始信息:{}", sw8);

        String tid = "";
        if(StringUtils.isNotEmpty(sw8)) {
            String[] sw8Array = StringUtils.split(sw8, "-");
            tid = new String(Base64.decode(sw8Array[1]));
            log.info("从skywalking获取到tid:{}", tid);
        }

        return  tid;
    }

    @GetMapping("/get1")
    public String getById1(@RequestHeader HttpHeaders headers) {
        // 方式一 Skywalking
        String traceId = TraceContext.traceId();
        log.info("Skywalking traceId:{}", traceId);

        // 方式二 Http Header
        String tid1 = request.getHeader("MyStoreTid");
        log.info("request.getHeader tid:{}", tid1);
        String tid2 = headers.getFirst("MyStoreTid");
        log.info("headers.getFirst tid:{}", tid2);

        String sw8 = request.getHeader("sw8");
        List<String> sw8encode = Lists.newArrayList();
        List<String> sw8decode = Lists.newArrayList();
        if(StringUtils.isNotEmpty(sw8)) {

            String[] sw8s = StringUtils.split(sw8, "-");
            String Tid = new String(Base64.decode(sw8s[1]));
            String spanId = new String(Base64.decode(sw8s[2]));
            System.out.println(Tid);
            System.out.println(spanId);

            for(String s : sw8s) {
                sw8encode.add(s);
                if(s.length() > 3) {
                    String decodeS = new String(Base64.decode(s));
                    sw8decode.add(decodeS);
                }
            }
        }
        System.out.println(sw8encode.toString());
        System.out.println(sw8decode.toString());

        String tid = tid1;
        if(StringUtils.isEmpty(tid)) {
            tid = traceId;
        }

        return  tid;
    }


}
