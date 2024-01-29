package entidades;

public class Pais {
	
	private String idPais;
	private String nombrePais;
	
	public Pais() {}
	
	public Pais(String idPais, String nombrePais) {
		super();
		this.idPais = idPais;
		this.nombrePais = nombrePais;
	}
	
	public String getIdPais() {
		return idPais;
	}
	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}
	public String getDescripcionPais() {
		return nombrePais;
	}
	public void setDescripcionPais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	@Override
	public String toString() {
		return "Pais [idPais=" + idPais + ", descripcionPais=" + nombrePais + "]";
	}
	
}
