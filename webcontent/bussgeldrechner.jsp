<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>



<f:view>
<h:form>
<f:subview id="bussgeldrechnerg_sv">
<t:beanprocessing id="g_1" beanbinding="#{d.BussgeldRechnerBean}" />
<t:rowtitlebar id="g_2" text="PRP Bußgeldkatalog-Rechner" />
<t:rowbodypane id="g_4" >
<t:row id="g_6" >
<t:pane id="g_7" height="100%" width="60%" >
<t:row id="g_3" >
<t:label id="g_9" text="Suche:" width="50" />
<t:field id="g_5" actionListener="#{d.BussgeldRechnerBean.sucheStartenButtonPressed}" flush="true" flushtimer="1" text="#{d.BussgeldRechnerBean.searchInput}" width="300" />
</t:row>
<t:rowdistance id="g_14" height="30" />
<t:row id="g_17" >
<t:fixgrid id="g_10" drawoddevenrows="false" focusable="false" height="100%" objectbinding="#{d.BussgeldRechnerBean.bussgeldKatalogGrid}" width="100%" >
<t:gridcol id="g_12" avoidselection="true" text="Bezeichnung" width="50%" >
<t:label id="g_42" text=".{description}" />
</t:gridcol>
<t:gridcol id="g_19" text="Bußgeld" width="20%" >
<t:label id="g_43" text=".{payAmount}" />
</t:gridcol>
<t:gridcol id="g_23" text="Haft" width="10%" >
<t:label id="g_44" text=".{jailTime}" />
</t:gridcol>
<t:gridcol id="g_16" text="Aktion" width="30" >
<t:button id="g_70" actionListener=".{addButtonPressed}" enabled=".{actionEnabled}" focusable="false" text="+" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:pane>
<t:label id="g_32" text="&gt;" width="10" />
<t:pane id="g_27" height="100%" width="40%" >
<t:row id="g_26" >
<t:label id="g_36" text="Presets" width="50" />
<t:combobox id="g_37" validvaluesbinding="#{d.BussgeldRechnerBean.presetsVVB}" width="150" />
<t:coldistance id="g_58" width="10" />
<t:button id="g_56" actionListener="#{d.BussgeldRechnerBean.presetUebernehmenButtonPressed}" text="Übernehmen" width="150" />
</t:row>
<t:rowdistance id="g_34" height="30" />
<t:row id="g_28" >
<t:fixgrid id="g_22" focusable="false" height="300" objectbinding="#{d.BussgeldRechnerBean.bussgeldAssignedGrid}" width="100%" >
<t:gridcol id="g_30" text="Beschreibung" width="50%" >
<t:label id="g_45" text=".{description}" />
</t:gridcol>
<t:gridcol id="g_52" text="Bußgeld" width="20%" >
<t:label id="g_46" text=".{payAmount}" />
</t:gridcol>
<t:gridcol id="g_33" text="Haft" width="20%" >
<t:label id="g_47" text=".{jailTime}" />
</t:gridcol>
<t:gridcol id="g_54" text="Aktion" width="30" >
<t:button id="g_35" actionListener=".{removeButtonPressed}" focusable="false" text="X" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_64" height="30" />
<t:row id="g_60" >
<t:button id="g_40" actionListener="#{d.BussgeldRechnerBean.strafeBerechnenButtonPressed}" text="Strafe berechnen" width="100%" />
</t:row>
<t:rowdistance id="g_65" height="30" />
<t:row id="g_29" >
<t:label id="g_67" text="Bußgeld:" width="150" />
<t:label id="g_68" text="#{d.BussgeldRechnerBean.bussgeldCalculated}" />
<t:label id="g_41" text="$" />
<t:label id="g_48" text="+" />
<t:label id="g_49" text="#{d.BussgeldRechnerBean.licensePointsCalculated}" />
<t:label id="g_50" text="Punkt(e)" />
</t:row>
<t:row id="g_72" >
<t:label id="g_74" text="Haft:" width="150" />
<t:label id="g_75" text="#{d.BussgeldRechnerBean.haftCalculated}" />
<t:label id="g_62" text="Monat(e)" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>

