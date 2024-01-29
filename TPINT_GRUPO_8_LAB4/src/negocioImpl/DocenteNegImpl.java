package negocioImpl;


import java.util.ArrayList;

import dao.DocentesDao;
import dao.UsuarioDao;
import daoImpl.DocentesDaoImpl;
import daoImpl.UsuarioDaoImpl;
import entidades.Docente;
import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;
import negocio.DocenteNeg;

public class DocenteNegImpl implements DocenteNeg {

	DocentesDao ddao = new DocentesDaoImpl();
	UsuarioDao udao = new UsuarioDaoImpl();
	
	public boolean AgregarDocente(String legajo, String dni, String nombreCompleto, String correo, String fechaNacimiento,  Pais pais, Provincia prov, Localidad loc, String direccion, String telefono ) {
		
		Docente doc = new Docente();
		
		doc.setLegajoDocente(legajo);
		doc.setDniDocente(dni);
		doc.setNombreYApellidoDocente(nombreCompleto);
		doc.setCorreoDocente(correo);
		doc.setFechaNacimientoDocente(fechaNacimiento);
		doc.setPais(pais);
		doc.setProvincia(prov);
		doc.setLocalidad(loc);
		doc.setDireccionDocente(direccion);
		doc.setTelefonoDocente(telefono);
		doc.setEstadoDocente(true);

		return ddao.agregarDocente(doc);
	}

	@Override
	public String SolicitarProximoLegajoDocente() {
		
		return ddao.proximoLegajoDocente();
	}

	@Override
	public boolean verificarDni(String dni) {
		return !ddao.existeDniDocente(dni);
	}

	@Override
	public Docente obtenerDocente(String legajoDocente) {
		
		return ddao.leerDocente(legajoDocente);
	}

	@Override
	public ArrayList<Docente> obtenerDocentes() {
		
		return ddao.leerDocentes();
	}

	@Override
	public boolean llamarBajaDocente(String legajo) {
		return ddao.bajaDocente(legajo);
	}

	@Override
	public boolean llamarActualizarDocente(Docente docente) {
		return ddao.actualizarDocente(docente);
	}

	@Override
	public ArrayList<Docente> obtenerDocentesProvincia(String idProvincia) {
		return ddao.leerDocentesProvincia(idProvincia);
	}

	@Override
	public ArrayList<Docente> obtenerDocentesLocalidad(String idLocalidad) {
		return ddao.leerDocentesLocalidad(idLocalidad);
	}

	@Override
	public ArrayList<Docente> obtenerDocentesDni(String dni) {
		return ddao.leerDocentesDni(dni);
	}

	@Override
	public ArrayList<Docente> obtenerDocentesNombre(String nombre) {
		return ddao.leerDocentesNombre(nombre);
	}

	@Override
	public ArrayList<Docente> obtenerDocentesLegajo(String legajo) {
		return ddao.leerDocentesLegajo(legajo);
	}

	@Override
	public Docente obtenerDocenteCorreo(String correoDocente) {
		return ddao.leerDocenteCorreo(correoDocente);
	}

	
	


}
