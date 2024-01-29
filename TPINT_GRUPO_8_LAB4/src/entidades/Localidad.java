package entidades;

public class Localidad {

	Pais pais=new Pais();
	Provincia provincia=new Provincia();
	private String idLocalidad;
	private String descripcionLocalidad;
	
	public Localidad() {}
	
	public Localidad(Pais pais, Provincia porvincia, String idLocalidad, String descripcionLocalidad) {
		super();
		this.pais = pais;
		this.provincia = porvincia;
		this.idLocalidad = idLocalidad;
		this.descripcionLocalidad = descripcionLocalidad;
	}
	
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setPorvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public String getDescripcionLocalidad() {
		return descripcionLocalidad;
	}
	public void setDescripcionLocalidad(String descripcionLocalidad) {
		this.descripcionLocalidad = descripcionLocalidad;
	}

	@Override
	public String toString() {
		return "Localidad [pais=" + pais + ", porvincia=" + provincia + ", idLocalidad=" + idLocalidad
				+ ", descripcionLocalidad=" + descripcionLocalidad + "]";
	}
	
	
	
}
