package managedbeans;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.jsfserver.pagebean.PageBean;

import eventhandler.EventControllerInterface;
import eventhandler.EventID;
import infoobjects.BussgeldGridItem;
import interfaces.GridButtonInterface;

@CCGenClass(expressionBase = "#{d.BussgeldRechnerBean}")

public class BussgeldRechnerBean extends PageBean implements Serializable {

	private static final long serialVersionUID = 566939381724734091L;

	private String searchInput;
	private FIXGRIDListBinding<BussgeldGridItem> bussgeldKatalogGrid;
	private GridButtonInterface bussgeldKatalogGridButtonInterface;
	
	private ValidValuesBinding presetsVVB;
	private FIXGRIDListBinding<BussgeldGridItem> bussgeldAssignedGrid;
	private GridButtonInterface bussgeldAssignedGridButtonInterface;
	private double bussgeldCalculated;
	private int haftCalculated;
	private int licensePointsCalculated;
	private String presetSelection;
	
	private EventControllerInterface eventControllerInterface;
	
	public BussgeldRechnerBean() {
		super();
	}
	
	public void init() {
		getEventControllerInterface().callEvent(EventID.INIT);
	}
	
	public void sucheStartenButtonPressed(ActionEvent event) {
		getEventControllerInterface().callEvent(EventID.SEARCH);
	}
	
	public void presetUebernehmenButtonPressed(ActionEvent event) {
		getEventControllerInterface().callEvent(EventID.APPLY_PRESET);
	}
	
	public void strafeBerechnenButtonPressed(ActionEvent event) {
		getEventControllerInterface().callEvent(EventID.CALCULATE);
	}
	
	public void resetBussgeldAssignedGrid(ActionEvent event) {
		getEventControllerInterface().callEvent(EventID.RESET);
	}
	
	public String getSearchInput() {
		return searchInput;
	}

	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	public FIXGRIDListBinding<BussgeldGridItem> getBussgeldKatalogGrid() {
		return bussgeldKatalogGrid;
	}

	public void setBussgeldKatalogGrid(FIXGRIDListBinding<BussgeldGridItem> bussgeldKatalogGrid) {
		this.bussgeldKatalogGrid = bussgeldKatalogGrid;
	}

	public GridButtonInterface getBussgeldKatalogGridButtonInterface() {
		return bussgeldKatalogGridButtonInterface;
	}

	public void setBussgeldKatalogGridButtonInterface(GridButtonInterface bussgeldKatalogGridButtonInterface) {
		this.bussgeldKatalogGridButtonInterface = bussgeldKatalogGridButtonInterface;
	}

	public ValidValuesBinding getPresetsVVB() {
		return presetsVVB;
	}

	public void setPresetsVVB(ValidValuesBinding presetsVVB) {
		this.presetsVVB = presetsVVB;
	}

	public FIXGRIDListBinding<BussgeldGridItem> getBussgeldAssignedGrid() {
		return bussgeldAssignedGrid;
	}

	public void setBussgeldAssignedGrid(FIXGRIDListBinding<BussgeldGridItem> bussgeldAssignedGrid) {
		this.bussgeldAssignedGrid = bussgeldAssignedGrid;
	}

	public GridButtonInterface getBussgeldAssignedGridButtonInterface() {
		return bussgeldAssignedGridButtonInterface;
	}

	public void setBussgeldAssignedGridButtonInterface(GridButtonInterface bussgeldAssignedGridButtonInterface) {
		this.bussgeldAssignedGridButtonInterface = bussgeldAssignedGridButtonInterface;
	}

	public double getBussgeldCalculated() {
		return bussgeldCalculated;
	}

	public void setBussgeldCalculated(double bussgeldCalculated) {
		this.bussgeldCalculated = bussgeldCalculated;
	}

	public int getHaftCalculated() {
		return haftCalculated;
	}

	public void setHaftCalculated(int haftCalculated) {
		this.haftCalculated = haftCalculated;
	}
	
	public int getLicensePointsCalculated() {
		return licensePointsCalculated;
	}

	public void setLicensePointsCalculated(int licensePointsCalculated) {
		this.licensePointsCalculated = licensePointsCalculated;
	}
	
	public String getPresetSelection() {
		return presetSelection;
	}

	public void setPresetSelection(String presetSelection) {
		this.presetSelection = presetSelection;
	}

	public EventControllerInterface getEventControllerInterface() {
		return eventControllerInterface;
	}

	public void setEventControllerInterface(EventControllerInterface eventControllerInterface) {
		this.eventControllerInterface = eventControllerInterface;
	}

	public String getPageName() {
		return "/bussgeldrechner.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.BussgeldRechnerBean}";
	}
}
