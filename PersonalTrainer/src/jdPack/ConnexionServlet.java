package jdPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class ConnexionServlet extends HttpServlet {

	private static final Map<String, String> openIdProviders;
	static {
		openIdProviders = new HashMap<String, String>();
		openIdProviders.put("Google", "https://www.google.com/accounts/o8/id");
		openIdProviders.put("Yahoo", "yahoo.com");
		openIdProviders.put("MyOpenId.com", "myopenid.com");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		boolean logged = false;
		String logoutURL = "", loginURLGoogle = "", loginURLOpenID = "", loginURLYahoo = "";
		Map<String, String> loginInf = new HashMap<String, String>();

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser(); // or
													// request.getUserPrincipal()
		Set<String> attributes = new HashSet();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (user != null) {
			/*
			 * out.println("Hello <i>" + user.getNickname() + "</i>!");
			 * out.println("[<a href=\"" +
			 * userService.createLogoutURL(request.getRequestURI()) +
			 * "\">sign out</a>]");
			 */
			// logoutURL = userService.createLogoutURL(request.getRequestURI());
			logoutURL = userService
					.createLogoutURL("/search.html");
			logged = true;
		} else {
			/*
			 * out.println("Hello world! Sign in at: "); for (String
			 * providerName : openIdProviders.keySet()) { String providerUrl =
			 * openIdProviders.get(providerName); String loginUrl =
			 * userService.createLoginURL( request.getRequestURI(), null,
			 * providerUrl, attributes); out.println("[<a href=\"" + loginUrl +
			 * "\">" + providerName + "</a>] "); }
			 */
			loginURLGoogle = userService.createLoginURL( "/search.html", null,
					openIdProviders.get("Google"), attributes);
			loginURLYahoo = userService.createLoginURL( "/search.html", null,
					openIdProviders.get("Yahoo"), attributes);
			loginURLOpenID = userService.createLoginURL( "/search.html", null,
					openIdProviders.get("MyOpenId.com"), attributes);
			/*
			loginURLGoogle = userService.createLoginURL( request.getRequestURI(), null,
					openIdProviders.get("Google"), attributes);
			loginURLYahoo = userService.createLoginURL( request.getRequestURI(), null,
					openIdProviders.get("Yahoo"), attributes);
			loginURLOpenID = userService.createLoginURL( request.getRequestURI(), null,
					openIdProviders.get("MyOpenId.com"), attributes);
			*/
			logoutURL = "";
			logged = false;
		}
		// System.out.println("Logged : " + logged + " - LogoutURL : " +
		// logoutURL);
		loginInf.put("login", Boolean.toString(logged));
		loginInf.put("logoutUrl", logoutURL);
		loginInf.put("loginUrlGoogle", loginURLGoogle);
		loginInf.put("loginUrlYahoo", loginURLYahoo);
		loginInf.put("loginUrlOpenId", loginURLOpenID);
		response.setContentType("text/plain");
		response.getWriter().println(new JSONObject(loginInf));
	}
}