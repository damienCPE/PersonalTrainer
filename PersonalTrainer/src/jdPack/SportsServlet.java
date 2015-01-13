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
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class SportsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String,Map<String, String>> outerMap  = new LinkedHashMap<String, Map<String, String>>();
		
		
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query("Sport");
        PreparedQuery pq = datastore.prepare(q);
        
        for (Entity result : pq.asIterable())
        {
        	Map<String, String> innerMap = new HashMap<String, String>();
        	//System.out.println(result.getProperty("name") + " " +result.getProperty("img"));
        	innerMap.put("id", Long.toString(result.getKey().getId()));
        	innerMap.put("name", (String) result.getProperty("name"));
    		innerMap.put("img", (String) result.getProperty("img"));
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
