package br.com.felipe.soapcustomclient.services;

import br.com.felipe.soapcustomclient.models.User;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service("SOAP_CLIENT_SERVICE")
public class SOAPClientService {

    public void execute(List<User> users) {
        users.forEach(u -> {
            try {
                read(u);
            } catch (SOAPException | IOException e) {
                UtilsService.handleExceptions(e);
            }
        });
    }

    private void read(User u) throws SOAPException, IOException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        MessageFactory messageFactory = MessageFactory.newInstance();
        MimeHeaders headers = new MimeHeaders();
        headers.addHeader(UtilsService.CONTENT_TYPE, UtilsService.TYPE);

        SOAPMessage msg = messageFactory.createMessage(headers, new ByteArrayInputStream(buildRequest(u.getId().toString()).getBytes()));
        SOAPMessage response = soapConnection.call(msg, UtilsService.URI);
        Document xml = response.getSOAPBody().getOwnerDocument();

    }

    private String buildRequest(String number) {

        return String.format("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v0=\"http://oi.com.br/cvas/mmp/MMPSubsQuery/schemas/MMPSubsQuery/v1_0\" xmlns:v1=\"http://oi.com.br/cvas/mmp/MMPSubsQuery/schemas/MMPSubsQuery/v1_e\">\n"
                + "   <soapenv:Header/>\n"
                + "   <soapenv:Body>\n"
                + "   <v0:MMPSubsQuery>\n"
                + "         <Subscriber>55%s</Subscriber>\n"
                + "      </v0:MMPSubsQuery>"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>", number);
    }



}
