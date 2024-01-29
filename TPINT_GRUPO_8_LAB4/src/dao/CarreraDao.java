package dao;

import java.util.ArrayList;

import entidades.Carrera;
import entidades.Pais;

public interface CarreraDao {

	public Carrera leerCarrera(String idCarrera);
	public ArrayList<Carrera> leerCarrera();
}
