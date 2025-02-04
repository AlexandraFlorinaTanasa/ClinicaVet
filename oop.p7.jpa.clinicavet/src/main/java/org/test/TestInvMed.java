package org.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.entity.InvestigatiiTratamente;

public class TestInvMed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
List<InvestigatiiTratamente>investigatiiTratamente=new ArrayList<InvestigatiiTratamente>();

investigatiiTratamente.add(new InvestigatiiTratamente(40, "consultatie",250.00));
investigatiiTratamente.add(new InvestigatiiTratamente(41, "radiografie", 200.00));
investigatiiTratamente.add(new InvestigatiiTratamente(42, "ecografie", 62.00));
investigatiiTratamente.add(new InvestigatiiTratamente(43, "antibiotic", 89.76));
investigatiiTratamente.add(new InvestigatiiTratamente(44, "sterilizare", 35.00));
investigatiiTratamente.add(new InvestigatiiTratamente(45, "deparazitare interna si externa", 25.00));

EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVetJPA");
EntityManager em = emf.createEntityManager();

// Clean-up
em.getTransaction().begin();
em.createQuery("Delete From InvestigatiiTratamente it").executeUpdate();
em.getTransaction().commit();

// Create
em.persist(investigatiiTratamente.get(0));
em.persist(investigatiiTratamente.get(1));
em.persist(investigatiiTratamente.get(2));
em.persist(investigatiiTratamente.get(3));
em.persist(investigatiiTratamente.get(4));
em.persist(investigatiiTratamente.get(5));
em.getTransaction().begin();
em.getTransaction().commit();
em.clear();

// Read after create
List<InvestigatiiTratamente> InvestigatiiPersistente = em.createQuery("Select it From InvestigatiiTratamente it", InvestigatiiTratamente.class).getResultList();

System.out.println("Lista investigatii persistente/salvate in baza de date");
for (InvestigatiiTratamente it : InvestigatiiPersistente)
	System.out.println("Cod: " + it.getCodInvestigatiiTratamente() + ", Investigatii/Tratamente: " + it.getDenInvestigatiiTratamente() + ", pret: " + it.getPretInvestigatiiTratamente());

}


	}


