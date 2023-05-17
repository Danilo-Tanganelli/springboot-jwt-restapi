package br.mackenzie.restapi.security.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.mackenzie.restapi.security.dao.UserDao;
import br.mackenzie.restapi.security.model.DAOUser;
import br.mackenzie.restapi.security.model.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DAOUser user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public DAOUser save(UserDTO user) throws ValidationException {
		DAOUser newUser = new DAOUser();
		if (user.getUsername().length()<3) {
			throw new ValidationException("O nome de usuário deve ter pelo menos 3 caracteres");
			}
		Optional<DAOUser> existingUser = Optional.ofNullable(userDao.findByUsername(user.getUsername()));
    if (existingUser.isPresent()) {
        throw new ValidationException("Usuário já cadastrado");
    }else{ 
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.save(newUser);
	}
	
	}
}