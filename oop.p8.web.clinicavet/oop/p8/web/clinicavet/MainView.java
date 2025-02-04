package org.app.clinicavet.oop.p8.web.clinicavet;



import org.app.clinicavet.web.views.invmed.FormInvMedView;
import org.app.clinicavet.web.views.invmed.NavigableGridInvMedView;
import org.app.clinicavet.web.views.medici.FormMedicView;
import org.app.clinicavet.web.views.medici.NavigableGridMediciView;

import com.vaadin.flow.component.UI;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

/**
 * The main view contains a button and a click listener.
 */
@Route
public class MainView extends VerticalLayout implements RouterLayout {

	public MainView() {
		setMenuBar();
		}
		private void setMenuBar() {
		MenuBar mainMenu = new MenuBar();
		MenuItem homeMenu = mainMenu.addItem("Home");
		homeMenu.addClickListener(event -> UI.getCurrent().navigate(MainView.class));
		//
		MenuItem gridFormsMedicMenu = mainMenu.addItem("Medic ");
		SubMenu gridFormsMedicMenuBar = gridFormsMedicMenu.getSubMenu();
		gridFormsMedicMenuBar.addItem("Lista Medici...",
		event -> UI.getCurrent().navigate(NavigableGridMediciView.class));
		gridFormsMedicMenuBar.addItem("Form Editare Retete...",
		event -> UI.getCurrent().navigate(FormMedicView.class)); 
		//
		MenuItem gridFormsInvestigatiiTratamenteMenu = mainMenu.addItem("Investigatie/Tratament");
		SubMenu gridFormsInvestigatiiTratamenteMenuBar = gridFormsInvestigatiiTratamenteMenu.getSubMenu();
		gridFormsInvestigatiiTratamenteMenuBar.addItem("Lista Investigatii/Tratemente...",
		event -> UI.getCurrent().navigate(NavigableGridInvMedView.class));
		gridFormsInvestigatiiTratamenteMenuBar.addItem("Form Editare Investigatii/Tratamente...",
		event -> UI.getCurrent().navigate(FormInvMedView.class)); 
		//
		add(new HorizontalLayout(mainMenu));
		}
		
		}

