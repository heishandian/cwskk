package com.yaoli.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FormatSqlUtil {
	public static void main(String[] args) throws IOException {

	}
	
	public void formatSql() throws IOException{
		File f=new File("C:\\Users\\will\\Desktop\\新建文本文档.txt");
		BufferedReader read=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String s="";
		String content="";
		while((s=read.readLine())!=null){
			s = s.replaceAll("^", "\"");
			s = s + "\t\"+";
			content+=s+"\r\n";
		}
		content = content + "\"\t\";";
		System.out.println(content);
	}
	
	public void deFormatSql() throws IOException{
		File f=new File("C:\\Users\\will\\Desktop\\新建文本文档.txt");
		BufferedReader read=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String s="";
		String content="";
		while((s=read.readLine())!=null){
			s = s.replaceAll("^\"\\.?", "\t");
			s = s.replaceAll("\\.?\"\\+", "");
			content+=s+"\r\n";
		}
		content = content + "\"\t\";";
		System.out.println(content);
	}
}
