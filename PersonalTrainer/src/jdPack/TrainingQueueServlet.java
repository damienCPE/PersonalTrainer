package jdPack;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class TrainingQueueServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put( "title", "training 1" );
	    data.put( "description", "this is a description" );
	    data.put( "domaineId", "4646446" );
	    //JSONObject json = new JSONObject(data);
	    
	    /*
	    JSONObject json = null;
		try {
			json = new JSONObject(""+
			"{"+
				 "\"training\": {"+
				   "\"title\": \"Le titre\","+
				   "\"description\": \"La description\","+
				   "\"domaineId\": \"13556777\","+
				   "\"exercices\": ["+
				     "{"+
				      "\"title\": \"Titre exercice\","+
				       "\"description\": \"Description exercice\","+
				       "\"time\": 1,"+
				       "\"repetitions\": 1,"+
				       "\"trainingId\": 12265688"+
				     "},"+
				     "{"+
				       "\"title\": \"Titre exercice\","+
				       "\"description\": \"Description exercice\","+
				       "\"time\": 1,"+
				       "\"repetitions\": 1,"+
				       "\"trainingId\": 12265688"+
				     "},"+
				     "{"+
				       "\"title\": \"Titre exercice\","+
				       "\"description\": \"Description exercice\","+
				       "\"time\": 1,"+
				       "\"repetitions\": 1,"+
				       "\"trainingId\": 12265688"+
				     "}"+
				   "]"+
				 "}"+
				"}"+
			");");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    System.out.println(req.getParameter("training"));
	    JSONObject json;
		try {
			json = new JSONObject(req.getParameter("training"));
			System.out.println(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	    
		
		Queue queue = QueueFactory.getDefaultQueue();
		// Ajout d’une tache simple
		if(json == null)return;
		TaskOptions task=TaskOptions.Builder.withUrl("/addTraining").param("training", json.toString());
		 queue.add(task);
		// Ajout d’une tache simple avec des paramètres de configuration 
		Map<String, String> headers=new HashMap<String, String>();
		headers.put("X-AppEngine-TaskName","task2");
		headers.put("X-AppEngine-TaskRetryCount","4");
		//...
		queue.deleteTask("task"); 
		//... 
		queue.purge();
	}
}