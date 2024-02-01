/*
package com.intecsec.java.basic.net.sse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.sse.SseEventSink;
import javax.servlet.sse.SseException;
import javax.servlet.sse.SseEventSource;

@WebServlet("/sse")
public class SSEDemo extends HttpServlet {

    private SseEventSource eventSource;

    @Override
    public void init() {
        // 创建 SseEventSource 对象
        eventSource = new SseEventSource();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        // 设置响应头，允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        // 创建 SseEventSink 对象，用于接收客户端的消息
        SseEventSink sink = eventSource.createSseEventSink(request, response);

        // 发送消息给客户端
        sink.send(new StringEvent("Hello, world!"));

        // 关闭 SseEventSink，释放资源
        sink.close();
    }

    private static class StringEvent extends SseEvent {
        private final String message;

        public StringEvent(String message) {
            this.message = message;
        }

        @Override
        public String getData() {
            return message;
        }
    }
}
 */