package com.pss.po;

import java.io.Serializable;

public class SellDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private String sid;
	private String pname;
	private String ename;
	private int amount;
	private String selldate;
	
	public SellDetail() {
		super();
	}

	public SellDetail(String sid, String pname, String ename, int amount, String selldate) {
		super();
		this.sid = sid;
		this.pname = pname;
		this.ename = ename;
		this.amount = amount;
		this.selldate = selldate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((ename == null) ? 0 : ename.hashCode());
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		result = prime * result + ((selldate == null) ? 0 : selldate.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
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
		SellDetail other = (SellDetail) obj;
		if (amount != other.amount)
			return false;
		if (ename == null) {
			if (other.ename != null)
				return false;
		} else if (!ename.equals(other.ename))
			return false;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
			return false;
		if (selldate == null) {
			if (other.selldate != null)
				return false;
		} else if (!selldate.equals(other.selldate))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SellDetail [sid=" + sid + ", pname=" + pname + ", ename=" + ename + ", amount=" + amount + ", selldate="
				+ selldate + "]";
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getSelldate() {
		return selldate;
	}
	public void setSelldate(String selldate) {
		this.selldate = selldate;
	}

}
