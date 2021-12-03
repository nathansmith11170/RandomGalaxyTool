<?xml version="1.0" encoding="utf-8"?>
<diff xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="region_definitions.xsd">
    <add  sel="//regions">
        <region name="dago1_outer_resources" density="1.5" rotation="0">
            <boundary class="splinetube">
                <size r="6000" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="0.0" outlength="87500" />
                <splineposition x="175000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="-175000" tx="-1" ty="0" tz="0" inlength="87500" outlength="87500" />
                <splineposition x="-175000" y="0" z="0" tx="0" ty="0" tz="1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="87500" outlength="0.0" />
            </boundary>
            <falloff>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_v1" densityfactor="0.25" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_sil_v1" densityfactor="0.25" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_niv_v1" densityfactor="0.25" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ice_xl" densityfactor="0.5" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ore_l" densityfactor="0.5" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_silicon_l" densityfactor="0.5" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <volumetricfog multiplier="0.05" medium="hydrogen" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1252334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="hydrogen" />
                <volumetricfog multiplier="0.05" medium="helium" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="5345334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="helium" />
                <volumetricfog multiplier="0.05" medium="methane" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1289974" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="methane" />
            </fields>
            <resources>
                <resource ware="ore" yield="high" />
                <resource ware="silicon" yield="high" />
                <resource ware="nividium" yield="high" />
                <resource ware="ice" yield="high" />
                <resource ware="hydrogen" yield="high" />
                <resource ware="helium" yield="high" />
                <resource ware="methane" yield="high" />
            </resources>
        </region>
        <region name="dago1_outer_resourcesblue" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="6000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="0.0" outlength="100000" />
                <splineposition x="200000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="-200000" tx="-1" ty="0" tz="0" inlength="100000" outlength="100000" />
                <splineposition x="-200000" y="0" z="0" tx="0" ty="0" tz="1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="100000" outlength="0.0" />
            </boundary>
            <falloff>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_highyield_sil_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_highyield_niv_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ice_xl" densityfactor="0.25" noisescale="1000" minnoisevalue="0.3" maxnoisevalue="0.7"/>
                <asteroid groupref="asteroid_ore_l" densityfactor="0.25" noisescale="1000" minnoisevalue="0.3" maxnoisevalue="0.7"/>
                <asteroid groupref="asteroid_silicon_l" densityfactor="0.25" noisescale="1000" minnoisevalue="0.3" maxnoisevalue="0.7"/>
                <volumetricfog multiplier="0.05" medium="hydrogen" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1252334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="hydrogen" />
                <volumetricfog multiplier="0.05" medium="helium" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="5345334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="helium" />
                <volumetricfog multiplier="0.05" medium="methane" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1289974" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="methane" />
            </fields>
            <resources>
                <resource ware="ore" yield="high" />
                <resource ware="silicon" yield="high" />
                <resource ware="nividium" yield="high" />
                <resource ware="ice" yield="high" />
                <resource ware="hydrogen" yield="high" />
                <resource ware="helium" yield="high" />
                <resource ware="methane" yield="high" />
            </resources>
        </region>
        <region name="dago1_outer_resources2" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="8000" />
                <splineposition x="0" y="0" z="250000" tx="1" ty="0" tz="0" inlength="0.0" outlength="125000" />
                <splineposition x="250000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="125000" outlength="125000" />
                <splineposition x="0" y="0" z="-250000" tx="-1" ty="0" tz="0" inlength="125000" outlength="125000" />
                <splineposition x="-250000" y="0" z="0" tx="0" ty="0" tz="1" inlength="125000" outlength="125000" />
                <splineposition x="0" y="0" z="250000" tx="1" ty="0" tz="0" inlength="125000" outlength="0.0" />
            </boundary>
            <falloff>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_highyield_sil_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_highyield_niv_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ore_l" densityfactor="0.25" noisescale="1000" minnoisevalue="0.3" maxnoisevalue="0.7"/>
                <asteroid groupref="asteroid_silicon_l" densityfactor="0.25" noisescale="1000" minnoisevalue="0.3" maxnoisevalue="0.7"/>
            </fields>
            <resources>
                <resource ware="ore" yield="high" />
                <resource ware="silicon" yield="high" />
                <resource ware="nividium" yield="high" />
            </resources>
        </region>
        <region name="dago2_outer_resources" density="1.5" rotation="0">
            <boundary class="splinetube">
                <size r="6000" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="0.0" outlength="87500" />
                <splineposition x="175000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="-175000" tx="-1" ty="0" tz="0" inlength="87500" outlength="87500" />
                <splineposition x="-175000" y="0" z="0" tx="0" ty="0" tz="1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="87500" outlength="0.0" />
            </boundary>
            <falloff>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_v1" densityfactor="0.3" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_sil_v1" densityfactor="0.2" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_niv_v1" densityfactor="0.25" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ice_xl" densityfactor="0.5" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ore_l" densityfactor="0.55" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_silicon_l" densityfactor="0.45" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <volumetricfog multiplier="0.05" medium="hydrogen" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1252334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="hydrogen" />
                <volumetricfog multiplier="0.05" medium="helium" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="5345334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="helium" />
                <volumetricfog multiplier="0.05" medium="methane" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1289974" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="methane" />
            </fields>
            <resources>
                <resource ware="ore" yield="high" />
                <resource ware="silicon" yield="high" />
                <resource ware="nividium" yield="high" />
                <resource ware="ice" yield="high" />
                <resource ware="hydrogen" yield="high" />
                <resource ware="helium" yield="high" />
                <resource ware="methane" yield="high" />
            </resources>
        </region>
        <region name="dago2_outer_resourcesred" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="6000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="0.0" outlength="100000" />
                <splineposition x="200000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="-200000" tx="-1" ty="0" tz="0" inlength="100000" outlength="100000" />
                <splineposition x="-200000" y="0" z="0" tx="0" ty="0" tz="1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="100000" outlength="0.0" />
            </boundary>
            <falloff>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_highyield_sil_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_highyield_niv_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ice_xl" densityfactor="0.25" noisescale="1000" minnoisevalue="0.3" maxnoisevalue="0.7"/>
                <asteroid groupref="asteroid_ore_l" densityfactor="0.25" noisescale="1000" minnoisevalue="0.3" maxnoisevalue="0.7"/>
                <asteroid groupref="asteroid_silicon_l" densityfactor="0.25" noisescale="1000" minnoisevalue="0.3" maxnoisevalue="0.7"/>
                <volumetricfog multiplier="0.05" medium="hydrogen" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1252334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="hydrogen" />
                <volumetricfog multiplier="0.05" medium="helium" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="5345334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="helium" />
                <volumetricfog multiplier="0.05" medium="methane" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1289974" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="methane" />
            </fields>
            <resources>
                <resource ware="ore" yield="high" />
                <resource ware="silicon" yield="high" />
                <resource ware="nividium" yield="high" />
                <resource ware="ice" yield="high" />
                <resource ware="hydrogen" yield="high" />
                <resource ware="helium" yield="high" />
                <resource ware="methane" yield="high" />
            </resources>
        </region>
        <region name="dago3_outer_resources" density="1.5" rotation="0">
            <boundary class="splinetube">
                <size r="6000" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="0.0" outlength="87500" />
                <splineposition x="175000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="-175000" tx="-1" ty="0" tz="0" inlength="87500" outlength="87500" />
                <splineposition x="-175000" y="0" z="0" tx="0" ty="0" tz="1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="87500" outlength="0.0" />
            </boundary>
            <falloff>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_v1" densityfactor="0.2" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_sil_v1" densityfactor="0.3" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_niv_v1" densityfactor="0.25" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ice_xl" densityfactor="0.5" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ore_l" densityfactor="0.45" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_silicon_l" densityfactor="0.55" noisescale="1000" minnoisevalue="0.8" maxnoisevalue="1"/>
                <volumetricfog multiplier="0.05" medium="hydrogen" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1252334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="hydrogen" />
                <volumetricfog multiplier="0.05" medium="helium" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="5345334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="helium" />
                <volumetricfog multiplier="0.05" medium="methane" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1289974" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="methane" />
            </fields>
            <resources>
                <resource ware="ore" yield="high" />
                <resource ware="silicon" yield="high" />
                <resource ware="nividium" yield="high" />
                <resource ware="ice" yield="high" />
                <resource ware="hydrogen" yield="high" />
                <resource ware="helium" yield="high" />
                <resource ware="methane" yield="high" />
            </resources>
        </region>
        <region name="dago3_outer_resourcesgreen" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="6000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="0.0" outlength="100000" />
                <splineposition x="200000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="-200000" tx="-1" ty="0" tz="0" inlength="100000" outlength="100000" />
                <splineposition x="-200000" y="0" z="0" tx="0" ty="0" tz="1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="100000" outlength="0.0" />
            </boundary>
            <falloff>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_highyield_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_highyield_sil_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_highyield_niv_v1" densityfactor="0.125" noisescale="1000" minnoisevalue="0.2" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ice_xl" densityfactor="0.25" noisescale="1000" minnoisevalue="0.3" maxnoisevalue="0.7"/>
                <asteroid groupref="asteroid_ore_l" densityfactor="0.25" noisescale="1000" minnoisevalue="0.3" maxnoisevalue="0.7"/>
                <asteroid groupref="asteroid_silicon_l" densityfactor="0.25" noisescale="1000" minnoisevalue="0.3" maxnoisevalue="0.7"/>
                <volumetricfog multiplier="0.05" medium="hydrogen" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1252334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="hydrogen" />
                <volumetricfog multiplier="0.05" medium="helium" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="5345334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="helium" />
                <volumetricfog multiplier="0.05" medium="methane" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1289974" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="methane" />
            </fields>
            <resources>
                <resource ware="ore" yield="high" />
                <resource ware="silicon" yield="high" />
                <resource ware="nividium" yield="high" />
                <resource ware="ice" yield="high" />
                <resource ware="hydrogen" yield="high" />
                <resource ware="helium" yield="high" />
                <resource ware="methane" yield="high" />
            </resources>
        </region>
        <region name="dago_solid_high_ore" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="10000" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="0.0" outlength="87500" />
                <splineposition x="175000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="-175000" tx="-1" ty="0" tz="0" inlength="87500" outlength="87500" />
                <splineposition x="-175000" y="0" z="0" tx="0" ty="0" tz="1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="87500" outlength="0.0" />
            </boundary>
            <falloff>
                <radial>
                    <step position="0.0" value="1.0" />
                    <step position="0.3" value="1.0" />
                    <step position="0.5" value="0.9" />
                    <step position="0.9" value="0.4" />
                    <step position="1.0" value="0.0" />
                </radial>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ore_xl" densityfactor="0.55" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ore_l" densityfactor="0.55" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_nividium_m" densityfactor="0.125" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_silicon_m" densityfactor="0.125" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ice_m" densityfactor="0.125" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <debris groupref="debris_s" minnoisevalue="0.4" maxnoisevalue="0.8"/>
            </fields>
            <resources>
                <resource ware="ore" yield="high" />
                <resource ware="silicon" yield="medium" />
                <resource ware="nividium" yield="medium" />
                <resource ware="ice" yield="medium" />
            </resources>
        </region>
        <region name="dago_solid_high_silicon" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="10000" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="0.0" outlength="87500" />
                <splineposition x="175000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="-175000" tx="-1" ty="0" tz="0" inlength="87500" outlength="87500" />
                <splineposition x="-175000" y="0" z="0" tx="0" ty="0" tz="1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="87500" outlength="0.0" />
            </boundary>
            <falloff>
                <radial>
                    <step position="0.0" value="1.0" />
                    <step position="0.3" value="1.0" />
                    <step position="0.5" value="0.9" />
                    <step position="0.9" value="0.4" />
                    <step position="1.0" value="0.0" />
                </radial>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_silicon_xl" densityfactor="0.55" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_silicon_l" densityfactor="0.55" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_nividium_m" densityfactor="0.125" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ore_m" densityfactor="0.125" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ice_m" densityfactor="0.125" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <debris groupref="debris_m" minnoisevalue="0.4" maxnoisevalue="0.8"/>
            </fields>
            <resources>
                <resource ware="ore" yield="medium" />
                <resource ware="silicon" yield="high" />
                <resource ware="nividium" yield="medium" />
                <resource ware="ice" yield="medium" />
            </resources>
        </region>
        <region name="dago_solid_high_ice" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="10000" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="0.0" outlength="87500" />
                <splineposition x="175000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="-175000" tx="-1" ty="0" tz="0" inlength="87500" outlength="87500" />
                <splineposition x="-175000" y="0" z="0" tx="0" ty="0" tz="1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="87500" outlength="0.0" />
            </boundary>
            <falloff>
                <radial>
                    <step position="0.0" value="1.0" />
                    <step position="0.3" value="1.0" />
                    <step position="0.5" value="0.9" />
                    <step position="0.9" value="0.4" />
                    <step position="1.0" value="0.0" />
                </radial>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ice_xl" densityfactor="0.55" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ice_l" densityfactor="0.55" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_nividium_m" densityfactor="0.125" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ore_m" densityfactor="0.125" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_silicon_m" densityfactor="0.125" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <debris groupref="debris_m" minnoisevalue="0.4" maxnoisevalue="0.8"/>
            </fields>
            <resources>
                <resource ware="ore" yield="medium" />
                <resource ware="silicon" yield="medium" />
                <resource ware="nividium" yield="medium" />
                <resource ware="ice" yield="high" />
            </resources>
        </region>
        <region name="dago_solid_high_all" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="10000" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="0.0" outlength="87500" />
                <splineposition x="175000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="-175000" tx="-1" ty="0" tz="0" inlength="87500" outlength="87500" />
                <splineposition x="-175000" y="0" z="0" tx="0" ty="0" tz="1" inlength="87500" outlength="87500" />
                <splineposition x="0" y="0" z="175000" tx="1" ty="0" tz="0" inlength="87500" outlength="0.0" />
            </boundary>
            <falloff>
                <radial>
                    <step position="0.0" value="1.0" />
                    <step position="0.3" value="1.0" />
                    <step position="0.5" value="0.9" />
                    <step position="0.9" value="0.4" />
                    <step position="1.0" value="0.0" />
                </radial>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ore_xl" densityfactor="0.3" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_silicon_xl" densityfactor="0.3" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ice_xl" densityfactor="0.3" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_nividium_l" densityfactor="0.3" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_highyield_niv_v1" densityfactor="0.3" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <debris groupref="debris_xl" minnoisevalue="0.4" maxnoisevalue="0.8"/>
            </fields>
            <resources>
                <resource ware="ore" yield="high" />
                <resource ware="silicon" yield="high" />
                <resource ware="nividium" yield="high" />
                <resource ware="ice" yield="high" />
            </resources>
        </region>
        <region name="dago_liquid_blue" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="30000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="0.0" outlength="100000" />
                <splineposition x="200000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="-200000" tx="-1" ty="0" tz="0" inlength="100000" outlength="100000" />
                <splineposition x="-200000" y="0" z="0" tx="0" ty="0" tz="1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="100000" outlength="0.0" />
            </boundary>
            <falloff>
                <radial>
                    <step position="0.0" value="1.0" />
                    <step position="0.3" value="1.0" />
                    <step position="0.5" value="0.9" />
                    <step position="0.9" value="0.4" />
                    <step position="1.0" value="0.0" />
                </radial>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <volumetricfog multiplier="0.05" medium="hydrogen" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1252334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="hydrogen" />
                <volumetricfog multiplier="0.05" medium="helium" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="5345334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="helium" />
                <volumetricfog multiplier="0.05" medium="methane" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1289974" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="methane" />
            </fields>
            <resources>
                <resource ware="hydrogen" yield="medium" />
                <resource ware="helium" yield="medium" />
                <resource ware="methane" yield="high" />
            </resources>
        </region>
        <region name="dago_liquid_red" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="30000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="0.0" outlength="100000" />
                <splineposition x="200000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="-200000" tx="-1" ty="0" tz="0" inlength="100000" outlength="100000" />
                <splineposition x="-200000" y="0" z="0" tx="0" ty="0" tz="1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="100000" outlength="0.0" />
            </boundary>
            <falloff>
                <radial>
                    <step position="0.0" value="1.0" />
                    <step position="0.3" value="1.0" />
                    <step position="0.5" value="0.9" />
                    <step position="0.9" value="0.4" />
                    <step position="1.0" value="0.0" />
                </radial>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <volumetricfog multiplier="0.05" medium="hydrogen" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1252334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="hydrogen" />
                <volumetricfog multiplier="0.05" medium="helium" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.10" rotation="360" rotationvariation="0.0" noisescale="15000" seed="5345334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="helium" />
                <volumetricfog multiplier="0.05" medium="methane" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.10" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1289974" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="methane" />
            </fields>
            <resources>
                <resource ware="hydrogen" yield="high" />
                <resource ware="helium" yield="medium" />
                <resource ware="methane" yield="medium" />
            </resources>
        </region>
        <region name="dago_liquid_green" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="30000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="0.0" outlength="100000" />
                <splineposition x="200000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="-200000" tx="-1" ty="0" tz="0" inlength="100000" outlength="100000" />
                <splineposition x="-200000" y="0" z="0" tx="0" ty="0" tz="1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="100000" outlength="0.0" />
            </boundary>
            <falloff>
                <radial>
                    <step position="0.0" value="1.0" />
                    <step position="0.3" value="1.0" />
                    <step position="0.5" value="0.9" />
                    <step position="0.9" value="0.4" />
                    <step position="1.0" value="0.0" />
                </radial>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <volumetricfog multiplier="0.05" medium="hydrogen" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.10" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1252334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="hydrogen" />
                <volumetricfog multiplier="0.05" medium="helium" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="5345334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="helium" />
                <volumetricfog multiplier="0.05" medium="methane" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.10" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1289974" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="methane" />            </fields>
            <resources>
                <resource ware="hydrogen" yield="medium" />
                <resource ware="helium" yield="high" />
                <resource ware="methane" yield="medium" />
            </resources>
        </region>
        <region name="dago_liquid_white" density="1.0" rotation="0">
            <boundary class="splinetube">
                <size r="30000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="0.0" outlength="100000" />
                <splineposition x="200000" y="0" z="0" tx="0" ty="0" tz="-1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="-200000" tx="-1" ty="0" tz="0" inlength="100000" outlength="100000" />
                <splineposition x="-200000" y="0" z="0" tx="0" ty="0" tz="1" inlength="100000" outlength="100000" />
                <splineposition x="0" y="0" z="200000" tx="1" ty="0" tz="0" inlength="100000" outlength="0.0" />
            </boundary>
            <falloff>
                <radial>
                    <step position="0.0" value="1.0" />
                    <step position="0.3" value="1.0" />
                    <step position="0.5" value="0.9" />
                    <step position="0.9" value="0.4" />
                    <step position="1.0" value="0.0" />
                </radial>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <volumetricfog multiplier="0.05" medium="hydrogen" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1252334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="hydrogen" />
                <volumetricfog multiplier="0.05" medium="helium" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="5345334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="helium" />
                <volumetricfog multiplier="0.05" medium="methane" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.20" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1289974" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="methane" />            
            </fields>
            <resources>
                <resource ware="hydrogen" yield="high" />
                <resource ware="helium" yield="high" />
                <resource ware="methane" yield="high" />
            </resources>
        </region>
        <region name="dago_mixed_one" density="0.10" rotation="0">
            <boundary class="sphere">
                <size r="250000" />
            </boundary>
            <falloff>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ore_s" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ice_s" minnoisevalue="0.4" maxnoisevalue="0.8"/>
            </fields>
            <resources>
                <resource ware="ore" yield="medium" />
                <resource ware="ice" yield="medium" />
            </resources>
        </region>
        <region name="dago_mixed_two" density="0.10" rotation="0">
            <boundary class="sphere">
                <size r="250000" />
            </boundary>
            <falloff>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_silicon_s" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <volumetricfog multiplier="0.05" medium="hydrogen" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.05" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1252334" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="hydrogen" />
            </fields>
            <resources>
                <resource ware="silicon" yield="medium" />
                <resource ware="hydrogen" yield="medium" />
            </resources>
        </region>
        <region name="dago_mixed_three" density="0.10" rotation="0">
            <boundary class="sphere">
                <size r="250000" />
            </boundary>
            <falloff>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ice_s" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <volumetricfog multiplier="0.05" medium="methane" texture="assets/textures/environments/volumefog\vf_smoothclouds_01-small" lodrule="nebulafar" size="30000" sizevariation="0.4" densityfactor="0.10" rotation="360" rotationvariation="0.0" noisescale="15000" seed="1289974" minnoisevalue="0.4" maxnoisevalue="1.0" distancefactor="0.5" />
                <nebula resources="methane" />
            </fields>
            <resources>
                <resource ware="ice" yield="medium" />
                <resource ware="methane" yield="medium" />
            </resources>
        </region>
        <region name="dago_mixed_four" density="0.10" rotation="0">
            <boundary class="sphere">
                <size r="250000" />
            </boundary>
            <falloff>
            </falloff>
            <fields>
                <object groupref="lockboxes_extra" densityfactor="0.00005" minnoisevalue="0.1" maxnoisevalue="1"/>
                <asteroid groupref="asteroid_ore_s" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_silicon_s" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_nividium_s" minnoisevalue="0.4" maxnoisevalue="0.8"/>
                <asteroid groupref="asteroid_ice_s" minnoisevalue="0.4" maxnoisevalue="0.8"/>
            </fields>
            <resources>
                <resource ware="ore" yield="medium" />
                <resource ware="silicon" yield="medium" />
                <resource ware="nividium" yield="medium" />
                <resource ware="ice" yield="medium" />
            </resources>
        </region>
    </add>
</diff>