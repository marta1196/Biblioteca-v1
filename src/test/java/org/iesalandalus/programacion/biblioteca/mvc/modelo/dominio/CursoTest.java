package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;


import org.junit.Test;

public class CursoTest {
	
	private static final String TEXTO_NO_ESPERADO = "El texto devuelto no es el esperado.";

	@Test
	public void valoresValidosDevuelveTextosValidos() {
		Curso curso = Curso.PRIMERO;
		assertThat(TEXTO_NO_ESPERADO, curso.toString(), is("1º ESO"));
		curso = Curso.SEGUNDO;
		assertThat(TEXTO_NO_ESPERADO, curso.toString(), is("2º ESO"));
		curso = Curso.TERCERO;
		assertThat(TEXTO_NO_ESPERADO, curso.toString(), is("3º ESO"));
		curso = Curso.CUARTO;
		assertThat(TEXTO_NO_ESPERADO, curso.toString(), is("4º ESO"));
	}

}
