package org.app.clinicavet.web.views.medici;


	import javax.persistence.EntityManager;
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.Persistence;

	 import org.app.clinicavet.oop.p8.web.clinicavet.MainView;
	import org.entity.Medic;

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

	@PageTitle("medic")
	@Route(value = "medic", layout = MainView.class)

		public class FormMedicView extends VerticalLayout implements HasUrlParameter<Integer>{

		
		// Definire model date
		private EntityManager em;
		private Medic medic = null;
		private Binder<Medic> binder = new BeanValidationBinder<>(Medic.class);
		// Definire componente view
		// Definire Form
		private VerticalLayout formLayoutToolbar;
		private H1 titluForm = new H1("Form Medic");
		private IntegerField id = new IntegerField("ID medic:");
		private TextField nume = new TextField("Nume medic: ");
		// Definire componente actiuni Form-Controller
		private Button cmdAdaugare = new Button("Adauga");
		private Button cmdSterge = new Button("Sterge");
		private Button cmdAbandon = new Button("Abandon");
		private Button cmdSalveaza = new Button("Salveaza");
			// … … //
			// Navigation Management:
			// URL-ul http://localhost:8080/clienti/3 asigură afișare detaliilor clientului cu ID 3
			@Override
			public void setParameter(BeforeEvent event, @OptionalParameter Integer id) {
			System.out.println("Medic ID: " + id);
			if (id != null) {
			// EDIT Item
			this.medic = em.find(Medic.class, id);
			System.out.println("Selected medic to edit:: " + medic);
			if (this.medic == null) {
			System.out.println("ADD medic:: " + medic);
			// NEW Item
			this.adaugaMedic();
			this.medic.setId(id);
			this.medic.setNume("Medic NOU " + id);
			}
			}
			this.refreshForm();
			}
			
			// init Data Model
			private void initDataModel(){
			System.out.println("DEBUG START FORM >>> ");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVetJPA");
			this.em = emf.createEntityManager();
			this.medic = em
			.createQuery("SELECT m FROM Medic m ORDER BY m.id", Medic.class)
			.getResultStream().findFirst().get();
			//
			binder.forField(id).bind("id");
			binder.forField(nume).bind("nume");
			//
			refreshForm();
			}
			// init View Model
			private void initViewLayout() {
			// Form-Master-Details -----------------------------------//
			// Form-Master
			FormLayout formLayout = new FormLayout();
			formLayout.add(id, nume);
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
			adaugaMedic();
			refreshForm();
			});
			cmdSterge.addClickListener(e -> {
			stergeMedic();
			// Navigate back to NavigableGridClienteForm
			this.getUI().ifPresent(ui -> ui.navigate(
			NavigableGridMediciView.class)
			);
			});
			cmdAbandon.addClickListener(e -> {
			// Navigate back to NavigableGridClienteForm
			this.getUI().ifPresent(ui -> ui.navigate(
			NavigableGridMediciView.class, this.medic.getId())
			);
			});
			cmdSalveaza.addClickListener(e -> {
			salveazaMedic();
			// refreshForm();
			// Navigate back to NavigableGridClienteForm
			this.getUI().ifPresent(ui -> ui.navigate(
			NavigableGridMediciView.class, this.medic.getId())
			);
			});
			}
			private void refreshForm() {
				System.out.println("Medic curent: " + this.medic);
				if (this.medic != null) {
				binder.setBean(this.medic);
				}
				}
			// CRUD actions
			private void salveazaMedic() {
			try {
			this.em.getTransaction().begin();
			this.medic = this.em.merge(this.medic);
			this.em.getTransaction().commit();
			System.out.println("Medic Salvat");
			} catch (Exception ex) {
			if (this.em.getTransaction().isActive())
			this.em.getTransaction().rollback();
			System.out.println("*** EntityManager Validation ex: " + ex.getMessage());
			throw new RuntimeException(ex.getMessage());
			}
			}
			// CRUD actions
			private void adaugaMedic() {
			this.medic = new Medic();
			this.medic.setId(999); // ID arbitrar, inexistent în baza de date
			this.medic.setNume("Medic Nou");
			}
			// CRUD actions
			private void stergeMedic() {
			System.out.println("To remove: " + this.medic);
			if (this.em.contains(this.medic)) {
			this.em.getTransaction().begin();
			this.em.remove(this.medic);
			this.em.getTransaction().commit();
			}
			}
			// Start Form
			public FormMedicView() {
			//
			initDataModel();
			//
			initViewLayout();
			//
			initControllerActions();
			}
		}
		



