package entidades;

public class Notas {
	
	//ATRIBUTOS
	Alumno alumno= new Alumno();
	Curso curso= new Curso();
	Materia materia= new Materia();
	private float notaPrimerParcial;
	private float notaSegundoParcial;
	private float notaPrimerRecuperatorio;
	private float notaSegundoRecuperatorio;
	EstadoAcademico estadoAcademico= new EstadoAcademico();
	
	//CONSTRUCTORES
	
	public Notas() {}

	public Notas(Alumno alumno, Curso curso, Materia materia, float notaPrimerParcial, float notaSegundoParcial,
			float notaPrimerRecuperatorio, float notaSegundoRecuperatorio, EstadoAcademico estadoAcademico) {
		super();
		this.alumno = alumno;
		this.curso = curso;
		this.materia = materia;
		this.notaPrimerParcial = notaPrimerParcial;
		this.notaSegundoParcial = notaSegundoParcial;
		this.notaPrimerRecuperatorio = notaPrimerRecuperatorio;
		this.notaSegundoRecuperatorio = notaSegundoRecuperatorio;
		this.estadoAcademico = estadoAcademico;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public float getNotaPrimerParcial() {
		return notaPrimerParcial;
	}

	public void setNotaPrimerParcial(float notaPrimerParcial) {
		this.notaPrimerParcial = notaPrimerParcial;
	}

	public float getNotaSegundoParcial() {
		return notaSegundoParcial;
	}

	public void setNotaSegundoParcial(float notaSegundoParcial) {
		this.notaSegundoParcial = notaSegundoParcial;
	}

	public float getNotaPrimerRecuperatorio() {
		return notaPrimerRecuperatorio;
	}

	public void setNotaPrimerRecuperatorio(float notaPrimerRecuperatorio) {
		this.notaPrimerRecuperatorio = notaPrimerRecuperatorio;
	}

	public float getNotaSegundoRecuperatorio() {
		return notaSegundoRecuperatorio;
	}

	public void setNotaSegundoRecuperatorio(float notaSegundoRecuperatorio) {
		this.notaSegundoRecuperatorio = notaSegundoRecuperatorio;
	}

	public EstadoAcademico getEstadoAcademico() {
		return estadoAcademico;
	}

	public void setEstadoAcademico(EstadoAcademico estadoAcademico) {
		this.estadoAcademico = estadoAcademico;
	}

	@Override
	public String toString() {
		return "Notas [alumno=" + alumno + ", curso=" + curso + ", materia=" + materia + ", notaPrimerParcial="
				+ notaPrimerParcial + ", notaSegundoParcial=" + notaSegundoParcial + ", notaPrimerRecuperatorio="
				+ notaPrimerRecuperatorio + ", notaSegundoRecuperatorio=" + notaSegundoRecuperatorio
				+ ", estadoAcademico=" + estadoAcademico + "]";
	}
	
	

}
