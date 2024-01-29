package negocioImpl;

import java.util.ArrayList;

import dao.CarreraDao;
import daoImpl.CarreraDaoImpl;
import entidades.Carrera;
import negocio.CarreraNeg;

public class CarreraNegImpl implements  CarreraNeg {
CarreraDao cdao=new CarreraDaoImpl();
	public ArrayList<Carrera> leerCarrera(){
		return cdao.leerCarrera();
	}
}
