package com.tarena.test;

public class CglibController extends FooController {

	@Override
	public String find() {
		Object result = null;
		try {
			System.out.println("@Around before");
			System.out.println("@Before");
			result = super.find();
			System.out.println("@Around after");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println("@After");
		}
		return (String) result;
	}

}
