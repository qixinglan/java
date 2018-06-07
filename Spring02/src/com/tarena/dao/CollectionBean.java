package com.tarena.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionBean {
	private List<Integer> age;
	private List<String> names;
	private Set<String> cities;
	private Map<String,Integer> map;
	private Properties cfg;
	private List<Date> dates;
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public Set<String> getCities() {
		return cities;
	}
	public void setCities(Set<String> cities) {
		this.cities = cities;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	public Properties getCfg() {
		return cfg;
	}
	public void setCfg(Properties cfg) {
		this.cfg = cfg;
	}
	public List<Date> getDates() {
		return dates;
	}
	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	@Override
	public String toString() {
		return "CollectionBean [age=" + age + ", names=" + names + ", citys="
				+ cities + ", map=" + map + ", cfg=" + cfg + ", dates=" + dates
				+ "]";
	}
	public List<Integer> getAge() {
		return age;
	}
	public void setAge(List<Integer> age) {
		this.age = age;
	}
	
}
