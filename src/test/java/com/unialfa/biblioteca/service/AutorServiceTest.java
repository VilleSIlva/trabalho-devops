package com.unialfa.biblioteca.service;

import com.unialfa.biblioteca.model.Autor;
import com.unialfa.biblioteca.repository.AutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

	@Mock
	private AutorRepository repository;

	@InjectMocks
	private AutorService service;

	@Test
	void salvarDelegaParaORepositorio() {
		Autor autor = new Autor();
		autor.setNome("Machado de Assis");
		when(repository.save(autor)).thenReturn(autor);

		Autor salvo = service.salvar(autor);

		assertEquals("Machado de Assis", salvo.getNome());
		verify(repository).save(autor);
	}

	@Test
	void listarPorIdDevolveOAutorDoRepositorio() {
		Autor autor = new Autor();
		autor.setId(1L);
		autor.setNome("Machado de Assis");
		when(repository.findById(1L)).thenReturn(Optional.of(autor));

		Autor encontrado = service.listar(1L);

		assertEquals("Machado de Assis", encontrado.getNome());
	}

	@Test
	void listarPorIdLancaQuandoNaoExiste() {
		when(repository.findById(9L)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> service.listar(9L));
	}

	@Test
	void listarDevolveTodosOsAutores() {
		Autor autor = new Autor();
		autor.setNome("Machado de Assis");
		when(repository.findAll()).thenReturn(List.of(autor));

		List<Autor> autores = service.listar();

		assertEquals(1, autores.size());
		assertEquals("Machado de Assis", autores.get(0).getNome());
	}

	@Test
	void removerDelegaOIdParaORepositorio() {
		service.remover(1L);

		verify(repository).deleteById(1L);
	}

}
