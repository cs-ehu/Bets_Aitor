package dataAccess;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Apuesta;
import domain.Event;
import domain.Question;
import domain.Result;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c;

	public DataAccess(boolean initializeMode) {

		c = ConfigXML.getInstance();

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode)
			fileName = fileName + ";drop";

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public DataAccess() {
		new DataAccess(true);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			User u1 = new User("111111111", "admin", "admin", 0, "admin");
			db.persist(u1);
			User u2 = new User("999999999", "aitor", "aitor", 100, "no admin");
			db.persist(u2);

			Event ev1 = new Event(1, "Atletico-Athletic", newDate(2018

					, 1, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", newDate(2018, 1, 17));
			Event ev3 = new Event(3, "Getafe-Celta", newDate(2018, 1, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", newDate(2018, 1, 17));
			Event ev5 = new Event(5, "Español-Villareal", newDate(2018, 1, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", newDate(2018, 1, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", newDate(2018, 1, 17));
			Event ev8 = new Event(8, "Girona-Leganés", newDate(2018, 1, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", newDate(2018, 1, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", newDate(2018, 1, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", newDate(2018, 2, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", newDate(2018, 2, 1));
			Event ev13 = new Event(13, "Getafe-Celta", newDate(2018, 2, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", newDate(2018, 2, 1));
			Event ev15 = new Event(15, "Español-Villareal", newDate(2018, 2, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", newDate(2018, 2, 1));

			Event ev17 = new Event(17, "Malaga-Valencia", newDate(2018, 2, 31));
			Event ev18 = new Event(18, "Girona-Leganés", newDate(2018, 2, 31));
			Event ev19 = new Event(19, "Real Sociedad-Levante", newDate(2018, 2, 31));
			Event ev20 = new Event(20, "Betis-Real Madrid", newDate(2018, 2, 31));
			Event ev21 = new Event(21, "Barca-Alaves", newDate(2018, 3, 14));
			Event ev22 = new Event(22, "Girona-Malaga", newDate(2018, 3, 21));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
			Question q7;
			Question q8;
			Question q9;

			Result r1 = new Result(1,"Alaves");
			Result r2 = new Result(2,"Malaga");
			Result r3 = new Result(3,"Girona");
			
			q7 = ev21.addQuestion("¿Quien ganará el partido?", r1, 1, 2);
			q8 = ev22.addQuestion("¿Quien ganará el partido?", r2, 1, 2);
			q9 = ev22.addQuestion("¿Quien ganará el partido?", r3, 1, 3);

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quien ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quien meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quien ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quien ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);

			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(q7);
			db.persist(q8);
			db.persist(q9);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);

			db.persist(ev21);
			db.persist(ev22);

			

			
			Event ev23 = new Event(23, "Liverpool-Real Madrid", newDate(2018, 4, 26));
			Question q23;
			Question q24;
			Question q22;

			Result r5 = new Result(1,"Liverpool");
			Result r7 = new Result(2,"Real Madrid");
			Result r8 = new Result(5,"Salah");
			
			q23 = ev23.addQuestion("¿Quien ganará el partido?", r5, 1, 2f);
			q24 = ev23.addQuestion("¿Quien ganará el partido?", r7, 1, 2f);
			q22 = ev23.addQuestion("Goleador durante el partido", r8, 1, 1.3f);
			
			

			
			
			Event ev26 = new Event(26, "Barca-Real Madrid", newDate(2018, 3, 28));
			Question q30;
			Question q31;
			Question q32;
			
			Result r30 = new Result(6,"Barca");
			//Result r31 = new Result(7,"Madrid");
			Result r32 = new Result(8,"Messi");
			
			q30 = ev26.addQuestion("¿Quien ganará el partido?", r30, 1, 2f);
			q31 = ev26.addQuestion("¿Quien ganará el partido?", r7, 1, 2f);
			q32 = ev26.addQuestion("Goleador durante el partido", r32, 1, 1.5f);
			
			
			db.persist(ev26);
			db.persist(q30);
			db.persist(q31);
			db.persist(r30);
		//	db.persist(r31);
			db.persist(q32);
			db.persist(r32);
			
			
			
			
			
			
			Event ev24 = new Event(24, "Liverpool-Roma", newDate(2018, 3, 24));
			Question q40;
			Question q41;
			Question q42;
			
			//Result r40 = new Result(9,"Liverpool");
			Result r41 = new Result(10,"Roma");
			Result r42 = new Result(11,"Dzeko");
			
			q40 = ev24.addQuestion("¿Quien ganará el partido?", r5, 1,1.3f);
			q41 = ev24.addQuestion("¿Quien ganará el partido?", r41, 1, 1.6f);
			q42 = ev24.addQuestion("Goleador durante el partido", r42, 1, 1.5f);
			
			
			db.persist(ev24);
			db.persist(q40);
			db.persist(q41);
			//db.persist(r40);
			db.persist(r41);
			db.persist(q42);
			db.persist(r42);
			
			Apuesta a4 = new Apuesta(3, u2, 10, 13, q40);
			a4.setCobrada(true);
			a4.setAcertada(true);
			db.persist(a4);
			
			Apuesta a5 = new Apuesta(4, u2, 10, 16, q41);
			a5.setCobrada(true);
			db.persist(a5);
			
			Apuesta a6 = new Apuesta(5, u2, 10, 15, q42);
			a6.setCobrada(true);
			a6.setAcertada(true);
			db.persist(a6);
			
			
			
			
			Event ev25 = new Event(25, "Alaves-Athletic", newDate(2018, 4, 12));
			Question q25;
			Question q26;
			
			Result r25 = new Result(3,"Alaves");
			Result r27 = new Result(4,"Athletic");
			
			q25 = ev25.addQuestion("¿Quien ganará el partido?", r25, 1, 1.21f);
			q26 = ev25.addQuestion("¿Quien ganará el partido?", r27, 1, 2f);
			
			db.persist(r5);
			db.persist(r7);	
			db.persist(r8);
			db.persist(r25);
			db.persist(r27);
			
			db.persist(q22);
			db.persist(q23);
			db.persist(q25);
			db.persist(q24);
			db.persist(q26);

			db.persist(ev23);
			db.persist(ev25);
			
			Apuesta a7 = new Apuesta(6, u2, 10, 12.1f, q25);
			db.persist(a7);

			
			Apuesta a = new Apuesta(0, u2, 100, 200, q31);
			a.setCobrada(true);
			db.persist(a);
			
			Apuesta a2 = new Apuesta(1, u2, 25, 50, q30);
			a2.setCobrada(true);
			db.persist(a2);
			
			Apuesta a3 = new Apuesta(2, u2, 10, 15, q32);
			db.persist(a3);
			
			
			
			Apuesta a8 = new Apuesta(7, u2, 20, 40f, q23);
			db.persist(a8);
			Apuesta a9 = new Apuesta(8, u2, 15, 30f, q24);
			db.persist(a9);
			Apuesta a10 = new Apuesta(9, u2, 10, 13f, q23);
			db.persist(a10);
			
			
			
			
			
			Event ev27 = new Event(27, "Leganes-Real Madrid", newDate(2018, 3, 28));
			Question q55;
			Question q56;
			
			Result r55 = new Result(9,"Leganes");
			
			q55 = ev27.addQuestion("¿Quien ganará el partido?", r55, 1, 1.55f);
			q56 = ev27.addQuestion("¿Quien ganará el partido?", r7, 1, 1.32f);
			
			db.persist(ev27);
			db.persist(q55);
			db.persist(q56);
			db.persist(r55);
			
			
			Apuesta a12 = new Apuesta(10, u2, 15, 23.25f, q55);
			db.persist(a12);
			Apuesta a11 = new Apuesta(11, u2, 10, 13.2f, q56);
			db.persist(a11);
			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event
	 *            to which question is added
	 * @param question
	 *            text of the question
	 * @param betMinimum
	 *            minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist
	 *             if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		// db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	public Question createQuestion(Event event, String question, Result result, float betMinimum, float cuota)
			throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum + " cuota=" + cuota);

		Event ev = db.find(Event.class, event.getEventNumber());

		//if (ev.DoesQuestionExists(question))
		//	throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, result, betMinimum, cuota);
		// db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date
	 *            in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}
	
	
	
	
	public List<Apuesta> getApuestasACoregir() {		
		TypedQuery<Apuesta> query = db.createQuery("SELECT ap FROM Apuesta ap WHERE ap.apuesta.event.eventDate<?1", Apuesta.class);
		query.setParameter(1, new Date());
		List<Apuesta> apuesta = query.getResultList();
		return apuesta;
	}
	
	
	public List<Apuesta> getApuestas(String name) {
		System.out.println(">> DataAccess: getApuestas de "+name);
		TypedQuery<Apuesta> query = db.createQuery("SELECT ap FROM Apuesta ap WHERE ap.usuario.usuario=?1", Apuesta.class);
		query.setParameter(1, name);
		List<Apuesta> apuesta = query.getResultList();
		return apuesta;
	}
	
	public List<Apuesta> getApuestasEnJuego(String name) {
		System.out.println(">> DataAccess: getApuestas de "+name);
		TypedQuery<Apuesta> query = db.createQuery("SELECT ap FROM Apuesta ap WHERE ap.usuario.usuario=?1 AND ap.cobrada=false", Apuesta.class);
		query.setParameter(1, name);
		List<Apuesta> apuesta = query.getResultList();
		return apuesta;
	}
	
	
	public List<Apuesta> getApuestasFinalizadas(String name) {
		System.out.println(">> DataAccess: getApuestas de "+name);
		TypedQuery<Apuesta> query = db.createQuery("SELECT ap FROM Apuesta ap WHERE ap.usuario.usuario=?1 AND ap.cobrada=true", Apuesta.class);
		query.setParameter(1, name);
		List<Apuesta> apuesta = query.getResultList();
		return apuesta;
	}
	

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	private Date newDate(int year, int month, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public boolean estaUsuario(String DNI) {
		TypedQuery<User> query = db.createQuery("SELECT p FROM User p  WHERE  p.DNI = ?1", User.class);
		query.setParameter(1, DNI);
		List<User> Usuario = query.getResultList();
		if (Usuario.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean contraseñaCorrecta(String usuario, String contraseña) {
		TypedQuery<User> query = db.createQuery("SELECT p FROM User p  WHERE  p.usuario = ?1 AND p.contraseña =?2",
				User.class);
		query.setParameter(1, usuario);
		query.setParameter(2, contraseña);
		List<User> Usuario = query.getResultList();
		if (Usuario.isEmpty()) {
			return false;
		}
		return true;
	}

	public String tipoUsuario(String usuario, String contraseña) {
		TypedQuery<User> query = db.createQuery("SELECT p FROM User p  WHERE  p.usuario = ?1 AND p.contraseña =?2",
				User.class);
		query.setParameter(1, usuario);
		query.setParameter(2, contraseña);
		List<User> Usuario = query.getResultList();
		User u = Usuario.get(0);
		String tip = u.getTipo();
		return tip;

	}

	public User crearUsuario(String DNI, String usuario, String contraseña) {
		User u = new User(DNI, usuario, contraseña, 0, "no admin");
		db.getTransaction().begin();
		db.persist(u);
		db.getTransaction().commit();
		return u;
	}

	public void crearEvento(String description, Date eventDate) {
		TypedQuery<Event> query = db.createQuery("SELECT p FROM Event p", Event.class);
		List<Event> eve = query.getResultList();
		int tam = eve.size() + 1;
		Event e = new Event(tam, description, eventDate);
		db.getTransaction().begin();
		db.persist(e);
		db.getTransaction().commit();
		System.out.println("Evento creado");
	}

	public float verSaldo(String usuario) {
		TypedQuery<User> query = db.createQuery("SELECT p FROM User p WHERE p.usuario=?1", User.class);
		query.setParameter(1, usuario);
		List<User> us = query.getResultList();
		User u = us.get(0);
		return u.getSaldo();
	}

	public void anadirSaldo(String usuario, float saldo) {
		db.getTransaction().begin();
		TypedQuery<User> query = db.createQuery("SELECT p FROM User p WHERE p.usuario=?1", User.class);
		query.setParameter(1, usuario);
		List<User> us = query.getResultList();
		User u = us.get(0);
		u.setSaldo(u.getSaldo() + saldo);
		db.getTransaction().commit();
	}

//	public User Usuario(String usuario, String contraseña) {
//		TypedQuery<User> query = db.createQuery("SELECT p FROM User p  WHERE  p.usuario = ?1 AND p.contraseña =?2",
//				User.class);
//		query.setParameter(1, usuario);
//		query.setParameter(2, contraseña);
//		List<User> Usuario = query.getResultList();
//		User u = Usuario.get(0);
//		return u;
//	}
	
	public User Usuario(String usuario) {
		TypedQuery<User> query = db.createQuery("SELECT p FROM User p  WHERE  p.usuario = ?1",User.class);
		query.setParameter(1, usuario);
		List<User> Usuario = query.getResultList();
		User u = Usuario.get(0);
		return u;
	}
	
	public void apostar (User usuario, float dineroApostado, float beneficios, Question apuesta) {
		db.getTransaction().begin();
		TypedQuery<Apuesta> query = db.createQuery("SELECT p FROM Apuesta p", Apuesta.class);
		List<Apuesta> apu = query.getResultList();
		int tam = apu.size() + 1;
		Apuesta a = new Apuesta(tam,usuario,dineroApostado, beneficios, apuesta);
		db.persist(a);
		db.getTransaction().commit();
		System.out.println("Apuesta creado");
	}
	
	public Vector<Apuesta> apuestas(String nombre){
		Vector<Apuesta> res = new Vector<Apuesta>();
		TypedQuery<Apuesta> query = db.createQuery("SELECT a FROM Apuesta a WHERE a.usuario=?1", Apuesta.class);
		User u = Usuario(nombre);
		query.setParameter(1, u);
		List<Apuesta> bets = query.getResultList();
		for (Apuesta a : bets) {
			System.out.println(a.toString());
			res.add(a);
		}
		return res;
	}
	
	
	public Result estaResultado(String result) {
		TypedQuery<Result> query = db.createQuery("SELECT p FROM Result p WHERE p.result=?1", Result.class);
		query.setParameter(1, result);
		List<Result> res = query.getResultList();
		Result r;
		if (res.isEmpty()){
			db.getTransaction().begin();
			TypedQuery<Result> que = db.createQuery("SELECT p FROM Result p", Result.class);
			List<Result> resultados = que.getResultList();
			int tam = resultados.size() + 1;
			r = new Result(tam, result);
			db.persist(r);
			db.getTransaction().commit();
		}else {
			r = res.get(0);
		}
		return r;
	}
	
	
	public void corregirResultado(Apuesta apu, String resultado) {
		db.getTransaction().begin();
		String pregunta = apu.getApuesta().getQuestion();
		String evento = apu.getApuesta().getEvent().getDescription();
		TypedQuery<Apuesta> query = db.createQuery("SELECT ap FROM Apuesta ap WHERE ap.apuesta.question=?1 AND ap.apuesta.event.description=?2", Apuesta.class);
		query.setParameter(1, pregunta);
		query.setParameter(2, evento);
		List<Apuesta> res = query.getResultList();
		if(res.isEmpty()) {
			System.out.println("error");
		}else {
			for(Apuesta ap : res) {
				User u = ap.getUsusario();
				String respu = ap.getApuesta().getResult().getResult();
				float beneficios = ap.getBeneficios();
				if (respu.equals(resultado)) {
					u.setSaldo(u.getSaldo()+beneficios);
					ap.setAcertada(true);
				}
				ap.setCobrada(true);
			}
		}
		db.getTransaction().commit();
	}
	
	
	
	public void borrarApuesta(Apuesta ap) {
		db.getTransaction().begin();		
		TypedQuery<Apuesta> query = db.createQuery("DELETE FROM Apuesta ap WHERE ap.codigoApuesta=?1", Apuesta.class);
		query.setParameter(1, ap.getcodigoApuesta());
		query.executeUpdate();
		db.getTransaction().commit();
	}
}
