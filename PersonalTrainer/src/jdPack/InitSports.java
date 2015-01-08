package jdPack;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class InitSports extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity sport1 = new Entity("Sport");
		sport1.setProperty("name", "Tir a l'arc");
		sport1.setProperty("img", "sportsPics-Arc");
		datastore.put(sport1);

		Entity sport2 = new Entity("Sport");
		sport2.setProperty("name", "Aviron");
		sport2.setProperty("img", "sportsPics-Aviron");
		datastore.put(sport2);

		Entity sport3 = new Entity("Sport");
		sport3.setProperty("name", "Basketball");
		sport3.setProperty("img", "sportsPics-Basketball");
		datastore.put(sport3);

		Entity sport4 = new Entity("Sport");
		sport4.setProperty("name", "Boxe");
		sport4.setProperty("img", "sportsPics-Boxe");
		datastore.put(sport4);

		Entity sport5 = new Entity("Sport");
		sport5.setProperty("name", "Course");
		sport5.setProperty("img", "sportsPics-Course");
		datastore.put(sport5);

		Entity sport6 = new Entity("Sport");
		sport6.setProperty("name", "Cyclisme");
		sport6.setProperty("img", "sportsPics-Cyclisme");
		datastore.put(sport6);

		Entity sport7 = new Entity("Sport");
		sport7.setProperty("name", "Danse");
		sport7.setProperty("img", "sportsPics-Danse");
		datastore.put(sport7);

		Entity sport8 = new Entity("Sport");
		sport8.setProperty("name", "Escrime");
		sport8.setProperty("img", "sportsPics-Escrime");
		datastore.put(sport8);

		Entity sport9 = new Entity("Sport");
		sport9.setProperty("name", "Football");
		sport9.setProperty("img", "sportsPics-Football");
		datastore.put(sport9);

		Entity sport10 = new Entity("Sport");
		sport10.setProperty("name", "Handball");
		sport10.setProperty("img", "sportsPics-Handball");
		datastore.put(sport10);

		Entity sport11 = new Entity("Sport");
		sport11.setProperty("name", "Hockey");
		sport11.setProperty("img", "sportsPics-Hockey");
		datastore.put(sport11);

		Entity sport12 = new Entity("Sport");
		sport12.setProperty("name", "Judo");
		sport12.setProperty("img", "sportsPics-Judo");
		datastore.put(sport12);

		Entity sport13 = new Entity("Sport");
		sport13.setProperty("name", "Karate");
		sport13.setProperty("img", "sportsPics-Karate");
		datastore.put(sport13);

		Entity sport14 = new Entity("Sport");
		sport14.setProperty("name", "Musculation");
		sport14.setProperty("img", "sportsPics-Musculation");
		datastore.put(sport14);

		Entity sport15 = new Entity("Sport");
		sport15.setProperty("name", "Natation");
		sport15.setProperty("img", "sportsPics-Natation");
		datastore.put(sport15);

		Entity sport16 = new Entity("Sport");
		sport16.setProperty("name", "Equitation");
		sport16.setProperty("img", "sportsPics-Peche");
		datastore.put(sport16);

		Entity sport17 = new Entity("Sport");
		sport17.setProperty("name", "Triathlon");
		sport17.setProperty("img", "sportsPics-Triathlon");
		datastore.put(sport17);

		Entity sport18 = new Entity("Sport");
		sport18.setProperty("name", "Unknown");
		sport18.setProperty("img", "sportsPics-Unknown");
		datastore.put(sport18);

		Entity sport19 = new Entity("Sport");
		sport19.setProperty("name", "Volley");
		sport19.setProperty("img", "sportsPics-Volley");
		datastore.put(sport19);
	}
}
