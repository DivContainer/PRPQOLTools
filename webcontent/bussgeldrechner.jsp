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
<t:rowbodypane id="g_4" background="#595959" >
<t:row id="g_6" >
<t:pane id="g_7" height="100%" width="60%" >
<t:row id="g_3" >
<t:label id="g_9" foreground="#bfbfbf" text="Suche:" width="50" />
<t:field id="g_5" actionListener="#{d.BussgeldRechnerBean.sucheStartenButtonPressed}" background="#bfbfbf" flush="true" flushtimer="500" text="#{d.BussgeldRechnerBean.searchInput}" width="100%" />
</t:row>
<t:rowdistance id="g_14" height="30" />
<t:row id="g_17" >
<t:fixgrid id="g_10" background="#7f7f7f" drawoddevenrows="false" focusable="false" height="100%" objectbinding="#{d.BussgeldRechnerBean.bussgeldKatalogGrid}" width="100%" >
<t:gridcol id="g_12" avoidselection="true" background="#bfbfbf" text="Bezeichnung" width="50%" >
<t:label id="g_42" text=".{description}" />
</t:gridcol>
<t:gridcol id="g_19" background="#bfbfbf" text="Bußgeld" width="20%" >
<t:label id="g_43" text=".{payAmount}" />
</t:gridcol>
<t:gridcol id="g_23" background="#bfbfbf" text="Haft" width="10%" >
<t:label id="g_44" text=".{jailTime}" />
</t:gridcol>
<t:gridcol id="g_16" background="#bfbfbf" text="Aktion" width="30" >
<t:button id="g_70" actionListener=".{addButtonPressed}" background="#bfbfbf" enabled=".{actionEnabled}" focusable="false" text="+" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:pane>
<t:coldistance id="g_77" width="10" />
<t:pane id="g_27" height="100%" width="40%" >
<t:row id="g_26" >
<t:label id="g_36" foreground="#bfbfbf" text="Presets" width="50" />
<t:combobox id="g_37" background="#bfbfbf" foreground="#000000" validvaluesbinding="#{d.BussgeldRechnerBean.presetsVVB}" value="#{d.BussgeldRechnerBean.presetSelection}" width="100%" />
<t:coldistance id="g_58" width="10" />
<t:button id="g_56" actionListener="#{d.BussgeldRechnerBean.presetUebernehmenButtonPressed}" background="#bfbfbf" text="Übernehmen" width="150" />
</t:row>
<t:rowdistance id="g_34" height="30" />
<t:row id="g_28" >
<t:fixgrid id="g_22" background="#7f7f7f" focusable="false" height="300" objectbinding="#{d.BussgeldRechnerBean.bussgeldAssignedGrid}" width="100%" >
<t:gridcol id="g_30" background="#bfbfbf" text="Beschreibung" width="50%" >
<t:label id="g_45" text=".{description}" />
</t:gridcol>
<t:gridcol id="g_52" background="#bfbfbf" text="Bußgeld" width="20%" >
<t:label id="g_46" text=".{payAmount}" />
</t:gridcol>
<t:gridcol id="g_33" background="#bfbfbf" text="Haft" width="20%" >
<t:label id="g_47" text=".{jailTime}" />
</t:gridcol>
<t:gridcol id="g_54" background="#bfbfbf" text="Aktion" width="30" >
<t:button id="g_35" actionListener=".{removeButtonPressed}" background="#bfbfbf" focusable="false" text="X" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_64" height="30" />
<t:row id="g_60" >
<t:button id="g_40" actionListener="#{d.BussgeldRechnerBean.strafeBerechnenButtonPressed}" background="#bfbfbf" text="Strafe berechnen" width="100%" />
</t:row>
<t:rowdistance id="g_65" height="30" />
<t:row id="g_29" >
<t:label id="g_67" foreground="#bfbfbf" text="Bußgeld:" width="150" />
<t:label id="g_68" foreground="#bfbfbf" text="#{d.BussgeldRechnerBean.bussgeldCalculated}" />
<t:label id="g_41" foreground="#bfbfbf" text="$" />
<t:label id="g_48" foreground="#bfbfbf" text="+" />
<t:label id="g_49" foreground="#bfbfbf" text="#{d.BussgeldRechnerBean.licensePointsCalculated}" />
<t:label id="g_50" foreground="#bfbfbf" text="Punkt(e)" />
</t:row>
<t:row id="g_72" >
<t:label id="g_74" foreground="#bfbfbf" text="Haft:" width="150" />
<t:label id="g_75" foreground="#bfbfbf" text="#{d.BussgeldRechnerBean.haftCalculated}" />
<t:label id="g_62" foreground="#bfbfbf" text="Monat(e)" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>

