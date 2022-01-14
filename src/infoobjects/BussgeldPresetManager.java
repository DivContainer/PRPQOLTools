package infoobjects;

import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;

import interfaces.GridButtonInterface;
import managedbeans.BussgeldRechnerBean;

public class BussgeldPresetManager {
	
	public void initPreset(ValidValuesBinding presetSelectionVVB) {
		presetSelectionVVB.addValidValue(null, "Bitte w‰hlen...");
		presetSelectionVVB.addValidValue("10-80", "Verfolgungsjagd (10-80)");
		presetSelectionVVB.addValidValue("METHZELT", "Methzelt");
	}
	
	public void applyPreset(String presetIdent, BussgeldRechnerBean bussgeldRechnerBean, FIXGRIDListBinding<BussgeldGridItem> targetGrid) {
		switch(presetIdent) {
		case "10-80":
			targetGrid.getItems().add(new BussgeldGridItem("Gef‰hrlicher Eingriff in den Straﬂenverkehr", 7500, 1, 3, null));
			targetGrid.getItems().add(new BussgeldGridItem("Fahren abseits der Straﬂe", 3000, 0, 2, null));
			targetGrid.getItems().add(new BussgeldGridItem("Fahren auf der falschen Straﬂenseite", 5000, 1, 3, null));
			targetGrid.getItems().add(new BussgeldGridItem("R¸cksichtsloses Fahren", 6000, 1, 6, null));
			targetGrid.getItems().add(new BussgeldGridItem("Missachten von Straﬂenmarkierungen, Verkehrszeichen & Lichtanlagen", 2500, 1, 2, null));
			targetGrid.getItems().add(new BussgeldGridItem("Missachten polizeilicher Anweisungen", 5000, 0, 2, null));
			break;
		case "METHZELT":
			targetGrid.getItems().add(new BussgeldGridItem("Besitz/Handel von Drogen", 15000, 0, 7, null));
			targetGrid.getItems().add(new BussgeldGridItem("Herstellung von Methamphetamin", 20000, 0, 9, null));
			targetGrid.getItems().add(new BussgeldGridItem("Umweltverschmutzung", 2000, 0, 2, null));
			break;
		default:
			break;
		}
		
		for(BussgeldGridItem item : targetGrid.getItems()) {
			item.setGridButtonInterface(new GridButtonInterface() {
				
				@Override
				public void removeButtonPressed() {
					targetGrid.getItems().remove(item);
					
					bussgeldRechnerBean.setBussgeldCalculated(0);
					bussgeldRechnerBean.setLicensePointsCalculated(0);
					bussgeldRechnerBean.setHaftCalculated(0);
				}
				
				@Override
				public void addButtonPressed() {}
			});
		}
	}
}