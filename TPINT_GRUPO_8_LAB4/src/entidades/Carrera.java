package entidades;

public class Carrera {
	
	private String idCarrera;
	private String descripcionCarrera;
	
	public Carrera() {}
	
	public Carrera(String idCarrera, String descripcionCarrera) {
		super();
		this.idCarrera = idCarrera;
		this.descripcionCarrera = descripcionCarrera;
	}
	public String getIdCarrera() {
		return idCarrera;
	}
	public void setIdCarrera(String idCarrera) {
		this.idCarrera = idCarrera;
	}
	public String getDescripcionCarrera() {
		return descripcionCarrera;
	}
	public void setDescripcionCarrera(String descripcionCarrera) {
		this.descripcionCarrera = descripcionCarrera;
	}

	@Override
	public String toString() {
		return "Carrera [idCarrera=" + idCarrera + ", descripcionCarrera=" + descripcionCarrera + "]";
	}

	
	
	
	
}

