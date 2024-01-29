package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.EstadoAcademicoDao;
import dao.NotasDao;
import entidades.Alumno;
import entidades.Carrera;
import entidades.Curso;
import entidades.Docente;
import entidades.EstadoAcademico;
import entidades.Materia;
import entidades.Notas;

public class NotasDaoImpl implements NotasDao{

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "Lab4_TPfinal";
	
	
	EstadoAcademicoDao eadao= new EstadoAcademicoDaoImpl();
	
	public boolean actualizarNotas(Notas not) {
		
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean actualizo=false;
		int filas=0;
		Connection conn = null;
		Statement statement;
		
			try{
				
				conn=DriverManager.getConnection(host + dbName, user, pass);
				statement = conn.createStatement();
				
				EstadoAcademico ea= not.getEstadoAcademico();
				Alumno a= not.getAlumno();
				Curso c= not.getCurso();
				Materia m= not.getMateria();
				
				String query="update notas set notaPrimerParcial="+not.getNotaPrimerParcial()+", notaSegundoParcial="+not.getNotaSegundoParcial()+
						", notaPrimerRecuperatorio="+not.getNotaPrimerRecuperatorio()+", notaSegundoRecuperatorio="+not.getNotaSegundoRecuperatorio()+
						", idEstadoAcademico="+ea.getIdEstadoAcademico()+" where legajoAlumno= "+ a.getLegajoAlumno()+" and idCurso= "+ c.getIdCurso()+
						" and idMateria= "+m.getIdMateria();
		
				filas=statement.executeUpdate(query);
				
				conn.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
			
			}
			if (filas==1) {
				actualizo=true;
			}
			
			return actualizo;
		
	}

	@Override
	public boolean agregarAlumnoN(Notas n) {
		boolean agrego=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	
		int filas=0;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			String query = "Insert into notas (legajoAlumno, idCurso, idMateria, idEstadoAcademico)"
					+ "values ('"+n.getAlumno().getLegajoAlumno()+"','"
					+n.getCurso().getIdCurso()+"','"+n.getMateria().getIdMateria()+"', '"+n.getEstadoAcademico().getIdEstadoAcademico()+"')";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if (filas==1) {
			agrego=true;
		}
		return agrego;
	}

}
