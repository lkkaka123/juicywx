package com.juicywx.async;

public class AsyncRequest extends AsyncWorker{
	private static class Internal {
		private static final AsyncRequest instance = new AsyncRequest();
	}
	
	private AsyncRequest() {}

	public static final AsyncRequest getInstance() {
		return Internal.instance;
	}
	
}