package com.tarena.test;

public class Dispatcher {

	public static void execute() {
		try {
			String result = new CglibController().find();
			System.out.println("@AfterReturning");
		} catch (Exception e) {
			System.out.println("@AfterThrowing");
		}
		
		// ����result��ת����ҳ��
	}

	public static void main(String[] args) {
		execute();
	}

}
