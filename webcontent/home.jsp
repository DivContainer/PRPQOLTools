<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>



<f:view>
<h:form>
<f:subview id="homeg_sv">
<t:beanprocessing id="g_1" />
<t:rowtitlebar id="g_2" text="PRP QOL Tools" />
<t:rowbodypane id="g_4" >
<t:row id="g_6" >
<t:pane id="g_8" height="100%" width="10%" >
<t:row id="g_9" >
<t:button id="g_7" actionListener="#{d.HomeBean.onStartBussgeldkatalogRechnerButtonPressed}" enabled="#{d.HomeBean.bussgeldProgramButtonEnabled}" text="Bußgeldkatalogrechner" width="100%" />
</t:row>
</t:pane>
<t:coldistance id="g_15" width="10" />
<t:colline id="g_14" height="100%" width="5" />
<t:coldistance id="g_13" width="20" />
<t:pane id="g_10" height="100%" width="90%" >
<t:rowpagebeaninclude id="g_12" pagebeanbinding="#{d.HomeBean.workPlaceProgram}" shownullcontent="false" />
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>

