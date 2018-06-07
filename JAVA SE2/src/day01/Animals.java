package day01;

public class Animals {
	public void f()throws RuntimeException{
		throw new RuntimeException();
	}
}
class Dog extends Animals{
	public void f(){
		throw new RuntimeException();
	}
}

