package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class LibroTest {
	
	private static final String ERROR_TITULO_NULO = "ERROR: El título no puede ser nulo.";
	private static final String ERROR_TITULO_NO_VALIDO = "ERROR: El título no puede estar vacío.";
	private static final String ERROR_AUTOR_NULO = "ERROR: El autor no puede ser nulo.";
	private static final String ERROR_AUTOR_NO_VALIDO = "ERROR: El autor no puede estar vacío.";
	private static final String ERROR_NUM_PAGINAS_NO_VALIDO = "ERROR: El número de páginas debe ser mayor que cero.";
	private static final String ERROR_COPIAR_LIBRO_NULO = "ERROR: No es posible copiar un libro nulo.";
	private static final String TITULO_INCORRECTO = "Debería haber saltado una excepción indicando que el título es incorrecto";
	private static final String AUTOR_INCORRECTO = "Debería haber saltado una excepción indicando que el autor es incorrecto";
	private static final String NUM_PAGINAS_INCORRECTO = "Debería haber saltado una excepción indicando que el número de curso es incorrecto";
	private static final String LIBRO_NULO = "Debería haber saltado una excepción indicando que no se puede copiar un libro nulo.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String LIBRO_NO_ESPERADO = "El libro copiado debería ser el mismo que el pasado como parámetro.";
	private static final String TITULO_NO_ESPERADO = "El título devuelto no es el mismo que el pasado al constructor.";
	private static final String AUTOR_NO_ESPERADO = "El autor devuelto no es el mismo que el pasado al constructor.";
	private static final String NUM_PAGINAS_NO_ESPERADO = "El número de páginas devuelto no es el mismo que el pasado al constructor.";
	private static final String PUNTOS_NO_ESPERADOS = "El número de puntos devuelto no es el esperado.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto alumno.";
	private static final String OBJETOS_DEBERIAN_SER_IGUALES = "Los objetos deberían ser iguales.";
	private static final String OBJETOS_DEBERIAN_SER_DIFERENTES = "Los objetos debería ser diferentes.";
	private static final String TITULO1 = "Cien años de soledad";
	private static final String AUTOR1 = "Gabriel García Márquez";
	private static final int NUM_PAGINAS1 = 471;
	private static final String TITULO2 = "El retrato de Dorian Gray";
	private static final String AUTOR2 = "Oscar Wilde";
	private static final int NUM_PAGINAS2 = 275;
	
	private static Libro primerLibro;
	private static Libro segundoLibro;
	
	@BeforeClass
	public static void inicializaAtributos() {
		primerLibro = new Libro(TITULO1, AUTOR1, NUM_PAGINAS1);
		segundoLibro = new Libro(TITULO2, AUTOR2, NUM_PAGINAS2);
	}

	@Test
	public void constructorTituloValidoAutorValidoNumPaginasValidoCreaLibroCorrectamente() {
		assertThat(TITULO_NO_ESPERADO, primerLibro.getTitulo(), is(TITULO1));
		assertThat(AUTOR_NO_ESPERADO, primerLibro.getAutor(), is(AUTOR1));
		assertThat(NUM_PAGINAS_NO_ESPERADO, primerLibro.getNumPaginas(), is(NUM_PAGINAS1));
		assertThat(TITULO_NO_ESPERADO, segundoLibro.getTitulo(), is(TITULO2));
		assertThat(AUTOR_NO_ESPERADO, segundoLibro.getAutor(), is(AUTOR2));
		assertThat(NUM_PAGINAS_NO_ESPERADO, segundoLibro.getNumPaginas(), is(NUM_PAGINAS2));
	}

	@Test
	public void constructorTituloNoValidoAutorValidoNumPaginasValidoLanzaExcepcion() {
		Libro libro = null;
		try {
			libro = new Libro(null, AUTOR1, NUM_PAGINAS1);
			fail(TITULO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TITULO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			libro = new Libro("", AUTOR1, NUM_PAGINAS1);
			fail(TITULO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TITULO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			libro = new Libro("  ", AUTOR1, NUM_PAGINAS1);
			fail(TITULO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TITULO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorTituloValidoAutorNoValidoNumPaginasValidoLanzaExcepcion() {
		Libro libro = null;
		try {
			libro = new Libro(TITULO2, null, NUM_PAGINAS2);
			fail(AUTOR_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AUTOR_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			libro = new Libro(TITULO2, "", NUM_PAGINAS2);
			fail(AUTOR_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AUTOR_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			libro = new Libro(TITULO2, "   ", NUM_PAGINAS2);
			fail(AUTOR_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AUTOR_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorTituloValidoAutorValidoNumPaginasNoValidoLanzaExcepcion() {
		Libro libro = null;
		try {
			libro = new Libro(TITULO1, AUTOR1, 0);
			fail(NUM_PAGINAS_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NUM_PAGINAS_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		Libro alumno = null;
		try {
			alumno = new Libro(TITULO1, AUTOR1, -1);
			fail(NUM_PAGINAS_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NUM_PAGINAS_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorLibroValidoCopiaLibroCorrectamente() {
		Libro libro1 = new Libro(TITULO1, AUTOR1, NUM_PAGINAS1);
		Libro libro2 = new Libro(libro1);
		assertThat(LIBRO_NO_ESPERADO, libro2, is(libro1));
		assertThat(TITULO_NO_ESPERADO, libro2.getTitulo(), is(TITULO1));
		assertThat(AUTOR_NO_ESPERADO, libro2.getAutor(), is(AUTOR1));
		assertThat(NUM_PAGINAS_NO_ESPERADO, libro2.getNumPaginas(), is(NUM_PAGINAS1));
	}
	
	@Test
	public void constructorLibroNuloLanzaExcepcion() {
		Libro libro = null;
		try {
			libro = new Libro(null);
			fail(LIBRO_NULO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_COPIAR_LIBRO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getLibroFicticioTituloValidoAutorValidoDevuelveLibroCorrectamente() {
		Libro libro = Libro.getLibroFicticio(TITULO1, AUTOR1);
		assertThat(TITULO_NO_ESPERADO, libro.getTitulo(), is(TITULO1));
		assertThat(AUTOR_NO_ESPERADO, libro.getAutor(), is(AUTOR1));
	}
	
	@Test
	public void getLibroFicticioTituloNoValidoAutorValidoLanzaExcepcion() {
		Libro libro = null;
		try {
			libro = Libro.getLibroFicticio(null, AUTOR1);
			fail(TITULO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TITULO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			libro = Libro.getLibroFicticio("", AUTOR1);
			fail(TITULO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TITULO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			libro = Libro.getLibroFicticio("  ", AUTOR1);
			fail(TITULO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TITULO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getLibroFicticioTituloValidoAutorNoValidoLanzaExcepcion() {
		Libro libro = null;
		try {
			libro = Libro.getLibroFicticio(TITULO2, null);
			fail(AUTOR_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AUTOR_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			libro = Libro.getLibroFicticio(TITULO2, "");
			fail(AUTOR_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AUTOR_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			libro = Libro.getLibroFicticio(TITULO2, "   ");
			fail(AUTOR_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AUTOR_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, libro, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getPuntosDevuelvePuntosCorrectamente() {
		assertThat(PUNTOS_NO_ESPERADOS, primerLibro.getPuntos(), is(9.5f));
		assertThat(PUNTOS_NO_ESPERADOS, segundoLibro.getPuntos(), is(6.0f));
	}
	
	@Test
	public void equalsHashCodeComparaCorrectamente() {
		Libro otroLibro = new Libro(TITULO1, AUTOR1, NUM_PAGINAS2);
		assertThat(OBJETOS_DEBERIAN_SER_IGUALES, primerLibro, is(primerLibro));
		assertThat(OBJETOS_DEBERIAN_SER_IGUALES, primerLibro.hashCode(), is(primerLibro.hashCode()));
		assertThat(OBJETOS_DEBERIAN_SER_IGUALES, primerLibro, is(otroLibro));
		assertThat(OBJETOS_DEBERIAN_SER_IGUALES, primerLibro.hashCode(), is(otroLibro.hashCode()));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, primerLibro, is(not(segundoLibro)));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, primerLibro.hashCode(), is(not(segundoLibro.hashCode())));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, primerLibro, is(not(nullValue())));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, primerLibro.hashCode(), is(not(nullValue().hashCode())));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, primerLibro, is(not("")));	
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, primerLibro.hashCode(), is(not("".hashCode())));
		otroLibro = new Libro(TITULO1, AUTOR2, NUM_PAGINAS2);
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, primerLibro, is(not(otroLibro)));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, primerLibro.hashCode(), is(not(otroLibro.hashCode())));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, segundoLibro, is(not(otroLibro)));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, segundoLibro.hashCode(), is(not(otroLibro.hashCode())));
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		assertThat(CADENA_NO_ESPERADA, primerLibro.toString(), is(String.format("título=%s, autor=%s, número de páginas=%s", TITULO1, AUTOR1, NUM_PAGINAS1)));
		assertThat(CADENA_NO_ESPERADA, segundoLibro.toString(), is(String.format("título=%s, autor=%s, número de páginas=%s", TITULO2, AUTOR2, NUM_PAGINAS2)));
	}

}
