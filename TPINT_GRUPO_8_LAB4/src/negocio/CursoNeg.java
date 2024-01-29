package negocio;

import java.util.ArrayList;

import entidades.Alumno;
import entidades.Carrera;
import entidades.Curso;
import entidades.Docente;
import entidades.EstadoAcademico;
import entidades.Materia;
import entidades.Notas;

public interface CursoNeg {
	public boolean agregarCurso(String id, Materia mat, Carrera car, Docente doc, String semestre, String anio) ;
	public int proximoId();
	public Curso obtenerCurso(String idCurso);
	public ArrayList<Curso> obtenerCursosDocente(Docente d);
	public ArrayList<Curso> obtenerCursosDocenteAnio(Docente d, String anio);
	public ArrayList<Notas> obtenerAlumnosCurso(Curso cur);
	public ArrayList<Notas> obtenerAlumnoLegajo(Curso cur, Alumno alu);
	public ArrayList<Notas> obtenerAlumnosEstadoAcademico(Curso cur, EstadoAcademico ea);

}
