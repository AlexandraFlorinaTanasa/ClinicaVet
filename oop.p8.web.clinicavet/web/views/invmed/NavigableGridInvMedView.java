package org.app.clinicavet.web.views.invmed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.app.clinicavet.oop.p8.web.clinicavet.MainView;
import org.entity.InvestigatiiTratamente;



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

@PageTitle("investigatiiTratamente")
@Route(value = "investigatiiTratamente", layout = MainView.class)

public class NavigableGridInvMedView extends VerticalLayout implements HasUrlParameter<Integer>{
	// Definire model date
	private EntityManager em;
	private List<InvestigatiiTratamente> investigatiiTrat = new ArrayList<>();
	private InvestigatiiTratamente investigatiiTratamente = null;
	private Binder<InvestigatiiTratamente> binder = new BeanValidationBinder<>(InvestigatiiTratamente.class);
	
	// Definire componente view
	private H1 titluForm = new H1("Lista Investigatii/Tratamente");
	
	// Definire componente suport navigare
	private VerticalLayout gridLayoutToolbar;
	private TextField filterText = new TextField();
	private Button cmdEditInvestigatiiTratamente = new Button("Editeaza Investigatie/Tratament...");
	private Button cmdAdaugaInvestigatiiTratamente = new Button("Adauga Investigatie/Tratament...");
	private Button cmdStergeInvestigatiiTratamente = new Button("Sterge Investigatie/Tratament");
	private Grid<InvestigatiiTratamente> grid = new Grid<>(InvestigatiiTratamente.class);
	
	// init Data Model
	private void initDataModel(){
	System.out.println("DEBUG START FORM >>> ");
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVetJPA");
	em = emf.createEntityManager();
	List<InvestigatiiTratamente> lst = em
	.createQuery("SELECT it FROM InvestigatiiTratamente it ORDER BY it.codInvestigatiiTratamente", InvestigatiiTratamente.class)
	.getResultList();
	investigatiiTrat.addAll(lst);
	if (lst != null && !lst.isEmpty()){
	Collections.sort(this.investigatiiTrat, (it1, it2) -> it1.getCodInvestigatiiTratamente().compareTo(it2.getCodInvestigatiiTratamente()));
	this.investigatiiTratamente = investigatiiTrat.get(0);
	System.out.println("DEBUG: InvestigatiiTratament init >>> " + investigatiiTratamente.getCodInvestigatiiTratamente());
	}
	//
	grid.setItems(this.investigatiiTrat);
	binder.setBean(this.investigatiiTratamente);
	grid.asSingleSelect().setValue(this.investigatiiTratamente);
	}
	// init View Model
	private void initViewLayout() {
	// Layout navigare -------------------------------------//
	// Toolbar navigare
	filterText.setPlaceholder("Filter by nume...");
	filterText.setClearButtonVisible(true);
	filterText.setValueChangeMode(ValueChangeMode.LAZY);
	HorizontalLayout gridToolbar = new HorizontalLayout(filterText,
	cmdEditInvestigatiiTratamente, cmdAdaugaInvestigatiiTratamente, cmdStergeInvestigatiiTratamente);
	// Grid navigare
	grid.setColumns("codInvestigatiiTratamente", "denInvestigatiiTratamente");
	grid.addComponentColumn(item -> createGridActionsButtons(item)).setHeader("Actiuni");
	// Init Layout navigare
	gridLayoutToolbar = new VerticalLayout(gridToolbar, grid);
	// ---------------------------
	this.add(titluForm, gridLayoutToolbar);
	//
	}
	private Component createGridActionsButtons(InvestigatiiTratamente item) {
		//
		Button cmdEditItem = new Button("Edit");
		cmdEditItem.addClickListener(e -> {
		grid.asSingleSelect().setValue(item);
		editInvestigatiiTratamente();
		});
		Button cmdDeleteItem = new Button("Sterge");
		cmdDeleteItem.addClickListener(e -> {
		System.out.println("Sterge item: " + item);
		grid.asSingleSelect().setValue(item);
		stergeInvestigatiiTratamente();
		refreshForm();
		} );
		//
		return new HorizontalLayout(cmdEditItem, cmdDeleteItem);
		}
	
