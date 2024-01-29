package negocioImpl;

import java.util.ArrayList;

import dao.ProvinciaDao;
import daoImpl.ProvinciaDaoImpl;
import entidades.Provincia;
import negocio.ProvinciaNeg;

public class ProvinciaNegImpl implements ProvinciaNeg {

	@Override
	public ArrayList<Provincia> leerProvincias() {
		ProvinciaDaoImpl provinciaDao = new ProvinciaDaoImpl();
		return provinciaDao.leerProvincias();
	}

	@Override
	public Provincia leerProvincia(String idProvincia) {
		ProvinciaDaoImpl provinciaDao = new ProvinciaDaoImpl();
		return provinciaDao.leerProvincia(idProvincia);
	}

	@Override
	public ArrayList<Provincia> leerProvinciasId(String idProvincia) {
		ProvinciaDaoImpl provinciaDao = new ProvinciaDaoImpl();
		return provinciaDao.leerProvinciasId(idProvincia);
	}

}