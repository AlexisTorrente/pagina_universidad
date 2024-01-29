package entidades;

public class Usuario {

	//Atributos
	private int idUsuario;
	private String correoUsuario;
	private String claveUsuario;
	private boolean esAdmin;
	
	//Constructores
	public Usuario() {
		
	}


	public Usuario(int idUsuario,String correoUsuario, String claveUsuario, boolean esAdmin) {
		this.idUsuario = idUsuario;
		this.correoUsuario = correoUsuario;
		this.claveUsuario = claveUsuario;
		this.esAdmin = esAdmin;
	}

	//Getters y setters
	

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getCorreoUsuario() {
		return correoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	public String getClaveUsuario() {
		return claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	//toString()

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", correoUsuario=" + correoUsuario + ", claveUsuario=" + claveUsuario
				+ ", esAdmin=" + esAdmin + "]";
	}

	
	
}
