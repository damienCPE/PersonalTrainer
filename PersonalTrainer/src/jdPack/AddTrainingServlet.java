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
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@SuppressWarnings("serial")
public class AddTrainingServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		
		Queue queue = QueueFactory.getDefaultQueue();
		// Ajout d’une tache simple
		TaskOptions task=TaskOptions.Builder.withUrl("/worker").param("key", "value");
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
