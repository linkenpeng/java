package com.intecsec.java.springboot.controller;

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
        // 方式一 Skywalking
        String traceId = TraceContext.traceId();
        log.info("Skywalking traceId:{}", traceId);

        // 方式二 Http Header
        String tid1 = request.getHeader("MyStoreTid");
        log.info("request.getHeader tid:{}", tid1);
        String tid2 = headers.getFirst("MyStoreTid");
        log.info("headers.getFirst tid:{}", tid2);

        String tid = tid1;
        if(StringUtils.isEmpty(tid)) {
            tid = traceId;
        }

        return  tid;
    }

}
