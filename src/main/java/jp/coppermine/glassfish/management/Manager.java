package jp.coppermine.glassfish.management;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.StringReader;
import java.net.URI;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

public class Manager {
    private UriBuilder uriBuilder;

    public Manager(URI uri) {
        this.uriBuilder = UriBuilder.fromUri(uri);
    }

    public Manager(UriBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

    public URI getUri() {
        return uriBuilder.build();
    }

    public Applications applications() {
        return new Applications(uriBuilder.clone().path("applications"));
    }

    public Clusters clusters() {
        // TODO should implement
        return null;
    }

    public Configs configs() {
        return new Configs(uriBuilder.clone().path("configs"));
    }

    public JmxUrls jmxUrls() {
        // TODO should implement
        return new JmxUrls();
    }

    public LbConfigs lbConfigs() {
        // TODO should implement
        return new LbConfigs();
    }

    public LoadBalancers loadBalancers() {
        // TODO should implement
        return new LoadBalancers();
    }

    public ManagedJobConfig managedJobConfig() {
        // TODO (GFv4) should implement
        return new ManagedJobConfig();
    }

    public NodeAgents nodeAgents() {
        // TODO should implement
        return new NodeAgents();
    }

    public Nodes nodes() {
        // TODO should implement
        return new Nodes();
    }

    public Property property() {
        // TODO should implement
        return new Property();
    }

    public Resources resources() {
        // TODO should implement
        return new Resources();
    }

    public SecureAdmin secureAdmin() {
        // TODO should implement
        return new SecureAdmin();
    }

    public SecurityConfigurations securityConfigurations() {
        // TODO (GFv4) should implement
        return new SecurityConfigurations();
    }

    public Servers servers() {
        // TODO should implement
        return new Servers();
    }

    public Object set() {
        // TODO should implement
        return null;
    }

    public SystemApplications systemApplications() {
        // TODO should implement
        return new SystemApplications();
    }

    public SystemProperties systemProperties() {
        // TODO should implement
        return new SystemProperties();
    }

    public ViewLog viewLog() {
        // TODO should implement
        return new ViewLog();
    }

    public void changeAdminPassword() {
        // TODO should implement
    }

    public void stop() {
        // TODO should implement
    }

    public void unfreezeTransactionService() {
        // TODO (GFv4) should implement
    }

    public void configureLdapForAdmin() {
        // TODO should implement
    }

    public void deletePasswordAlias() {
        // TODO should implement
    }

    public void createPasswordAlias() {
        // TODO should implement
    }

    public void updatePasswordAlias() {
        // TODO should implement
    }

    public void listPasswordAliases() {
        // TODO should implement
    }

    public void enableSecureAdmin() {
        // TODO should implement
    }

    public void disableSecureAdmin() {
        // TODO should implement
    }

    public void osgi() {
        // TODO (GFv4) should implement
    }

    public void listTimers() {
        // TODO should implement
    }

    public Map<String, Map<String, String>> listJvmOptions(String server) {
    	Client client = ClientBuilder.newClient();
    	WebTarget target = client.target(uriBuilder).path("list-jvm-options").queryParam("target", server);
    	String json = target.request(APPLICATION_JSON).get(String.class);
    	
    	JsonReader jsonReader = Json.createReader(new StringReader(json));
    	JsonObject root = jsonReader.readObject();
    	
    	String command = root.getString("command");
    	String exitCode = root.getString("exit_code");
    	String message = root.getString("message");
    	if (!exitCode.equals("SUCCESS")) {
    		throw new RuntimeException(String.format("[command=%s][exit_code=%s]%s", command, exitCode, message));
    	}
    	
    	Map<String, Map<String, String>> jvmOptions = new HashMap<>();
    	JsonArray children = root.getJsonArray("children");
    	for (JsonValue value : children) {
    		if (value.getValueType() == ValueType.OBJECT) {
    			JsonObject child = (JsonObject) value;
    			Map<String, String> properties = new HashMap<>();
    			for (String key : child.getJsonObject("properties").keySet()) {
    				properties.put(key, child.getJsonObject("properties").getString(key, ""));
    			}
    			jvmOptions.put(child.getString("message"), properties);
    		}
    	}
    	return jvmOptions;
    }

    public void get() {
        // TODO should implement
    }
    
    public static enum Version {
    	VERSION, VERSION_NUMBER, FULL_VERSION
    }
    
    public EnumMap<Version, String> version() {
    	Client client = ClientBuilder.newClient();
    	WebTarget target = client.target(uriBuilder).path("version");
    	String json = target.request(APPLICATION_JSON).get(String.class);
    	
    	JsonReader jsonReader = Json.createReader(new StringReader(json));
    	JsonObject root = jsonReader.readObject();
    	
    	String command = root.getString("command");
    	String exitCode = root.getString("exit_code");
    	String message = root.getString("message");
    	if (!exitCode.equals("SUCCESS")) {
    		throw new RuntimeException(String.format("[command=%s][exit_code=%s]%s", command, exitCode, message));
    	}
    	
    	JsonObject extraProperties = root.getJsonObject("extraProperties");
    	String version = extraProperties.getString("version");
    	String versionNumber = extraProperties.getString("version-number");
    	String fullVersion = extraProperties.getString("full-version");
    	
    	EnumMap<Version, String> result = new EnumMap<>(Version.class);
    	result.put(Version.VERSION, version);
    	result.put(Version.VERSION_NUMBER, versionNumber);
    	result.put(Version.FULL_VERSION, fullVersion);
    	return result;
    }

    public void restartDomain() {
        // TODO should implement
        // TODO GFv3 - /management/domain/restart
    }

    public void uptime() {
        // TODO should implement
    }

    public void listContainers() {
        // TODO should implement
    }

    public void listSystemProperties() {
        // TODO should implement
    }

    public void listModules() {
        // TODO should implement
    }

    public void listCommands() {
        // TODO should implement
    }

    public void listBatchJobs() {
        // TODO should implement
    }

    public void setBatchRuntimeConfiguration() {
        // TODO should implement
    }

    public void listBatchJobExecutions() {
        // TODO should implement
    }

    public void listBatchRuntimeConfiguration() {
        // TODO should implement
    }

    public void listBatchJobSteps() {
        // TODO should implement
    }

    public void listTransports() {
        // TODO should implement
    }

    public void generateDomainSchema() {
        // TODO should implement
    }

    public void listPersistenceTypes() {
        // TODO should implement
    }

    public void listInstances() {
        // TODO should implement
    }

    public void listNodes() {
        // TODO should implement
    }

    public void createInstance() {
        // TODO should implement
    }

    public void restartInstance() {
        // TODO should implement
    }

    public void listNodesConfig() {
        // TODO should implement
    }

    public void exportSyncBundle() {
        // TODO should implement
    }

    public void listLoggers() {
        // TODO should implement
    }

    public void deleteLogLevels() {
        // TODO should implement
    }

    public void setLogFileFormat() {
        // TODO (GFv4) should implement
    }

    public void listLogAttributes() {
        // TODO should implement
    }

    public void setLogAttributes() {
        // TODO should implement
    }

    public void rotateLog() {
        // TODO should implement
    }

    public void setLogLevels() {
        // TODO should implement
    }

    public void collectLogFiles() {
        // TODO should implement
    }

    public void listLogLevels() {
        // TODO should implement
    }

    public void disableMonitoring() {
        // TODO should implement
    }

    public void enableMonitoring() {
        // TODO should implement
    }

    public void addLibrary() {
        // TODO should implement
    }

    public void listComponents() {
        // TODO should implement
    }

    public void removeLibrary() {
        // TODO should implement
    }

    public void listLibraries() {
        // TODO should implement
    }

    public void listLifecycleModules() {
        // TODO should implement
    }
}
