package org.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.entity.Medic;




public class TestMedic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	List<Medic>  medic = new ArrayList<Medic>();
		
		medic.add(new Medic(80, "Avram Iuliana ","Interne"));
		medic.add(new Medic(81, "Eminovici Veronica","Oftalmologie"));
		medic.add(new Medic(82, "Barna Lucian","Ortopedie"));
		medic.add(new Medic(83, "Popescu Sara","Chirurgie generala" ));
		medic.add(new Medic(84, "Andrei Bogdan", "Neurochirurgie"));
		medic.add(new Medic(85, "Andei Andrei", "Hematologie"));

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVetJPA");
		EntityManager em = emf.createEntityManager();

		// Clean-up
		em.getTransaction().begin();
		em.createQuery("Delete From Medic m").executeUpdate();
		em.getTransaction().commit();

		// Create
		em.persist(medic.get(0));
		em.persist(medic.get(1));
		em.persist(medic.get(2));
		em.persist(medic.get(3));
		em.persist(medic.get(4));
		em.persist(medic.get(5));
		em.getTransaction().begin();
		em.getTransaction().commit();
		em.clear();

		// Read after create
		List<Medic> Medic = em.createQuery("Select m From Medic m", Medic.class).getResultList();

		System.out.println("Lista medici persistenti/salvati in baza de date");
		for (Medic m : Medic)
			System.out.println("Id: " + m.getId() + ", Nume: " + m.getNume() + ", specializare: " + m.getSpecializare());
	}



		
		
		
		
			
			
	



		
	}


