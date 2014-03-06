package com.juicywx.async;



public class AsyncRequestTest {
	public static void main(String[] args){
		System.out.println("do");
		test();
		
	}
	public static void test() {
		AsyncRequest asyncRequest = AsyncRequest.getInstance();
		RequestComplete complete = new RequestComplete() {
			@Override
			public void requestComplete(String str) {
				System.out.println(str);
			}
		};
		AsyncRequestTask task =new AsyncRequestTask(AsyncRequestTask.Method.get, "http://www.youdao.com/", "", complete);asyncRequest.add(task);
		 task =new AsyncRequestTask(AsyncRequestTask.Method.get, "http://mine.juicywx.com/", "", complete);asyncRequest.add(task);
		 task =new AsyncRequestTask(AsyncRequestTask.Method.get, "http://www.baidu.com/", "", complete);asyncRequest.add(task);
		
		
	}

}
