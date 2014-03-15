package com.school.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;


public class HttpClientUtils {
	private static PoolingHttpClientConnectionManager cm = null;
	private static HttpClientBuilder httpBulder;
	static {
		cm = new PoolingHttpClientConnectionManager();
		httpBulder = HttpClients.custom();
		cm.setMaxTotal(HttpPool.MAXCONNECTION);
		cm.setDefaultMaxPerRoute(HttpPool.DEFAULTMAXCONNECTION);
		/*HttpHost target = new HttpHost(HttpPool.IP, HttpPool.PORT);
	    cm.setMaxPerRoute(new HttpRoute(target), 20);*/
		httpBulder.setConnectionManager(cm);
	}
	public static CloseableHttpClient getConnection(){

			CloseableHttpClient  httpClient = httpBulder.build();
			//HttpClient httpClient = httpBulder.build();

		return httpClient;
	}
	
	public static  void postMethod(Map<String,String> map,String url,HttpContext context,RequestCallInterface rci) throws Exception
	{
		if(context==null)
		{
			context = new BasicHttpContext();
		}
		CloseableHttpResponse response = null;
		try
		{
			HttpUriRequest request = getRequest(map, url, rci,"post");
			response  = getConnection().execute(request,context);
			HttpEntity httpEntity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));  
			StringBuffer stringBuffer = new StringBuffer();
			String line;  
			while((line = br.readLine()) != null){  
				stringBuffer.append(line);
			}
			String html = stringBuffer.toString();
			rci.success(html,response);
		}catch(Exception e)
		{
			throw e;
		}finally{
			response.close();
		}
	}
	public static HttpContext postMethod(Map<String,String> map,String url,String charset,HttpContext context,RequestCallInterface rci) throws IOException
	{
		if(context==null)
		{
			context = new BasicHttpContext();
		}
		CloseableHttpResponse response = null;
		try
		{
			HttpUriRequest request = getRequest(map, url, rci,"post");
			response  = getConnection().execute(request,context);
			HttpEntity httpEntity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), charset));  
			StringBuffer stringBuffer = new StringBuffer();
			String line;  
			while((line = br.readLine()) != null){  
				stringBuffer.append(line);
			}
			String html = stringBuffer.toString();
			rci.success(html,response);
		}catch(Exception e)
		{
			throw e;
		}finally{
			response.close();
			return context;
		}
	}
	/**
	 * <p>用来执行get方法，通过回调函数获取返回参数</p>
	 * @param map <p>用来存放传到服务器的key 和value</p>
	 * @param url <p >请求的地址</p>
	 * @param charset <p>响应编码</p>
	 * @param context <p >请求的上下文对象，用来保持session会话</p>
	 * @param rci <p >请求结果回调对象</p>
	 * */
	public static HttpContext getMethod(Map<String,String> map,String url,String charset,HttpContext context,RequestCallInterface rci) throws Exception 
	{
		if(context==null)
		{
			context = new BasicHttpContext();
		}
		CloseableHttpResponse response = null;
		try
		{
			HttpUriRequest request = getRequest(map, url, rci,"get");
			response  = getConnection().execute(request,context);
			HttpEntity httpEntity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), charset));  
			StringBuffer stringBuffer = new StringBuffer();
			String line;  
			while((line = br.readLine()) != null){  
				stringBuffer.append(line);
			}
			String html = stringBuffer.toString();
			rci.success(html,response);
		}catch(Exception e)
		{
			throw e;
		}finally{
			response.close();
			return context;
		}
		
	    
	}
	public static void getMethod(Map<String,String> map,String url,HttpContext context,RequestCallInterface rci) throws Exception 
	{
		if(context==null)
		{
			context = new BasicHttpContext();
		}
		CloseableHttpResponse response = null;
		try
		{
			HttpUriRequest request = getRequest(map, url, rci,"get");
			response  = getConnection().execute(request,context);
			HttpEntity httpEntity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));  
			StringBuffer stringBuffer = new StringBuffer();
			String line;  
			while((line = br.readLine()) != null){  
				stringBuffer.append(line);
			}
			String html = stringBuffer.toString();
			rci.success(html,response);
		}catch(Exception e)
		{
			throw e;
		}finally{
			response.close();
		}
		
		
		
	    
	}
	private static HttpUriRequest getRequest(Map<String,String> map,String url,RequestCallInterface rci,String method)
	{
		NameValuePair[] nvp = new BasicNameValuePair[map.size()];
		 Set<String> set = map.keySet();
		 int i = 0;
		 for(String key : set)
		 {
			 String value = map.get(key);
			 nvp[i] = new BasicNameValuePair(key, value);
			 i++;
		 }
		 HttpUriRequest login = null;
		 if("post".equals(method))
		 {
			 login = RequestBuilder.post().setUri(url).addParameters(nvp).build();
		 }
		 else if("get".equals(method))
		 {
			 login = RequestBuilder.get().setUri(url).addParameters(nvp).build();
		 }
		
		
		return login;
	}
}
