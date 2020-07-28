package com.pss.po;

import java.io.Serializable;

public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	private int eid;//销售员编号
	private String name;//销售员姓名
	private String sex;//销售员性别
	private String birthday;//销售员出生日期
	private String phone;//销售员电话号码
	private int state;//销售员状态
	
	public Employee() {
		super();
	}
	public Employee(int eid, String name, String sex, String birthday, String phone, int state) {
		super();
		this.eid = eid;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.phone = phone;
		this.state = state;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + eid;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + state;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (eid != other.eid)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (state != other.state)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", phone="
				+ phone + ", state=" + state + "]";
	}
}
