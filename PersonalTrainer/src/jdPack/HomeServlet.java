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
			String key = "objKey1"; // Définir la clé de la valeur à stocker
			String value = "myValue1"; // Définir l'objet à stocker
			// Méthode de cache synchrone
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
				// Récupération du résultat de la requète à l’aide de PreparedQuery  
				PreparedQuery pq = datastore.prepare(q); 
				 
				for (Entity result : pq.asIterable()) {
				  value = (String) result.getProperty("content");
				}

				syncCache.put(key, value); // Mise à jour du cache
			}
			
			// Méthode de cache asynchrone
			AsyncMemcacheService asyncCache = MemcacheServiceFactory
					.getAsyncMemcacheService();
			asyncCache.setErrorHandler(ErrorHandlers
					.getConsistentLogAndContinue(Level.INFO));
			Future<Object> futureValue = asyncCache.get(key); // Lecture depuis
																// le cache
			// ... Executation de tache en parallèle de la recherche dans le
			// cache
			value = (String) futureValue.get();
			if (value == null) {
				// récupération de la valeur et exécution de son code ….
				// Mise à jour du cache asynchrone
				// Retourne Future<Void> qui peut être utilisé pour bloquer
				// jusqu’à ce la fin de l’action
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
