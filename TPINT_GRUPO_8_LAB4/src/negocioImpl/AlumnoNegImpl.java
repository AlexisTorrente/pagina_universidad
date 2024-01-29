package negocioImpl;

import java.sql.Date;
import java.util.ArrayList;


import dao.AlumnoDao;
import daoImpl.AlumnoDaoImpl;
import entidades.Alumno;
import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;
import negocio.AlumnoNeg;

public class AlumnoNegImpl implements AlumnoNeg {

	AlumnoDao adao= new AlumnoDaoImpl();
	
	public Alumno obtenerAlumno(String legajoAlumno) {	
		 return adao.leerAlumno(legajoAlumno);
	}

	public ArrayList<Alumno> leerAlumnos() {
		return adao.leerAlumnos();
	}

	public boolean altaAlumno(String legajo, String dni, String nombreCompleto, String fechaNacimiento,
			Pais nacionalidad, Provincia provincia, Localidad localidad, String direccion, String telefono) {
		Alumno alumno = new Alumno();
		alumno.setLegajoAlumno(legajo);
		alumno.setDniAlumno(dni);
		alumno.setNombreYApellidoAlumno(nombreCompleto);
		alumno.setFechaNacimientoAlumno(fechaNacimiento);
		alumno.setPais(nacionalidad);
		alumno.setProvincia(provincia);
		alumno.setLocalidad(localidad);
		alumno.setDireccionAlumno(direccion);
		alumno.setTelefonoAlumno(telefono);
		
		return adao.altaAlumno(alumno);
	}

	public String proximoLegajo() {
		return adao.proximoLegajo();
	}

	public ArrayList<Alumno> buscarXlegajo(String lalum) {
		return adao.buscarXlegajo(lalum);
	}

	public ArrayList<Alumno> buscarXNombre(String nombre) {
		return adao.buscarXNombre(nombre);
	}

	public boolean existeDni(String dni) {
		return adao.existeDni(dni);
	}

	public boolean bajaAlumno(String legajo) {
		return adao.bajaAlumno(legajo);
	}

	public boolean modificarAlumno(String legajo, String nombreCompleto, String fechaNacimiento,
			Pais nacionalidad, Provincia provincia, Localidad localidad, String direccion, String telefono) {
		Alumno alumno = new Alumno();
		alumno.setLegajoAlumno(legajo);
		alumno.setNombreYApellidoAlumno(nombreCompleto);
		alumno.setFechaNacimientoAlumno(fechaNacimiento);
		alumno.setPais(nacionalidad);
		alumno.setProvincia(provincia);
		alumno.setLocalidad(localidad);
		alumno.setDireccionAlumno(direccion);
		alumno.setTelefonoAlumno(telefono);
		
		return adao.modificarAlumno(alumno);
	}

	public ArrayList<Alumno> buscarXdni(String dni) {
		return adao.buscarXdni(dni);
	}

	public ArrayList<Alumno> buscarXProvincia(String idProvincia) {
		return adao.buscarXProvincia(idProvincia);
	}

	public ArrayList<Alumno> buscarXNacionalidad(String idPais) {
		return adao.buscarXNacionalidad(idPais);
	}

	public ArrayList<Alumno> buscarDadosDeBaja() {
		return adao.buscarDadosDeBaja();
	}
}
