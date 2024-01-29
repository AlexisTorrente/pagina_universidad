package dao;

import java.util.ArrayList;

import entidades.Carrera;
import entidades.Materia;

public interface MateriaDao {
	
	public Materia leerMateria(String idMateria);
	public ArrayList<Materia> leerMaterias();
}
