package com.first.shop.dto;

import java.util.Date;

public class User {
	
	private String id;
	private String password;
	private String name;
	private int age;
	private int sex;
	private Date birth;
	private String address;
	private String email;
	private Date regdate;
	private int admincheck;
	private int point;
	private int money;
	private String tel;
	private String color;
	private int snscheck;
	
	public int getSnscheck() {
		return snscheck;
	}
	public void setSnscheck(int snscheck) {
		this.snscheck = snscheck;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getAdmincheck() {
		return admincheck;
	}
	public void setAdmincheck(int admincheck) {
		this.admincheck = admincheck;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", age=" + age + ", sex=" + sex
				+ ", birth=" + birth + ", address=" + address + ", email=" + email + ", regdate=" + regdate
				+ ", admincheck=" + admincheck + ", point=" + point + ", money=" + money + ", tel=" + tel + ", color="
				+ color + ", snscheck=" + snscheck + "]";
	}
	
	
	
	
	
}
