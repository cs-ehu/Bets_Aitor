package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.Result;
import domain.User;
import domain.Apuesta;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade {

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	@WebMethod
	Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;

	@WebMethod
	Question createQuestion(Event event, String question, Result result, float betMinimum, float cuota)
			throws EventFinished, QuestionAlreadyExist;

	@WebMethod
	public void corregirResultado(Apuesta apu, String resultado);

	/**
	 * This method retrieves the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEvents(Date date);

	@WebMethod
	public List<Apuesta> getApuestas(String name);

	@WebMethod
	public List<Apuesta> getApuestasEnJuego(String name);

	@WebMethod
	public List<Apuesta> getApuestasFinalizadas(String name);

	@WebMethod
	public List<Apuesta> getApuestasACoregir();

	/**
	 * This method calls the data access to initialize the database with some events
	 * and questions. It is invoked only when the option "initialize" is declared in
	 * the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD();

	@WebMethod
	public void crearUsuario(String DNI, String usuario, String contraseña);

	@WebMethod
	public boolean Login(String usuario, String contraseña);

	@WebMethod
	public boolean Esta(String DNI);

	@WebMethod
	public boolean EsAdmin(String usuario, String contraseña);

	@WebMethod
	public void createEvent(String description, Date eventDate);

	@WebMethod
	public float saldo(String usuario);

	@WebMethod
	public void anadirSaldo(String usuario, float saldo);

	@WebMethod
	public void createApuesta(User usuario, float dineroApostado, float beneficios, Question apuesta);

	@WebMethod
	public User usuario(String nombre);

	@WebMethod
	public Vector<Apuesta> apuestas(String name);

	@WebMethod
	public Result estaResultado(String res);

	@WebMethod
	public void borrarApuesta(Apuesta ap);

	// @WebMethod public User UsuarioLogueado(String usuario,String contrasena);

}
