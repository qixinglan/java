package day01;

import java.io.Serializable;

public class Food implements Serializable{
Egg egg;

@Override
public String toString() {
	return "��������"+egg;
}

public Egg getEgg() {
	return egg;
}

public void setEgg(Egg egg) {
	this.egg = egg;
}

}
