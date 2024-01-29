package negocioImpl;

import java.util.ArrayList;

import dao.CarreraDao;
import dao.MateriaDao;
import daoImpl.CarreraDaoImpl;
import daoImpl.MateriaDaoImpl;
import entidades.Materia;
import negocio.MateriaNeg;

public class MateriaNegImpl implements MateriaNeg{
	MateriaDao mdao=new MateriaDaoImpl();
	@Override
	public ArrayList<Materia> leerMaterias() {
		
		return mdao.leerMaterias();
	}

}
