package com.tarena.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Food {
	Egg egg;
	@Autowired
	Tomato tomato;
	public Egg getEgg() {
		return egg;
	}
	@Resource(name="egg2")
	public void setEgg(Egg egg) {
		this.egg = egg;
		System.out.println("setEgg");
	}

	public Tomato getTomato() {
		return tomato;
	}

	public void setTomato1(Tomato tomato) {
		this.tomato = tomato;
	}

	public String toString(){
		return egg+""+tomato;
	}
}
