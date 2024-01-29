package negocio;

import java.util.ArrayList;

import entidades.EstadoAcademico;
import entidades.Notas;

public interface EstadoAcademicoNeg {

	public EstadoAcademico calcularEstadoAcademico(Notas not);
	public EstadoAcademico obtenerEstadoAcademico(String idEstadoAcademico);
	public ArrayList<EstadoAcademico> obtenerTodosLosEstadosAcademicos();
}
