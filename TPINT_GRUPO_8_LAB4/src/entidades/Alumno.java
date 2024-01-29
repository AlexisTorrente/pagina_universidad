package entidades;

import java.sql.Date;

public class Alumno {
	
	//Atributos
	private String legajoAlumno;
	private String dniAlumno;
	private String nombreYApellidoAlumno;
	private String fechaNacimientoAlumno;
	Pais pais=new Pais();
	Provincia provincia=new Provincia();
	Localidad localidad= new Localidad();
	private String direccionAlumno;
	private String telefonoAlumno;
	private boolean estadoAlumno;
	
	
	public Alumno() {}
	
	public Alumno(String legajoAlumno, String dniAlumno, String nombreYApellidoAlumno, String fechaNacimientoAlumno,
			Pais pais, Provincia provincia, Localidad localidad, String direccionAlumno, String telefonoAlumno,
			boolean estadoAlumno) {
		super();
		this.legajoAlumno = legajoAlumno;
		this.dniAlumno = dniAlumno;
		this.nombreYApellidoAlumno = nombreYApellidoAlumno;
		this.fechaNacimientoAlumno = fechaNacimientoAlumno;
		this.pais = pais;
		this.provincia = provincia;
		this.localidad = localidad;
		this.direccionAlumno = direccionAlumno;
		this.telefonoAlumno = telefonoAlumno;
		this.estadoAlumno = estadoAlumno;
	}
	public String getLegajoAlumno() {
		return legajoAlumno;
	}
	public void setLegajoAlumno(String legajoAlumno) {
		this.legajoAlumno = legajoAlumno;
	}
	public String getDniAlumno() {
		return dniAlumno;
	}
	public void setDniAlumno(String dniAlumno) {
		this.dniAlumno = dniAlumno;
	}
	public String getNombreYApellidoAlumno() {
		return nombreYApellidoAlumno;
	}
	public void setNombreYApellidoAlumno(String nombreYApellidoAlumno) {
		this.nombreYApellidoAlumno = nombreYApellidoAlumno;
	}
	public String getFechaNacimientoAlumno() {
		return fechaNacimientoAlumno;
	}
	public void setFechaNacimientoAlumno(String fechaNacimientoAlumno) {
		this.fechaNacimientoAlumno = fechaNacimientoAlumno;
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
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public String getDireccionAlumno() {
		return direccionAlumno;
	}
	public void setDireccionAlumno(String direccionAlumno) {
		this.direccionAlumno = direccionAlumno;
	}
	public String getTelefonoAlumno() {
		return telefonoAlumno;
	}
	public void setTelefonoAlumno(String telefonoAlumno) {
		this.telefonoAlumno = telefonoAlumno;
	}
	public boolean isEstadoAlumno() {
		return estadoAlumno;
	}
	public void setEstadoAlumno(boolean estadoAlumno) {
		this.estadoAlumno = estadoAlumno;
	}

	@Override
	public String toString() {
		return "Alumno [legajoAlumno=" + legajoAlumno + ", dniAlumno=" + dniAlumno + ", nombreYApellidoAlumno="
				+ nombreYApellidoAlumno + ", fechaNacimientoAlumno=" + fechaNacimientoAlumno + ", pais=" + pais
				+ ", provincia=" + provincia + ", localidad=" + localidad + ", direccionAlumno=" + direccionAlumno
				+ ", telefonoAlumno=" + telefonoAlumno + ", estadoAlumno=" + estadoAlumno + "]";
	}
	
	
	
}


