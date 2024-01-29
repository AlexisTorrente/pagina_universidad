package dao;

import java.util.ArrayList;

import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;

public interface LocalidadDao {
	
	public Localidad leerLocalidad(String idLocalidad);
	public ArrayList<Localidad> leerLocalidades(String idProvincia);
	public ArrayList<Localidad> leerTodasLasLocalidades();
}
