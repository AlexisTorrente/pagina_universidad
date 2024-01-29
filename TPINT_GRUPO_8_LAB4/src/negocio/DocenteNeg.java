package negocio;

import java.util.ArrayList;

import entidades.Docente;
import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;

public interface DocenteNeg {
	
	boolean AgregarDocente(String legajo,String dni, String nombreCompleto, String correo,String fechaNacimiento, 
			Pais pais,Provincia prov,  Localidad loc, String direccion, String telefono);
	String SolicitarProximoLegajoDocente();
	boolean verificarDni(String dni);
	public Docente obtenerDocente(String legajoDocente);
	public Docente obtenerDocenteCorreo(String correoDocente);
	public ArrayList<Docente> obtenerDocentes();
	public boolean llamarBajaDocente(String legajo);
	public boolean llamarActualizarDocente(Docente docente);
	public ArrayList<Docente> obtenerDocentesProvincia(String idProvincia);
	public ArrayList<Docente> obtenerDocentesLocalidad(String idLocalidad);
	public ArrayList<Docente> obtenerDocentesDni(String dni);
	public ArrayList<Docente> obtenerDocentesNombre(String nombre);
	public ArrayList<Docente> obtenerDocentesLegajo(String legajo);
}
