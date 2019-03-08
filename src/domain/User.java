package domain;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User {
	@Id 
	private String DNI;
	private String usuario;
	private String contraseña;
	private float saldo;
	private String tipo;


	public User(String DNI, String usuario, String contraseña,float Saldo, String tipo) {
		super();
		this.DNI=DNI;
		this.usuario=usuario;
		this.contraseña=contraseña;
		this.saldo=Saldo;
		this.tipo=tipo;
	}
	


	public String getUser() {
		return usuario;
	}


	public String getContraseña() {
		return contraseña;
	}

	public String getTipo() {
		return tipo;
	}

	public float getSaldo() {
		return saldo;
	}
	
	public String getDNI() {
		return DNI;
	}
	
	public void setUser(String usuario) {
		this.usuario=usuario;
	}


	public void setContraseña(String contraseña) {
		this.contraseña= contraseña;
	}

	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	
	
	public void setSaldo(float saldo) {
		this.saldo=saldo;
	}
	
	public void setDNI(String DNI) {
		this.DNI=DNI;
	}
}
