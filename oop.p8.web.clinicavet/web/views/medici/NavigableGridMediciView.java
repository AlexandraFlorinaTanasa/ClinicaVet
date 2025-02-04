package org.app.clinicavet.web.views.medici;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.app.clinicavet.oop.p8.web.clinicavet.MainView;
import org.entity.Medic;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("medici")
@Route(value = "medici", layout = MainView.class)

public class NavigableGridMediciView extends VerticalLayout implements HasUrlParameter<Integer>{
	// Definire model date
	private EntityManager em;
	private List<Medic> medici = new ArrayList<>();
	private Medic medic = null;
	private Binder<Medic> binder = new BeanValidationBinder<>(Medic.class);
	
	// Definire componente view
	private H1 titluForm = new H1("Lista Medici");
	
	// Definire componente suport navigare
	private VerticalLayout gridLayoutToolbar;
	private TextField filterText = new TextField();
	private Button cmdEditMedic = new Button("Editeaza medic...");
	private Button cmdAdaugaMedic = new Button("Adauga medic...");
	private Button cmdStergeMedic = new Button("Sterge medic");
	private Grid<Medic> grid = new Grid<>(Medic.class);
	
	// init Data Model
	private void initDataModel(){
	System.out.println("DEBUG START FORM >>> ");
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVetJPA");
	em = emf.createEntityManager();
	List<Medic> lst = em
	.createQuery("SELECT m FROM Medic m ORDER BY m.id", Medic.class)
	.getResultList();
	medici.addAll(lst);
	if (lst != null && !lst.isEmpty()){
	Collections.sort(this.medici, (m1, m2) -> m1.getId().compareTo(m2.getId()));
	this.medic = medici.get(0);
	System.out.println("DEBUG: medic init >>> " + medic.getId());
	}
	//
	grid.setItems(this.medici);
	binder.setBean(this.medic);
	grid.asSingleSelect().setValue(this.medic);
	}
	// init View Model
	private void initViewLayout() {
	// Layout navigare -------------------------------------//
	// Toolbar navigare
	filterText.setPlaceholder("Filter by nume...");
	filterText.setClearButtonVisible(true);
	filterText.setValueChangeMode(ValueChangeMode.LAZY);
	HorizontalLayout gridToolbar = new HorizontalLayout(filterText,
	cmdEditMedic, cmdAdaugaMedic, cmdStergeMedic);
	// Grid navigare
	grid.setColumns("id", "nume");
	grid.addComponentColumn(item -> createGridActionsButtons(item)).setHeader("Actiuni");
	// Init Layout navigare
	gridLayoutToolbar = new VerticalLayout(gridToolbar, grid);
	// ---------------------------
	this.add(titluForm, gridLayoutToolbar);
	//
	}
	private Component createGridActionsButtons(Medic item) {
		//
		Button cmdEditItem = new Button("Edit");
		cmdEditItem.addClickListener(e -> {
		grid.asSingleSelect().setValue(item);
		editMedic();
		});
		Button cmdDeleteItem = new Button("Sterge");
		cmdDeleteItem.addClickListener(e -> {
		System.out.println("Sterge item: " + item);
		grid.asSingleSelect().setValue(item);
		stergeMedic();
		refreshForm();
		} );
		//
		return new HorizontalLayout(cmdEditItem, cmdDeleteItem);
		}
	
	// init Controller components
	private void initControllerActions() {
	// Navigation Actions
	filterText.addValueChangeListener(e -> updateList());
	cmdEditMedic.addClickListener(e -> {
	editMedic();
	});
	cmdAdaugaMedic.addClickListener(e -> {
	adaugaMedic();
	});
	cmdStergeMedic.addClickListener(e -> {
	stergeMedic();
	refreshForm();
	});
	}
	// CRUD actions
	// Adaugare: delegare catre Formular detalii medic
	private void adaugaMedic() {
	this.getUI().ifPresent(ui -> ui.navigate(FormMedicView.class, 999));
	}
	// Editare: delegare catre Formular detalii medic
	private void editMedic() {
	this.medic = this.grid.asSingleSelect().getValue();
	System.out.println("Selected medic:: " + medic);
	if (this.medic != null) {
	this.getUI().ifPresent(ui -> ui.navigate(
	FormMedicView.class, this.medic.getId())
	);
	}
	}
	// CRUD actions
	// Stergere: tranzactie locala cu EntityManager
	private void stergeMedic() {
	this.medic = this.grid.asSingleSelect().getValue();
	System.out.println("To remove: " + this.medic);
	this.medici.remove(this.medic);
	if (this.em.contains(this.medic)) {
	this.em.getTransaction().begin();
	this.em.remove(this.medic);
	this.em.getTransaction().commit();
	}
	if (!this.medici.isEmpty())
	this.medic = this.medici.get(0);
	else
	this.medic = null;
	}
	// Start Form
	public NavigableGridMediciView() {
	//
	initDataModel();
	//
	initViewLayout();
	//
	initControllerActions();
	}
	// Populare grid cu set de date din model - filtrare
	private void updateList() {
	try {
	List<Medic> lstMediciFiltered = this.medici;
	if (filterText.getValue() != null) {
	lstMediciFiltered = this.medici.stream()
	.filter(m -> m.getNume().contains(filterText.getValue()))
	.toList();
	grid.setItems(lstMediciFiltered);
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	// Resincronizare componente-view cu modelul de date
	private void refreshForm() {
	System.out.println("Medic curent: " + this.medic);
	if (this.medic != null) {
	grid.setItems(this.medici);
	binder.setBean(this.medic);
	grid.select(this.medic);
	}
	}
	
	// … … //
	// Navigation Management:
	// URL-ul http://localhost:8080/clienti/3 asigură selecția clientului cu ID 3
	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter Integer id) {
	if (id != null) {
	this.medic = em.find(Medic.class, id);
	System.out.println("Back medic: " + medic);
	if (this.medic == null) {
	// DELETED Item
	if (!this.medici.isEmpty())
	this.medic = this.medici.get(0);
	}
	// else: EDITED or NEW Item
	}
	this.refreshForm();
	}
	// … … //
	}
 





