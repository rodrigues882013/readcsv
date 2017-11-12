package br.com.felipe.soapcustomclient.services;

import br.com.felipe.soapcustomclient.models.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service("LOAD_CSV_SERVICE")
public class LoadCSVService {

    @Autowired
    private ResourceLoader resource;

    public List<User> load(String formatSource) throws IOException {
        Reader in  = new InputStreamReader(resource.getResource("classpath:user.csv").getInputStream());
        return process(getFormat(formatSource).parse(in));
    }

    private CSVFormat getFormat(String required){
        CSVFormat fmt;
        switch (required){
            case "mysql":
                fmt = CSVFormat.MYSQL;
                break;

            case "rfc4180":
                fmt = CSVFormat.RFC4180.withFirstRecordAsHeader();
                    break;

            default:
                fmt = null;
        }

        return fmt;
    }

    private List<User> process(Iterable<CSVRecord> it){
        ArrayList<User> users = new ArrayList<>();

        for(CSVRecord csv : it){
            User u = new User();
            u.setId(Integer.parseInt(csv.get("id")));
            u.setUsername(csv.get("username"));
            users.add(u);
        }

        return users;
    }


}
