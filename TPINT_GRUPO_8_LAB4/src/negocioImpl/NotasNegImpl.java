package negocioImpl;

import dao.EstadoAcademicoDao;
import dao.NotasDao;
import daoImpl.EstadoAcademicoDaoImpl;
import daoImpl.NotasDaoImpl;
import entidades.Alumno;
import entidades.Curso;
import entidades.Materia;
import entidades.Notas;
import negocio.NotasNeg;

public class NotasNegImpl implements NotasNeg {

	NotasDao ndao = new NotasDaoImpl();
	EstadoAcademicoDao eadao= new EstadoAcademicoDaoImpl();
	@Override
	public boolean calificarAlumno(Notas not) {
		
		return ndao.actualizarNotas(not);
	}
	public boolean agregarAlumnoN(Curso cur, Materia mat, Alumno alum1) {
		Notas n=new Notas();
		n.setCurso(cur);
		n.setMateria(mat);
		n.setAlumno(alum1);
		n.setEstadoAcademico(eadao.leerEstadoAcademico("0"));
		return ndao.agregarAlumnoN(n);
	}

}
