package com.mobdb.android;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.os.AsyncTask;
/**
 * This class send file request and retrieves file bytes array 
 */
public class MobDBRequest extends AsyncTask<String, Void, byte[]>  {

	private String params;
	private String contentType ="";
	private MobDBResponseListener listener;
	private boolean secure;
		
	public MobDBRequest(boolean secure, MobDBResponseListener listner) {
		// TODO Auto-generated constructor stub
		this.listener = listner;
		this.secure = secure;
	}
	
	public void executeRequest(){
		execute(params);
	}
	
	public void setParams(String jsonParams){
		this.params = jsonParams;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(byte[] result) {
		// TODO Auto-generated method stub
		
		super.onPostExecute(result);	
		
		try {
			
			if( this.listener != null && result != null && contentType != null){
				
				if( contentType.indexOf("json") == -1 ){
					 
					this.listener.mobDBFileResponse( contentType.substring(contentType.indexOf(";") + 1  ), result );
					
				}else{
					
					String jsonStr =   new String( result ) ;
					this.listener.mobDBResponse(jsonStr);	
					MobDBJSONHandler jsonParser = new MobDBJSONHandler(this.listener);
					jsonParser.parse(jsonStr);
					
				}
				
			}
			MobDB.requestCompleted(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected byte[] doInBackground(String... params) {


		try {
			
			HttpClient httpClient = null;
			String url = SDKConstants.URL_HTTP;

			if(secure){
				url = SDKConstants.URL_HTTPS;
				SchemeRegistry schemeRegistry = new SchemeRegistry();
				schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
				schemeRegistry.register(new Scheme("https", new EasySSLSocketFactory(), 443));
				HttpParams httpParams = new BasicHttpParams();
				httpParams.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, 30);
				httpParams.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, new ConnPerRouteBean(30));
				httpParams.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, false);
				HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
				ClientConnectionManager cm = new SingleClientConnManager(httpParams, schemeRegistry);
				httpClient = new DefaultHttpClient(cm, httpParams);
			}else{
				httpClient = new DefaultHttpClient();	
			}

			HttpProtocolParams.setUseExpectContinue( httpClient.getParams(), false ) ;			

			HttpPost httppost = new HttpPost(url);			

			// Per mobDB backend communication request specification, app needs to send content type, 
			// if content type is null, 114 Error code come as mobDB response
			httppost.setHeader( "Content-Type", SDKConstants.JSON_CONTENT );

			// create request entity
			StringEntity req_entity = new StringEntity(params[0]);

			httppost.setEntity( req_entity );

			BasicHttpResponse httpResponse = ( BasicHttpResponse ) httpClient .execute( httppost );										

			this.contentType = httpResponse.getEntity().getContentType().toString();

			byte[] responseData = readBytes( httpResponse.getEntity().getContent() );	

			//Log.i( "mobDB", imageBytesData.toString()  );

			return responseData;

		} catch( Exception e ) {

			e.printStackTrace();

			return null;
		}	

	}
	
	
	public byte[] readBytes(InputStream inputStream) throws IOException {
	
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		int len = 0;
		
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}

		return byteBuffer.toByteArray();
	}

}
