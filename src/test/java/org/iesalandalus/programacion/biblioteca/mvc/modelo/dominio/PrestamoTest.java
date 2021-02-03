package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

public class PrestamoTest {
	
	private static final String ERROR_ALUMNO_NULO = "ERROR: El alumno no puede ser nulo.";
	private static final String ERROR_LIBRO_NULO = "ERROR: El libro no puede ser nulo.";
	private static final String ERROR_FECHA_PRESTAMO_NULA = "ERROR: La fecha de préstamo no puede ser nula.";
	private static final String ERROR_FECHA_PRESTAMO_FUTURA = "ERROR: La fecha de préstamo no puede ser futura.";
	private static final String ERROR_FECHA_DEVOLUCION_NULA = "ERROR: La fecha de devolución no puede ser nula.";
	private static final String ERROR_FECHA_DEVOLUCION_FUTURA = "ERROR: La fecha de devolución no puede ser futura.";
	private static final String ERROR_FECHA_DEVOLUCION_ANTERIOR_PRESTAMO = "ERROR: La fecha de devolución debe ser posterior a la fecha de préstamo.";
	private static final String ERROR_FECHA_DEVOLUCION_YA_REGISTRADA = "ERROR: La devolución ya estaba registrada.";
	private static final String ERROR_COPIAR_PRESTAMO_NULO = "ERROR: No es posible copiar un préstamo nulo.";
	private static final String ALUMNO_INCORRECTO = "Debería haber saltado una excepción indicando que el alumno es incorrecto.";
	private static final String LIBRO_INCORRECTO = "Debería haber saltado una excepción indicando que el libro es incorrecto.";
	private static final String FECHA_PRESTAMO_INCORRECTA = "Debería haber saltado una excepción indicando que la fecha de préstamo es incorrecta.";
	private static final String FECHA_DEVOLUCION_INCORRECTA = "Debería haber saltado una excepción indicando que la fecha de devolución es incorrecta.";
	private static final String PRESTAMO_NULO = "Debería haber saltado una excepción indicando que no se puede copiar un préstamo nulo.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String PRESTAMO_NO_ESPERADO = "El préstamo copiado debería ser el mismo que el pasado como parámetro.";
	private static final String ALUMNO_NO_ESPERADO = "El alumno devuelto no es el mismo que el pasado al constructor.";
	private static final String LIBRO_NO_ESPERADO = "El libro devuelto no es el mismo que el pasado al constructor.";
	private static final String FECHA_PRESTAMO_NO_ESPERADA = "La fecha de préstamo devuelta no es la misma que la pasado al constructor.";
	private static final String FECHA_DEVOLUCION_NO_ESPERADA = "La fecha de devolución devuelta no es la misma que la pasado al constructor.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto.";
	private static final String PUNTOS_NO_ESPERADOS = "Los puntos no son los esperados.";
	private static final String OBJETOS_DEBERIAN_SER_IGUALES = "Los objetos deberían ser iguales.";
	private static final String OBJETOS_DEBERIAN_SER_DIFERENTES = "Los objetos debería ser diferentes.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";

	private static Alumno alumno;
	private static Libro libro;
	private static LocalDate hoy;
	private static LocalDate manana;
	private static LocalDate semanaPasada;
	
	@BeforeClass
	public static void inicializaAtributos() {
		alumno = new Alumno("José Ramón Jiménez Reyes", "joseramon.jimenez@iesalandalus.org", Curso.CUARTO);
		libro = new Libro("Cien años de soledad", "Gabriel García Márquez", 471);
		hoy = LocalDate.now();
		manana = hoy.plusDays(1);
		semanaPasada = hoy.minusDays(7);
	}

	@Test
	public void constructorAlumnoValidoLibroValidoFechaPrestamoValidaCreaPrestamoCorrectamente() {
		Prestamo prestamo = new Prestamo(alumno, libro, hoy);
		assertThat(ALUMNO_NO_ESPERADO, prestamo.getAlumno(), is(alumno));
		assertThat(REFERENCIA_NO_ESPERADA, prestamo.getAlumno(), not(sameInstance(alumno)));
		assertThat(LIBRO_NO_ESPERADO, prestamo.getLibro(), is(libro));
		assertThat(REFERENCIA_NO_ESPERADA, prestamo.getLibro(), not(sameInstance(libro)));
		assertThat(FECHA_PRESTAMO_NO_ESPERADA, prestamo.getFechaPrestamo(), is(hoy));
	}
	
