package com.intecsec.java.basic.refelect.compile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class JavaCompilerTask {

	public static void main(String[] args) throws UnsupportedEncodingException {

		compileJavaFromString();
	}

	
	public static void compileJavaFromString() {
		
		StringBuilder sb = new StringBuilder();
		String className = "Hello";
		
		//sb.append("package com.test;\n");
		sb.append("public class Hello{\n");
		sb.append("public static void main(String[]args){\n");
		sb.append("System.out.print(\"hello world\"); \n");
		sb.append("}\n");
		sb.append("}");

		//将上述源码编译
		Class<?> c = compile(className, sb.toString());
		try {
			// 生成对象
			Object obj = c.newInstance();
			// 调用main方法
			Method m = c.getMethod("main", String[].class);
			m.invoke(obj, new Object[] { new String[] {} });
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Class<?> compile(String className, String javaCodes) {
		
		//将字符串包装为SimpleJavaFileObject对象
		JavaSourceFromString srcObject = new JavaSourceFromString(className, javaCodes);		
		System.out.println(srcObject.getCode());
		Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
		
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<JavaFileObject>();  
		
		//设置编译的输出目录，并包装在options中
		String flag = "-d";
		String outDir = "";
		try {
			File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
			outDir = classPath.getAbsolutePath() + File.separator;
			System.out.println(outDir);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}		
		Iterable<String> options = Arrays.asList(flag, outDir);
		
		//JavaCompiler.getTask方法：以future的任务形式(多线程)，来执行编译任务
		
		// 第一个参数：额外输出流，null表示默认使用system.err
		// 第二个参数：文件管理器，null表示编译器标准文件管理器
		// 第三个参数：诊断监听器，null表示使用编译器默认方法来报告诊断信息
		// 第四个参数：编译器参数，null表示无参数
		// 第五个参数：需要经过annotation处理的类名，null表示没有类需要annotation处理
		// 第六个参数：待编译的类
		
		JavaCompiler.CompilationTask task = 
				compiler.getTask(null, fileManager, diagnosticCollector, options, null, fileObjects);
		
		//等待编译结束
		boolean result = task.call();
		if (result == true) {
			try {
				return Class.forName(className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else			
		{
			//print the Diagnostic's information   
            for  (Diagnostic diagnostic : diagnosticCollector  
                    .getDiagnostics())  
            {  
                System.out.println("Error on line: "   
                        + diagnostic.getLineNumber() + "; URI: "   
                        + diagnostic.getSource().toString());  
            }  
		}
		return null;
	}
}

