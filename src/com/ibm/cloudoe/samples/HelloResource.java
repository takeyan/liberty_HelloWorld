package com.ibm.cloudoe.samples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import java.io.IOException;

@Path("/hello")
public class HelloResource {

	@GET
	public String getInformation() {

		// 'VCAP_APPLICATION' is in JSON format, it contains useful information about a deployed application
		// String envApp = System.getenv("VCAP_APPLICATION");

		// 'VCAP_SERVICES' contains all the credentials of services bound to this application.
		// String envServices = System.getenv("VCAP_SERVICES");
		// JSONObject sysEnv = new JSONObject(System.getenv());
		
//		return "Hi Bluemix World!";
		return this.getVcapServices().toString();
	}
	
	public JSONObject getVcapServices() {
		  String vcap = System.getenv("VCAP_SERVICES");

		  if (vcap == null) return null;
		  JSONObject vcapObject = null;
		  JSONObject credential = null;
		  try {
		     vcapObject = JSONObject.parse(vcap);
		     JSONArray up = (JSONArray)vcapObject.get("user-provided");
		     JSONObject up0 = (JSONObject)up.get(0);
		     credential = (JSONObject)up0.get("credentials");
//		     apikey = (String)credential.get("apikey");

		  }
		  catch (IOException e) {
		    e.printStackTrace();
		  }
		  return credential;
//		  return vcapObject;
		}	
	
	
}