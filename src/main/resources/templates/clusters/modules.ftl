<?xml version='1.0' encoding='utf-8'?>
<diff>
  <replace sel="/modules/module[@id='prod_gen_advancedelectronics']/compatibilities/limits/@production">8</replace>
  <replace sel="/modules/module[@id='prod_gen_advancedelectronics']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_gen_antimattercells']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_gen_antimattercells']/compatibilities/production[@ware='antimattercells']/@chance">95</replace>
  <remove sel="/modules/module[@id='prod_gen_antimattercells']/compatibilities/production[@ware='energycells']"/>
  <remove sel="/modules/module[@id='prod_gen_antimattercells']/compatibilities/production[@ware='graphene']"/>
  <remove sel="/modules/module[@id='prod_gen_antimattercells']/compatibilities/production[@ware='superfluidcoolant']"/>
  <replace sel="/modules/module[@id='prod_gen_antimatterconverters']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_gen_antimatterconverters']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_gen_advancedcomposites']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_gen_advancedcomposites']/compatibilities/production[@ware='advancedcomposites']/@chance">95</replace>
  <remove sel="/modules/module[@id='prod_gen_advancedcomposites']/compatibilities/production[@ware='graphene']"/>
  <replace sel="/modules/module[@id='prod_gen_claytronics']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_gen_claytronics']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_gen_dronecomponents']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_gen_dronecomponents']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_gen_energycells']/compatibilities/production[@ware='energycells']/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_gen_energycells']/compatibilities/production[@ware='antimattercells']">
    <limits production="6"/>
  </replace>
  <remove sel="/modules/module[@id='prod_gen_energycells']/compatibilities/production[@ware='graphene']"/>
  <remove sel="/modules/module[@id='prod_gen_energycells']/compatibilities/production[@ware='superfluidcoolant']"/>
  <replace sel="/modules/module[@id='prod_gen_engineparts']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_gen_engineparts']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_gen_fieldcoils']/compatibilities/limits/@production">8</replace>
  <replace sel="/modules/module[@id='prod_gen_fieldcoils']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_gen_hullparts']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_gen_hullparts']/compatibilities">
    <limits production="10"/>
  </add>
  <replace sel="/modules/module[@id='prod_gen_graphene']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_gen_graphene']/compatibilities/production[@ware='graphene']/@chance">95</replace>
  <remove sel="/modules/module[@id='prod_gen_graphene']/compatibilities/production[@ware='antimattercells']"/>
  <remove sel="/modules/module[@id='prod_gen_graphene']/compatibilities/production[@ware='energycells']"/>
  <remove sel="/modules/module[@id='prod_gen_graphene']/compatibilities/production[@ware='superfluidcoolant']"/>
  <replace sel="/modules/module[@id='prod_gen_microchips']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_gen_microchips']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_gen_missilecomponents']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_gen_missilecomponents']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_gen_superfluidcoolant']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_gen_superfluidcoolant']/compatibilities/production[@ware='superfluidcoolant']/@chance">95</replace>
  <remove sel="/modules/module[@id='prod_gen_superfluidcoolant']/compatibilities/production[@ware='antimattercells']"/>
  <remove sel="/modules/module[@id='prod_gen_superfluidcoolant']/compatibilities/production[@ware='energycells']"/>
  <remove sel="/modules/module[@id='prod_gen_superfluidcoolant']/compatibilities/production[@ware='graphene']"/>
  <replace sel="/modules/module[@id='prod_gen_plasmaconductors']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_gen_plasmaconductors']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_gen_quantumtubes']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_gen_quantumtubes']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_gen_refinedmetals']/compatibilities/production[@ware='refinedmetals']/@chance">95</replace>
  <remove sel="/modules/module[@id='prod_gen_refinedmetals']/compatibilities/production[@ref='prod_gen_siliconwafers']"/>
  <replace sel="/modules/module[@id='prod_gen_scanningarrays']/compatibilities/limits/@production">8</replace>
  <replace sel="/modules/module[@id='prod_gen_scanningarrays']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_gen_shieldcomponents']/compatibilities/limits/@production">8</replace>
  <replace sel="/modules/module[@id='prod_gen_shieldcomponents']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_gen_siliconwafers']/compatibilities/production[@ware='siliconwafers']/@chance">95</replace>
  <remove sel="/modules/module[@id='prod_gen_siliconwafers']/compatibilities/production[@ref='prod_gen_refinedmetals']"/>
  <replace sel="/modules/module[@id='prod_gen_smartchips']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_gen_smartchips']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_gen_spices']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_gen_spices']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_gen_turretcomponents']/compatibilities/limits/@production">8</replace>
  <replace sel="/modules/module[@id='prod_gen_turretcomponents']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_gen_water']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_gen_water']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_gen_weaponcomponents']/compatibilities/limits/@production">8</replace>
  <replace sel="/modules/module[@id='prod_gen_weaponcomponents']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_arg_meat']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_arg_meat']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_arg_medicalsupplies']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_arg_medicalsupplies']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_arg_foodrations']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_arg_foodrations']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_arg_spacefuel']/compatibilities/production/@chance">80</replace>
  <add sel="/modules/module[@id='prod_arg_spacefuel']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_arg_wheat']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_arg_wheat']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_par_majadust']/compatibilities/production/@chance">80</replace>
  <add sel="/modules/module[@id='prod_par_majadust']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_par_majasnails']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_par_majasnails']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_par_medicalsupplies']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_par_medicalsupplies']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_par_sojabeans']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_par_sojabeans']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_par_sojahusk']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_par_sojahusk']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_tel_advancedcomposites']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_tel_advancedcomposites']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_tel_engineparts']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_tel_engineparts']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_tel_hullparts']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_tel_hullparts']/compatibilities">
    <limits production="10"/>
  </add>
  <replace sel="/modules/module[@id='prod_tel_scanningarrays']/compatibilities/limits/@production">8</replace>
  <replace sel="/modules/module[@id='prod_tel_scanningarrays']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_tel_medicalsupplies']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_tel_medicalsupplies']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_tel_nostropoil']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_tel_nostropoil']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_tel_spaceweed']/compatibilities/production/@chance">80</replace>
  <add sel="/modules/module[@id='prod_tel_spaceweed']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_tel_sunriseflowers']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_tel_sunriseflowers']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_tel_swampplant']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_tel_swampplant']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_tel_teladianium']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_tel_teladianium']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_sca_majasnails']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_sca_majasnails']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_sca_foodrations']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_sca_foodrations']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_sca_meat']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_sca_meat']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_sca_wheat']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_sca_wheat']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_xen_energycells']/compatibilities/limits/@production">2</replace>
  <replace sel="/modules/module[@id='prod_xen_energycells']/compatibilities/production/@chance">80</replace>
  <replace sel="/modules/module[@id='prod_spl_medicalsupplies']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_spl_medicalsupplies']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_spl_scruffinfruits']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_spl_scruffinfruits']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_spl_cheltmeat']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_spl_cheltmeat']/compatibilities">
    <limits production="8"/>
  </add>
  <replace sel="/modules/module[@id='prod_ter_computronicsubstrate']/compatibilities/limits/@production">2</replace>
  <replace sel="/modules/module[@id='prod_ter_computronicsubstrate']/compatibilities/production/@chance">95</replace>
  <replace sel="/modules/module[@id='prod_ter_medicalsupplies']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_ter_medicalsupplies']/compatibilities">
    <limits production="4"/>
  </add>
  <replace sel="/modules/module[@id='prod_ter_metallicmicrolattice']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_ter_metallicmicrolattice']/compatibilities">
    <limits production="2"/>
  </add>
  <replace sel="/modules/module[@id='prod_ter_proteinpaste']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_ter_proteinpaste']/compatibilities/production[@ware='proteinpaste']/@chance">95</replace>
  <remove sel="/modules/module[@id='prod_ter_proteinpaste']/compatibilities/production[@ware='medicalsupplies']"/>
  <remove sel="/modules/module[@id='prod_ter_proteinpaste']/compatibilities/production[@ware='terranmre']"/>
  <replace sel="/modules/module[@id='prod_ter_terranmre']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_ter_terranmre']/compatibilities/production[@ware='terranmre']/@chance">95</replace>
  <remove sel="/modules/module[@id='prod_ter_terranmre']/compatibilities/production[@ware='proteinpaste']"/>
  <replace sel="/modules/module[@id='prod_ter_siliconcarbide']/compatibilities/limits/@production">4</replace>
  <replace sel="/modules/module[@id='prod_ter_siliconcarbide']/compatibilities/production/@chance">95</replace>
  <add sel="/modules/module[@id='prod_ter_stimulants']/compatibilities">
    <limits production="4"/>
  </add>
</diff>