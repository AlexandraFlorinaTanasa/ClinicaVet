package org.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
@Entity
public class LinieFactura {

@Id
	@GeneratedValue(strategy = AUTO)
	private Integer idLinie;
	@ManyToOne
	InvestigatiiTratamente investigatiiTratamente;
	@ManyToOne
	Factura factura;
	
	private Double TVALinie;
	private Double valoareLinie;
	
	
	//Proprietati	
	public Integer getIdLinie() {
		return idLinie;
	}
	public void setIdLinie(Integer idLinie) {
		this.idLinie = idLinie;
	}
	
	
	public InvestigatiiTratamente getInvestigatiiTratamente() {
		return investigatiiTratamente;
	}
	public void setInvestigatiiTratamente(InvestigatiiTratamente investigatiiTratamente) {
		this.investigatiiTratamente = investigatiiTratamente;
	}


	
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	public Double getTVALinie() {
		if(TVALinie==null || TVALinie==0) TVALinie=calcTVALinie();
		return TVALinie;
	}
	public Double getValoareLinie() {
		if(valoareLinie==null || valoareLinie==0.0) valoareLinie=calcValLinie();
		return valoareLinie;
	}
	Double calcValLinie() {
		Double val=null;
		if(investigatiiTratamente!=null)
			val=investigatiiTratamente.getPretInvestigatiiTratamente();
		return val;
	}
	Double calcTVALinie() {
		Double valTVA=null;
		if(investigatiiTratamente!=null )
			valTVA=0.19/1.19*(investigatiiTratamente.getPretInvestigatiiTratamente());
		return valTVA;
	}
	public LinieFactura(Integer idLinie, InvestigatiiTratamente investigatiiTratamente, Factura factura, Double tVALinie,
			Double valoareLinie) {
		super();
		this.idLinie = idLinie;
		this.investigatiiTratamente = investigatiiTratamente;
		this.factura = factura;
		TVALinie = tVALinie;
		this.valoareLinie = valoareLinie;
	}
	public LinieFactura() {
		super();
	}
	public static void add(LinieFactura linieFactura) {
		LinieFactura.add(linieFactura);
	}
	@Override
	public int hashCode() {
		return Objects.hash(TVALinie, factura, idLinie, investigatiiTratamente, valoareLinie);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinieFactura other = (LinieFactura) obj;
		return Objects.equals(TVALinie, other.TVALinie) && Objects.equals(factura, other.factura)
				&& Objects.equals(idLinie, other.idLinie)
				&& Objects.equals(investigatiiTratamente, other.investigatiiTratamente)
				&& Objects.equals(valoareLinie, other.valoareLinie);
	}
	@Override
	public String toString() {
		return "LinieFactura [idLinie=" + idLinie + ", investigatiiTratamente=" + investigatiiTratamente + ", factura="
				+ factura + ", TVALinie=" + TVALinie + ", valoareLinie=" + valoareLinie + "]";
	}
	
	
	}
	
	

	


