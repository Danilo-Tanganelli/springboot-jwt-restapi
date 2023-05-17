package br.mackenzie.restapi.security.controller;



import java.util.Optional;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.mackenzie.restapi.security.config.JwtTokenUtil;
import br.mackenzie.restapi.security.dao.UserDao;
import br.mackenzie.restapi.security.model.DAOUser;
import br.mackenzie.restapi.security.model.JwtRequest;
import br.mackenzie.restapi.security.model.JwtResponse;
import br.mackenzie.restapi.security.model.UserDTO;
import br.mackenzie.restapi.security.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	UserDao repository;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
		try {
			DAOUser savedUser = userDetailsService.save(user);
			return ResponseEntity.ok(savedUser);
		} catch (ValidationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		} catch (Exception ex) {
			if (ex instanceof DataIntegrityViolationException) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred while saving the user");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
			}
		}
	}

	

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}