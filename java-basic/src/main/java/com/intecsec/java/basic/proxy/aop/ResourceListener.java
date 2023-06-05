package com.intecsec.java.basic.proxy.aop;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResourceListener {

	private static ExecutorService fixedThreadPool=Executors.newCachedThreadPool();
    private WatchService ws;
    private String listenerPath;
    private ResourceListener(String path){
        try
        {
            ws=FileSystems.getDefault().newWatchService();
            this.listenerPath=path;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void start(){
        fixedThreadPool.execute(new Listener(ws, listenerPath));
    }
    
    public static void addListener(String path){
    	try{
    		ResourceListener resourceListener=new ResourceListener(path);
            Path p=Paths.get(path);
            p.register(resourceListener.ws, StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE);
            resourceListener.start();
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    
    class Listener implements Runnable{

    	WatchService ws;
    	String listenerPath;
    	
    	Listener(WatchService ws,String listenerPath){
    		this.ws = ws;
    		this.listenerPath = listenerPath;
    	}
    	
		@Override
		public void run() {
			try {  
                while(true){  
                    WatchKey watchKey = ws.take();  
                    List<WatchEvent<?>> watchEvents = watchKey.pollEvents();  
                    for(WatchEvent<?> event : watchEvents){  
                    	String context = event.context().toString();//对象
                    	String kind = event.kind().toString();//变更类型
                    	if(context.equals("aops.xml")){
                    		if(kind.equals("ENTRY_MODIFY")){
                    			XmlReader.readXml(listenerPath+"/"+event.context());
                    		}
                    	}
                    }  
                    watchKey.reset();  
                }  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
                try {
                    ws.close();
                } catch (Exception e1) {
                }
            }
		}
    	
    }
}
