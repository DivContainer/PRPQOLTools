package eventhandler;

import java.util.ArrayList;

import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;

import infoobjects.BussgeldGridItem;
import infoobjects.BussgeldPresetManager;
import interfaces.GridButtonInterface;
import managedbeans.BussgeldRechnerBean;

public class EventController {
	
	private BussgeldRechnerBean bussgeldRechnerBean;
	
	public EventController() {
		super();
	}
	
	public void initBussgeldRechnerEventController() {
		BussgeldPresetManager presetManager = new BussgeldPresetManager();

		EventControllerInterface ecInterface = new EventControllerInterface() {
			
			@Override
			public void callEvent(EventID eventId) {

				switch(eventId) {
				case INIT:
					getBussgeldRechnerBean().setBussgeldAssignedGrid(new FIXGRIDListBinding<BussgeldGridItem>());
					getBussgeldRechnerBean().setPresetsVVB(new ValidValuesBinding());
					fillBussgeldkatalog(null);
					
					getBussgeldRechnerBean().setPresetSelection(null);
					
					presetManager.initPreset(getBussgeldRechnerBean().getPresetsVVB());
					break;
				case SEARCH:
					getBussgeldRechnerBean().getBussgeldKatalogGrid().getItems().clear();
					fillBussgeldkatalog(getBussgeldRechnerBean().getSearchInput());
					break;
				case APPLY_PRESET:
					if(getBussgeldRechnerBean().getPresetsVVB() != null && getBussgeldRechnerBean().getPresetSelection() != null) {
						presetManager.applyPreset(getBussgeldRechnerBean().getPresetSelection(), bussgeldRechnerBean, getBussgeldRechnerBean().getBussgeldAssignedGrid());
					}
					break;
				case CALCULATE:
					double bussgeldSum = 0.0;
					int jailTimeSum = 0;
					int licensePoints = 0;
					for(BussgeldGridItem gridItem : getBussgeldRechnerBean().getBussgeldAssignedGrid().getItems()) {
						bussgeldSum = bussgeldSum + gridItem.getPayAmount();
						jailTimeSum = jailTimeSum + gridItem.getJailTime();		
						licensePoints = licensePoints + gridItem.getLicensePoints();
					}
					getBussgeldRechnerBean().setBussgeldCalculated(bussgeldSum);
					getBussgeldRechnerBean().setHaftCalculated(jailTimeSum);
					getBussgeldRechnerBean().setLicensePointsCalculated(licensePoints);
					break;
				case RESET:
					getBussgeldRechnerBean().getBussgeldAssignedGrid().getItems().clear();
					getBussgeldRechnerBean().setBussgeldCalculated(0);
					getBussgeldRechnerBean().setLicensePointsCalculated(0);
					getBussgeldRechnerBean().setHaftCalculated(0);
					break;
				default:
					break;
				}
			}
		};
		getBussgeldRechnerBean().setEventControllerInterface(ecInterface);
	}
	
