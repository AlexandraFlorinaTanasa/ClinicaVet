package org.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
@Entity
public class Medic {
	@Id
	
	@GeneratedValue(strategy = AUTO)
	private Integer id;
	private String nume;
	private String specializare;
	
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
	public String getSpecializare() {
		return specializare;
	}
	public void setSpecializare(String specializare) {
		this.specializare = specializare;
	}
	public Medic(Integer id, String nume, String specializare) {
		super();
		this.id = id;
		this.nume = nume;
		this.specializare = specializare;
	}
	public Medic() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, nume, specializare);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medic other = (Medic) obj;
		return Objects.equals(id, other.id) && Objects.equals(nume, other.nume)
				&& Objects.equals(specializare, other.specializare);
	}
	@Override
	public String toString() {
		return "Medic [id=" + id + ", nume=" + nume + ", specializare=" + specializare + "]";
	}
	
	
	
}