	@Test
	public void constructorAlumnoNoValidoLibroValidoFechaPrestamoValidaLanzaExcepcion() {
		Prestamo prestamo = null;
		try {
			prestamo = new Prestamo(null, libro, hoy);
			fail(ALUMNO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_ALUMNO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, prestamo, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorAlumnoValidoLibroNoValidoFechaPrestamoValidaLanzaExcepcion() {
		Prestamo prestamo = null;
		try {
			prestamo = new Prestamo(alumno, null, hoy);
			fail(LIBRO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_LIBRO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, prestamo, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorAlumnoValidoLibroValidoFechaPrestamoNoValidaLanzaExcepcion() {
		Prestamo prestamo = null;
		try {
			prestamo = new Prestamo(alumno, libro, null);
			fail(FECHA_PRESTAMO_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_FECHA_PRESTAMO_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, prestamo, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			prestamo = new Prestamo(alumno, libro, manana);
			fail(FECHA_PRESTAMO_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_FECHA_PRESTAMO_FUTURA));
			assertThat(OBJETO_DEBERIA_SER_NULO, prestamo, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorPrestamoValidoCopiaPrestamoCorrectamente() {
		Prestamo prestamo1 = new Prestamo(alumno, libro, hoy);
		Prestamo prestamo2 = new Prestamo(prestamo1);
		assertThat(PRESTAMO_NO_ESPERADO, prestamo2, is(prestamo1));
		assertThat(REFERENCIA_NO_ESPERADA, prestamo1, not(sameInstance(prestamo2)));
		assertThat(ALUMNO_NO_ESPERADO, prestamo2.getAlumno(), is(alumno));
		assertThat(REFERENCIA_NO_ESPERADA, prestamo2.getAlumno(), not(sameInstance(alumno)));
		assertThat(LIBRO_NO_ESPERADO, prestamo2.getLibro(), is(libro));
		assertThat(REFERENCIA_NO_ESPERADA, prestamo2.getLibro(), not(sameInstance(libro)));
		assertThat(FECHA_PRESTAMO_NO_ESPERADA, prestamo2.getFechaPrestamo(), is(hoy));
	}
	
	@Test
	public void constructorPrestamoNoValidoLanzaExcepcion() {
		Prestamo prestamo = null;
		try {
			prestamo = new Prestamo(null);
			fail(PRESTAMO_NULO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_COPIAR_PRESTAMO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, prestamo, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getPrestamoFicticioAlumnoValidoLibroValidoCreaPrestamoCorrectamente() {
		Prestamo prestamo = Prestamo.getPrestamoFicticio(alumno, libro);
		assertThat(ALUMNO_NO_ESPERADO, prestamo.getAlumno(), is(alumno));
		assertThat(REFERENCIA_NO_ESPERADA, prestamo.getAlumno(), not(sameInstance(alumno)));
		assertThat(LIBRO_NO_ESPERADO, prestamo.getLibro(), is(libro));
		assertThat(REFERENCIA_NO_ESPERADA, prestamo.getLibro(), not(sameInstance(libro)));
		assertThat(FECHA_PRESTAMO_NO_ESPERADA, prestamo.getFechaPrestamo(), is(hoy));
	}
	
	@Test
	public void getPrestamoFicticioAlumnoNoValidoLibroValidoLanzaExcepcion() {
		Prestamo prestamo = null;
		try {
			prestamo = Prestamo.getPrestamoFicticio(null, libro);
			fail(ALUMNO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_ALUMNO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, prestamo, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getPrestamoFicticioAlumnoValidoLibroNoValidoLanzaExcepcion() {
		Prestamo prestamo = null;
		try {
			prestamo = Prestamo.getPrestamoFicticio(alumno, null);
			fail(LIBRO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_LIBRO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, prestamo, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void devolverFechaDevolucionValidaRealizaCorrectamenteDevolucion() {
		Prestamo prestamo = new Prestamo(alumno, libro, semanaPasada);
		prestamo.devolver(hoy);
		assertThat(FECHA_DEVOLUCION_NO_ESPERADA, prestamo.getFechaDevolucion(), is(hoy));
	}
	
	@Test
	public void devolverFechaDevolucionNoValidaLanzaExcepcion() {
		Prestamo prestamo = new Prestamo(alumno, libro, hoy);
		try {
			prestamo.devolver(null);
			fail(FECHA_DEVOLUCION_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_FECHA_DEVOLUCION_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, prestamo.getFechaDevolucion(), is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			prestamo.devolver(manana);
			fail(FECHA_DEVOLUCION_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_FECHA_DEVOLUCION_FUTURA));
			assertThat(OBJETO_DEBERIA_SER_NULO, prestamo.getFechaDevolucion(), is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			prestamo.devolver(hoy);
			fail(FECHA_DEVOLUCION_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_FECHA_DEVOLUCION_ANTERIOR_PRESTAMO));
			assertThat(OBJETO_DEBERIA_SER_NULO, prestamo.getFechaDevolucion(), is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			prestamo.devolver(semanaPasada);
			fail(FECHA_DEVOLUCION_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_FECHA_DEVOLUCION_ANTERIOR_PRESTAMO));
			assertThat(OBJETO_DEBERIA_SER_NULO, prestamo.getFechaDevolucion(), is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		prestamo = new Prestamo(alumno, libro, semanaPasada);
		prestamo.devolver(hoy);
		try {
			prestamo.devolver(hoy);
			fail(FECHA_DEVOLUCION_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_FECHA_DEVOLUCION_YA_REGISTRADA));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getPuntosDevuelvePuntosCorrectamente() {
		Prestamo prestamo = new Prestamo(alumno, libro, semanaPasada);
		assertThat(PUNTOS_NO_ESPERADOS, prestamo.getPuntos(), is(0));
		prestamo.devolver(hoy);
		assertThat(PUNTOS_NO_ESPERADOS, prestamo.getPuntos(), is(1));
		prestamo = new Prestamo(alumno, libro, hoy.minusDays(21));
		prestamo.devolver(hoy);
		assertThat(PUNTOS_NO_ESPERADOS, prestamo.getPuntos(), is(0));
		prestamo = new Prestamo(alumno, libro, hoy.minusDays(1));
		prestamo.devolver(hoy);
		assertThat(PUNTOS_NO_ESPERADOS, prestamo.getPuntos(), is(10));
		prestamo = new Prestamo(alumno, libro, hoy.minusDays(2));
		prestamo.devolver(hoy);
		assertThat(PUNTOS_NO_ESPERADOS, prestamo.getPuntos(), is(5));
		prestamo = new Prestamo(alumno, libro, hoy.minusDays(5));
		prestamo.devolver(hoy);
		assertThat(PUNTOS_NO_ESPERADOS, prestamo.getPuntos(), is(2));
	}
	
	@Test
	public void equalsHashCodeComparaCorrectamente() {
		Prestamo prestamo1 = new Prestamo(alumno, libro, hoy);
		Prestamo prestamo2 = new Prestamo(prestamo1);
		Libro libro2 = new Libro("El retrato de Dorian Gray", "Oscar Wilde", 275);
		Prestamo prestamo3 = new Prestamo(alumno, libro2, hoy);
		assertThat(OBJETOS_DEBERIAN_SER_IGUALES, prestamo1, is(prestamo1));
		assertThat(OBJETOS_DEBERIAN_SER_IGUALES, prestamo1.hashCode(), is(prestamo1.hashCode()));
		assertThat(OBJETOS_DEBERIAN_SER_IGUALES, prestamo1, is(prestamo2));
		assertThat(OBJETOS_DEBERIAN_SER_IGUALES, prestamo1.hashCode(), is(prestamo2.hashCode()));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, prestamo1, is(not(prestamo3)));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, prestamo1.hashCode(), is(not(prestamo3.hashCode())));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, prestamo1, is(not(nullValue())));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, prestamo1.hashCode(), is(not(nullValue().hashCode())));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, prestamo1, is(not("")));	
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, prestamo1.hashCode(), is(not("".hashCode())));
		prestamo3 = new Prestamo(Alumno.getAlumnoFicticio("kk@kk.es"), libro, hoy);
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, prestamo1, is(not(prestamo3)));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, prestamo1.hashCode(), is(not(prestamo3.hashCode())));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, prestamo2, is(not(prestamo3)));
		assertThat(OBJETOS_DEBERIAN_SER_DIFERENTES, prestamo2.hashCode(), is(not(prestamo3.hashCode())));
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		Prestamo prestamo = new Prestamo(alumno, libro, semanaPasada);
		assertThat(CADENA_NO_ESPERADA, prestamo.toString(), is(String.format("alumno=(%s), libro=(%s), fecha de préstamo=%s, puntos=%d", alumno, libro, semanaPasada.format(Prestamo.FORMATO_FECHA), 0)));
		prestamo.devolver(hoy);
		assertThat(CADENA_NO_ESPERADA, prestamo.toString(), is(String.format("alumno=(%s), libro=(%s), fecha de préstamo=%s, fecha de devolución=%s, puntos=%d", alumno, libro, semanaPasada.format(Prestamo.FORMATO_FECHA), hoy.format(Prestamo.FORMATO_FECHA), 1)));
	}

}
