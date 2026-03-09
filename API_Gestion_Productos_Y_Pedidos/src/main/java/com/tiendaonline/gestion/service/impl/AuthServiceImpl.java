package com.tiendaonline.gestion.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiendaonline.gestion.repository.UsuarioRepository;
import com.tiendaonline.gestion.security.jwt.JwtService;
import com.tiendaonline.gestion.service.AuthResponse;
import com.tiendaonline.gestion.service.AuthService;
import com.tiendaonline.gestion.service.LoginRequest;
import com.tiendaonline.gestion.service.RegisterRequest;

@Service	// Marca esta clase como un servicio de Spring, lo que permite su gestión y uso en otras partes de la aplicación
public class AuthServiceImpl implements AuthService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	

	@Override
	public AuthResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthResponse login(LoginRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
