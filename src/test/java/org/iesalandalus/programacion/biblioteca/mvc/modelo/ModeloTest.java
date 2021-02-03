package org.iesalandalus.programacion.biblioteca.mvc.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Libros;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Prestamos;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ModeloTest {
	
	private static final String ERROR_PRESTAMO_ALUMNO_NO_EXISTENTE = "ERROR: No existe el alumno del préstamo.";
	private static final String ERROR_PRESTAMO_LIBRO_NO_EXISTENTE = "ERROR: No existe el libro del préstamo.";
	private static final String ERROR_PRESTAMO_NULO = "ERROR: No se puede prestar un préstamo nulo.";
	private static final String ERROR_PRESTAMO_NO_EXISTENTE = "ERROR: No se puede devolver un préstamo no prestado.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	private static final String EXCEPCION_ESPERADA = "Debería haber saltado la excepción.";
	
	private static Alumno alumnoExistente;
	private static Alumno alumnoNoExistente;
	private static Libro libroExistente;
	private static Libro libroNoExistente;
	private static Prestamo prestamoExistente;
	private static Prestamo prestamoNoExistente;
	
	@InjectMocks private static Modelo modelo;
	
	@Mock private Alumnos alumnosSimulados;
	@Mock private Libros librosSimulados;
	@Mock private Prestamos prestamosSimulados;

	@BeforeClass
	public static void asignarValoresAtributos() {
		alumnoExistente = Alumno.getAlumnoFicticio("bob@gmail.com");
		alumnoNoExistente = Alumno.getAlumnoFicticio("patricio@gmail.com");
		libroExistente = Libro.getLibroFicticio("Cien años de soledad", "Gabriel García Márquez");
		libroNoExistente = Libro.getLibroFicticio("El retrato de Dorian Gray", "Oscar Wilde");
		prestamoExistente = Prestamo.getPrestamoFicticio(alumnoExistente, libroExistente);
		prestamoNoExistente = Prestamo.getPrestamoFicticio(alumnoNoExistente, libroNoExistente);
	}
	
	@Test
	public void insertarAlumnoLlamaAlumnosInsertar() {
		try {
			modelo.insertar(alumnoExistente);
			verify(alumnosSimulados).insertar(alumnoExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarAlumnoLlamaAlumnosBuscar() {
		modelo.buscar(alumnoExistente);
		verify(alumnosSimulados).buscar(alumnoExistente);
	}
	
	@Test
	public void borrarAlumnoSinPrestamosLlamaPrestamosGetAlumnosBorrar() {
		InOrder orden = Mockito.inOrder(alumnosSimulados, prestamosSimulados);
		try {
			modelo.borrar(alumnoExistente);
			orden.verify(prestamosSimulados).get(alumnoExistente);
			orden.verify(alumnosSimulados).borrar(alumnoExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void borrarAlumnoConPrestamosLlamaPrestamosGetPrestamosBorrarAlumnosBorrar() {
		InOrder orden = Mockito.inOrder(alumnosSimulados, prestamosSimulados);
		List<Prestamo> prestamosAlumno = simularComportamientoBorrarAlumnoConPrestamos();
		try {
			modelo.borrar(alumnoExistente);
			orden.verify(prestamosSimulados).get(alumnoExistente);
			for (Prestamo prestamo : prestamosAlumno) {
				orden.verify(prestamosSimulados).borrar(prestamo);
			}
			orden.verify(alumnosSimulados).borrar(alumnoExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private List<Prestamo> simularComportamientoBorrarAlumnoConPrestamos() {
		List<Prestamo> prestamosAlumno = new ArrayList<>();
		prestamosAlumno.add(prestamoExistente);
		when(prestamosSimulados.get(alumnoExistente)).thenReturn(prestamosAlumno);
		return prestamosAlumno;
	}
	
	@Test
	public void getAlumnosLlamaAlumnosGet() {
		modelo.getAlumnos();
		verify(alumnosSimulados).get();
	}
	
	@Test
	public void insertarLibroLlamaLibrosInsertar() {
		try {
			modelo.insertar(libroExistente);
			verify(librosSimulados).insertar(libroExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarLibroLlamaLibrosBuscar() {
		modelo.buscar(libroExistente);
		verify(librosSimulados).buscar(libroExistente);
	}
	
	@Test
	public void borrarLibroSinPrestamosLlamaPrestamosGetLibrosBorrar() {
		InOrder orden = Mockito.inOrder(librosSimulados, prestamosSimulados);
		try {
			modelo.borrar(libroExistente);
			orden.verify(prestamosSimulados).get(libroExistente);
			orden.verify(librosSimulados).borrar(libroExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void borrarLibroConPrestamosLlamaPrestamosGetPrestamosBorrarLibrosBorrar() {
		InOrder orden = Mockito.inOrder(librosSimulados, prestamosSimulados);
		List<Prestamo> prestamosLibro = simularComportamientoBorrarLibroConPrestamos();
		try {
			modelo.borrar(libroExistente);
			orden.verify(prestamosSimulados).get(libroExistente);
			for (Prestamo prestamo : prestamosLibro) {
				orden.verify(prestamosSimulados).borrar(prestamo);
			}
			orden.verify(librosSimulados).borrar(libroExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private List<Prestamo> simularComportamientoBorrarLibroConPrestamos() {
		List<Prestamo> prestamosLibro = new ArrayList<>();
		prestamosLibro.add(prestamoExistente);
		when(prestamosSimulados.get(libroExistente)).thenReturn(prestamosLibro);
		return prestamosLibro;
	}
	
	@Test
	public void getLibrosLlamaLibrosGet() {
		modelo.getLibros();
		verify(librosSimulados).get();
	}
	
	@Test
	public void prestarPrestamoValidoLlamaAlumnosBuscarLibrosBuscarPrestamosInsertar() {
		simularComportamientoPrestarPrestamoValido();
		InOrder orden = Mockito.inOrder(alumnosSimulados, librosSimulados, prestamosSimulados);
		try {
			modelo.prestar(prestamoExistente);
			orden.verify(alumnosSimulados).buscar(alumnoExistente);
			orden.verify(librosSimulados).buscar(libroExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private void simularComportamientoPrestarPrestamoValido() {
		when(alumnosSimulados.buscar(alumnoExistente)).thenReturn(alumnoExistente);
		when(librosSimulados.buscar(libroExistente)).thenReturn(libroExistente);
	}
	
	@Test
	public void prestarPrestamoAlumnoNoExistenteLanzaExcepcion() {
		Prestamo prestamoAlumnoNoExistente = Prestamo.getPrestamoFicticio(alumnoNoExistente, libroExistente);
		try {
			simularComportamientoPrestarPrestamoAlumnoNoExistente();
			modelo.prestar(prestamoAlumnoNoExistente);
			fail(EXCEPCION_ESPERADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PRESTAMO_ALUMNO_NO_EXISTENTE));
		}
	}	
	
	private void simularComportamientoPrestarPrestamoAlumnoNoExistente() {
		when(alumnosSimulados.buscar(alumnoNoExistente)).thenReturn(null);
	}
	
	@Test
	public void prestarPrestamoLibroNoExistenteLanzaExcepcion() {
		Prestamo prestamoLibroNoExistente = Prestamo.getPrestamoFicticio(alumnoExistente, libroNoExistente);
		try {
			simularComportamientoPrestarPrestamoLibroNoExistente();
			modelo.prestar(prestamoLibroNoExistente);
			fail(EXCEPCION_ESPERADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PRESTAMO_LIBRO_NO_EXISTENTE));
		}
	}	
	
	private void simularComportamientoPrestarPrestamoLibroNoExistente() {
		when(alumnosSimulados.buscar(alumnoExistente)).thenReturn(alumnoExistente);
		when(librosSimulados.buscar(libroNoExistente)).thenReturn(null);
	}
	
	@Test
	public void prestarPrestamoNuloLanzaExcepcion() {
		Prestamo prestamoNulo = null;
		try {
			modelo.prestar(prestamoNulo);
			fail(EXCEPCION_ESPERADA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PRESTAMO_NULO));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void devolverPrestamoValidoLlamaAlumnosBuscarLibrosBuscarPrestamosInsertar() {
		simularComportamientoDevolverPrestamoValido();
		InOrder orden = Mockito.inOrder(prestamosSimulados);
		try {
			modelo.devolver(prestamoExistente, LocalDate.now());
			orden.verify(prestamosSimulados).buscar(prestamoExistente);
			orden.verify(prestamosSimulados).devolver(prestamoExistente, LocalDate.now());
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private void simularComportamientoDevolverPrestamoValido() {
		when(prestamosSimulados.buscar(prestamoExistente)).thenReturn(prestamoExistente);
	}
	
	@Test
	public void DevolverPrestamoNoExistenteLanzaExcepcion() {
		simularComportamientoDevolverPrestamoNoExistente();
		try {
			modelo.devolver(prestamoNoExistente, LocalDate.now());
			fail(EXCEPCION_ESPERADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PRESTAMO_NO_EXISTENTE));
		}
	}
	
	private void simularComportamientoDevolverPrestamoNoExistente() {
		when(prestamosSimulados.buscar(prestamoNoExistente)).thenReturn(null);
	}
	
	@Test
	public void buscarPrestamoLlamaPrestamosBuscar() {
		modelo.buscar(prestamoExistente);
		verify(prestamosSimulados).buscar(prestamoExistente);
	}
	
	@Test
	public void borrarPrestamoLlamaPrestamosBorrar() {
		try {
			modelo.borrar(prestamoExistente);
			verify(prestamosSimulados).borrar(prestamoExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getPrestamosLlamaPrestamosGet() {
		modelo.getPrestamos();
		verify(prestamosSimulados).get();
	}
	
	@Test
	public void getPrestamosAlumnoValidoLlamaPrestamosGet() {
		modelo.getPrestamos(alumnoExistente);
		verify(prestamosSimulados).get(alumnoExistente);
	}
	
	@Test
	public void getPrestamosLibroValidoLlamaPrestamosGet() {
		modelo.getPrestamos(libroExistente);
		verify(prestamosSimulados).get(libroExistente);
	}
	
	@Test
	public void getPrestamosFechaValidaLlamaPrestamosGet() {
		modelo.getPrestamos(LocalDate.now());
		verify(prestamosSimulados).get(LocalDate.now());
	}
	
	@Test
	public void getEstadisticaMensualPorCurso() {
		modelo.getEstadisticaMensualPorCurso(LocalDate.now());
		verify(prestamosSimulados).getEstadisticaMensualPorCurso(LocalDate.now());
	}

}
