package com.sy.s1.util;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class FilePathGenerator {
//파일을 저장한 실제 경로 가져오기

	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private ServletContext servletContext;
	
	//1번이나 2번 추천
	
	//static/upload/notice
	//static/upload/qna 	< 이런식으로 해당 페이지에서 나온 애들끼리 모아놓기 
	public File getUserResouceLoader(String path)throws Exception{
		//ResourceLoader
		//classes 경로를 받아오기 위해 사용
		//생성하려는 디렉토리가 없으면 에러 발생
		//Default로 만들어진 static를 이용해서 File객체를 생성
		//하위 디렉토리를 Child로 사용해 디렉토리(폴더) 생성
		
		String defaultPath="classpath:/static/";
		File file = resourceLoader.getResource(defaultPath).getFile();
		
		file = new File(file, path);
		
		if(!file.exists()) {
			file.mkdirs();
			
		}
		System.out.println(file.getAbsolutePath());
		
		return file;
	}
	
	public File getUseClassPathResource(String path)throws Exception{
		//ClassPathResource
		//classes 경로를 받아 오기 위해 사용
		//ResourceLoader와 같지만
		//시작 경로에 classpath를 안쓴다.  (String defaultPath="classpath:/static/"; < 이거 안씀)
		//개발자가 직접 객체 생성
		
		String defaultPath="static";
		
		ClassPathResource classPathResource = new ClassPathResource(defaultPath);
		
		File file = classPathResource.getFile();
		
		file = new File(file, path);
		
		if(!file.exists()) {//해당파일이 없으면
		
			file.mkdirs();//파일을 만든다.
		}
		System.out.println(file.getAbsolutePath());
		return file;
	}

	//폴더에 저장된 이미지들은 maven clean 하면 사라진다.
	
	//예전부터 쓰던 방식
	public File getUseServletContext(String path)throws Exception{
		//Servlet Context
		//classpath가 아니라 webapp 하위에 만들어짐
		//생성할 디렉토리가 static이라면
		//webapp 하위에 static 폴더가 하나 더 만들어 진다.
		
		path = servletContext.getRealPath(path);
		
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		System.out.println(path);
		return file;
	}
	
}

