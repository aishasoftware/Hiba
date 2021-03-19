	import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
	
public class token {
	// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)

	    public static void main(String[] args) 
	    {
	        HttpClient httpclient = HttpClients.createDefault();

	        try
	        {
	            URIBuilder builder = new URIBuilder("https://sandbox.momodeveloper.mtn.com/collection/token/");

String user = "aisha";
String pass = "4f1814307485402ab9a463c6b87deb5a";
String cred = user + pass ;
	            URI uri = builder.build();
	            HttpPost request = new HttpPost(uri);
	            request.setHeader("Authorization", "");
	            request.setHeader("Ocp-Apim-Subscription-Key", "{subscription key}");


	            // Request body
	            StringEntity reqEntity = new StringEntity("{body}");
	            request.setEntity(reqEntity);

	            HttpResponse response = httpclient.execute(request);
	            HttpEntity entity = response.getEntity();

	            if (entity != null) 
	            {
	                System.out.println(EntityUtils.toString(entity));
	            }
	        }
	        catch (Exception e)
	        {
	            System.out.println(e.getMessage());
	        }
	    }
	}

