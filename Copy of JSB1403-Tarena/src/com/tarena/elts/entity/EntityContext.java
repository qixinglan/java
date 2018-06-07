package com.tarena.elts.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tarena.elts.util.Config;

/**
 * ���ݹ���  ʵ���������ݿ�֮��   ֵ�������Ŀ֮�� �����Ĺ�ϵ
 * @author asus
 *����洢������
 */
public class EntityContext {
	private Config config;
	//�û���ʵ�����ϣ�Map���ϣ�ID=�û���
	private HashMap<Integer,User> users=new HashMap();
	//���п����ʵ������
	private List<Question> questions=new ArrayList<Question>();
	public EntityContext(Config config){
		this.config=config;
		//�����û�
		loadUsers(config.getString("UserFile"));
		//���ؿ���
		loadQuestions(config.getString("QuestionFile"));
	}
	//���ؿ���
	private void loadQuestions(String fileName){
		try {
			BufferedReader in=new BufferedReader(
					new InputStreamReader(new FileInputStream(fileName),"utf-8"));
			String line =null;
			while((line=in.readLine())!=null){
				if(line.startsWith("#")||line.equals("")){
					continue;
				}
			//�������� ת��ΪQuestion
			Question q=paseQuestion(line,in);
			//��Question������question
			questions.add(q);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private Question paseQuestion(String line, BufferedReader in) throws IOException {
		// TODO Auto-generated method stub
		//��line����ת��Ϊ��ȷ�𰸺��Ѷ�
		String[] temp=line.split("[@,][a-z]+=");
		String[] i=temp[1].split("/");
		List<Integer> answer=new ArrayList<Integer>();
		for(int j=0;j<i.length;j++){
			answer.add(Integer.parseInt(i[j]));
		}
		//�ڶ�ȡһ�У�ת��Ϊ���
		String title=in.readLine();
		//�ڶ�ȡ����ת��Ϊѡ���
		List<String> options=new ArrayList<String>();
		options.add(in.readLine());
		options.add(in.readLine());
		options.add(in.readLine());
		options.add(in.readLine());
		//����Question����
		Question q=new Question();
		q.setLevel(Integer.parseInt(temp[2]));
		q.setAnswer(answer);
		q.setTitle(title);
		q.setOptions(options);
		return q;
	}
	//�����û�   �������ļ������û�
	//�����ļ����ҵ��ļ�
	//�����ļ������������뵽ϵͳ��
	//��ȡÿһ������
	//��������
	//�������ݴ���User
	//��User��users
	private void loadUsers(String fileName){
		try {
			BufferedReader in=new BufferedReader(
					new InputStreamReader(new FileInputStream(fileName),"utf-8"));
			String line=null;
			while((line=in.readLine())!=null){
				line=line.trim();//ȥ���������ߵĿո�
				if(line.startsWith("#")||line.equals("")){
					continue;//���Կ��к�ע��
				}
				//����line��ת��ΪUsers
				User u=paseUser(line);
				//
				users.put(u.getId(), u);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private User paseUser(String line) {
		String[] data=line.split(":");
		//
		User user=new User();
		user.setId(Integer.parseInt(data[0]));
		user.setName(data[1]);
		user.setPassword(data[2]);
		user.setPhonenumber(data[3]);
		user.setEmail(data[4]);
		return user;
	}
	//����ID����һ��user
	public User getUserById(int id){
		return users.get(id);
	}
	public List<Question> getQuestions() {
		return questions;
	}
	//��ȡ������Ϣ��һϵ������
	public String getExamTitle(){
		return config.getString("ExamTitle");
	}
	public int gettotalScore(){
		return config.getInt("TotalScore");
	}
	public int getTimeLimit(){
		return config.getInt("TimeLimit");
	}
	public int getQuestionCount(){
		return config.getInt("QuestionCount");
	}
	public String getRule(){
		return config.getString("ExamRule");
	}
}

