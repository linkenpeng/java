package com.intecsec.java.basic.bytecode.agent;



import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


public class PreTransformer implements ClassFileTransformer {

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		System.out.println("loading class " + className);
		
		if(className.equals("com/intecsec/java/basic/bytecode/test/Greeting"))
		{
			ClassReader classReader = new ClassReader(classfileBuffer);
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ClassVisitor classVisitor = new MyClassVisitor(classWriter);
			classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
			return classWriter.toByteArray();
		}
		else {			
			return classfileBuffer;
		}
	}
}
