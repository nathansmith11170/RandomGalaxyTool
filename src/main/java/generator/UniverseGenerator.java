package generator;

import model.Cluster;
import model.Galaxy;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class UniverseGenerator {

    public static final String CLUSTERS = "clusters";
    private final FreemarkerConfiguration freemarkerConfiguration;

    private final ZoneConnectionProcessor zoneConnectionProcessor;
    private final BeltProcessor beltProcessor;
    private final FactionLogicProcessor factionLogicProcessor;
    private final SpaceObjectProcessor spaceObjectProcessor;
    private final DataVaultProcessor dataVaultProcessor;

    private final Randomizer randomizer;

    public UniverseGenerator(Galaxy galaxy) {
        randomizer = new Randomizer(galaxy.getSeed());
        freemarkerConfiguration = new FreemarkerConfiguration();
        zoneConnectionProcessor = new ZoneConnectionProcessor();
        beltProcessor = new BeltProcessor(randomizer);
        factionLogicProcessor = new FactionLogicProcessor();
        this.spaceObjectProcessor = new SpaceObjectProcessor(randomizer);
        this.dataVaultProcessor = new DataVaultProcessor(randomizer);
    }

    public byte[] generateUniverse(Galaxy galaxy) throws IOException, TemplateException, URISyntaxException {
        runProcessors(galaxy);
        return generateOutput(galaxy);
    }

    private void runProcessors(Galaxy galaxy) {
        for (Cluster cluster : galaxy.getClusters()) {
            zoneConnectionProcessor.processConnections(galaxy, cluster);
            beltProcessor.processBelts(galaxy, cluster);
            factionLogicProcessor.processFactionLogicData(galaxy, cluster);
            spaceObjectProcessor.processSpaceObjects(galaxy, cluster);
        }
        dataVaultProcessor.generateDataVaultData(galaxy);
    }

    private byte[] generateOutput(Galaxy galaxy) throws IOException, TemplateException, URISyntaxException {
        Configuration cfg = freemarkerConfiguration.configure();
        Map<String, Object> root = new HashMap<>();
        root.put("galaxy", galaxy);

        ArrayList<ZipEntry> zipEntries = new ArrayList<>();
        ArrayList<byte[]> files = new ArrayList<>();

        String path = "output/" + galaxy.getGalaxyName() + "/maps/" + galaxy.getGalaxyName() + "/zones.xml";
        ByteArrayOutputStream tempFile = generateZones( cfg, root, CLUSTERS );
        ZipEntry tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/maps/" + galaxy.getGalaxyName() + "/sectors.xml";
        tempFile = generateSectors( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/maps/" + galaxy.getGalaxyName() + "/clusters.xml";
        tempFile = generateClusters( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/maps/" + galaxy.getGalaxyName() + "/galaxy.xml";
        tempFile = generateUniverse( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/index/macros.xml";
        tempFile = generateMacros( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/libraries/mapdefaults.xml";
        tempFile = generateMapDefaults( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/content.xml";
        tempFile = generateContent( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/libraries/god.xml";
        tempFile = generateGod( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/libraries/jobs.xml";
        tempFile = generateJobs( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/libraries/gamestarts.xml";
        tempFile = generateGameStart( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/md/FactionLogic.xml";
        tempFile = generateMdFixFileOne( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/md/Drain_Stations.xml";
        tempFile = generateMdFixFileTwo( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/md/playerreputation.xml";
        tempFile = generateMdFixFileThree( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/md/x4ep1_war_subscriptions.xml";
        tempFile = generateMdFixFileFour( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/md/CustomGameStart.xml";
        tempFile = generateMdFixFileFive( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/md/PlacedObjects.xml";
        tempFile = generatePlacedObjects( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/assets/environments/cluster/empty_space.xml";
        tempFile = generateAssetsOne( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        path = "output/" + galaxy.getGalaxyName() + "/index/components.xml";
        tempFile = generateAssetsTwo( cfg, root, CLUSTERS );
        tempZip = new ZipEntry( path );
        tempZip.setSize( tempFile.size() );
        zipEntries.add( tempZip );
        files.add( tempFile.toByteArray() );

        return copyCoreResources(root, zipEntries, files, galaxy);
    }

    private byte[] copyCoreResources(Map<String, Object> root, ArrayList<ZipEntry> zips, ArrayList<byte[]> files, Galaxy galaxy) throws IOException, URISyntaxException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream( baos );

        for( int i = 0; i < zips.size(); i++ ) {
            zos.putNextEntry( zips.get(i) );
            zos.write( files.get(i) );
            zos.closeEntry();
        }

        return baos.toByteArray();

        // Maybe enable double travel speed later
        // if (galaxy.getGalaxyOptions().isAddDoubleTravelSpeed()) {
        //     addDoubleTravelSpeedToOutput(baos, zos);
        // }
    }

    // private void addDoubleTravelSpeedToOutput(String path) throws URISyntaxException, IOException {
    //     String originFolder = "core/engines";
    //     String targetFolder = path + "/assets/props/Engines/macros/";
    //     copyUtils.copyDirectoryToOutputDir(originFolder, targetFolder);
    // }

    private ByteArrayOutputStream generateAssetsOne(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/empty_space.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }
    private ByteArrayOutputStream generateAssetsTwo( Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/components.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateGameStart(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/gamestarts.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateZones(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/zone.ftl");        
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateSectors(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/sector.ftl");        
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateClusters(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/cluster.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateUniverse(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/universe.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateMacros(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/macros.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateMapDefaults(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/mapdefaults.ftl");        
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateContent(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/content.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateGod(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/god.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateJobs(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/jobs.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateMdFixFileOne(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/factionLogic.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateMdFixFileTwo(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/drainStations.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateMdFixFileThree(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/playerreputation.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateMdFixFileFour(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/x4ep1_war_subscriptions.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generateMdFixFileFive(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/customGameStart.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream generatePlacedObjects(Configuration cfg, Map<String, Object> root, String type) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(type + "/placedObjects.ftl");
        return writeToByteArrayOutputStream(root, temp);
    }

    private ByteArrayOutputStream writeToByteArrayOutputStream(Map<String, Object> root, Template temp) throws IOException, TemplateException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        temp.process(root, new OutputStreamWriter( stream ) );
        return stream;
    }
}
