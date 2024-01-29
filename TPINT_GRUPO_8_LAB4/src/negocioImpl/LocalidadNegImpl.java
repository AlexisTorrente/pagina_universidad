package negocioImpl;

import java.util.ArrayList;

import daoImpl.LocalidadDaoImpl;
import entidades.Localidad;
import negocio.LocalidadNeg;

public class LocalidadNegImpl implements LocalidadNeg {

	@Override
	public ArrayList<Localidad> leerLocalidades(String idProvincia) {
		LocalidadDaoImpl localidadDao = new LocalidadDaoImpl();
		return localidadDao.leerLocalidades(idProvincia);
	}

	@Override
	public ArrayList<Localidad> leerTodasLocalidades() {
		LocalidadDaoImpl localidadDao = new LocalidadDaoImpl();
		return localidadDao.leerTodasLasLocalidades();
	}

}
