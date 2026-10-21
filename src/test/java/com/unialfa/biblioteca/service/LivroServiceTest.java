package com.unialfa.biblioteca.service;

import com.unialfa.biblioteca.model.Livro;
import com.unialfa.biblioteca.repository.LivroRepository;
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
class LivroServiceTest {

	@Mock
	private LivroRepository repository;

	@InjectMocks
	private LivroService service;

	@Test
	void salvarDelegaParaORepositorio() {
		Livro livro = new Livro();
		livro.setTitulo("Dom Casmurro");
		when(repository.save(livro)).thenReturn(livro);

		Livro salvo = service.salvar(livro);

		assertEquals("Dom Casmurro", salvo.getTitulo());
		verify(repository).save(livro);
	}

	@Test
	void listarPorIdDevolveOLivroDoRepositorio() {
		Livro livro = new Livro();
		livro.setId(1L);
		livro.setTitulo("Dom Casmurro");
		when(repository.findById(1L)).thenReturn(Optional.of(livro));

		Livro encontrado = service.listar(1L);

		assertEquals("Dom Casmurro", encontrado.getTitulo());
	}

	@Test
	void listarPorIdLancaQuandoNaoExiste() {
		when(repository.findById(9L)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> service.listar(9L));
	}

	@Test
	void listarDevolveTodosOsLivros() {
		Livro livro = new Livro();
		livro.setTitulo("Dom Casmurro");
		when(repository.findAll()).thenReturn(List.of(livro));

		List<Livro> livros = service.listar();

		assertEquals(1, livros.size());
		assertEquals("Dom Casmurro", livros.get(0).getTitulo());
	}

	@Test
	void removerDelegaOIdParaORepositorio() {
		service.remover(1L);

		verify(repository).deleteById(1L);
	}

}
