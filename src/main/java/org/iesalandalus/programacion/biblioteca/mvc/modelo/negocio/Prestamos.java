package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;

public class Prestamos {

	private Prestamo[] coleccionPrestamos;
	private int capacidad;
	private int tamano;

	public Prestamos(int capacidad) {

		if (capacidad <= 0) {

			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}

		tamano = 0;
		this.capacidad = capacidad;
		coleccionPrestamos = new Prestamo[capacidad];
	}

	public Prestamo[] get() {

		return copiaProfundaPrestamos();
	}

	private Prestamo[] copiaProfundaPrestamos() {

		Prestamo[] copiaPrestamos = new Prestamo[capacidad];

		for (int i = 0; !tamanoSuperado(i); i++) {

			copiaPrestamos[i] = new Prestamo(coleccionPrestamos[i]);
		}

		return copiaPrestamos;
	}

	public Prestamo[] get(Alumno alumno) {

		int j = 0;

		if (alumno == null) {

			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}

		Prestamo[] prestamoAlumno = new Prestamo[capacidad];

		for (int i = 0; !tamanoSuperado(i); i++) {

			if (coleccionPrestamos[i].getAlumno().equals(alumno)) {

				prestamoAlumno[j++] = new Prestamo(coleccionPrestamos[i]);
			}

		}

		return prestamoAlumno;
	}

	public Prestamo[] get(Libro libro) {

		int j = 0;

		if (libro == null) {

			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}

		Prestamo[] prestamoLibro = new Prestamo[capacidad];

		for (int i = 0; !tamanoSuperado(i); i++) {

			if (coleccionPrestamos[i].getLibro().equals(libro)) {

				prestamoLibro[j++] = new Prestamo(coleccionPrestamos[i]);
			}
		}

		return prestamoLibro;
	}

	public Prestamo[] get(LocalDate fecha) {

		int j = 0;

		if (fecha == null) {

			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}

		Prestamo[] prestamoFecha = new Prestamo[capacidad];

		for (int i = 0; !tamanoSuperado(i); i++) {

			if (mismoMes(coleccionPrestamos[i].getFechaPrestamo(), fecha)) {

				prestamoFecha[j++] = new Prestamo(coleccionPrestamos[i]);
			}
		}

		return prestamoFecha;
	}

	private boolean mismoMes(LocalDate primeraFecha, LocalDate segundaFecha) {

		return (primeraFecha.getYear() == segundaFecha.getYear() && primeraFecha.getMonth() == segundaFecha.getMonth());
	}

	public int getTamano() {

		return tamano;
	}

	public int getCapacidad() {

		return capacidad;
	}

	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {

		int indicePrestamo = buscarIndice(prestamo);

		if (prestamo == null) {

			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}

		if (capacidadSuperada(indicePrestamo)) {

			throw new OperationNotSupportedException("ERROR: No se aceptan más préstamos.");
		}

		if (!tamanoSuperado(indicePrestamo)) {

			throw new OperationNotSupportedException("ERROR: Ya existe un préstamo igual.");

		} else {

			coleccionPrestamos[indicePrestamo] = new Prestamo(prestamo);
			tamano++;
		}
	}

	private int buscarIndice(Prestamo prestamo) {

		boolean existePrestamo = false;
		int indice = 0;

		while (!tamanoSuperado(indice) && !existePrestamo) {

			if (coleccionPrestamos[indice].equals(prestamo)) {

				existePrestamo = true;

			} else {

				indice++;
			}
		}

		return indice;
	}

	private boolean tamanoSuperado(int indice) {

		return indice >= tamano;
	}

	private boolean capacidadSuperada(int indice) {

		return indice >= capacidad;
	}

	public void devolver(Prestamo prestamo, LocalDate fechaDevolver) throws OperationNotSupportedException {

		int indicePrestamo = buscarIndice(prestamo);

		if (prestamo == null) {

			throw new NullPointerException("ERROR: No se puede devolver un préstamo nulo.");

		} else if (tamanoSuperado(indicePrestamo)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		}

		prestamo.devolver(fechaDevolver);
		coleccionPrestamos[indicePrestamo] = prestamo;
	}

	public Prestamo buscar(Prestamo prestamo) {

		int indicePrestamo = buscarIndice(prestamo);

		if (prestamo == null) {

			throw new IllegalArgumentException("ERROR: No se puede buscar un préstamo nulo.");
		}

		if (!tamanoSuperado(indicePrestamo)) {

			prestamo = new Prestamo(coleccionPrestamos[indicePrestamo]);

		} else {

			prestamo = null;
		}

		return prestamo;
	}

	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {

		int indicePrestamo = buscarIndice(prestamo);

		if (prestamo == null) {

			throw new IllegalArgumentException("ERROR: No se puede borrar un préstamo nulo.");
		}

		if (tamanoSuperado(indicePrestamo)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");

		} else {

			desplazarUnaPosicionHaciaIzquierda(indicePrestamo);
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {

		int i;

		for (i = indice; !tamanoSuperado(i); i++) {

			coleccionPrestamos[i] = coleccionPrestamos[i + 1];
		}

		coleccionPrestamos[i] = null;
		tamano--;
	}
}
