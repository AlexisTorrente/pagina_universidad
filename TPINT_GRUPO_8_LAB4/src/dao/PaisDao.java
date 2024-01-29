package dao;

import java.util.ArrayList;

import entidades.Pais;

public interface PaisDao {
	
	public Pais leerPais(String idPais);
	public ArrayList<Pais> leerPaises();
}
