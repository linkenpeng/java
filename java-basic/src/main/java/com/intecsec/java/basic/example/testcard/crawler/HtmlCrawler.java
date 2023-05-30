package com.intecsec.java.basic.example.testcard.crawler;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlCrawler implements Runnable{
	
	private static String tempPath = "c:/temp/html";// 图片保存目录
	String url;
	
	public HtmlCrawler(String url)
	{
		this.url = url;
	}
		
	public void run() 
	{
		try 
		{
			String html = new HttpUtil().getHtml(url);//读取html
			List<String> urls = getUrl(html);//解析html中的url
			if(urls!=null&&urls.size()>0)
			{
				for (String url : urls) 
				{
					if(url.startsWith("http"))
					{						
						boolean result = Spider.getHtml(url);//存在可请求链接
						if(!result)
						{
							//超过请求下载的界限
							break;
						}
					}
				}				
			}
			String htmlPath = tempPath+"/"+System.currentTimeMillis() + (new Random().nextInt(1000)) +".html";//图片路径
			writeFile(html, htmlPath);
			System.out.println("url " + url + " is finished.");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 正则匹配获取html中的url集合
	 * @param html
	 * @return
	 */
	public List<String> getUrl(String html)
	{
		List<String> list = new ArrayList<>();
		Pattern pattern = Pattern.compile("<a(.+?)href=(.+?)( |>)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(html);
		while (matcher.find()) 
		{
			String url = matcher.group();
			url = url.replace("\"", "");
			url = url.substring(8,url.length()-1);
			list.add(url);
		}
		return list;
	}
	
	public void writeFile(String content, String path) {
		//try-resource 语句，自动关闭资源
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {
			bw.write(content);
			bw.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
