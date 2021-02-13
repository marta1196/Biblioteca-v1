package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;

public class Libros {

	private Libro[] coleccionLibros;
	private int capacidad;
	private int tamano;

	public Libros(int capacidad) {

		if (capacidad <= 0) {

			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}

		tamano = 0;
		this.capacidad = capacidad;
		coleccionLibros = new Libro[capacidad];
	}

	public Libro[] get() {

		return copiaProfundaLibros();
	}

	private Libro[] copiaProfundaLibros() {

		Libro[] copiaLibros = new Libro[capacidad];

		for (int i = 0; !tamanoSuperado(i); i++) {

			copiaLibros[i] = new Libro(coleccionLibros[i]);
		}

		return copiaLibros;
	}

	public int getTamano() {

		return tamano;
	}

	public int getCapacidad() {

		return capacidad;
	}

	public void insertar(Libro libro) throws OperationNotSupportedException {

		int indiceLibro = buscarIndice(libro);

		if (libro == null) {

			throw new NullPointerException("ERROR: No se puede insertar un libro nulo.");
		}

		if (capacidadSuperada(indiceLibro)) {

			throw new OperationNotSupportedException("ERROR: No se aceptan más libros.");
		}

		if (!tamanoSuperado(indiceLibro)) {

			throw new OperationNotSupportedException("ERROR: Ya existe un libro con ese título y autor.");

		} else {

			coleccionLibros[indiceLibro] = new Libro(libro);
			tamano++;
		}
	}

	private int buscarIndice(Libro libro) {

		boolean existeLibro = false;
		int indice = 0;

		while (!tamanoSuperado(indice) && !existeLibro) {

			if (coleccionLibros[indice].equals(libro)) {

				existeLibro = true;

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

	public Libro buscar(Libro libro) {

		int indiceLibro = buscarIndice(libro);

		if (libro == null) {

			throw new IllegalArgumentException("ERROR: No se puede buscar un libro nulo.");
		}

		if (!tamanoSuperado(indiceLibro)) {

			libro = new Libro(coleccionLibros[indiceLibro]);

		} else {

			libro = null;
		}

		return libro;
	}

	public void borrar(Libro libro) throws OperationNotSupportedException {

		int indiceLibro = buscarIndice(libro);

		if (libro == null) {

			throw new IllegalArgumentException("ERROR: No se puede borrar un libro nulo.");
		}

		if (tamanoSuperado(indiceLibro)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún libro con ese título y autor.");

		} else {

			desplazarUnaPosicionHaciaIzquierda(indiceLibro);
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {

		int i;

		for (i = indice; !tamanoSuperado(i); i++) {

			coleccionLibros[i] = coleccionLibros[i + 1];
		}

		coleccionLibros[i] = null;
		tamano--;
	}
}