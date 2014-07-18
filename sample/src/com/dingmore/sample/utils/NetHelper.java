package com.dingmore.sample.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class NetHelper {
	
	
	public static String getDataByHttpClient(String url) {
		System.out.println("mytesturl"+url);
		String strResult = "";
		try {
			// HttpGet连接对象
			HttpGet httpRequest = new HttpGet(url);
			// 取得HttpClient对象
			HttpClient httpClient = getNewHttpClient();
			// 请求HttpClient, 取得HttpResponse
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 取得返回的字符串
				strResult = EntityUtils.toString(httpResponse.getEntity(),
						"UTF-8");
				strResult = strResult.substring(strResult.indexOf("{"), 
						strResult.lastIndexOf("}") + 1);
			}
		} catch (Exception e) {
			Log.i("Err",e.toString());
		}
		return strResult;
	}
	public static HttpClient newHttpClient = null;
	public static HttpClient getNewHttpClient()
	{
		if(newHttpClient == null)
		{
			try {
				KeyStore trustStore  =  KeyStore.getInstance(KeyStore.getDefaultType());
				trustStore.load(null, null);
				SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);  
	            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
	    
	            HttpParams params = new BasicHttpParams();  
	            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);  
	            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);  
	    
	            SchemeRegistry registry = new SchemeRegistry();  
	            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));  
	            registry.register(new Scheme("https", sf, 443));  
	    
	            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);  
	    
	            newHttpClient = new DefaultHttpClient(ccm, params); 
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnrecoverableKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return newHttpClient;
	}
	public static String getResponByHttpClient(String url) {
		return getDataByHttpClient(url);
	}	
	
	public static String getResponByHttpClient(String url,String arg0) {
		url = String.format(url, arg0);
		return getDataByHttpClient(url);
	}
	public static String getResponByHttpClient(String url,String arg0,String arg1) {
		url = String.format(url, arg0,arg1);
		return getDataByHttpClient(url);
	}
	public static String getResponByHttpClient(String url,String arg0,String arg1,String arg2) {
		url = String.format(url, arg0,arg1,arg2);
		System.out.println("mytesturl"+url);
		return getDataByHttpClient(url);
	}
	
	public static String getResponByHttpClient(String url,String arg0,String arg1,String arg2,String arg3) {
		url = String.format(url, arg0,arg1,arg2,arg3);
		return getDataByHttpClient(url);
	}
	
	public static String getResponByHttpClient(String url,String arg0,String arg1,String arg2,String arg3,String agr4) {
		url = String.format(url, arg0,arg1,arg2,arg3,agr4);
		return getDataByHttpClient(url);
	}
	
	public static String getResponByHttpClient(String url,String arg0,String arg1,String arg2,String arg3,String agr4,String arg5) {
		url = String.format(url, arg0,arg1,arg2,arg3,agr4,arg5);
		return getDataByHttpClient(url);
	}
	
	public static String getResponByHttpClient(String url,String arg0,String arg1,String arg2,String arg3,String agr4,
			String arg5,String arg6,String arg7,String arg8,String arg9,String arg10) {
		url = String.format(url, arg0,arg1,arg2,arg3,agr4,arg5,arg6,arg7,arg8,arg9,arg10);
		return getDataByHttpClient(url);
	}
	
	/**
	 * 	上传图片
	 */
	public static String uploadPic(String str_url,Drawable drawable,String argJpg)
	{
		boolean status = false;
	    String end = "\r\n";
	    String twoHyphens = "--";
	    String boundary = "*****";
	    try{
	    	URL url =new URL(str_url);
	        HttpURLConnection con=(HttpURLConnection)url.openConnection();
	        /* 允许Input、Output，不使用Cache */
	        con.setDoInput(true);
	        con.setDoOutput(true);
	        con.setUseCaches(false);
	        /* 设置传送的method=POST */
	        con.setRequestMethod("POST");
	        /* setRequestProperty */
	        con.setRequestProperty("Connection", "Keep-Alive");
	        con.setRequestProperty("Charset", "UTF-8");
	        con.setRequestProperty("Content-Type",
	                           "multipart/form-data;boundary="+boundary);
	        /* 设置DataOutputStream */
	        DataOutputStream ds = 
	          new DataOutputStream(con.getOutputStream());
	        ds.writeBytes(twoHyphens + boundary + end);
	        ds.writeBytes("Content-Disposition: form-data; " +
	                      "name=\"" + argJpg  + "\";filename=\"upload.jpg\"" + end);
	        ds.writeBytes(end);   
	
	        /* 取得文件的FileInputStream */
	      //  FileInputStream fStream = new FileInputStream(drawable.);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
			ds.write(baos.toByteArray());
	        /* 设置每次写入1024bytes */
//	        int bufferSize = 1024;
//	        byte[] buffer = new byte[bufferSize];
//	
//	        int length = -1;
//	        /* 从文件读取数据至缓冲区 */
//	        while((length = fStream.read(buffer)) != -1)
//	        {
//	          /* 将资料写入DataOutputStream中 */
//	          ds.write(buffer, 0, length);
//	        }
	        ds.writeBytes(end);
	        ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
	
	        /* close streams */
	       // fStream.close();
	        ds.flush();
	
	        /* 取得Response内容 */
	        InputStream is = con.getInputStream();
	        int ch;
	        StringBuffer b =new StringBuffer();
	        while( ( ch = is.read() ) != -1 )
	        {
	          b.append( (char)ch );
	        }
	        /* 关闭DataOutputStream */
	        ds.close();
	       return b.toString(); 
      }
      catch(Exception e)
      {
    	  status = false;
    	  e.printStackTrace();
      }
     // return status;
		/*
		String MULTIPART_FORM_DATA ="multipart/form-data";
		try {
			URL url = new URL(str_url);
			 StringBuilder bitmapSB = new StringBuilder();
            // bitmapSB.append("--");
            // bitmapSB.append(BOUNDARY);
             bitmapSB.append("\r\n");
             bitmapSB.append("Content-Disposition:form-data;name=\"attach\";fileName=\"upload.jpg\"\r\n");
             bitmapSB.append("Content-Type:image/jpg\r\n\r\n");
             
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
			 byte[] pic_data = baos.toByteArray();
             byte[] end_data =("\r\n").getBytes();//数据结束标志
             
             StringBuilder emailSB = new StringBuilder();
             emailSB.append("\r\n");
            // emailSB.append("Content-Disposition:form-data;name=\"uid\";value=\""+UserMgr.getInstance().getInfo(UserMgr.PARAM_BET_UID)+"\"\r\n\r\n");
             emailSB.append("Content-Disposition:form-data;name=\"uid\"\r\n\r\n");
             emailSB.append(UserMgr.getInstance().getInfo(UserMgr.PARAM_BET_UID));
             emailSB.append("\r\n\r\n");
            // emailSB.append("Content-Disposition:form-data;name=\"uploadsubmit2\";value=\"true\"\r\n\r\n");
             emailSB.append("Content-Disposition:form-data;name=\"uploadsubmit2\"\r\n\r\n" + "\"true\"\r\n\r\n");
             
           //  emailSB.append("Content-Disposition:form-data;name=\"m_auth\";value=\""+UserMgr.getInstance().getInfo(UserMgr.PARAM_M_AUTH)+"\"\r\n\r\n");
             emailSB.append("Content-Disposition:form-data;name=\"m_auth\"\r\n\r\n"+UserMgr.getInstance().getInfo(UserMgr.PARAM_M_AUTH)+"\r\n\r\n");
             
            // emailSB.append("Content-Disposition:form-data;name=\"topicid\";value=\"0\"\r\n\r\n");
             emailSB.append("Content-Disposition:form-data;name=\"topicid\"\r\n\r\n0\r\n\r\n");
      //       emailSB.append("Content-Disposition:form-data;name=\"ac\";value=upload\r\n\r\n");
             emailSB.append("Content-Disposition:form-data;name=\"albumid\"\r\n\r\n0\r\n\r\n");
        //     emailSB.append("Content-Disposition:form-data;name=\"submit\";value=\"提交\"\r\n\r\n");
             
             emailSB.append("\r\n");
             
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);//忽略缓存
			connection.setRequestProperty("Content-Type",
                    "multipart/form-data");
			connection.setRequestProperty("Connection", "Keep-Alive");
			long contentLenght = emailSB.toString().getBytes().length +  end_data.length
            
	          + bitmapSB.toString().getBytes().length + pic_data.length ;
	          
	          connection.setRequestProperty("Content-Length", Long.toString(contentLenght));
	          
			
			//建立输出流，并写入数据
				//ByteArrayOutputStream baos = new ByteArrayOutputStream();
				//Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
				//bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
				//InputStream isBm = new ByteArrayInputStream(baos .toByteArray());
				
	          OutputStream outputStream = connection.getOutputStream();
	          
	          
	          outputStream.write(bitmapSB.toString().getBytes());
	          outputStream.write(pic_data);
	          outputStream.write(emailSB.toString().getBytes());
	          outputStream.write(end_data);
	          outputStream.flush();
	         // outputStream.close();
	          int cah = connection.getResponseCode();
	          InputStream is = connection.getInputStream();
              int ch;
              StringBuilder result = new StringBuilder();
              while((ch=is.read())!=-1){
              
            	  	result.append((char)ch);
              }
              System.out.println("result :" + result.toString());
             
	          
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return "";
	}
	/**
	 * 	Post 
	 */
	public static String uploadByPost(String str_url,Map<String, Object> list,String fileName)
	{
		boolean status = false;
	    String end = "\r\n";
	    String twoHyphens = "--";
	    String boundary = "*****";
	    try{
	    	URL url =new URL(str_url);
	        HttpURLConnection con=(HttpURLConnection)url.openConnection();
	        /* 允许Input、Output，不使用Cache */
	        con.setDoInput(true);
	        con.setDoOutput(true);
	        con.setUseCaches(false);
	        /* 设置传送的method=POST */
	        con.setRequestMethod("POST");
	        /* setRequestProperty */
	        con.setRequestProperty("Connection", "Keep-Alive");
	        //con .setRequestProperty("http.keepAlive", "false"); 
	        con.setRequestProperty("Charset", "UTF-8");
	        con.setRequestProperty("Content-Type",
	                           "multipart/form-data;boundary="+boundary);
	        /* 设置DataOutputStream */
	        DataOutputStream ds = 
	          new DataOutputStream(con.getOutputStream());
	        ds.writeBytes(twoHyphens + boundary + end);
	        int len = list.size();
	        	Set<String> key = list.keySet();
	        	String string;
	        	for (Iterator it = key.iterator(); it.hasNext();) {
	        		string = (String)it.next();
	        		if( string.equals(fileName) )
	        		{
	        			ds.writeBytes("\r\n--"+boundary+"\r\nContent-Disposition: form-data; name=\"pic\"; " +
	        					"filename=\"image.jpg\"\r\nContent-Type: image/jpeg\r\n\r\n");
	        			Drawable drawable = (Drawable) list.get(string);
	    				ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    				Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
	    				bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
	    	            ds.write(baos.toByteArray());
	        		}else {
	        			ds.writeBytes("Content-Disposition: form-data; " +
      			              "name=\"" + string  + "\";value=\"" + "(String)list.get(string)" + "\"" + end);
					}
	        		
	        		
	        	}
	        	
			
	       // ds.writeBytes("Content-Disposition: form-data; " +
	       //               "name=\"" + argJpg  + "\";filename=\"upload.jpg\"" + end);
	        ds.writeBytes(end);   
	
	        /* 取得文件的FileInputStream */
	      //  FileInputStream fStream = new FileInputStream(drawable.);
//	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
//			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
//			ds.write(baos.toByteArray());
	        /* 设置每次写入1024bytes */
//	        int bufferSize = 1024;
//	        byte[] buffer = new byte[bufferSize];
//	
//	        int length = -1;
//	        /* 从文件读取数据至缓冲区 */
//	        while((length = fStream.read(buffer)) != -1)
//	        {
//	          /* 将资料写入DataOutputStream中 */
//	          ds.write(buffer, 0, length);
//	        }
	        System.setProperty("http.keepAlive", "false"); 
	        ds.writeBytes(end);
	        ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
	
	        /* close streams */
	       // fStream.close();
	        ds.flush();
	
	        /* 取得Response内容 */
	        InputStream is = con.getInputStream();
	        int ch;
	        StringBuffer b =new StringBuffer();
	        while( ( ch = is.read() ) != -1 )
	        {
	          b.append( (char)ch );
	        }
	        /* 关闭DataOutputStream */
	        ds.close();
	       return b.toString(); 
      }
      catch(Exception e)
      {
    	  status = false;
    	  e.printStackTrace();
      }
		return "";
	}

	static class SSLSocketFactoryEx extends SSLSocketFactory {
        
        SSLContext sslContext = SSLContext.getInstance("TLS");
        
        public SSLSocketFactoryEx(KeyStore truststore) 
                        throws NoSuchAlgorithmException, KeyManagementException,
                        KeyStoreException, UnrecoverableKeyException {
                super(truststore);
                
                TrustManager tm = new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {return null;}  
    
            @Override  
            public void checkClientTrusted(
                            java.security.cert.X509Certificate[] chain, String authType)
                                            throws java.security.cert.CertificateException {}  
    
            @Override  
            public void checkServerTrusted(
                            java.security.cert.X509Certificate[] chain, String authType)
                                            throws java.security.cert.CertificateException {}
        };  
        sslContext.init(null, new TrustManager[] { tm }, null);  
    }  
    
    @Override  
    public Socket createSocket(Socket socket, String host, int port,boolean autoClose) throws IOException, UnknownHostException {  
            return sslContext.getSocketFactory().createSocket(socket, host, port,autoClose);  
    }  
    
    @Override  
    public Socket createSocket() throws IOException {  
        return sslContext.getSocketFactory().createSocket();  
    }  
}
	
	public static String getStringWithoutHtml(String src)
	{
		if (src == null || src.trim().equals("")) {
			return "";
		}
		String rep = src.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
		rep = rep.replaceAll("[(/>)<]", "");
		return rep;
	}
}
