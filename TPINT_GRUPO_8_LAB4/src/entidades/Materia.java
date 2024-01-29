package entidades;

public class Materia {
	
	private String idMateria;
	private String descripcionMateria;
	
	public Materia() {}
	
	public Materia(String idMateria, String descripcionMateria) {
		super();
		this.idMateria = idMateria;
		this.descripcionMateria = descripcionMateria;
	}
	
	public String getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(String idMateria) {
		this.idMateria = idMateria;
	}
	public String getDescripcion() {
		return descripcionMateria;
	}
	public void setDescripcion(String descripcionMateria) {
		this.descripcionMateria = descripcionMateria;
	}
	
	@Override
	public String toString() {
		return "Carrera [idMateria=" + idMateria + ", descripcion=" + descripcionMateria + "]";
	}
	
	
}