package com.ujc.students.controller;

import com.ujc.students.dao.UsuarioDao;
import com.ujc.students.model.Usuario;
import com.ujc.students.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // POST /auth/login — Autenticação
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null || !passwordEncoder.matches(password, usuario.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("erro", "Credenciais inválidas"));
        }

        String token = jwtUtil.generateToken(username, usuario.getPerfil().name());

        return ResponseEntity.ok(Map.of(
            "token", token,
            "perfil", usuario.getPerfil().name(),
            "username", username
        ));
    }

    // POST /auth/registar — Registar novo utilizador
    @PostMapping("/registar")
    public ResponseEntity<?> registar(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioDao.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of("mensagem", "Utilizador criado com sucesso"));
    }
}
