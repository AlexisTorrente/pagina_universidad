package entidades;

public class Docente {
		//Atributos
		//private int id ;
		private String legajoDocente;
		private String correoDocente;
		private String dniDocente;
		private String nombreYApellidoDocente;
		private String fechaNacimientoDocente;
		Pais pais= new Pais();
		Provincia provincia= new Provincia();
		Localidad localidad= new Localidad();
		private String direccionDocente;
		private String telefonoDocente;
		private boolean estadoDocente;

		//Constructores
		public Docente() {
			
		}
		
		
		public Docente(String legajoDocente, String correoDocente, String dniDocente, String nombreYApellidoDocente,
				String fechaNacimientoDocente, Pais pais, Provincia provincia, Localidad localidad,
				String direccionDocente, String telefonoDocente, boolean estadoDocente) {
			super();
			this.legajoDocente = legajoDocente;
			this.correoDocente = correoDocente;
			this.dniDocente = dniDocente;
			this.nombreYApellidoDocente = nombreYApellidoDocente;
			this.fechaNacimientoDocente = fechaNacimientoDocente;
			this.pais = pais;
			this.provincia = provincia;
			this.localidad = localidad;
			this.direccionDocente = direccionDocente;
			this.telefonoDocente = telefonoDocente;
			this.estadoDocente = estadoDocente;
		}


		public String getLegajoDocente() {
			return legajoDocente;
		}

		public void setLegajoDocente(String legajoDocente) {
			this.legajoDocente = legajoDocente;
		}

		public String getCorreoDocente() {
			return correoDocente;
		}

		public void setCorreoDocente(String correoDocente) {
			this.correoDocente = correoDocente;
		}

		public String getDniDocente() {
			return dniDocente;
		}

		public void setDniDocente(String dniDocente) {
			this.dniDocente = dniDocente;
		}

		public String getNombreYApellidoDocente() {
			return nombreYApellidoDocente;
		}

		public void setNombreYApellidoDocente(String nombreYApellidoDocente) {
			this.nombreYApellidoDocente = nombreYApellidoDocente;
		}

		public String getFechaNacimientoDocente() {
			return fechaNacimientoDocente;
		}

		public void setFechaNacimientoDocente(String fechaNacimientoDocente) {
			this.fechaNacimientoDocente = fechaNacimientoDocente;
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

		public String getDireccionDocente() {
			return direccionDocente;
		}

		public void setDireccionDocente(String direccionDocente) {
			this.direccionDocente = direccionDocente;
		}

		public String getTelefonoDocente() {
			return telefonoDocente;
		}

		public void setTelefonoDocente(String telefonoDocente) {
			this.telefonoDocente = telefonoDocente;
		}

		public boolean isEstadoDocente() {
			return estadoDocente;
		}

		public void setEstadoDocente(boolean estadoDocente) {
			this.estadoDocente = estadoDocente;
		}


		@Override
		public String toString() {
			return "Docente [legajoDocente=" + legajoDocente + ", correoDocente=" + correoDocente + ", dniDocente="
					+ dniDocente + ", nombreYApellidoDocente=" + nombreYApellidoDocente + ", fechaNacimientoDocente="
					+ fechaNacimientoDocente + ", pais=" + pais + ", provincia=" + provincia + ", localidad="
					+ localidad + ", direccionDocente=" + direccionDocente + ", telefonoDocente=" + telefonoDocente
					+ ", estadoDocente=" + estadoDocente + "]";
		}
		
		
				
}
