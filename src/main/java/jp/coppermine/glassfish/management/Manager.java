package jp.coppermine.glassfish.management;

import java.net.URI;

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
        // TODO should implement
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
        // TODO should implement
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
        // TODO should implement
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
        // TODO should implement
    }

    public void listTimers() {
        // TODO should implement
    }

    public void listJvmOptions() {
        // TODO should implement
    }

    public void get() {
        // TODO should implement
    }

    public void version() {
        // TODO should implement
    }

    public void restartDomain() {
        // TODO should implement
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
        // TODO should implement
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
