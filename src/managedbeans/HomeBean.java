package managedbeans;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.pagebean.IPageBean;
import org.eclnt.jsfserver.pagebean.PageBean;

import eventhandler.EventController;

@CCGenClass(expressionBase = "#{d.HomeBean}")

public class HomeBean extends PageBean implements Serializable {

	private static final long serialVersionUID = 8926965797029130309L;
	
	private boolean bussgeldProgramButtonEnabled;	
	private IPageBean workPlaceProgram;
	
	private EventController eventController;
	private BussgeldRechnerBean bussgeldRechnerBean;
	
	public HomeBean() {
		super();
		
		this.eventController = new EventController();
		this.bussgeldRechnerBean = new BussgeldRechnerBean();
		
		eventController.setBussgeldRechnerBean(bussgeldRechnerBean);
		eventController.initBussgeldRechnerEventController();
		
		bussgeldRechnerBean.init();
		
		checkButtonAvailability();
	}
	
	public void onStartBussgeldkatalogRechnerButtonPressed(ActionEvent event) {
		this.workPlaceProgram = this.bussgeldRechnerBean;
		
		checkButtonAvailability();
	}
	
	private void checkButtonAvailability() {
		if(workPlaceProgram == bussgeldRechnerBean) {
			setBussgeldProgramButtonEnabled(false);
		} else {
			setBussgeldProgramButtonEnabled(true);
		}
	}

	public IPageBean getWorkPlaceProgram() {
		return workPlaceProgram;
	}
	
	public boolean isBussgeldProgramButtonEnabled() {
		return bussgeldProgramButtonEnabled;
	}

	public void setBussgeldProgramButtonEnabled(boolean bussgeldProgramButtonEnabled) {
		this.bussgeldProgramButtonEnabled = bussgeldProgramButtonEnabled;
	}

	public void setWorkPlaceProgram(IPageBean workPlaceProgram) {
		this.workPlaceProgram = workPlaceProgram;
	}

	public String getPageName() {
		return "/home.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.HomeBean}";
	}
}
