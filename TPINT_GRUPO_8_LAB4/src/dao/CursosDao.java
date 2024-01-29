package dao;

import java.util.ArrayList;

import entidades.Alumno;
import entidades.Curso;
import entidades.Docente;
import entidades.EstadoAcademico;
import entidades.Notas;

public interface CursosDao {
	
	public Curso leerCurso(String idCurso);
	public ArrayList<Curso> leerCursosDocente(Docente d);
	public ArrayList<Curso> leerCursosDocenteAnio(Docente d, String anio);
	public ArrayList<Notas> leerAlumnosCurso(Curso cur);
	public boolean agregarCurso(Curso c);
	public int proximoId();
	public ArrayList<Notas> leerAlumnoLegajo(Curso cur, Alumno alu );
	public ArrayList<Notas> leerAlumnosEstadoAcademico (Curso cur, EstadoAcademico ea);
	
}
