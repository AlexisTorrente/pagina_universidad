package dao;

import java.util.ArrayList;

import entidades.Provincia;

public interface ProvinciaDao {

	public Provincia leerProvincia(String idProvincia);
	public ArrayList<Provincia> leerProvincias();
	public ArrayList<Provincia> leerProvinciasId(String idProvincia);
}
