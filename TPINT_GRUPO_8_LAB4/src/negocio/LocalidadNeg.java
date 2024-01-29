package negocio;

import java.util.ArrayList;

import entidades.Localidad;

public interface LocalidadNeg {
	public ArrayList<Localidad> leerLocalidades(String idProvincia);
	public ArrayList<Localidad> leerTodasLocalidades();
}