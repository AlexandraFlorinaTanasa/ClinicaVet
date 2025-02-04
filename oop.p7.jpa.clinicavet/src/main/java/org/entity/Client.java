package org.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
@Entity
public class Client {
	@Id
	
 @GeneratedValue(strategy = AUTO)
private Integer id;
 private String nume;
 private Integer nrAnimale;
 private String tipAnimal;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getNume() {
	return nume;
}
public void setNume(String nume) {
	this.nume = nume;
}
public Integer getNrAnimale() {
	return nrAnimale;
}
public void setNrAnimale(Integer nrAnimale) {
	this.nrAnimale = nrAnimale;
}
public String getTipAnimal() {
	return tipAnimal;
}
public void setTipAnimal(String tipAnimal) {
	this.tipAnimal = tipAnimal;
}
public Client(Integer id, String nume, Integer nrAnimale, String tipAnimal) {
	super();
	this.id = id;
	this.nume = nume;
	this.nrAnimale = nrAnimale;
	this.tipAnimal = tipAnimal;
}
public Client() {
	super();
}
@Override
public int hashCode() {
	return Objects.hash(id, nrAnimale, nume, tipAnimal);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Client other = (Client) obj;
	return Objects.equals(id, other.id) && Objects.equals(nrAnimale, other.nrAnimale)
			&& Objects.equals(nume, other.nume) && Objects.equals(tipAnimal, other.tipAnimal);
}
@Override
public String toString() {
	return "Client [id=" + id + ", nume=" + nume + ", nrAnimale=" + nrAnimale + ", tipAnimal=" + tipAnimal + "]";
}

}
