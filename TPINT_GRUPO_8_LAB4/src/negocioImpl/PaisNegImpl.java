package negocioImpl;

import java.util.ArrayList;

import daoImpl.PaisDaoImpl;
import entidades.Pais;
import negocio.PaisNeg;

public class PaisNegImpl implements PaisNeg{

	@Override
	public ArrayList<Pais> leerPaises() {
		PaisDaoImpl paisDao = new PaisDaoImpl();
		return paisDao.leerPaises();
	}

}