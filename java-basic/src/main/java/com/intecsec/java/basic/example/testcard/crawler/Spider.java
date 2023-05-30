package com.intecsec.java.basic.example.testcard.crawler;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;


public class Spider {

	static Set<String> urls = new CopyOnWriteArraySet<String>();
	static ThreadPoolExecutor pool=(ThreadPoolExecutor) Executors.newFixedThreadPool(4);
	static AtomicInteger count = new AtomicInteger(0);
	
	public static boolean getHtml(String url)
	{
		if(count.get()>=5)
		{
			//限制地址请求数量
			pool.shutdown(); //不再接受新的任务请求，会等待旧任务完成
			return false;
		}
		else if(urls.contains(url))
		{
			return false;//重复的地址不再请求
		}
		else 
		{
			count.incrementAndGet();
			urls.add(url);
			//对每个url，抓取静态html和image
	        pool.execute(new HtmlCrawler(url));
	        pool.execute(new ImageCrawler(url));
		}       
       
        return true;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Spider.getHtml("https://www.baidu.com/");
		
		while(true)
		{
			Thread.sleep(3000);
			int count = Spider.pool.getActiveCount();
			if(0 == count)
			{
				System.out.println("there is no tasks");
				break;
			}			
		}
	}
}

