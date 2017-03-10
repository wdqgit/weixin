package com.domain.po;

import java.util.Date;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 活动表
 * */
@Entity
public class Activity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(length=200,nullable=false)
	private String title;
	@Column(length=10)
	private String author;
	@Column(length=11)
	private String phone;
	@Lob @Column(nullable=false)
	private String content;
	@Column(length=100,nullable=false)
	private String location;
	@Temporal(TemporalType.TIMESTAMP)
	private Date begin_time;
	@Temporal(TemporalType.TIMESTAMP)
	private Date end_time;
	@Column
	private Integer money;
	@Column
	private Integer peopleNum;
	@Enumerated(EnumType.STRING)
	@Column(length=10)
	private ActivityType run_type = ActivityType.RUN;
	
	
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH},
			fetch=FetchType.EAGER)
	@JoinTable(name="activity_people",
	joinColumns={@JoinColumn(name="activity_id")},
	inverseJoinColumns={@JoinColumn(name="people_id")})
	private Set<People> peoples = new HashSet<People>();
	
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}
	public ActivityType getRun_type() {
		return run_type;
	}
	public void setRun_type(ActivityType run_type) {
		this.run_type = run_type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Set<People> getPeoples() {
		return peoples;
	}
	public void setPeoples(Set<People> peoples) {
		this.peoples = peoples;
	}
	
	
	

}
