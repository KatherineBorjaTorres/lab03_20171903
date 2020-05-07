
package pe.edu.pucp.congresoft.model;

import java.sql.Date;
public class Congreso{

	private int idCongreso;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private String pais;
        private boolean activo;
	
	public Congreso(){
	}
	
	public Congreso( String nombre, Date fechaInicio, Date fechaFin, String pais, boolean activo){
                this.nombre=nombre;
                this.fechaInicio= fechaInicio;
                this.fechaFin=fechaFin;
                this.pais=pais;
                this.activo=activo;
	}
	
	public int getIdCongreso(){
		return idCongreso;
	}
	public void setIdCongreso(int idCongreso){
		this.idCongreso = idCongreso;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getNombre(){
		return nombre;
	}
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaInicio(){
		return fechaInicio;
	}
	public void setFechaFin(Date fechaFin){
		this.fechaFin = fechaFin;
	}
	public Date getFechaFin(){
		return fechaFin;
	}
	public String getPais(){
		return pais;
	}
	public void setPais(String pais){
		this.pais = pais;
	}
        
        
        public boolean isActivo() {
            return activo;
        }

        
        public void setActivo(boolean activo) {
            this.activo = activo;
        }
        	
        public String mostrarDatos(){
            return idCongreso + " - " + nombre + " - " + fechaInicio + " - " + fechaFin + " - " + pais ;
        }
	
}
