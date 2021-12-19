<?xml version="1.0" encoding="utf-8"?>
<!--Remove all loaded before really hard-->
<diff>
  <replace sel="/jobs">
    <jobs>
      <#list galaxy.jobs as job>
      <job id="${job.id}"<#if job.name??> name="${job.name}"</#if><#if job.startActive??> startactive="${job.startActive?c}"</#if><#if job.disabled??> disabled="${job.disabled?c}"</#if><#if job.comment??> comment="${job.comment}"</#if><#if job.description??> description="${job.description}"</#if><#if job.ignorecommanderwares??> ignorecommanderwares="${job.ignorecommanderwares?c}"</#if>>
        <#if job.basket??>
        <basket basket="${job.basket}"/>
        </#if>
        <#if job.category??>
        <category faction="${job.category.faction}" tags="${job.category.tags}"<#if job.category.size??> size="${job.category.size}"</#if>/>
        </#if>
        <#if job.order??>
        <orders>
            <order order="${job.order.order}" default="${job.order.defaultOrder?c}">
            <#list job.order.params as key, value>
                <param name="${key}" value="${value}"/>
            </#list>
            </order>
        </orders>
        </#if>
        <#if job.task??>
        <task task="${job.task}" />
        </#if>
        <#if job.quota??>
        <quota <#if job.quota.galaxy?? && job.quota.galaxy != 0>galaxy="${job.quota.galaxy?c}"</#if><#if job.quota.maxgalaxy?? && job.quota.maxgalaxy != 0> maxgalaxy="${job.quota.maxgalaxy?c}"</#if><#if job.quota.cluster?? && job.quota.cluster != 0> cluster="${job.quota.cluster?c}"</#if><#if job.quota.sector?? && job.quota.sector != 0> sector="${job.quota.sector?c}"</#if><#if job.quota.zone?? && job.quota.zone != 0> zone="${job.quota.zone?c}"</#if><#if job.quota.wing?? && job.quota.wing != 0> wing="${job.quota.wing?c}"</#if><#if job.quota.variation?? && job.quota.variation != 0> variation="${job.quota.variation?c}"</#if><#if job.quota.station??> station="${job.quota.station?c}"</#if> />
        </#if>
        <#if job.location??>
        <location <#if job.location.clazz??>class="${job.location.clazz}"</#if><#if job.location.macro??> macro="${job.location.macro}"</#if><#if job.location.regionBasket??> regionbasket="${job.location.regionBasket}"</#if><#if job.location.relation??> relation="${job.location.relation}"</#if><#if job.location.comparison??> comparison="${job.location.comparison}"</#if><#if job.location.matchExtension??> matchextension="${job.location.matchExtension?c}"</#if><#if job.location.faction??> faction="${job.location.faction}"</#if><#if job.location.policeFaction??> policefaction="${job.location.policeFaction}"</#if><#if job.location.factionRace??> factionrace="${job.location.factionRace}"</#if><#if job.location.negateFactionRace??> negatefactionrace="${job.location.negateFactionRace?c}"</#if>/>
        </#if>
        <#if job.encounters??>
        <encounters id="${job.encounters}"/>
        </#if>
        <#if job.environment??>
        <environment buildatshipyard="${job.environment.buildatshipyard?c}"<#if job.environment.gate??> gate="${job.environment.gate?c}"</#if> />
        </#if>
        <#if job.modifiers??>
        <modifiers <#if job.modifiers.commandeerable??>commandeerable="${job.modifiers.commandeerable?c}"</#if><#if job.modifiers.rebuild??> rebuild="${job.modifiers.rebuild?c}"</#if><#if job.modifiers.subordinate??> subordinate="${job.modifiers.subordinate?c}"</#if>  />  
        </#if>
        <#if job.time??>
        <time <#if job.time.interval??>interval="${job.time.interval?c}"</#if><#if job.time.start??> start="${job.time.start?c}"</#if> />
        </#if>
        <#if job.expirationTime??>
        <expirationtime <#if job.expirationTime.min??>min="${job.expirationTime.min?c}"</#if><#if job.expirationTime.max??> max="${job.expirationTime.max?c}"</#if> />
        </#if>
        <#if job.subordinates??>
        <subordinates>
            <#list job.subordinates as subordinate>
                <subordinate job="${subordinate}" />
            </#list>
        </subordinates>
        </#if>
        <#if job.ship??>
        <ship>
          <#if job.ship.select??>
          <select <#if job.ship.select.faction??>faction="${job.ship.select.faction}"</#if><#if job.ship.select.tags??> tags="${job.ship.select.tags}"</#if><#if job.ship.select.size??> size="${job.ship.select.size}"</#if> />
          </#if>
          <#if job.ship.loadout??>
          <loadout>
            <#if job.ship.loadout.level??>
            <level <#if job.ship.loadout.level.min??>min="${job.ship.loadout.level.min?c}"</#if><#if job.ship.loadout.level.max??> max="${job.ship.loadout.level.max?c}"</#if><#if job.ship.loadout.level.exact??> exact="${job.ship.loadout.level.exact?c}"</#if> />
            </#if>
            <#if job.ship.loadout.exact??>
            <variation exact="${job.ship.loadout.exact?c}"/>
            </#if>
          </loadout>
          </#if>
          <#if job.ship.unit??>
          <units>
            <unit <#if job.ship.unit.category??>category="${job.ship.unit.category}"</#if><#if job.ship.unit.min??> min="${job.ship.unit.min?c}"</#if><#if job.ship.unit.max??> max="${job.ship.unit.max?c}"</#if> />
          </units>
          </#if>
          <#if job.ship.owner??>
          <owner <#if job.ship.owner.exact??>exact="${job.ship.owner.exact}"</#if> overridenpc="${job.ship.owner.overridenpc?c}" />
          </#if>
          <#if job.ship.cargo??>
          <cargo>
            <wares <#if job.ship.cargo.multiple??>exact="${job.ship.cargo.multiple?c}"</#if>>
              <#if job.ship.cargo.fillPercent??>
              <fillpercent <#if job.ship.cargo.fillPercent.min??>min="${job.ship.cargo.fillPercent.getMin()}"</#if><#if job.ship.cargo.fillPercent.max??> max="${job.ship.cargo.fillPercent.getMax()}"</#if><#if job.ship.cargo.fillPercent.profile??> profile="${job.ship.cargo.fillPercent.getProfile()}"</#if>/>
              </#if>
            </wares>
          </cargo>
          </#if>
          <#if job.ship.pilot??>
          <pilot <#if job.ship.pilot.macro??>macro="${job.ship.pilot.macro}"</#if>>
            <#if job.ship.pilot.page??>
            <page <#if job.ship.pilot.page.comment??>comment="${job.ship.pilot.page.comment}"</#if><#if job.ship.pilot.page.exact??> exact="${job.ship.pilot.page.exact?c}"</#if>/>
            </#if>
          </pilot>
          </#if>
        </ship>  
        </#if>
        <#if job.masstraffic??>
          <masstraffic <#if job.masstraffic.ref??>ref="${job.masstraffic.ref}"</#if><#if job.masstraffic.relaunchdelay??> relaunchdelay="${job.masstraffic.relaunchdelay?c}"</#if><#if job.masstraffic.respawndelay??> respawndelay="${job.masstraffic.respawndelay?c}"</#if>>
            <#if job.masstraffic.owner??>
            <owner <#if job.masstraffic.owner.exact??>exact="${job.masstraffic.owner.exact}"</#if> overridenpc="${job.masstraffic.owner.overridenpc?c}" />
            </#if>
          </masstraffic>
        </#if>
      </job>
      </#list>

      <!-- MASS TRAFFIC -->
      <!--terran sectors-->
      <job id="masstraffic_terran_civilian">
        <task task="masstraffic.generic"/>
        <location class="sector" factionrace="terran"/>
        <quota sector="1000"/>
        <masstraffic ref="masstraffic_terran_civilian"/>
      </job>
      <!--terran stations-->
      <job id="masstraffic_terran_police">
        <task task="masstraffic.police"/>
        <location class="station" faction="[terran]"/>
        <quota station="5"/>
        <masstraffic ref="masstraffic_terran_police"/>
      </job>
      <job id="masstraffic_terran_criminal">
        <task task="masstraffic.generic"/>
        <location class="station" faction="[terran]"/>
        <quota sector="2" station="1"/>
        <masstraffic ref="masstraffic_terran_civilian" respawndelay="300" relaunchdelay="60">
          <owner exact="faction.criminal" overridenpc="true"/>
        </masstraffic>
      </job>
      <!--pioneers stations-->
      <job id="masstraffic_pioneers_criminal">
        <task task="masstraffic.generic"/>
        <location class="station" faction="[pioneers]"/>
        <quota sector="2" station="1"/>
        <masstraffic ref="masstraffic_terran_civilian" respawndelay="300" relaunchdelay="60">
          <owner exact="faction.criminal" overridenpc="true"/>
        </masstraffic>
      </job>
      <!--terran station modules-->
      <job id="masstraffic_terran_dockarea">
        <task task="masstraffic.generic"/>
        <location class="dockarea" factionrace="terran"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_terran_dockarea"/>
      </job>
      <job id="masstraffic_terran_pier">
        <task task="masstraffic.generic"/>
        <location class="pier" factionrace="terran"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_terran_pier"/>
      </job>
      <job id="masstraffic_terran_storage">
        <task task="masstraffic.generic"/>
        <location class="storage" factionrace="terran"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_terran_storage"/>
      </job>
      <job id="masstraffic_terran_production">
        <task task="masstraffic.generic"/>
        <location class="production" factionrace="terran"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_terran_production"/>
      </job>
      <job id="masstraffic_terran_buildmodule">
        <task task="masstraffic.generic"/>
        <location class="buildmodule" factionrace="terran"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_terran_buildmodule"/>
      </job>
      <job id="masstraffic_terran_defencemodule">
        <task task="masstraffic.generic"/>
        <location class="defencemodule" factionrace="terran"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_terran_defencemodule"/>
      </job>

      <!--argon sectors-->
      <job id="masstraffic_argon_civilian">
        <task task="masstraffic.generic"/>
        <location class="sector" factionrace="argon"/>
        <quota sector="1000"/>
        <masstraffic ref="masstraffic_argon_civilian"/>
      </job>
      <!--argon stations-->
      <job id="masstraffic_argon_police">
        <task task="masstraffic.police"/>
        <location class="station" policefaction="argon"/>
        <quota station="5"/>
        <masstraffic ref="masstraffic_argon_police"/>
      </job>
      <job id="masstraffic_argon_criminal">
        <task task="masstraffic.generic"/>
        <location class="station" faction="[argon, antigone]"/>
        <quota sector="2" station="1"/>
        <masstraffic ref="masstraffic_argon_civilian" respawndelay="300" relaunchdelay="60">
          <owner exact="faction.criminal" overridenpc="true"/>
        </masstraffic>
      </job>
      <!--argon station modules-->
      <job id="masstraffic_argon_dockarea">
        <task task="masstraffic.generic"/>
        <location class="dockarea" factionrace="argon"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_argon_dockarea"/>
      </job>
      <job id="masstraffic_argon_pier">
        <task task="masstraffic.generic"/>
        <location class="pier" factionrace="argon"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_argon_pier"/>
      </job>
      <job id="masstraffic_argon_storage">
        <task task="masstraffic.generic"/>
        <location class="storage" factionrace="argon"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_argon_storage"/>
      </job>
      <job id="masstraffic_argon_production">
        <task task="masstraffic.generic"/>
        <location class="production" factionrace="argon"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_argon_production"/>
      </job>
      <job id="masstraffic_argon_buildmodule">
        <task task="masstraffic.generic"/>
        <location class="buildmodule" factionrace="argon"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_argon_buildmodule"/>
      </job>
      <job id="masstraffic_argon_defencemodule">
        <task task="masstraffic.generic"/>
        <location class="defencemodule" factionrace="argon"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_argon_defencemodule"/>
      </job>

      <job id="masstraffic_antigone_police">
        <task task="masstraffic.police"/>
        <location class="station" policefaction="antigone"/>
        <quota station="5"/>
        <masstraffic ref="masstraffic_antigone_police"/>
      </job>

      <!--teladi sectors-->
      <job id="masstraffic_teladi_civilian">
        <task task="masstraffic.generic"/>
        <location class="sector" factionrace="teladi"/>
        <quota sector="1000"/>
        <masstraffic ref="masstraffic_teladi_civilian"/>
      </job>
      <!--teladi stations-->
      <job id="masstraffic_teladi_police">
        <task task="masstraffic.police"/>
        <location class="station" policefaction="ministry"/>
        <quota station="5"/>
        <masstraffic ref="masstraffic_ministry_police"/>
      </job>
      <job id="masstraffic_teladi_criminal">
        <task task="masstraffic.generic"/>
        <location class="station" faction="[teladi, ministry]"/>
        <quota sector="2" station="1"/>
        <masstraffic ref="masstraffic_teladi_civilian" respawndelay="300" relaunchdelay="60">
          <owner exact="faction.criminal" overridenpc="true"/>
        </masstraffic>
      </job>
      <!--teladi station modules-->
      <job id="masstraffic_teladi_dockarea">
        <task task="masstraffic.generic"/>
        <location class="dockarea" factionrace="teladi"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_teladi_dockarea"/>
      </job>
      <job id="masstraffic_teladi_pier">
        <task task="masstraffic.generic"/>
        <location class="pier" factionrace="teladi"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_teladi_pier"/>
      </job>
      <job id="masstraffic_teladi_storage">
        <task task="masstraffic.generic"/>
        <location class="storage" factionrace="teladi"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_teladi_storage"/>
      </job>
      <job id="masstraffic_teladi_production">
        <task task="masstraffic.generic"/>
        <location class="production" factionrace="teladi"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_teladi_production"/>
      </job>
      <job id="masstraffic_teladi_buildmodule">
        <task task="masstraffic.generic"/>
        <location class="buildmodule" factionrace="teladi"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_teladi_buildmodule"/>
      </job>
      <job id="masstraffic_teladi_defencemodule">
        <task task="masstraffic.generic"/>
        <location class="defencemodule" factionrace="teladi"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_teladi_defencemodule"/>
      </job>

      <!--paranid sectors-->
      <job id="masstraffic_paranid_civilian">
        <task task="masstraffic.generic"/>
        <location class="sector" factionrace="paranid"/>
        <quota sector="1000"/>
        <masstraffic ref="masstraffic_paranid_civilian"/>
      </job>
      <!--paranid stations-->
      <job id="masstraffic_paranid_police">
        <task task="masstraffic.police"/>
        <location class="station" policefaction="paranid"/>
        <quota station="5"/>
        <masstraffic ref="masstraffic_paranid_police"/>
      </job>
      <job id="masstraffic_holyorder_police">
        <task task="masstraffic.police"/>
        <location class="station" policefaction="holyorder"/>
        <quota station="5"/>
        <masstraffic ref="masstraffic_holyorder_police"/>
      </job>
      <job id="masstraffic_paranid_criminal">
        <task task="masstraffic.generic"/>
        <location class="station" faction="[paranid, holyorder]"/>
        <quota sector="2" station="1"/>
        <masstraffic ref="masstraffic_paranid_civilian" respawndelay="300" relaunchdelay="60">
          <owner exact="faction.criminal" overridenpc="true"/>
        </masstraffic>
      </job>
      <!--paranid station modules-->
      <job id="masstraffic_paranid_dockarea">
        <task task="masstraffic.generic"/>
        <location class="dockarea" factionrace="paranid"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_paranid_dockarea"/>
      </job>
      <job id="masstraffic_paranid_pier">
        <task task="masstraffic.generic"/>
        <location class="pier" factionrace="paranid"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_paranid_pier"/>
      </job>
      <job id="masstraffic_paranid_storage">
        <task task="masstraffic.generic"/>
        <location class="storage" factionrace="paranid"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_paranid_storage"/>
      </job>
      <job id="masstraffic_paranid_production">
        <task task="masstraffic.generic"/>
        <location class="production" factionrace="paranid"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_paranid_production"/>
      </job>
      <job id="masstraffic_paranid_buildmodule">
        <task task="masstraffic.generic"/>
        <location class="buildmodule" factionrace="paranid"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_paranid_buildmodule"/>
      </job>
      <job id="masstraffic_paranid_defencemodule">
        <task task="masstraffic.generic"/>
        <location class="defencemodule" factionrace="paranid"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_paranid_defencemodule"/>
      </job>

      <!--habitation modules (using race-appropriate ships, no matter who actually owns it)-->
      <job id="masstraffic_hab_arg_s_01">
        <task task="masstraffic.generic"/>
        <location class="habitation" macro="hab_arg_s_01_macro"/>
        <quota station="50"/>
        <masstraffic ref="masstraffic_argon_civilian"/>
      </job>
      <job id="masstraffic_hab_arg_m_01">
        <task task="masstraffic.generic"/>
        <location class="habitation" macro="hab_arg_m_01_macro"/>
        <quota station="60"/>
        <masstraffic ref="masstraffic_argon_civilian"/>
      </job>
      <job id="masstraffic_hab_arg_l_01">
        <task task="masstraffic.generic"/>
        <location class="habitation" macro="hab_arg_l_01_macro"/>
        <quota station="70"/>
        <masstraffic ref="masstraffic_argon_civilian"/>
      </job>
      <job id="masstraffic_hab_tel_s_01">
        <task task="masstraffic.generic"/>
        <location class="habitation" macro="hab_tel_s_01_macro"/>
        <quota station="50"/>
        <masstraffic ref="masstraffic_teladi_civilian"/>
      </job>
      <job id="masstraffic_hab_tel_m_01">
        <task task="masstraffic.generic"/>
        <location class="habitation" macro="hab_arg_m_01_macro"/>
        <quota station="60"/>
        <masstraffic ref="masstraffic_teladi_civilian"/>
      </job>
      <job id="masstraffic_hab_tel_l_01">
        <task task="masstraffic.generic"/>
        <location class="habitation" macro="hab_tel_l_01_macro"/>
        <quota station="70"/>
        <masstraffic ref="masstraffic_teladi_civilian"/>
      </job>
      <job id="masstraffic_hab_par_s_01">
        <task task="masstraffic.generic"/>
        <location class="habitation" macro="hab_par_s_01_macro"/>
        <quota station="50"/>
        <masstraffic ref="masstraffic_paranid_civilian"/>
      </job>
      <job id="masstraffic_hab_par_m_01">
        <task task="masstraffic.generic"/>
        <location class="habitation" macro="hab_par_m_01_macro"/>
        <quota station="60"/>
        <masstraffic ref="masstraffic_paranid_civilian"/>
      </job>
      <job id="masstraffic_hab_par_l_01">
        <task task="masstraffic.generic"/>
        <location class="habitation" macro="hab_par_l_01_macro"/>
        <quota station="70"/>
        <masstraffic ref="masstraffic_paranid_civilian"/>
      </job>

      <job id="masstraffic_effectzone">
        <task task="masstraffic.generic"/>
        <location class="zone" macro="effectzone_macro"/>
        <quota zone="2000"/>
        <masstraffic ref="masstraffic_argon_civilian"/>
      </job>

    <!-- MASS TRAFFIC -->
      <!--split sectors-->
      <job id="masstraffic_split_civilian">
        <task task="masstraffic.generic"/>
        <location class="sector" factionrace="split"/>
        <quota sector="1000"/>
        <masstraffic ref="masstraffic_split_civilian"/>
      </job>
      <!--split stations-->
      <job id="masstraffic_split_police">
        <task task="masstraffic.police"/>
        <location class="station" faction="[split]"/>
        <quota station="5"/>
        <masstraffic ref="masstraffic_split_police"/>
      </job>
      <job id="masstraffic_split_criminal">
        <task task="masstraffic.generic"/>
        <location class="station" faction="[split]"/>
        <quota sector="2" station="1"/>
        <masstraffic ref="masstraffic_split_civilian" respawndelay="300" relaunchdelay="60">
          <owner exact="faction.criminal" overridenpc="true"/>
        </masstraffic>
      </job>
      <!--freesplit stations-->
      <!-- REMOVE -->
      <job id="masstraffic_freesplit_criminal">
        <task task="masstraffic.generic"/>
        <location class="station" faction="[freesplit]"/>
        <quota sector="2" station="1"/>
        <masstraffic ref="masstraffic_split_civilian" respawndelay="300" relaunchdelay="60">
          <owner exact="faction.criminal" overridenpc="true"/>
        </masstraffic>
      </job>
      <!--split station modules-->
      <job id="masstraffic_split_dockarea">
        <task task="masstraffic.generic"/>
        <location class="dockarea" factionrace="split"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_split_dockarea"/>
      </job>
      <job id="masstraffic_split_pier">
        <task task="masstraffic.generic"/>
        <location class="pier" factionrace="split"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_split_pier"/>
      </job>
      <job id="masstraffic_split_storage">
        <task task="masstraffic.generic"/>
        <location class="storage" factionrace="split"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_split_storage"/>
      </job>
      <job id="masstraffic_split_production">
        <task task="masstraffic.generic"/>
        <location class="production" factionrace="split"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_split_production"/>
      </job>
      <job id="masstraffic_split_buildmodule">
        <task task="masstraffic.generic"/>
        <location class="buildmodule" factionrace="split"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_split_buildmodule"/>
      </job>
      <job id="masstraffic_split_defencemodule">
        <task task="masstraffic.generic"/>
        <location class="defencemodule" factionrace="split"/>
        <quota station="20"/>
        <masstraffic ref="masstraffic_split_defencemodule"/>
      </job>

    </jobs>
  </replace>
</diff>