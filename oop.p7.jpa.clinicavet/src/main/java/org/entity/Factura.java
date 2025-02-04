package org.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

import static javax.persistence.TemporalType.DATE;
@Entity

public class Factura {
	@Id
	
	@GeneratedValue(strategy = AUTO)
	private Integer nrFactura;
	@Temporal(DATE)
	private Date dataFactura;
	@OneToMany(mappedBy = "factura")
	private List<LinieFactura> linieFactura = new ArrayList<LinieFactura>();
	
	@ManyToOne
	private Animal animal; 
	
	private Double totalFactura;
	private Double totalTVA;
	
	

	public Integer getNrFactura() {
		return nrFactura;
	}

	public void setNrFactura(Integer nrFactura) {
		this.nrFactura = nrFactura;
	}
	

	public Date getDataFactura() {
		return dataFactura;
	}

	public void setDataFactura(Date dataFactura) {
		this.dataFactura = dataFactura;
	}

	
	public List<LinieFactura> getLinieFactura() {
		return linieFactura;
	}

	public void setLinieFactura(List<LinieFactura> linieFactura) {
		this.linieFactura = linieFactura;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public void setTotalFact(Double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public void setTotalTVA(Double totalTVA) {
		this.totalTVA = totalTVA;
	}

	public Double getTotalFactura() {
		if(linieFactura.isEmpty()) return null;
		Double totalFactura=0.0;
		for(LinieFactura lf:linieFactura)
			totalFactura+=lf.getValoareLinie();
		return totalFactura;
	}
	
	Double calculTotal() {
		Double totalFactura=.0;
		for(LinieFactura lf:linieFactura ) totalFactura+=lf.getValoareLinie();
		return totalFactura;
	}
	public Double getTotalTVA() {
		if(linieFactura.isEmpty())
			return null;
		Double totalFactura=calculTotal();
		return 0.19/1.09*totalFactura; // se aplica tva de 19%
	}
	
	
	
	
	public void adaugaLinie (LinieFactura linieFactura) {
		LinieFactura.add(linieFactura);
	}
	public void adauga(InvestigatiiTratamente investigatiiTratamente) {
		LinieFactura lf =new LinieFactura();
		lf.setFactura(this);
		lf.setInvestigatiiTratamente(investigatiiTratamente);
		this.linieFactura.add(lf);
	}
	
	


	public Factura(Integer nrFactura, Date dataFactura, List<LinieFactura> linieFactura, Animal animal, Double totalFactura,
			Double totalTVA) {
		super();
		this.nrFactura = nrFactura;
		this.dataFactura = dataFactura;
		this.linieFactura = linieFactura;
		this.animal = animal;
		this.totalFactura = totalFactura;
		this.totalTVA = totalTVA;
	}

	public Factura() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataFactura, linieFactura, nrFactura, animal, totalFactura, totalTVA);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		return Objects.equals(dataFactura, other.dataFactura) && Objects.equals(linieFactura, other.linieFactura)
				&& Objects.equals(nrFactura, other.nrFactura) && Objects.equals(animal, other.animal)
				&& Objects.equals(totalFactura, other.totalFactura) && Objects.equals(totalTVA, other.totalTVA);
	}

	@Override
	public String toString() {
		return "Factura [nrFactura=" + nrFactura + ", dataFactura=" + dataFactura + ", linieFactura=" + linieFactura
				+ ", animal=" + animal + ", totalFactura=" + totalFactura + ", totalTVA=" + totalTVA + "]";
	}


	


}



