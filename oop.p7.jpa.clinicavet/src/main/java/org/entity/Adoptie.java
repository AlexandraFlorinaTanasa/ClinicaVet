package org.entity;

import java.util.Objects;

import javax.persistence.Entity;
@Entity
public class Adoptie extends Client {
private Integer nrAnimaleDisponibile;
private String tipAnimaleDisponibile;

public Integer getNrAnimaleDisponibile() {
	return nrAnimaleDisponibile;
}
public void setNrAnimaleDisponibile(Integer nrAnimaleDisponibile) {
	this.nrAnimaleDisponibile = nrAnimaleDisponibile;
}
public String getTipAnimaleDisponibile() {
	return tipAnimaleDisponibile;
}
public void setTipAnimaleDisponibile(String tipAnimaleDisponibile) {
	this.tipAnimaleDisponibile = tipAnimaleDisponibile;
}
public Adoptie(Integer id, String nume, Integer nrAnimale, String tipAnimal, Integer nrAnimaleDisponibile,
		String tipAnimaleDisponibile) {
	super(id, nume, nrAnimale, tipAnimal);
	this.nrAnimaleDisponibile = nrAnimaleDisponibile;
	this.tipAnimaleDisponibile = tipAnimaleDisponibile;
}
public Adoptie(Integer id, String nume, Integer nrAnimale, String tipAnimal) {
	super(id, nume, nrAnimale, tipAnimal);
}
public Adoptie() {
	super();
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(nrAnimaleDisponibile, tipAnimaleDisponibile);
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
	Adoptie other = (Adoptie) obj;
	return Objects.equals(nrAnimaleDisponibile, other.nrAnimaleDisponibile)
			&& Objects.equals(tipAnimaleDisponibile, other.tipAnimaleDisponibile);
}
@Override
public String toString() {
	return "Adoptie [nrAnimaleDisponibile=" + nrAnimaleDisponibile + ", tipAnimaleDisponibile=" + tipAnimaleDisponibile
			+ "]";
}



}