package dao;

import java.util.ArrayList;
import entidades.Alumno;

public interface AlumnoDao {
	
	public Alumno leerAlumno(String legajoAlumno);
	public ArrayList<Alumno> leerAlumnos();
	public String proximoLegajo();
	public boolean altaAlumno(Alumno alumno);
	public boolean existeDni(String dni);
	public boolean bajaAlumno(String legajo);
	public boolean modificarAlumno(Alumno alumno);
	public ArrayList<Alumno> buscarXlegajo(String lalum);
	public ArrayList<Alumno> buscarXNombre(String nombre);
	public ArrayList<Alumno> buscarXdni(String dni);
	public ArrayList<Alumno> buscarXProvincia(String idProvincia);
	public ArrayList<Alumno> buscarXNacionalidad(String idPais);
	public ArrayList<Alumno> buscarDadosDeBaja();
}
