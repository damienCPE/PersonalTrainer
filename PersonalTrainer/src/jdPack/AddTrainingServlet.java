package jdPack;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class AddTrainingServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String key = req.getParameter("key");
		System.out.println(key);
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		String title = "1";//req.getParameter("title");
		String description = "jb";//req.getParameter("description");
		String time = "1:12:00";//req.getParameter("time");
		String idExercices = "46846844";//req.getParameter("idExercices");
		
		Entity Exercices = new Entity("Exercices");
		Exercices.setProperty("title", "exercice 1");
		Exercices.setProperty("description", "voici un exercice");
		Exercices.setProperty("time", "1:20:00");
		datastore.put(Exercices);
		long domaineId = (Exercices.getKey().getId());
		
		idExercices = Long.toString(domaineId);
		Entity Training = new Entity("Training");
		Training.setProperty("title", title);
		Training.setProperty("description", description);
		Training.setProperty("time", time);
		Training.setProperty("exercicesId", idExercices);
		datastore.put(Training);
		
		
	}
}
