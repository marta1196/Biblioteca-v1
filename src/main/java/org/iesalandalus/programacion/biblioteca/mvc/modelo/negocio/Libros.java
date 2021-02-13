package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;

public class Libros {

	private List<Libro> coleccionLibros;

	public Libros() {

		coleccionLibros = new ArrayList<>();
	}

	public List<Libro> get() {

		return copiaProfundaLibros();
	}

	private List<Libro> copiaProfundaLibros() {

		List<Libro> copiaLibros = new ArrayList<>();

		for (Libro libro : coleccionLibros) {

			copiaLibros.add(new Libro(libro));
		}

		return copiaLibros;
	}

	public int getTamano() {

		return coleccionLibros.size();
	}

	public void insertar(Libro libro) throws OperationNotSupportedException {

		if (libro == null) {

			throw new NullPointerException("ERROR: No se puede insertar un libro nulo.");
		}

		if (coleccionLibros.contains(libro)) {

			throw new OperationNotSupportedException("ERROR: Ya existe un libro con ese título y autor.");

		} else {

			coleccionLibros.add(new Libro(libro));
		}
	}

	public Libro buscar(Libro libro) {

		int indiceLibro;

		if (libro == null) {

			throw new IllegalArgumentException("ERROR: No se puede buscar un libro nulo.");
		}

		indiceLibro = coleccionLibros.indexOf(libro);

		if (indiceLibro == -1) {

			libro = null;

		} else {

			libro = new Libro(coleccionLibros.get(indiceLibro));
		}

		return libro;
	}

	public void borrar(Libro libro) throws OperationNotSupportedException {

		if (libro == null) {

			throw new IllegalArgumentException("ERROR: No se puede borrar un libro nulo.");
		}

		if (!coleccionLibros.contains(libro)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún libro con ese título y autor.");

		} else {

			coleccionLibros.remove(libro);
		}
	}
}