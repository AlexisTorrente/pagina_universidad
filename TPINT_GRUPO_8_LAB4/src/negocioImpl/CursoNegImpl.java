package negocioImpl;

import java.util.ArrayList;

import daoImpl.CursosDaoImpl;
import entidades.Alumno;
import entidades.Carrera;
import entidades.Curso;
import entidades.Docente;
import entidades.EstadoAcademico;
import entidades.Materia;
import entidades.Notas;
import negocio.CursoNeg;

public class CursoNegImpl implements CursoNeg {

	CursosDaoImpl cdao=new CursosDaoImpl();
	
	public int proximoId() {
		
		return cdao.proximoId();
	}

	public boolean agregarCurso(String id, Materia mat, Carrera car, Docente doc, String semestre, String anio) {
		Curso c=new Curso();
		c.setIdCurso(id);
		c.setMateria(mat);
		c.setCarrera(car);
		c.setDocente(doc);
		c.setSemestreCurso(semestre);
		c.setAnioCurso(anio);
		return cdao.agregarCurso(c);
	}

	@Override
	public Curso obtenerCurso(String idCurso) {
		
		return cdao.leerCurso(idCurso);
	}

	@Override
	public ArrayList<Curso> obtenerCursosDocente(Docente d) {
		
		return cdao.leerCursosDocente(d);
	}

	@Override
	public ArrayList<Notas> obtenerAlumnosCurso(Curso cur) {
		
		return cdao.leerAlumnosCurso(cur);
	}

	
	public ArrayList<Notas> obtenerAlumnoLegajo(Curso cur, Alumno alu) {
		
		return cdao.leerAlumnoLegajo(cur, alu);
	}

	@Override
	public ArrayList<Notas> obtenerAlumnosEstadoAcademico(Curso cur, EstadoAcademico ea) {
		
		return cdao.leerAlumnosEstadoAcademico(cur, ea);
	}

	@Override
	public ArrayList<Curso> obtenerCursosDocenteAnio(Docente d, String anio) {
		
		return cdao.leerCursosDocenteAnio(d, anio);
	}

	public boolean VerificarLegajo(Docente doc) {
		return cdao.VerificarLegajo(doc);
	}

	public boolean Verfaño(String anio) {
		return cdao.Verfaño(anio);
	}
	
}
