package jdPack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class SearchPlanServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String,Map<String, String>> outerMap = new LinkedHashMap<String, Map<String, String>>();

		String paramFilter = request.getParameter("filter");
		System.out.println(paramFilter);

		// Get the Datastore Service
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Filter exFilter = new FilterPredicate("title", FilterOperator.EQUAL,
				paramFilter);

		// Use class Query to assemble a query
		Query q = new Query("Training").setFilter(exFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);
        
        for (Entity result : pq.asIterable())
        {
        	Map<String, String> innerMap = new HashMap<String, String>();
        	System.out.println(result.getProperty("title") + " " +result.getProperty("description"));
        	innerMap.put("id", Long.toString(result.getKey().getId()));
        	innerMap.put("title", (String) result.getProperty("title"));
    		innerMap.put("description", (String) result.getProperty("description"));
    		outerMap.put(result.getKey().toString(), innerMap);
        }
        
        // Encodage en JSON et retour du résultat
        response.setContentType("text/plain");
        response.getWriter().println(getJSON(outerMap));
	}
	
	public JSONObject getJSON(Map map) {
        Iterator iter = map.entrySet().iterator();
        JSONObject holder = new JSONObject();
        
        int i =0;
        while (iter.hasNext()) {
                Map.Entry pairs = (Map.Entry) iter.next();
                String key = (String) pairs.getKey();
                Map m = (Map) pairs.getValue();
                JSONObject data = new JSONObject();
                
                try {
                        Iterator iter2 = m.entrySet().iterator();
                        int j = 0;
                        while (iter2.hasNext()) {
                                Map.Entry pairs2 = (Map.Entry) iter2.next();
                                data.put((String) pairs2.getKey(), (String) pairs2.getValue());
                        }
                        holder.put(key, data);
                } catch (JSONException e) {
                        System.out.println("There was an error packaging JSON : " + e);
                }
        }
        
        return holder;
	}
}