package com.tarena.elts.service;

import java.util.List;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;

public interface ExamService {
	User login(int id,String pwd) throws IdOrPwdException;
	void setEntityContext(EntityContext entityContext);
	//添加开始考试的方法吧
	ExamInfo start();
	//添加一个获得考题的方法
	QuestionInfo getQuestionInfoByIndex(int index);
	//添加考试结束功能，返回考试分数
	int over();
	//返回分数功能
	int getScore();
	//保存用户答案
	void saveUserAnswer(int questionIndex,List<Integer>useranswer);
	//获得规则方法
	String getRule();
	
}
