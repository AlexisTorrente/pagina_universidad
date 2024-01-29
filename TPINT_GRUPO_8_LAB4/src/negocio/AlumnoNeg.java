package negocio;


import java.sql.Date;
import java.util.ArrayList;


import entidades.Alumno;
import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;

public interface AlumnoNeg {

	public Alumno obtenerAlumno(String legajoAlumno);	
	public ArrayList<Alumno> leerAlumnos();
	public String proximoLegajo();
	public boolean altaAlumno(String legajo, String dni, String nombreCompleto, String fechaNacimiento, Pais nacionalidad, Provincia provincia, Localidad localidad, String direccion, String telefono);
	public boolean existeDni(String dni);
	public boolean bajaAlumno(String legajo);
	public boolean modificarAlumno(String legajo, String nombreCompleto, String fechaNacimiento, Pais nacionalidad, Provincia provincia, Localidad localidad, String direccion, String telefono);
	public ArrayList<Alumno> buscarXlegajo(String lalum);
	public ArrayList<Alumno> buscarXNombre(String nombre);
	public ArrayList<Alumno> buscarXdni(String dni);
	public ArrayList<Alumno> buscarXProvincia(String idProvincia);
	public ArrayList<Alumno> buscarXNacionalidad(String idPais);
	public ArrayList<Alumno> buscarDadosDeBaja();
}
