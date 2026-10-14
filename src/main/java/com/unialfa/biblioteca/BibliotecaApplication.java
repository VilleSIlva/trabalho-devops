package com.unialfa.biblioteca;

import com.unialfa.biblioteca.model.Usuario;
import com.unialfa.biblioteca.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}


	@Bean
	public CommandLineRunner initDataBase(UsuarioRepository usuarioRepository) {
		return args -> {
			salvarUsuarioSeSenhaDefinida(usuarioRepository, "admin", "DEMO_ADMIN_PASSWORD", "Admin", "ADMIN");
			salvarUsuarioSeSenhaDefinida(usuarioRepository, "user", "DEMO_USER_PASSWORD", "user", "USER");
		};
	}

	private static void salvarUsuarioSeSenhaDefinida(UsuarioRepository usuarioRepository, String login, String variavelSenha, String nome, String privilegio) {
		String senha = System.getenv(variavelSenha);
		if (senha == null || senha.isBlank()) {
			return;
		}
		usuarioRepository.save(new Usuario(null, login, new BCryptPasswordEncoder().encode(senha), nome, privilegio));
	}
}
