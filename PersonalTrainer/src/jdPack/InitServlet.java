package jdPack;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

@SuppressWarnings("serial")
public class InitServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Entity HomeMessage = new Entity("Message");
		HomeMessage.setProperty("content", "Voici le message de la page home");
		HomeMessage.setProperty("type", "homeMessage");
		datastore.put(HomeMessage);
		
		Key homeMessageKey = HomeMessage.getKey(); 
		/*try {
			Entity homeMessage2 = datastore.get(homeMessageKey);
			resp.getWriter().println(homeMessage2.getProperty("content").toString());
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
