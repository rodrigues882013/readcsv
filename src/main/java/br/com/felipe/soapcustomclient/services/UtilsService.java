package br.com.felipe.soapcustomclient.services;

public class UtilsService {

    public static String URI = "https://sdpvas.oi.com.br:8443/eProxy/service/MMPSQ_Port_Router";
    public static String CONTENT_TYPE = "Content-Type";
    public static String TYPE = "text/xml";

    public static void handleExceptions(Exception err){
        System.out.println(err.getMessage());
    }

}
