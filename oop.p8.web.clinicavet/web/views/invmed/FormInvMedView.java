package org.app.clinicavet.web.views.invmed;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

 import org.app.clinicavet.oop.p8.web.clinicavet.MainView;
import org.entity.InvestigatiiTratamente;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("investigatiiTratament")
@Route(value = "investigatiiTratament", layout = MainView.class)

	public class FormInvMedView extends VerticalLayout implements HasUrlParameter<Integer>{

	
	// Definire model date
	private EntityManager em;
	private InvestigatiiTratamente investigatiiTratament = null;
	private Binder<InvestigatiiTratamente> binder = new BeanValidationBinder<>(InvestigatiiTratamente.class);
	// Definire componente view
	// Definire Form
	private VerticalLayout formLayoutToolbar;
	private H1 titluForm = new H1("Form Investigatie/Tratament");
	private IntegerField codInvestigatiiTratamente = new IntegerField("Cod investigatie/tratament:");
	private TextField denInvestigatiiTratamente = new TextField("Nume investigatie/tratament: ");
	// Definire componente actiuni Form-Controller
	private Button cmdAdaugare = new Button("Adauga");
	private Button cmdSterge = new Button("Sterge");
	private Button cmdAbandon = new Button("Abandon");
	private Button cmdSalveaza = new Button("Salveaza");
		// … … //
		// Navigation Management:
		// URL-ul http://localhost:8080/clienti/3 asigură afișare detaliilor clientului cu ID 3
		@Override
		public void setParameter(BeforeEvent event, @OptionalParameter Integer codInvestigatiiTratamente) {
		System.out.println("Cod investigatie/tratament: " + codInvestigatiiTratamente);
		if (codInvestigatiiTratamente!= null) {
		// EDIT Item
		this.investigatiiTratament = em.find(InvestigatiiTratamente.class, codInvestigatiiTratamente);
		System.out.println("Selected investigatie/tratament to edit:: " + investigatiiTratament);
		if (this.investigatiiTratament == null) {
		System.out.println("ADD investigatie/tratament:: " + investigatiiTratament);
		// NEW Item
		this.adaugaInvestigatiiTratament();
		this.investigatiiTratament.setCodInvestigatiiTratamente(codInvestigatiiTratamente);
		this.investigatiiTratament.setDenInvestigatiiTratamente("Investigatie/Tratament NOU " + codInvestigatiiTratamente);
		}
		}
		this.refreshForm();
		}
		
		// init Data Model
		private void initDataModel(){
		System.out.println("DEBUG START FORM >>> ");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVetJPA");
		this.em = emf.createEntityManager();
		this.investigatiiTratament = em
		.createQuery("SELECT it FROM InvestigatiiTratamente it ORDER BY it.codInvestigatiiTratamente", InvestigatiiTratamente.class)
		.getResultStream().findFirst().get();
		//
		binder.forField(codInvestigatiiTratamente).bind("codInvestigatiiTratamente");
		binder.forField(denInvestigatiiTratamente).bind("denInvestigatiiTratamente");
		//
		refreshForm();
		}
		// init View Model
		private void initViewLayout() {
		// Form-Master-Details -----------------------------------//
		// Form-Master
		FormLayout formLayout = new FormLayout();
		formLayout.add(codInvestigatiiTratamente, denInvestigatiiTratamente);
		formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
		formLayout.setMaxWidth("400px");
		// Toolbar-Actions-Master
		HorizontalLayout actionToolbar =
		new HorizontalLayout(cmdAdaugare, cmdSterge, cmdAbandon, cmdSalveaza);
		actionToolbar.setPadding(false);
		//
		this.formLayoutToolbar = new VerticalLayout(formLayout, actionToolbar);
		// ---------------------------
		this.add(titluForm, formLayoutToolbar);
		//
		}
		// init Controller components
		private void initControllerActions() {
		// Transactional Master Actions
		cmdAdaugare.addClickListener(e -> {
		adaugaInvestigatiiTratament();
		refreshForm();
		});
		cmdSterge.addClickListener(e -> {
		stergeInvestigatiiTratamente();
		// Navigate back to NavigableGridClienteForm
		this.getUI().ifPresent(ui -> ui.navigate(
		NavigableGridInvMedView.class)
		);
		});
		cmdAbandon.addClickListener(e -> {
		// Navigate back to NavigableGridClienteForm
		this.getUI().ifPresent(ui -> ui.navigate(
		NavigableGridInvMedView.class, this.investigatiiTratament.getCodInvestigatiiTratamente())
		);
		});
		cmdSalveaza.addClickListener(e -> {
		salveazaInvestigatiiTratamente();
		// refreshForm();
		// Navigate back to NavigableGridClienteForm
		this.getUI().ifPresent(ui -> ui.navigate(
		NavigableGridInvMedView.class, this.investigatiiTratament.getCodInvestigatiiTratamente())
		);
		});
		}
		private void refreshForm() {
			System.out.println("Investigatie/Tratament curent: " + this.investigatiiTratament);
			if (this.investigatiiTratament != null) {
			binder.setBean(this.investigatiiTratament);
			}
			}
		// CRUD actions
		private void salveazaInvestigatiiTratamente() {
		try {
		this.em.getTransaction().begin();
		this.investigatiiTratament = this.em.merge(this.investigatiiTratament);
		this.em.getTransaction().commit();
		System.out.println("Investigatie/Tratament Salvat");
		} catch (Exception ex) {
		if (this.em.getTransaction().isActive())
		this.em.getTransaction().rollback();
		System.out.println("*** EntityManager Validation ex: " + ex.getMessage());
		throw new RuntimeException(ex.getMessage());
		}
		}
		// CRUD actions
		private void adaugaInvestigatiiTratament() {
		this.investigatiiTratament = new InvestigatiiTratamente();
		this.investigatiiTratament.setCodInvestigatiiTratamente(999); // ID arbitrar, inexistent în baza de date
		this.investigatiiTratament.setDenInvestigatiiTratamente("Investigatie/Tratament Nou");
		}
		// CRUD actions
		private void stergeInvestigatiiTratamente() {
		System.out.println("To remove: " + this.investigatiiTratament);
		if (this.em.contains(this.investigatiiTratament)) {
		this.em.getTransaction().begin();
		this.em.remove(this.investigatiiTratament);
		this.em.getTransaction().commit();
		}
		}
		// Start Form
		public FormInvMedView() {
		//
		initDataModel();
		//
		initViewLayout();
		//
		initControllerActions();
		}
	}
	



