package com.tarena.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class HttpRequester{
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpclient=HttpClients.createDefault();
		URI uri=new URIBuilder()
		.setScheme("http")
		.setHost("www.goole.com")
		.setPath("/search")
		.setParameter("q","httpclient")
		.build();
		HttpGet httpGet=new HttpGet(uri);
		CloseableHttpResponse response=httpclient.execute(httpGet);
		System.out.println(response);
		System.out.println(httpGet.getURI());
		System.out.println(response.getProtocolVersion());
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
	}
}