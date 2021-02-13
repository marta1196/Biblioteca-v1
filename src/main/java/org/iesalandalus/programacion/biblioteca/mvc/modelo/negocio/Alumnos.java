package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;

public class Alumnos {

	private Alumno[] coleccionAlumnos;
	private int capacidad;
	private int tamano;

	public Alumnos(int capacidad) {

		if (capacidad <= 0) {

			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}

		tamano = 0;
		this.capacidad = capacidad;
		coleccionAlumnos = new Alumno[capacidad];
	}

	public Alumno[] get() {

		return copiaProfundaAlumnos();
	}

	private Alumno[] copiaProfundaAlumnos() {

		Alumno[] copiaAlumnos = new Alumno[capacidad];

		for (int i = 0; !tamanoSuperado(i); i++) {

			copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
		}

		return copiaAlumnos;
	}

	public int getTamano() {

		return tamano;
	}

	public int getCapacidad() {

		return capacidad;
	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {

		int indiceAlumno = buscarIndice(alumno);

		if (alumno == null) {

			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}

		if (capacidadSuperada(indiceAlumno)) {

			throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
		}

		if (!tamanoSuperado(indiceAlumno)) {

			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese correo.");

		} else {

			coleccionAlumnos[indiceAlumno] = new Alumno(alumno);
			tamano++;
		}
	}

	private int buscarIndice(Alumno alumno) {

		boolean existeAlumno = false;
		int indice = 0;

		while (!tamanoSuperado(indice) && !existeAlumno) {

			if (coleccionAlumnos[indice].equals(alumno)) {

				existeAlumno = true;

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

	public Alumno buscar(Alumno alumno) {

		int indiceAlumno;

		if (alumno == null) {

			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}

		indiceAlumno = buscarIndice(alumno);

		if (!tamanoSuperado(indiceAlumno)) {

			alumno = new Alumno(coleccionAlumnos[indiceAlumno]);

		} else {

			alumno = null;
		}

		return alumno;
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {

		int indiceAlumno = buscarIndice(alumno);

		if (alumno == null) {

			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}

		if (tamanoSuperado(indiceAlumno)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese correo.");

		} else {

			desplazarUnaPosicionHaciaIzquierda(indiceAlumno);
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {

		int i;

		for (i = indice; !tamanoSuperado(i); i++) {

			coleccionAlumnos[i] = coleccionAlumnos[i + 1];
		}

		coleccionAlumnos[i] = null;
		tamano--;
	}
}
