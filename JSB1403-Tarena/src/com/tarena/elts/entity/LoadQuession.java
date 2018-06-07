package com.tarena.elts.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tarena.elts.util.Config;

public class LoadQuession {
	private Config config;
	private List<Question>ques=new ArrayList<Question>();
	public LoadQuession(Config config ){
		this.config=config;
		loadCorejava(config.getString("QuestionFile"));
	}
	private void loadCorejava(String fileName) {
		// TODO Auto-generated method stub
		try {
			BufferedReader in=new BufferedReader(
					new InputStreamReader(new FileInputStream(fileName),"utf-8"));
			String line =null;
			while((line=in.readLine())!=null){
				line.trim();
				Question q=paseQuestion(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	private Question paseQuestion(String line) {
		// TODO Auto-generated method stub
		Question q=new Question();
		if(line.startsWith("@")){
			String[]str=line.split(",");
			if(str[0].length()>8){
				q.setType(1);
				List<Integer> answers=new ArrayList<Integer>();
				for(int i=9;i<str[0].length();i+=2){
					answers.add(((int)str[0].charAt(i))-48);
				}
				q.setAnswers(answers);
					
			}else{
				q.setType(0);
				List<Integer> answers=new ArrayList<Integer>();
				answers.add(((int)str[0].indexOf(9))-48);
				q.setAnswers(answers);
			}
			int i=((int)str[1].indexOf(6))-48;
			q.setLevel(i);
		}
		else if(line.matches(":")){
			q.setTitle(line);
		}else{
			List<String> options=new ArrayList<String>();
			options.add(line);
			while((options.size())>=4){
				q.setOptions(options);
			}
			
		}
		ques.add(q);
		return q;
	}
	public Collection<Question> getQuesstion(){
		return ques;
	}
	public static void main(String[] args) {
		Config config=new Config("client.properties");
		System.out.println(new LoadQuession(config).getQuesstion());
	}
}
