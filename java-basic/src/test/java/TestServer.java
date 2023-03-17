import com.intecsec.java.vo.Students;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Peter.Peng
 * Created on 2023/3/17 14:59
 */

public class TestServer {

    // header头部消息传递
    @Test
    public void testWebService() {
        try {
            // 创建服务
            URL url = new URL("http://localhost:9999/server");
            QName qname = new QName("http://www.webservice.com",
                    "MyServiceImplService");
            Service service = Service.create(url, qname);

            // 创建Dispatch
            Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName("http://www.webservice.com",
                    "MyServiceImplPort"), SOAPMessage.class, Service.Mode.MESSAGE);

            // 创建SOAP的body消息
            SOAPMessage message = MessageFactory.newInstance().createMessage();
            SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
            SOAPBody body = envelope.getBody();

            // 创建SOAP的header消息
            SOAPHeader header = envelope.getHeader();
            if (header == null) {
                header = envelope.addHeader();
            }
            QName hname = new QName("http://www.webservice.com", "auth", "ns");
            header.addHeaderElement(hname).setValue("12345");

            // 根据QName创建相应的节点
            QName ename = new QName("http://www.webservice.com", "list", "ns");
            body.addBodyElement(ename);
            message.writeTo(System.out);
            System.out.println("\n invoking...");

            // 通过dispatch传递消息，返回响应消息
            SOAPMessage response = dispatch.invoke(message);
            response.writeTo(System.out);
            System.out.println();

            // 将响应的消息转换为dom对象
            Document doc = response.getSOAPBody().extractContentAsDocument();
            NodeList studentList = doc.getElementsByTagName("students");
            Unmarshaller unmarshaller = JAXBContext.newInstance(Students.class).createUnmarshaller();
            for (int i = 0; i < studentList.getLength(); i++) {
                Students students = (Students) unmarshaller.unmarshal(studentList.item(i));
                System.out.println(students.getName());
            }
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
