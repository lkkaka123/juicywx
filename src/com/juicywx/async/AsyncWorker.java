package com.juicywx.async;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncWorker {

	private static final BlockingQueue<AsyncTask> queue = new LinkedBlockingQueue<AsyncTask>();

	private volatile Thread thread;

	private Object lock = new Object();

	public void add(AsyncRequestTask task) {
		queue.add(task);
		synchronized (lock) {
			if (thread == null) {
				workInitialized();
			}
		}
	}

	private void workInitialized() {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				AsyncTask task;
				while ((task = queue.poll()) != null) {
					task.doTask();
				}
				synchronized(lock){
					if(true==queue.isEmpty()){
						thread=null;
						System.out.println("finish");
					}
				}
			}
		});
		thread.start();
	}
}
