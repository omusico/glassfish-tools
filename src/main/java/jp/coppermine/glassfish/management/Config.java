package jp.coppermine.glassfish.management;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public class Config {

    private UriBuilder uriBuilder;

    Config(UriBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

    public URI getUri() {
        return uriBuilder.build();
    }

    public Object adminService() {
        // TODO should implement
        return null;
    }

    public Object applicationRef() {
        // TODO should implement
        return null;
    }

    public Object availabilityService() {
        // TODO should implement
        return null;
    }

    public Object batchRuntimeConfiguration() {
        // TODO should implement
        return null;
    }

    public Object cdiService() {
        // TODO should implement
        return null;
    }

    public Object connectorService() {
        // TODO should implement
        return null;
    }

    public Object diagnosticService() {
        // TODO should implement
        return null;
    }

    public Object ejbContainer() {
        // TODO should implement
        return null;
    }

    public Object groupManagementService() {
        // TODO should implement
        return null;
    }

    public Object httpService() {
        // TODO should implement
        return null;
    }

    public Object iiopService() {
        // TODO should implement
        return null;
    }

    public JavaConfig javaConfig() {
        return new JavaConfig(uriBuilder.clone().path("java-config"));
    }

    public Object jsmAvailability() {
        // TODO should implement
        return null;
    }

    public Object jmsHost() {
        // TODO should implement
        return null;
    }

    public Object jmsService() {
        // TODO should implement
        return null;
    }

    public Object logService() {
        // TODO should implement
        return null;
    }

    public Object mdbContainer() {
        // TODO should implement
        return null;
    }

    public Object monitoringService() {
        // TODO should implement
        return null;
    }

    public Object networkConfig() {
        // TODO should implement
        return null;
    }

    public Object property() {
        // TODO should implement
        return null;
    }

    public Object resourceRef() {
        // TODO should implement
        return null;
    }

    public Object restConfig() {
        // TODO should implement
        return null;
    }

    public Object securityService() {
        // TODO should implement
        return null;
    }

    public Object systemProperties() {
        // TODO should implement
        return null;
    }

    public Object systemProperty() {
        // TODO should implement
        return null;
    }

    public Object threadPools() {
        // TODO should implement
        return null;
    }

    public Object transactionService() {
        // TODO should implement
        return null;
    }

    public Object webContainer() {
        // TODO should implement
        return null;
    }

    public boolean isDynamicReconfigurationEnabled() {
        return true;
    }

    public void setDynamicReconfigurationEnabled() {

    }

    public void deleteConfig() {

    }
}
