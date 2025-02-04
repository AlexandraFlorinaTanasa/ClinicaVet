package org.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
@Entity
public class InvestigatiiTratamente {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer codInvestigatiiTratamente;
	private String denInvestigatiiTratamente;
	private Double pretInvestigatiiTratamente;

	public Integer getCodInvestigatiiTratamente() {
		return codInvestigatiiTratamente;
	}
	public void setCodInvestigatiiTratamente(Integer codInvestigatiiTratamente) {
		this.codInvestigatiiTratamente = codInvestigatiiTratamente;
	}
	public String getDenInvestigatiiTratamente() {
		return denInvestigatiiTratamente;
	}
	public void setDenInvestigatiiTratamente(String denInvestigatiiTratamente) {
		this.denInvestigatiiTratamente = denInvestigatiiTratamente;
	}
	public Double getPretInvestigatiiTratamente() {
		return pretInvestigatiiTratamente;
	}
	public void setPretInvestigatiiTratamente(Double pretInvestigatiiTratamente) {
		this.pretInvestigatiiTratamente = pretInvestigatiiTratamente;
	}
	public InvestigatiiTratamente(Integer codInvestigatiiTratamente, String denInvestigatiiTratamente,
			Double pretInvestigatiiTratamente) {
		super();
		this.codInvestigatiiTratamente = codInvestigatiiTratamente;
		this.denInvestigatiiTratamente = denInvestigatiiTratamente;
		this.pretInvestigatiiTratamente = pretInvestigatiiTratamente;
	}
	public InvestigatiiTratamente() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(codInvestigatiiTratamente, denInvestigatiiTratamente, pretInvestigatiiTratamente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestigatiiTratamente other = (InvestigatiiTratamente) obj;
		return Objects.equals(codInvestigatiiTratamente, other.codInvestigatiiTratamente)
				&& Objects.equals(denInvestigatiiTratamente, other.denInvestigatiiTratamente)
				&& Objects.equals(pretInvestigatiiTratamente, other.pretInvestigatiiTratamente);
	}
	@Override
	public String toString() {
		return "InvestigatiiTratamente [codInvestigatiiTratamente=" + codInvestigatiiTratamente
				+ ", denInvestigatiiTratamente=" + denInvestigatiiTratamente + ", pretInvestigatiiTratamente="
				+ pretInvestigatiiTratamente + "]";
	}
	
}
	