package org.entity;

import java.util.Objects;

import javax.persistence.Entity;
@Entity
public class Animal extends Client {

private Integer id;
private String tip;

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getTip() {
	return tip;
}
public void setTip(String tip) {
	this.tip = tip;
}
public Animal(Integer id, String nume, Integer nrAnimale, String tipAnimal, Integer id2, String tip) {
	super(id, nume, nrAnimale, tipAnimal);
	id = id2;
	this.tip = tip;
}
public Animal(Integer id, String nume, Integer nrAnimale, String tipAnimal) {
	super(id, nume, nrAnimale, tipAnimal);
}
public Animal() {
	super();
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(id, tip);
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	Animal other = (Animal) obj;
	return Objects.equals(id, other.id) && Objects.equals(tip, other.tip);
}
@Override
public String toString() {
	return "Animal [id=" + id + ", tip=" + tip + "]";
}


}


