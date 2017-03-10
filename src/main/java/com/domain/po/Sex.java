package com.domain.po;

public enum Sex {
	
	MAN{public String getName(){return "男人";}},
	WOMAN{public String getName(){return "女人";}};
	public abstract String getName();

}
