package com.juicywx.async;

import java.io.InputStream;

import com.juicywx.http.HttpClient;

public class AsyncRequestTask implements AsyncTask {
	public enum Method{post,get};
	private Method _method;
	private String _url;
	private String _data;
	private RequestComplete mComplete;
	public AsyncRequestTask(Method m, String url, String data,
			final RequestComplete complete) throws NullPointerException {
		_method = m;
		_url = url;
		_data = data;
		mComplete=complete;
	}
	
//	private Runnable RCFactory(RequestComplete complete){
//		 return new RequestCompleteRunnable(mInput,complete);
//	}
//	
//	private class RequestCompleteRunnable implements Runnable{
//		private InputStream nInput;
//		private RequestComplete nComplete;
//		public RequestCompleteRunnable(InputStream input,RequestComplete complete){
//			nInput=input;
//			nComplete=complete;
//		}
//		@Override
//		public void run() {
//			nComplete.requestComplete(nInput);
//		}
//		
//	}
	
	private String httpGet(){
		return HttpClient.httpGet(_url);
	}
	
	private String httpPost(){
		return HttpClient.httpPost(_url, _data);
	}
	

	@Override
	public void doTask() {
		if(_method==Method.get){
			String getStr = httpGet();
			mComplete.requestComplete(getStr);
		}else{
			String getStr = httpPost();
			mComplete.requestComplete(getStr);
		}
	}
}
