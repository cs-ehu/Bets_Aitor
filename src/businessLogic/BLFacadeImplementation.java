package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Result;
import domain.User;
import domain.Apuesta;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}
		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	    DataAccess dBManager=new DataAccess();
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dBManager.createQuestion(event,question,betMinimum);		

		dBManager.close();
		
		return qry;
   };
   
   
   @WebMethod
   public Question createQuestion(Event event, String question,Result result, float betMinimum,float cuota) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	    DataAccess dBManager=new DataAccess();
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dBManager.createQuestion(event,question,result,betMinimum,cuota);		

		dBManager.close();
		
		return qry;
   };
   
   
   @WebMethod
   public void crearUsuario(String DNI,String usuario, String contraseña) {

	    DataAccess dBManager=new DataAccess();

		if(dBManager.estaUsuario(DNI)) {
			System.out.println("El usuario existe");
		}else {
			dBManager.crearUsuario(DNI,usuario,contraseña);		
			dBManager.close();
		}
   };
      
   
   @WebMethod
   public boolean Login(String usuario, String contraseña) {

	    DataAccess dBManager=new DataAccess();

		if(dBManager.contraseñaCorrecta(usuario,contraseña)) {
			dBManager.close();
			return true;
		}else{
			dBManager.close();
			return false;
		}
		
   };
   
   
   
   
   @WebMethod
   public boolean Esta(String DNI) {

	    DataAccess dBManager=new DataAccess();

		if(dBManager.estaUsuario(DNI)) {
			dBManager.close();
			return true;
		}else{
			dBManager.close();
			return false;
		}
		
   };
   
   
   @WebMethod
   public boolean EsAdmin(String usuario, String contraseña) {

	    DataAccess dBManager=new DataAccess();

		if(dBManager.tipoUsuario(usuario, contraseña).equals("admin")) {
			dBManager.close();
			return true;
		}else{
			dBManager.close();
			return false;
		}
		
   };
   
   
   
   @WebMethod
   public void createEvent(String description,Date eventDate){
	   
	    DataAccess dBManager=new DataAccess();

	    dBManager.crearEvento(description, eventDate);		

		dBManager.close();
   };
   
   
   @WebMethod
   public float saldo(String usuario){
	   
	    DataAccess dBManager=new DataAccess();

	    float saldo=dBManager.verSaldo(usuario);		

		dBManager.close();
		
		return saldo;
   };
   
   @WebMethod
   public void anadirSaldo(String usuario, float saldo){
	   DataAccess dBManager=new DataAccess();
	   dBManager.anadirSaldo(usuario, saldo);
	   dBManager.close();	   
   };
   
	
   
   
   
   @WebMethod
   public void createApuesta(User usuario, float dineroApostado, float beneficios, Question apuesta){
	   
	    DataAccess dBManager=new DataAccess();

	    dBManager.apostar(usuario, dineroApostado, beneficios, apuesta);		

		dBManager.close();
   }; 
   
   
   
   
   @WebMethod
   public User usuario(String nombre){
	   
	    DataAccess dBManager=new DataAccess();

	    User u=dBManager.Usuario(nombre);		

		dBManager.close();
		return u;
   }; 
   
   
   @WebMethod
   public Result estaResultado(String res){
	   
	    DataAccess dBManager=new DataAccess();

	    Result r=dBManager.estaResultado(res);

		dBManager.close();
		return r;
   };
   
   
   
//   @WebMethod
//   public User UsuarioLogueado(String usuario,String contrasena){
//	   
//	    DataAccess dBManager=new DataAccess();
//
//	    User us=dBManager.Usuario(usuario, contrasena);	
//
//		dBManager.close();
//		
//		return us;
//   };
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		DataAccess dbManager=new DataAccess();
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	};
	
	
    @WebMethod	
	public List<Apuesta> getApuestas(String name)  {
		DataAccess dbManager=new DataAccess();
		List<Apuesta>  apuestas=dbManager.getApuestas(name);
		dbManager.close();
		return apuestas;
	};
	
	
    @WebMethod	
	public List<Apuesta> getApuestasEnJuego(String name)  {
		DataAccess dbManager=new DataAccess();
		List<Apuesta>  apuestas=dbManager.getApuestasEnJuego(name);
		dbManager.close();
		return apuestas;
	};
   
	@WebMethod 
	public List<Apuesta> getApuestasFinalizadas(String name)  {
		DataAccess dbManager=new DataAccess();
		List<Apuesta>  apuestas=dbManager.getApuestasFinalizadas(name);
		dbManager.close();
		return apuestas;
	};
	
	
    @WebMethod	
	public List<Apuesta> getApuestasACoregir()  {
		DataAccess dbManager=new DataAccess();
		List<Apuesta>  apuestas=dbManager.getApuestasACoregir();
		dbManager.close();
		return apuestas;
	};

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
		DataAccess dBManager=new DataAccess();
		dBManager.initializeDB();
		dBManager.close();
	};

	
	@WebMethod 
	public Vector<Apuesta> apuestas(String name){
		DataAccess dbManager=new DataAccess();
		Vector<Apuesta> apuesta=dbManager.apuestas(name);
		dbManager.close();
		return apuesta;
	};
	
	
	@WebMethod 
	public void corregirResultado(Apuesta apu, String resultado) {
		DataAccess dbManager=new DataAccess();
		dbManager.corregirResultado(apu,resultado);
		dbManager.close();
	}
	
	@WebMethod 
	public void borrarApuesta(Apuesta ap) {
		DataAccess dbManager=new DataAccess();
		dbManager.borrarApuesta(ap);
		dbManager.close();
	}
	
}

