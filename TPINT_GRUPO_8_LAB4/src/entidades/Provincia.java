package entidades;

public class Provincia {

	Pais pais=new Pais();
	private String idProvincia;
	private String DescripcionProvincia;
	
	public Provincia() {}
	
	public Provincia(Pais pais, String idProvincia, String descripcionProvincia) {
		super();
		this.pais = pais;
		this.idProvincia = idProvincia;
		DescripcionProvincia = descripcionProvincia;
	}
	
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getDescripcionProvincia() {
		return DescripcionProvincia;
	}
	public void setDescripcionProvincia(String descripcionProvincia) {
		DescripcionProvincia = descripcionProvincia;
	}

	@Override
	public String toString() {
		return "Provincia [pais=" + pais + ", idProvincia=" + idProvincia + ", DescripcionProvincia="
				+ DescripcionProvincia + "]";
	}
	
	
}
