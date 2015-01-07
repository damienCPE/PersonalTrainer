package jdPack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;

import javax.cache.Cache;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.stdimpl.GCacheFactory;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Cache cache = null;
		Map props = new HashMap();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
		props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
		try {
			String key = "homeMessage"; // D�finir la cl� de la valeur � stocker
			String value = "myValue1"; // D�finir l'objet � stocker
			// M�thode de cache synchrone
			MemcacheService syncCache = MemcacheServiceFactory
					.getMemcacheService();
			syncCache.setErrorHandler(ErrorHandlers
					.getConsistentLogAndContinue(Level.INFO));
			value = (String) syncCache.get(key); // Lecture depuis le cache
			
			if (value == null) {
				//datastorage
				DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				
				Filter homeMessageFilter =
						  new FilterPredicate("type",
						                      FilterOperator.EQUAL,
						                      "homeMessage");
				
				Query q = new Query("Message").setFilter(homeMessageFilter);
				// R�cup�ration du r�sultat de la requ�te � l�aide de PreparedQuery  
				PreparedQuery pq = datastore.prepare(q); 
				 
				for (Entity result : pq.asIterable()) {
				  value = (String) result.getProperty("content");
				}

				syncCache.put(key, value); // Mise � jour du cache
			}
			
			// M�thode de cache asynchrone
			AsyncMemcacheService asyncCache = MemcacheServiceFactory
					.getAsyncMemcacheService();
			asyncCache.setErrorHandler(ErrorHandlers
					.getConsistentLogAndContinue(Level.INFO));
			Future<Object> futureValue = asyncCache.get(key); // Lecture depuis
																// le cache
			// ... Executation de tache en parall�le de la recherche dans le
			// cache
			value = (String) futureValue.get();
			if (value == null) {
				// r�cup�ration de la valeur et ex�cution de son code �.
				// Mise � jour du cache asynchrone
				// Retourne Future<Void> qui peut �tre utilis� pour bloquer
				// jusqu�� ce la fin de l�action
				asyncCache.put(key, value);
			}
			
			resp.getWriter().println(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
