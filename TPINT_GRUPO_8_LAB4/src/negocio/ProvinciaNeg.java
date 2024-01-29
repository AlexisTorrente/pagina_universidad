package negocio;

import java.util.ArrayList;

import entidades.Provincia;

public interface ProvinciaNeg {
	public ArrayList<Provincia> leerProvincias();
	public Provincia leerProvincia(String idProvincia);
	public ArrayList<Provincia> leerProvinciasId(String idProvincia);
}