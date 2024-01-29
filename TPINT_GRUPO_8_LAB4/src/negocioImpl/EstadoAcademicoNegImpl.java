package negocioImpl;

import java.util.ArrayList;

import dao.EstadoAcademicoDao;
import daoImpl.EstadoAcademicoDaoImpl;
import entidades.EstadoAcademico;
import entidades.Notas;
import negocio.EstadoAcademicoNeg;

public class EstadoAcademicoNegImpl implements EstadoAcademicoNeg {

	EstadoAcademicoDao eadao= new EstadoAcademicoDaoImpl();
	
	public EstadoAcademico calcularEstadoAcademico(Notas not) {
		
		EstadoAcademico ea= new EstadoAcademico();
		
		float primerParcial= not.getNotaPrimerParcial();
		float segundoParcial= not.getNotaSegundoParcial();
		float primerRecuperatorio = not.getNotaPrimerRecuperatorio();
		float segundoRecuperatorio = not.getNotaSegundoRecuperatorio();
		String estado="1";
	
			
		
		if(primerParcial <= 6) {
			
			if(primerRecuperatorio <= 6) {
				
				estado= "2";
			}
		}
		
		
		if(segundoParcial <= 6) {
			
			if(segundoRecuperatorio <= 6) {
				
				estado= "2";
			}
		}
		
		if(primerParcial == 0 && segundoParcial == 0 && primerRecuperatorio==0 && segundoRecuperatorio==0) {
			
			estado="0";
		}
		
		ea= eadao.leerEstadoAcademico(estado);

		return ea;
	}

	@Override
	public EstadoAcademico obtenerEstadoAcademico(String idEstadoAcademico) {
		
		return eadao.leerEstadoAcademico(idEstadoAcademico);
	}

	@Override
	public ArrayList<EstadoAcademico> obtenerTodosLosEstadosAcademicos() {
		
		return eadao.leerTodosLosEstadosAcademicos();
	}

}
