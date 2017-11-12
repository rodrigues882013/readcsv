package br.com.felipe.soapcustomclient.dao;

import br.com.felipe.soapcustomclient.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDAO extends CrudRepository<User, Integer> { }