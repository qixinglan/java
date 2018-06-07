package com.tarena.elts.service;

import java.util.List;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;

public interface ExamService {
	User login(int id,String pwd) throws IdOrPwdException;
	void setEntityContext(EntityContext entityContext);
	//��ӿ�ʼ���Եķ�����
	ExamInfo start();
	//���һ����ÿ���ķ���
	QuestionInfo getQuestionInfoByIndex(int index);
	//��ӿ��Խ������ܣ����ؿ��Է���
	int over();
	//���ط�������
	int getScore();
	//�����û���
	void saveUserAnswer(int questionIndex,List<Integer>useranswer);
	//��ù��򷽷�
	String getRule();
	
}
