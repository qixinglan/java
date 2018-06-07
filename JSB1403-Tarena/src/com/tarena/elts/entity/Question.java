package com.tarena.elts.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable{
	public static final  int LEVEL_ONE=1;
	public static final  int LEVEL_TWO=2;
	public static final  int LEVEL_THREE=3;
	public static final  int LEVEL_FOUR=4;
	public static final  int LEVEL_FIVE=5;
	public static final  int LEVEL_SIX=6;
	public static final  int LEVEL_SEVEN=7;
	public static final  int LEVEL_EIGHT=8;
	public static final  int LEVEL_NINE=9;
	public static final  int LEVEL_TEN=10;
	public static final  int SINGLE_SELECTION=0;//单选
	public static final  int MULTI_SELECTION=1;
	
	private List<Integer> answers=new ArrayList<Integer>();//答案
	private int level;//难度级别
	private String title;//题干
	private List<String> options=new ArrayList<String>();//选项
	private int id;//题号
	private int type;//单多选
	
	public Question() {
		super();
	}
	
	
	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append(title);
		sb.append("\n");
		for(int i=0;i<options.size();i++){
			//String[] a=new String[]{"A","B","C","D"};
			sb.append((char)('A'+i)).append(".").append(options.get(i)).append("\n");
		}
		sb.append("正确答案:");
		for(int i=0;i<answers.size();i++){
			sb.append((char)(answers.get(i)+'A')).append("");
		}
		sb.append("难度级别:"+level);
		sb.append("\n");
		return sb.toString();
	}


	public List<String> getOptions() {
		return options;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Integer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Integer> answers) {
		this.answers = answers;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
