package jdPack;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class AddTrainingServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		String jsonString = req.getParameter("training");
		System.out.println(jsonString);
		if (jsonString != null) {
			System.out.println("Parsing json");
			try {
				JSONObject json = new JSONObject(req.getParameter("training"));
				//
				json = json.getJSONObject("training");
				System.out.println(json);
				String title = json.getString("title");
				String description = json.getString("description");
				long domaineId = json.getLong("domaineId");

				if (title == null || description == null)
					return;

				Entity Training = new Entity("Training");
				Training.setProperty("title", title);
				Training.setProperty("description", description);
				Training.setProperty("domaineId", domaineId);
				datastore.put(Training);

				long trainingId = (Training.getKey().getId());

				JSONArray exercices = json.getJSONArray("exercices");
				if (exercices != null) {
					for (int i = 0; i < exercices.length(); i++) {
						JSONObject exercice = exercices.getJSONObject(i);
						String exerciceTitle = exercice.getString("title");
						String exerciceDescription = exercice
								.getString("description");
						int time = exercice.getInt("time");
						int repetitions = exercice.getInt("repetitions");

						if (exerciceTitle == null
								|| exerciceDescription == null)
							return;

						Entity Exercice = new Entity("Exercice");
						Exercice.setProperty("title", exerciceTitle);
						Exercice.setProperty("description", exerciceDescription);
						Exercice.setProperty("time", time);
						Exercice.setProperty("repetitions", repetitions);
						Exercice.setProperty("trainingId", trainingId);

						datastore.put(Exercice);
					}
				}
				return;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}
}
