package com.example.ej157crudsecurity.security.service;

import com.example.ej157crudsecurity.entity.Persona;
import com.example.ej157crudsecurity.repository.PersonaRepositorio;
import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    PersonaRepositorio personaRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Persona persona = personaRepo.findByUsuario(username);
            String rol;

            if (persona.isAdmin()) {
                rol = "ADMIN";
            } else {
                rol = "USER";
            }

            User.UserBuilder userBuilder = User.withUsername(username);
            String password = persona.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encryptedPassword = encoder.encode(password);
            userBuilder.password(encryptedPassword).roles(rol);

            // Muestra si la contraseña encriptada y la contreaseña obtenida en la base de datos son lo mismo.
            System.out.println(encoder.matches(persona.getPassword(),encryptedPassword));

            return userBuilder.build();

        } catch (UsernameNotFoundException e) {
            new UsernameNotFoundException("Usuario " + username + " no encontrado.");
        }
        return null;
    }
}
