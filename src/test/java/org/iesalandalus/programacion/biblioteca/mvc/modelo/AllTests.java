package org.iesalandalus.programacion.biblioteca.mvc.modelo;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.AlumnoTest;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.LibroTest;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.PrestamoTest;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.AlumnosTest;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.LibrosTest;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.PrestamosTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AlumnoTest.class, LibroTest.class, PrestamoTest.class,
				AlumnosTest.class, LibrosTest.class, PrestamosTest.class,
				ModeloTest.class })
public class AllTests {

}
