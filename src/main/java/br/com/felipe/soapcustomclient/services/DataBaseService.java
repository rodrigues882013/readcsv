package br.com.felipe.soapcustomclient.services;

import br.com.felipe.soapcustomclient.dao.UserDAO;
import br.com.felipe.soapcustomclient.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DATABASE_SERVICE")
public class DataBaseService{

    @Autowired
    private UserDAO userDao;

    public void read(){
        List<User> users = (List<User>) userDao.findAll();
        users.forEach( u -> System.out.println(u.getUsername()));
    }

}
