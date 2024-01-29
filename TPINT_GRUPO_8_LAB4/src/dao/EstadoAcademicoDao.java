package dao;

import java.util.ArrayList;

import entidades.EstadoAcademico;

public interface EstadoAcademicoDao {

	public EstadoAcademico leerEstadoAcademico(String idEstadoAcademico);
	public ArrayList<EstadoAcademico> leerTodosLosEstadosAcademicos();
}
