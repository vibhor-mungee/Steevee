package com.example.webservices;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class LoginWebservice {

	private static String NAMESPACE = "http://DefaultNamespace";
	private static String URL = "http://10.0.2.2:8080/WebServiceProject/services/LoginService";
	private static String SOAP_ACTION = "http://DefaultNamespace";

	public static boolean invokeLoginWS(String username, String password,
			String webMethName) {
		boolean loginStatus = false;
		SoapObject request = new SoapObject(NAMESPACE, webMethName);
		PropertyInfo usernamePi = new PropertyInfo();
		PropertyInfo passwordPi = new PropertyInfo();
		usernamePi.setName("username");
		usernamePi.setValue(username);
		request.addProperty(usernamePi);
		passwordPi.setName("password");
		passwordPi.setValue(password);
		passwordPi.setType(String.class);
		request.addProperty(passwordPi);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		
		try{
			androidHttpTransport.call(SOAP_ACTION+webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			loginStatus = Boolean.parseBoolean(response.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return loginStatus;
	}

}
