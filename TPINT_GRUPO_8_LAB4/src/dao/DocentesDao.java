package dao;

import java.util.ArrayList;

import entidades.Docente;

public interface DocentesDao {

	public Docente leerDocente(String legajoDocente);
	public Docente leerDocenteCorreo(String correoDocente);
	boolean existeDniDocente(String dni);
	boolean agregarDocente(Docente docente);
	String proximoLegajoDocente();
	public ArrayList<Docente> leerDocentes();
	public boolean bajaDocente(String legajo);
	public boolean actualizarDocente(Docente docente);
	public ArrayList<Docente> leerDocentesProvincia(String idProvincia);
	public ArrayList<Docente> leerDocentesLocalidad(String idLocalidad);
	public ArrayList<Docente> leerDocentesDni(String dni);
	public ArrayList<Docente> leerDocentesNombre(String nombre);
	public ArrayList<Docente> leerDocentesLegajo(String legajo);
}
