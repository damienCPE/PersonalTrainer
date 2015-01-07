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
		sport1.setProperty("name", request.getParameter("Tir à l'arc"));
		sport1.setProperty("img", request.getParameter("img/sportsPics-Arc"));
		datastore.put(sport1);

		Entity sport2 = new Entity("Sport");
		sport2.setProperty("name", request.getParameter("Aviron"));
		sport2.setProperty("img", request.getParameter("img/sportsPics-Aviron"));
		datastore.put(sport2);

		Entity sport3 = new Entity("Sport");
		sport3.setProperty("name", request.getParameter("Basketball"));
		sport3.setProperty("img", request.getParameter("img/sportsPics-Basketball"));
		datastore.put(sport3);

		Entity sport4 = new Entity("Sport");
		sport4.setProperty("name", request.getParameter("Boxe"));
		sport4.setProperty("img", request.getParameter("img/sportsPics-Boxe"));
		datastore.put(sport4);

		Entity sport5 = new Entity("Sport");
		sport5.setProperty("name", request.getParameter("Course"));
		sport5.setProperty("img", request.getParameter("img/sportsPics-Course"));
		datastore.put(sport5);

		Entity sport6 = new Entity("Sport");
		sport6.setProperty("name", request.getParameter("Cyclisme"));
		sport6.setProperty("img", request.getParameter("img/sportsPics-Cyclisme"));
		datastore.put(sport6);

		Entity sport7 = new Entity("Sport");
		sport7.setProperty("name", request.getParameter("Danse"));
		sport7.setProperty("img", request.getParameter("img/sportsPics-Danse"));
		datastore.put(sport7);

		Entity sport8 = new Entity("Sport");
		sport8.setProperty("name", request.getParameter("Escrime"));
		sport8.setProperty("img", request.getParameter("img/sportsPics-Escrime"));
		datastore.put(sport8);

		Entity sport9 = new Entity("Sport");
		sport9.setProperty("name", request.getParameter("Football"));
		sport9.setProperty("img", request.getParameter("img/sportsPics-Football"));
		datastore.put(sport9);

		Entity sport10 = new Entity("Sport");
		sport10.setProperty("name", request.getParameter("Handball"));
		sport10.setProperty("img", request.getParameter("img/sportsPics-Handball"));
		datastore.put(sport10);

		Entity sport11 = new Entity("Sport");
		sport11.setProperty("name", request.getParameter("Hockey"));
		sport11.setProperty("img", request.getParameter("img/sportsPics-Hockey"));
		datastore.put(sport11);

		Entity sport12 = new Entity("Sport");
		sport12.setProperty("name", request.getParameter("Judo"));
		sport12.setProperty("img", request.getParameter("img/sportsPics-Judo"));
		datastore.put(sport12);

		Entity sport13 = new Entity("Sport");
		sport13.setProperty("name", request.getParameter("Karate"));
		sport13.setProperty("img", request.getParameter("img/sportsPics-Karate"));
		datastore.put(sport13);

		Entity sport14 = new Entity("Sport");
		sport14.setProperty("name", request.getParameter("Musculation"));
		sport14.setProperty("img", request.getParameter("img/sportsPics-Musculation"));
		datastore.put(sport14);

		Entity sport15 = new Entity("Sport");
		sport15.setProperty("name", request.getParameter("Natation"));
		sport15.setProperty("img", request.getParameter("img/sportsPics-Natation"));
		datastore.put(sport15);

		Entity sport16 = new Entity("Sport");
		sport16.setProperty("name", request.getParameter("Peche"));
		sport16.setProperty("img", request.getParameter("img/sportsPics-Peche"));
		datastore.put(sport16);

		Entity sport17 = new Entity("Sport");
		sport17.setProperty("name", request.getParameter("Triathlon"));
		sport17.setProperty("img", request.getParameter("img/sportsPics-Triathlon"));
		datastore.put(sport17);

		Entity sport18 = new Entity("Sport");
		sport18.setProperty("name", request.getParameter("Unknown"));
		sport18.setProperty("img", request.getParameter("img/sportsPics-Unknown"));
		datastore.put(sport18);

		Entity sport19 = new Entity("Sport");
		sport19.setProperty("name", request.getParameter("Volley"));
		sport19.setProperty("img", request.getParameter("img/sportsPics-Volley"));
		datastore.put(sport19);
	}
}
