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
		bussgelderFromCatalog.add(new BussgeldGridItem("Gefährlicher Eingriff in den Straßenverkehr", 7500, 1, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Fußgängerüberwegspflicht", 1500, 0, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Fahren abseits der Straße", 3000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten von Straßenmarkierungen, Verkehrszeichen & Lichtanlagen", 2500, 1, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Fahren auf der falschen Straßenseite", 5000, 1, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Vorfahrtsregeln", 1500, 1, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Geschwindigkeitsüberschreitung auf Parkplätzen", 3500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("1 - 20km/h Geschwindigkeitsüberschreitung (-5km/h Toleranz)", 3500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("21 - 50km/h Geschwindigkeitsüberschreitung (-5km/h Toleranz)", 4500, 1, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Ab 51km/h Geschwindigkeitsüberschreitung (-5km/h Toleranz)", 6000, 1, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Führen eines Fahrzeugs ohne Führerschein nach Klasse B", 6000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Führen eines Fahrzeugs ohne Führerschein nach Klasse C", 6500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Beeinträchtiges Fahren", 5000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Teilnahme am Straßenverkehr mit fahruntüchtigem Fahrzeug", 2500, 1, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Parken außerhalb einer vorgegebenen Parkzone / Falschparken", 1500, 0, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Halten für länger als 3 Minuten", 1000, 0, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Blockieren von Einsatzwegen der Staatsbehörden", 4500, 1, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Rücksichtsloses Fahren", 6000, 1, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten polizeilicher Signale", 3000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Nichteinhaltung des Sicherheitsabstandes", 2000, 0, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Unterlassene Hilfeleistung", 6000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Unrechtmäßige Nutzung von Einsatzwegen", 4000, 1, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Fahren ohne Licht auf schlecht beleuchteten Straßen", 2500, 0, 2, null));
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
		bussgelderFromCatalog.add(new BussgeldGridItem("Illegaler Verkauf alkoholischer Getränke (je Getränk)", 5000, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Transport von mehr als zwei Kästen Alkohol", 4500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Verweigern der Stichprobe", 10000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Herstellung von Marihuana", 15000, 0, 8, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Handel von gestrecktem Marihuana", 22500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Herstellung von Methamphetamin", 20000, 0, 9, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Handel mit gestrecktem Methamphetamin", 28000, 0, 4, null));
		
		
		/*
		 * StGB
		 */
		bussgelderFromCatalog.add(new BussgeldGridItem("Körperverletzung (+ Schadensersatz)", 7500, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Fahrlässige Körperverletzung", 3500, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Mord", 30000, 0, 15, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Totschlag", 25000, 0, 10, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Körperverletzung mit Todesfolge", 17500, 0, 13, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Fahrlässige Körperverletzung mit Todesfolge", 15000, 0, 11, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Diebstahl", 7500, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Behalten geliehener Gegenstände", 5000, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Raub", 10000, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Schwerer Raub", 12500, 0, 8, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Sachbeschädigung (+ Schadensersatz)", 3000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Bestechung", 12500, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Drohung", 4500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Beleidigung/Rufmord", 2500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Erpressung (bis zu 12500)", 5000, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Freiheitsberaubung", 20000, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Geiselnahme", 25000, 0, 11, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Planen einer Straftat", 5000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Versperren von Wegen auf Grundstücken anderer Personen", 2000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Hausfriedensbruch", 5000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Illegale Dienstleistungen", 1000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Erschleichen von Dienstleistungen (+ Schadensersatz)", 1500, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz/Handel/Herstellung von gefälschter Ware", 6000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz gefälschten Geldes", 12000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Belästigung", 2000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Amtsanmaßung", 10000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten polizeilicher Anweisungen", 5000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Umgehung polizeilicher Maßnahmen", 5000, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Behinderung eines Beamten bei der Ausführung seiner Arbeit", 7000, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Gefangenenbefreiung", 7500, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missbrauch des Notrufs", 5000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Angabe falscher Information unter geleistetem Eid", 5500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Umweltverschmutzung", 2000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Belästigung", 2000, 0, 2, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Amtsanmaßung", 10000, 0, 6, null));
		
		/*
		 * Waffengesetze
		 */
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz illegaler Waffen (+Entzug Waffenlizenz)", 30000, 0, 12, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz/Handel einer Waffe ohne gültige Waffenlizenz", 10000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Illegaler Waffenhandel", 25000, 0, 15, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Einsatz von Waffen außerhalb von Selbstverteidigungszwecken", 20000, 0, 8, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Entsichertes Führen einer Waffe", 7500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Unsicheres Lagern einer Waffe im Fahrzeug", 6000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Lagern einer Waffe in einem ungesichertem Container", 2500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Funktionierende Waffe als Schmückstuck", 15000, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Nicht melden von Gebrauch einer Schusswaffe", 5000, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Verantwortung über Waffen (+Entzug Waffenlizenz)", 4500, 0, 3, null));
		
		/*
		 * Tierschutzgesetze
		 */
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Leinenpflicht", 2500, 0, 1, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachtung der Vorraussetzungen für das Halten von Tieren", 4500, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Direkte Verletzung eines Tiers", 6500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Beabsichtige Beeinträchtigung des natürlichen Lebensraums von Tieren", 3500, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Freiheitsberaubung von Tieren", 7500, 0, 6, null));
		
		/*
		 * Grundgesetz
		 */
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Gleichberechtigung (+Schadensersatz)", 10000, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Menschwenwürde (+Schadensersatz)", 10000, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Unterdrückung der freien Meinungsäußerung", 8000, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Errichten von Bauten auf Grundstücken Anderer", 3000, 0, 3, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Missachten der Privatssphäre", 4500, 0, 4, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz gefälschter Dokumente", 6500, 0, 6, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Besitz von polizeilichen Mitteln", 10000, 0, 5, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Brechen der Schweigepflicht (+Schadensersatz)", 5000, 0, 7, null));
		bussgelderFromCatalog.add(new BussgeldGridItem("Vorsätzliches Verschweigen von Straftaten", 5000, 0, 3, null));
		
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
