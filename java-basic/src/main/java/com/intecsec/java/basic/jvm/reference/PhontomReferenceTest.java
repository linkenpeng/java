package com.intecsec.java.basic.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhontomReferenceTest {

	public static void main(String[] args) {
		ReferenceQueue<StringBuilder> queue = new ReferenceQueue<StringBuilder>();
		Thread t = new CheckReferenceQueue(queue);
		t.setDaemon(true);
		t.start();
		
		StringBuilder s1 = new StringBuilder();
		for(int i=0;i<100000;i++)
		{
			s1.append("0000000000");
		}		
		
		PhantomReference<StringBuilder> s2 = new PhantomReference<StringBuilder>(s1,queue);
		s1 = null;
		//虚引用随时可能被回收,甚至没有被GC
		System.out.println(s2.get());
		
		System.gc();
		
		System.out.println(s2.get()); //null
		
		byte[] b = new byte[(int)(1024*1024*3.5)];	
		
	}

}

class CheckReferenceQueue extends Thread
{
	ReferenceQueue<StringBuilder> queue = null;
	
	public CheckReferenceQueue(ReferenceQueue<StringBuilder> queue)
	{
		this.queue = queue;
	}
	
	public void run()
	{
		while(true)
		{
			if(queue != null)
			{
				PhantomReference<StringBuilder> obj = null;
				try
				{
					obj = (PhantomReference<StringBuilder>) queue.remove();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				if(obj != null)
				{
					System.out.println("obj is deleted");
				}
			}
		}
	}
}
