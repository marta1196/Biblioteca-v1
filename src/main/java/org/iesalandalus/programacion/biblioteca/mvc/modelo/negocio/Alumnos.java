package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;

public class Alumnos {

	private List<Alumno> coleccionAlumnos;

	public Alumnos() {

		coleccionAlumnos = new ArrayList<>();
	}

	public List<Alumno> get() {

		List<Alumno> copiaAlumnos = copiaProfundaAlumnos();
		copiaAlumnos.sort(Comparator.comparing(Alumno::getNombre));
		
		return copiaAlumnos;
	}

	private List<Alumno> copiaProfundaAlumnos() {

		List<Alumno> copiaAlumnos = new ArrayList<>();

		for (Alumno alumno : coleccionAlumnos) {

			copiaAlumnos.add(new Alumno(alumno));
		}

		return copiaAlumnos;
	}

	public int getTamano() {

		return coleccionAlumnos.size();
	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {

		if (alumno == null) {

			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}

		if (coleccionAlumnos.contains(alumno)) {

			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese correo.");

		} else {

			coleccionAlumnos.add(new Alumno(alumno));
		}
	}

	public Alumno buscar(Alumno alumno) {

		int indiceAlumno;

		if (alumno == null) {

			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}

		indiceAlumno = coleccionAlumnos.indexOf(alumno);

		if (indiceAlumno == -1) {

			alumno = null;

		} else {

			alumno = new Alumno(coleccionAlumnos.get(indiceAlumno));
		}

		return alumno;
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {

		if (alumno == null) {

			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}

		if (!coleccionAlumnos.contains(alumno)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese correo.");

		} else {

			coleccionAlumnos.remove(alumno);
		}
	}
}
