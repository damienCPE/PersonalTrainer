package jdPack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// Création de l'array contenant la liste des domaines
        Map<String, String> aDomaine = new HashMap<String, String>();
        
        // Réquête sur la base de données : récupération du HomeMessage
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query("Sport");
        PreparedQuery pq = datastore.prepare(q);
        
        for (Entity result : pq.asIterable())
        {
            aDomaine.put((String) result.getProperty("name"), (String) result.getProperty("img"));
        }
        
        // Encodage en JSON et retour du résultat
        resp.setContentType("text/plain");
        resp.getWriter().println(new JSONObject(aDomaine));
	}
}
