package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Apuesta {
	@Id
	private int codigoApuesta;
	private User usuario;
	private float dineroApostado;
	private float beneficios;
	private Question apuesta;
	private boolean acertada;
	private boolean cobrada;

	public Apuesta(int codigoApuesta, User usuario, float dineroApostado, float beneficios, Question apuesta) {
		super();
		this.codigoApuesta=codigoApuesta;
		this.usuario=usuario;
		this.dineroApostado=dineroApostado;
		this.beneficios=beneficios;
		this.apuesta=apuesta;
		this.acertada=false;
		this.cobrada=false;
	}
	
	
	public boolean getAcertada() {
		return acertada;
	}


	public void setAcertada(boolean acertada) {
		this.acertada = acertada;
	}


	public boolean getCobrada() {
		return cobrada;
	}


	public void setCobrada(boolean cobrada) {
		this.cobrada = cobrada;
	}


	public int getcodigoApuesta() {
		return codigoApuesta;
	}
	
	public User getUsusario() {
		return usuario;
	}
	
	public float getDineroApostado() {
		return dineroApostado;
	}
	
	public float getBeneficios() {
		return beneficios;
	}
	
	public Question getApuesta() {
		return apuesta;
	}
	
	public void setcodigoApuesta(int codigoApuesta) {
		this.codigoApuesta=codigoApuesta;
	}
	
	public void setUsuario(User usuario) {
		this.usuario=usuario;
	}
	
	public void setDineroApostado(int dineroApostado) {
		this.dineroApostado=dineroApostado;
	}
	
	public void setBeneficios(int beneficios) {
		this.beneficios=beneficios;
	}
	
	public void setApuesta(Question apuesta) {
		this.apuesta=apuesta;
	}
}
