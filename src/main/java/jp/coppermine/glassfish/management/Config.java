package jp.coppermine.glassfish.management;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public class Config {

    private URI uri;

    Config(URI uri) {
    	this.uri = uri;
    }
    
    Config(UriBuilder uriBuilder) {
        this.uri = uriBuilder.build();
    }

    public URI getUri() {
        return uri;
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
        return new JavaConfig(UriBuilder.fromUri(uri).path("java-config"));
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Config other = (Config) obj;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Config [uri=" + uri + "]";
	}
}
