package com.tarena.elts.service;

import java.util.List;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;

public interface ExamService {
	User login(int id,String pwd) throws IdOrPwdException;
	void setEntityContext(EntityContext entityContext);
	//添加开始考试的方法
	ExamInfo start() throws Exception;
	//添加一个获得考题的方法getQuestionInfo(int index）
	QuestionInfo getQuestionInfoByIndex(int index);
	//添加结束考试功能,返回考试分数
	int over();
	//返回分数的功能
	int getScore();
	//保存用户答案
	void saveUserAnswer(int questionIndex,List<Integer> userAnswer);
	//获得规则方法
	String getRule();
//	//获得考试时间方法
//	int getTime();
	
}
