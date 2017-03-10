package com.domain.po;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class People {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(length=20)
	private String name;
	@Enumerated(EnumType.STRING)
	@Column(length=10)
	private Sex sex;
	@Column(length=100)
	private String address;
	
	
	@Column(length=20,nullable=false)
	private String phone;
	@ManyToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinTable(name="activity_people",
	joinColumns={@JoinColumn(name="people_id")},
	inverseJoinColumns={@JoinColumn(name="activity_id")})
	private Set<Activity> activitys = new HashSet<Activity>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Set<Activity> getActivitys() {
		return activitys;
	}
	public void setActivitys(Set<Activity> activitys) {
		this.activitys = activitys;
	}
	
}
