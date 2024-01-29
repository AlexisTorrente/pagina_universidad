package entidades;

public class EstadoAcademico {

	private String idEstadoAcademico;
	private String descripcionEstadoAcademico;
	
	public EstadoAcademico() {}
	
	public EstadoAcademico(String idEstadoAcademico, String descripcionEstadoAcademico) {
		super();
		this.idEstadoAcademico = idEstadoAcademico;
		this.descripcionEstadoAcademico = descripcionEstadoAcademico;
	}
	
	public String getIdEstadoAcademico() {
		return idEstadoAcademico;
	}
	public void setIdEstadoAcademico(String idEstadoAcademico) {
		this.idEstadoAcademico = idEstadoAcademico;
	}
	public String getDescripcionEstadoAcademico() {
		return descripcionEstadoAcademico;
	}
	public void setDescripcionEstadoAcademico(String descripcionEstadoAcademico) {
		this.descripcionEstadoAcademico = descripcionEstadoAcademico;
	}

	@Override
	public String toString() {
		return "EstadoAcademico [idEstadoAcademico=" + idEstadoAcademico + ", descripcionEstadoAcademico="
				+ descripcionEstadoAcademico + "]";
	}
	
	
}
