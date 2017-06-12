package com.getfos.vicarium.model.external;

public class PoliticExternal {
	private ObjectJson identifier;
	private ObjectJson name;
	private ObjectJson birthday;
	private ObjectJson birthplace;
	private ObjectJson politicalList;
	private ObjectJson politicalGroup;
	private ObjectJson lastUpdateDate;
	private ObjectJson surname;
	private ObjectJson sex;
	private ObjectJson qualification;
	
	public PoliticExternal() {
	}

	public ObjectJson getIdentifier() {
		return identifier;
	}

	public void setIdentifier(ObjectJson identifier) {
		this.identifier = identifier;
	}

	public ObjectJson getName() {
		return name;
	}

	public void setName(ObjectJson name) {
		this.name = name;
	}

	public ObjectJson getBirthday() {
		return birthday;
	}

	public void setBirthday(ObjectJson birthday) {
		this.birthday = birthday;
	}

	public ObjectJson getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(ObjectJson birthplace) {
		this.birthplace = birthplace;
	}

	public ObjectJson getPoliticalList() {
		return politicalList;
	}

	public void setPoliticalList(ObjectJson politicalList) {
		this.politicalList = politicalList;
	}

	public ObjectJson getPoliticalGroup() {
		return politicalGroup;
	}

	public void setPoliticalGroup(ObjectJson politicalGroup) {
		this.politicalGroup = politicalGroup;
	}

	public ObjectJson getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(ObjectJson lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public ObjectJson getSurname() {
		return surname;
	}

	public void setSurname(ObjectJson surname) {
		this.surname = surname;
	}

	public ObjectJson getSex() {
		return sex;
	}

	public void setSex(ObjectJson sex) {
		this.sex = sex;
	}

	public ObjectJson getQualification() {
		return qualification;
	}

	public void setQualification(ObjectJson qualification) {
		this.qualification = qualification;
	}

	@Override
	public String toString() {
		return "PoliticExternal [identifier=" + identifier + ", name=" + name + ", birthday=" + birthday
				+ ", birthplace=" + birthplace + ", politicalList=" + politicalList + ", politicalGroup="
				+ politicalGroup + ", lastUpdateDate=" + lastUpdateDate + ", surname=" + surname + ", sex=" + sex
				+ ", qualification=" + qualification + "]";
	}


}
