package org.tyaa.spring.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tyaa.spring.aop.objects.FileManager;
import org.tyaa.spring.aop.objects.FileManager2;

public class Start {

	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext("org.tyaa.spring.*");
		FileManager fileManager = (FileManager) context.getBean("fileManager");
		// FileManager2 fileManager2 = (FileManager2) context.getBean("fileManager2");
		
		// fileManager.getExtensionCount("/home/yurii/Документы/книги");
		//fileManager.getExtensionCount("c:\\Windows\\");
		//fileManager.getExtensionCount("c:\\Windows\\system32\\drivers");
		//fileManager.getExtensionList("c:\\Windows\\system32\\drivers");
		// fileManager2.getExtensionCount("c:\\Windows");
		fileManager.getExtensionList("c:\\\\Windows\\\\system32\\\\drivers");
		//fileManager.getExtensionCount("c:\\\\Windows\\\\system32\\\\drivers");
	}
}