	private void fillBussgeldkatalog(String filterString) {
		ArrayList<BussgeldGridItem> bussgelderFromCatalog = new ArrayList<BussgeldGridItem>();
		
		/*
		 * StVO / Verkehrsdelikte (Land)	
		 */
		bussgelderFromCatalog.add(new BussgeldGridItem("Gef�hrlicher Eingriff in den Stra�enverkehr", 7500, 1, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Fu�g�nger�berwegspflicht", 1500, 0, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Fahren abseits der Stra�e", 3000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten von Stra�enmarkierungen, Verkehrszeichen & Lichtanlagen", 2500, 1, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Fahren auf der falschen Stra�enseite", 5000, 1, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Vorfahrtsregeln", 1500, 1, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Geschwindigkeits�berschreitung auf Parkpl�tzen", 3500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("1 - 20km/h Geschwindigkeits�berschreitung (-5km/h Toleranz)", 3500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("21 - 50km/h Geschwindigkeits�berschreitung (-5km/h Toleranz)", 4500, 1, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Ab 51km/h Geschwindigkeits�berschreitung (-5km/h Toleranz)", 6000, 1, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("F�hren eines Fahrzeugs ohne F�hrerschein nach Klasse B", 6000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("F�hren eines Fahrzeugs ohne F�hrerschein nach Klasse C", 6500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Beeintr�chtiges Fahren", 5000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Teilnahme am Stra�enverkehr mit fahrunt�chtigem Fahrzeug", 2500, 1, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Parken au�erhalb einer vorgegebenen Parkzone / Falschparken", 1500, 0, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Halten f�r l�nger als 3 Minuten", 1000, 0, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Blockieren von Einsatzwegen der Staatsbeh�rden", 4500, 1, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("R�cksichtsloses Fahren", 6000, 1, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten polizeilicher Signale", 3000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Nichteinhaltung des Sicherheitsabstandes", 2000, 0, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Unterlassene Hilfeleistung", 6000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Unrechtm��ige Nutzung von Einsatzwegen", 4000, 1, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Fahren ohne Licht auf schlecht beleuchteten Stra�en", 2500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachtung der Verbandsmaterialpflicht", 500, 0, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Illegales Fahrzeugtuning", 5000, 1, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Anbringen von Blaulicht am Fahrzeug", 10000, 1, 5, null));
		
		/*
		 * BtMG / Drogen
		 */
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz/Handel von Drogen", 15000, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Begehen von Straftaten unter Drogeneinfluss", 5000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Illegales Bierbrauen", 12500, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Schwarzarbeit (je Arbeiter)", 10000, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Illegaler Verkauf alkoholischer Getr�nke (je Getr�nk)", 5000, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Transport von mehr als zwei K�sten Alkohol", 4500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Verweigern der Stichprobe", 10000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Herstellung von Marihuana", 15000, 0, 8, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Handel von gestrecktem Marihuana", 22500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Herstellung von Methamphetamin", 20000, 0, 9, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Handel mit gestrecktem Methamphetamin", 28000, 0, 4, null));
		
		
		/*
		 * StGB
		 */
		bussgelderFromCatalog.add(new BussgeldGridItem("K�rperverletzung (+ Schadensersatz)", 7500, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Fahrl�ssige K�rperverletzung", 3500, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Mord", 30000, 0, 15, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Totschlag", 25000, 0, 10, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("K�rperverletzung mit Todesfolge", 17500, 0, 13, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Fahrl�ssige K�rperverletzung mit Todesfolge", 15000, 0, 11, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Diebstahl", 7500, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Behalten geliehener Gegenst�nde", 5000, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Raub", 10000, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Schwerer Raub", 12500, 0, 8, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Sachbesch�digung (+ Schadensersatz)", 3000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Bestechung", 12500, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Drohung", 4500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Beleidigung/Rufmord", 2500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Erpressung (bis zu 12500)", 5000, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Freiheitsberaubung", 20000, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Geiselnahme", 25000, 0, 11, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Planen einer Straftat", 5000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Versperren von Wegen auf Grundst�cken anderer Personen", 2000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Hausfriedensbruch", 5000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Illegale Dienstleistungen", 1000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Erschleichen von Dienstleistungen (+ Schadensersatz)", 1500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz/Handel/Herstellung von gef�lschter Ware", 6000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz gef�lschten Geldes", 12000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Bel�stigung", 2000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Amtsanma�ung", 10000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten polizeilicher Anweisungen", 5000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Umgehung polizeilicher Ma�nahmen", 5000, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Behinderung eines Beamten bei der Ausf�hrung seiner Arbeit", 7000, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Gefangenenbefreiung", 7500, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missbrauch des Notrufs", 5000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Angabe falscher Information unter geleistetem Eid", 5500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Umweltverschmutzung", 2000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Bel�stigung", 2000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Amtsanma�ung", 10000, 0, 6, null));
		
		/*
		 * Waffengesetze
		 */
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz illegaler Waffen (+Entzug Waffenlizenz)", 30000, 0, 12, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz/Handel einer Waffe ohne g�ltige Waffenlizenz", 10000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Illegaler Waffenhandel", 25000, 0, 15, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Einsatz von Waffen au�erhalb von Selbstverteidigungszwecken", 20000, 0, 8, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Entsichertes F�hren einer Waffe", 7500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Unsicheres Lagern einer Waffe im Fahrzeug", 6000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Lagern einer Waffe in einem ungesichertem Container", 2500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Funktionierende Waffe als Schm�ckstuck", 15000, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Nicht melden von Gebrauch einer Schusswaffe", 5000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Verantwortung �ber Waffen (+Entzug Waffenlizenz)", 4500, 0, 3, null));
		
		/*
		 * Tierschutzgesetze
		 */
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Leinenpflicht", 2500, 0, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachtung der Vorraussetzungen f�r das Halten von Tieren", 4500, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Direkte Verletzung eines Tiers", 6500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Beabsichtige Beeintr�chtigung des nat�rlichen Lebensraums von Tieren", 3500, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Freiheitsberaubung von Tieren", 7500, 0, 6, null));
		
		/*
		 * Grundgesetz
		 */
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Gleichberechtigung (+Schadensersatz)", 10000, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Menschwenw�rde (+Schadensersatz)", 10000, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Unterdr�ckung der freien Meinungs�u�erung", 8000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Errichten von Bauten auf Grundst�cken Anderer", 3000, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Privatssph�re", 4500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz gef�lschter Dokumente", 6500, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz von polizeilichen Mitteln", 10000, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Brechen der Schweigepflicht (+Schadensersatz)", 5000, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Vors�tzliches Verschweigen von Straftaten", 5000, 0, 3, null));
		
		FIXGRIDListBinding<BussgeldGridItem> bussgeldGridList = new FIXGRIDListBinding<BussgeldGridItem>();
		
		for(BussgeldGridItem gridItem : bussgelderFromCatalog) {
			gridItem.setGridButtonInterface(new GridButtonInterface() {
				
				@Override
				public void removeButtonPressed() {
					/*
					if(getBussgeldRechnerBean().getBussgeldKatalogGrid().getItems().stream().filter(c -> c.getDescription().equals(gridItem.getDescription())).findFirst().isPresent()) {
						getBussgeldRechnerBean().getBussgeldKatalogGrid().getItems().stream().filter(c -> c.getDescription().equals(gridItem.getDescription())).findFirst().get().setActionEnabled(true);
					}
					*/
					getBussgeldRechnerBean().getBussgeldAssignedGrid().getItems().remove(gridItem);
					
					getBussgeldRechnerBean().setBussgeldCalculated(0);
					getBussgeldRechnerBean().setLicensePointsCalculated(0);
					getBussgeldRechnerBean().setHaftCalculated(0);
				}
				
				@Override
				public void addButtonPressed() {
					/*
					if(getBussgeldRechnerBean().getBussgeldKatalogGrid().getItems().stream().filter(c -> c.getDescription().equals(gridItem.getDescription())).findFirst().isPresent()) {
						getBussgeldRechnerBean().getBussgeldKatalogGrid().getItems().stream().filter(c -> c.getDescription().equals(gridItem.getDescription())).findFirst().get().setActionEnabled(false);
					}
					*/
					getBussgeldRechnerBean().getBussgeldAssignedGrid().getItems().add(gridItem);
					
					getBussgeldRechnerBean().setBussgeldCalculated(0);
					getBussgeldRechnerBean().setLicensePointsCalculated(0);
					getBussgeldRechnerBean().setHaftCalculated(0);
				}
			});
			if(filterString != null && filterString.length() > 0) {
				if(gridItem.getDescription().toLowerCase().contains(filterString.toLowerCase())) {
					if(getBussgeldRechnerBean().getBussgeldAssignedGrid().getItems().stream().filter(c -> c.getDescription().equals(gridItem.getDescription())).findAny().isPresent()) {
						gridItem.setActionEnabled(false);
					}
					bussgeldGridList.getItems().add(gridItem);
				}
			} else {
				if(getBussgeldRechnerBean().getBussgeldAssignedGrid().getItems().stream().filter(c -> c.getDescription().equals(gridItem.getDescription())).findAny().isPresent()) {
					gridItem.setActionEnabled(false);
				}
				bussgeldGridList.getItems().add(gridItem);
			}
		}
		getBussgeldRechnerBean().setBussgeldKatalogGrid(bussgeldGridList);
	}
	
	public BussgeldRechnerBean getBussgeldRechnerBean() {
		return bussgeldRechnerBean;
	}

	public void setBussgeldRechnerBean(BussgeldRechnerBean bussgeldRechnerBean) {
		this.bussgeldRechnerBean = bussgeldRechnerBean;
	}
	
}
