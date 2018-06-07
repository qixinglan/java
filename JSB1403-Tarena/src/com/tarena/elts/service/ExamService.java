package com.tarena.elts.service;

import java.util.List;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;

public interface ExamService {
	User login(int id,String pwd) throws IdOrPwdException;
	void setEntityContext(EntityContext entityContext);
	//��ӿ�ʼ���Եķ���
	ExamInfo start() throws Exception;
	//���һ����ÿ���ķ���getQuestionInfo(int index��
	QuestionInfo getQuestionInfoByIndex(int index);
	//��ӽ������Թ���,���ؿ��Է���
	int over();
	//���ط����Ĺ���
	int getScore();
	//�����û���
	void saveUserAnswer(int questionIndex,List<Integer> userAnswer);
	//��ù��򷽷�
	String getRule();
//	//��ÿ���ʱ�䷽��
//	int getTime();
	
}
