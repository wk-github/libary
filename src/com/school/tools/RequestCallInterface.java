package com.school.tools;

import org.apache.http.client.methods.CloseableHttpResponse;

public interface RequestCallInterface {
	public void success(String msg,CloseableHttpResponse response);

}
