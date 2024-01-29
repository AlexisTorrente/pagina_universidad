package negocio;

import entidades.Alumno;
import entidades.Curso;
import entidades.Materia;
import entidades.Notas;

public interface NotasNeg {

	public boolean calificarAlumno(Notas not);
	public boolean agregarAlumnoN(Curso cur, Materia mat, Alumno alum1);
}
