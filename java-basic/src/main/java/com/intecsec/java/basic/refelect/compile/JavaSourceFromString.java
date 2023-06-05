package com.intecsec.java.basic.refelect.compile;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * A file object used to represent source coming from a string.
 * 这个类来自于chm帮助
  */

public class JavaSourceFromString extends SimpleJavaFileObject {
	private String code;

	public JavaSourceFromString(String name, String code) {
		super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
		this.code = code;
	}

	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
	public String getCode()
	{
		return code;
	}
}