package entidades;

public class Curso {
	
	//Atributos
	private String idCurso;
	Materia materia=new Materia();
	Carrera carrera= new Carrera();
	Docente docente= new Docente();
	private String semestreCurso;
	private String anioCurso;
	
	//Constructores
	public Curso() {}
	
	public Curso(String idCurso, Materia materia, Carrera carrera, Docente docente, String semestreCurso, String anioCurso) {
		super();
		this.idCurso = idCurso;
		this.materia = materia;
		this.carrera = carrera;
		this.docente = docente;
		this.semestreCurso = semestreCurso;
		this.anioCurso = anioCurso;
	}

	
	//Getters y setters
	public String getSemestreCurso() {
		return semestreCurso;
	}

	public String getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public void setSemestreCurso(String semestreCurso) {
		this.semestreCurso = semestreCurso;
	}

	public String getAnioCurso() {
		return anioCurso;
	}

	public void setAnioCurso(String anioCurso) {
		this.anioCurso = anioCurso;
	}

	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", materia=" + materia.getDescripcion() + ", carrera=" + carrera.getDescripcionCarrera() +
				", docente=" + docente.getLegajoDocente()+ ", semestreCurso=" + semestreCurso + ", anioCurso=" + anioCurso + "]";
	}

	//toString()
	
}
