package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;

public class Prestamos {

	List<Prestamo> coleccionPrestamos;
	
	public Prestamos() {

		coleccionPrestamos = new ArrayList<>();
	}

	public List<Prestamo> get() {

		return copiaProfundaPrestamos();
	}

	private List<Prestamo> copiaProfundaPrestamos() {

		List<Prestamo> copiaPrestamos = new ArrayList<>();

		for (Prestamo prestamo : coleccionPrestamos) {

			copiaPrestamos.add(new Prestamo(prestamo));
		}

		return copiaPrestamos;
	}
	
	public int getTamano() {

		return coleccionPrestamos.size();
	}
	
	public List<Prestamo> get(Alumno alumno) {
		
		if (alumno == null) {

			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}

		List<Prestamo> prestamoAlumno = new ArrayList<>();

		for (Prestamo prestamo : coleccionPrestamos) {

			if (prestamo.getAlumno().equals(alumno)) {

				prestamoAlumno.add(new Prestamo(prestamo));
			}
		}

		return prestamoAlumno;
	}
	
	public List<Prestamo> get(Libro libro) {

		if (libro == null) {

			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}

		List<Prestamo> prestamoLibro = new ArrayList<>();
		
		for (Prestamo prestamo : coleccionPrestamos) {

			if (prestamo.getLibro().equals(libro)) {

				prestamoLibro.add(new Prestamo(prestamo));
			}
		}

		return prestamoLibro;
	}
	
	public List<Prestamo> get(LocalDate fecha) {

		if (fecha == null) {

			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}

		List<Prestamo> prestamoFecha = new ArrayList<>();
		
		for (Prestamo prestamo : coleccionPrestamos) {

			if (mismoMes(prestamo.getFechaPrestamo(), fecha)) {

				prestamoFecha.add(new Prestamo(prestamo));
			}
		}

		return prestamoFecha;
	}

	private boolean mismoMes(LocalDate primeraFecha, LocalDate segundaFecha) {

		return (primeraFecha.getYear() == segundaFecha.getYear() && primeraFecha.getMonth() == segundaFecha.getMonth());
	}
	
	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {

		if (prestamo == null) {

			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}

		if (coleccionPrestamos.contains(prestamo)) {

			throw new OperationNotSupportedException("ERROR: Ya existe un préstamo igual.");

		} else {

			coleccionPrestamos.add(new Prestamo(prestamo));
		}
	}

	public void devolver(Prestamo prestamo, LocalDate fechaDevolver) throws OperationNotSupportedException {
		
		int indicePrestamo;

		if (prestamo == null) {

			throw new NullPointerException("ERROR: No se puede devolver un préstamo nulo.");
		} 
		
		indicePrestamo = coleccionPrestamos.indexOf(prestamo);
		
		if (indicePrestamo == -1) {

			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");

		} else {

			coleccionPrestamos.get(indicePrestamo).devolver(fechaDevolver);
		}
	}

	public Prestamo buscar(Prestamo prestamo) {

		int indicePrestamo;

		if (prestamo == null) {

			throw new IllegalArgumentException("ERROR: No se puede buscar un préstamo nulo.");
		}

		indicePrestamo = coleccionPrestamos.indexOf(prestamo);
		
		if (indicePrestamo == -1) {

			prestamo = null;

		} else {

			prestamo = new Prestamo(coleccionPrestamos.get(indicePrestamo));
		}

		return prestamo;
	}

	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {

		if (prestamo == null) {

			throw new IllegalArgumentException("ERROR: No se puede borrar un préstamo nulo.");
		}

		if (!coleccionPrestamos.contains(prestamo)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");

		} else {

			coleccionPrestamos.remove(prestamo);
		}
	}
}