	// init Controller components
	private void initControllerActions() {
	// Navigation Actions
	filterText.addValueChangeListener(e -> updateList());
	cmdEditInvestigatiiTratamente.addClickListener(e -> {
	editInvestigatiiTratamente();
	});
	cmdAdaugaInvestigatiiTratamente.addClickListener(e -> {
	adaugaInvestigatiiTratamente();
	});
	cmdStergeInvestigatiiTratamente.addClickListener(e -> {
	stergeInvestigatiiTratamente();
	refreshForm();
	});
	}
	// CRUD actions
	// Adaugare: delegare catre Formular detalii medic
	private void adaugaInvestigatiiTratamente() {
	this.getUI().ifPresent(ui -> ui.navigate(FormInvMedView.class, 999));
	}
	// Editare: delegare catre Formular detalii medic
	private void editInvestigatiiTratamente() {
	this.investigatiiTratamente= this.grid.asSingleSelect().getValue();
	System.out.println("Selected Investigatie/Tratament:: " + investigatiiTratamente);
	if (this.investigatiiTratamente != null) {
	this.getUI().ifPresent(ui -> ui.navigate(
	FormInvMedView.class, this.investigatiiTratamente.getCodInvestigatiiTratamente())
	);
	}
	}
	// CRUD actions
	// Stergere: tranzactie locala cu EntityManager
	private void stergeInvestigatiiTratamente() {
	this.investigatiiTratamente = this.grid.asSingleSelect().getValue();
	System.out.println("To remove: " + this.investigatiiTratamente);
	this.investigatiiTrat.remove(this.investigatiiTratamente);
	if (this.em.contains(this.investigatiiTratamente)) {
	this.em.getTransaction().begin();
	this.em.remove(this.investigatiiTratamente);
	this.em.getTransaction().commit();
	}
	if (!this.investigatiiTrat.isEmpty())
	this.investigatiiTratamente = this.investigatiiTrat.get(0);
	else
	this.investigatiiTratamente = null;
	}
	// Start Form
	public NavigableGridInvMedView() {
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
	List<InvestigatiiTratamente> lstInvestigatiiTratFiltered = this.investigatiiTrat;
	if (filterText.getValue() != null) {
	lstInvestigatiiTratFiltered = this.investigatiiTrat.stream()
	.filter(m -> m.getDenInvestigatiiTratamente().contains(filterText.getValue()))
	.toList();
	grid.setItems(lstInvestigatiiTratFiltered);
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	// Resincronizare componente-view cu modelul de date
	private void refreshForm() {
	System.out.println("Investigatie/Tratament curent: " + this.investigatiiTratamente);
	if (this.investigatiiTratamente != null) {
	grid.setItems(this.investigatiiTrat);
	binder.setBean(this.investigatiiTratamente);
	grid.select(this.investigatiiTratamente);
	}
	}
	
	// … … //
	// Navigation Management:
	// URL-ul http://localhost:8080/clienti/3 asigură selecția clientului cu ID 3
	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter Integer codInvestigatiiTratamente) {
	if (codInvestigatiiTratamente != null) {
	this.investigatiiTratamente = em.find(InvestigatiiTratamente.class, codInvestigatiiTratamente);
	System.out.println("Back Investigatie/Tratament: " + investigatiiTratamente);
	if (this.investigatiiTratamente == null) {
	// DELETED Item
	if (!this.investigatiiTrat.isEmpty())
	this.investigatiiTratamente = this.investigatiiTrat.get(0);
	}
	// else: EDITED or NEW Item
	}
	this.refreshForm();
	}
	// … … //
	}
 





