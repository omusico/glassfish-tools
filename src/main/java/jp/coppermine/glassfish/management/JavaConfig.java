package jp.coppermine.glassfish.management;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static jp.coppermine.glassfish.management.Domain.X_REQUESTED_BY;
import static jp.coppermine.glassfish.management.Domain.X_REQUESTED_BY_VALUE;

import java.io.StringReader;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class JavaConfig {
	private final URI uri;
	
	JavaConfig(URI uri) {
		this.uri = uri;
	}
	
	private static final String JVM_OPTOINS = "jvm-options";
	
	public Set<String> getJvmOptions() {
		Client client = ClientBuilder.newClient();
		System.out.println(UriBuilder.fromUri(uri).path(JVM_OPTOINS).build());
		WebTarget target = client.target(UriBuilder.fromUri(uri).path(JVM_OPTOINS));
		String xml = target.request().accept(APPLICATION_XML).get(String.class);
		
		Set<String> options = new HashSet<>();
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader reader = null;
		try {
			reader = factory.createXMLEventReader(new StringReader(xml));
			
			String command = "";
			String exitCode = "";
			String message = "";
			
			boolean outputJvmOptions = false;
			boolean outputJvmOption = false;
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				
				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					if (startElement.getName().equals(new QName("entry"))) {
						Attribute key = startElement.getAttributeByName(new QName("key"));
						Attribute value = startElement.getAttributeByName(new QName("value"));
						
						if (key.getValue().equals("command")) {
							command = value.getValue();
							continue;
						}
						if (key.getValue().equals("exit_code")) {
							exitCode = value.getValue();
							continue;
						}
						if (key.getValue().equals("message")) {
							exitCode = value.getValue();
							continue;
						}
						
						if (key.getValue().equals("leafList")) {
							outputJvmOptions = true;
							continue;
						} else {
							outputJvmOptions = false;
							continue;
						}
					}
					if (startElement.getName().equals(new QName("string"))) {
						outputJvmOption = true;
					}
				}
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().equals(new QName("string"))) {
						outputJvmOption = false;
						continue;
					}
					if (endElement.getName().equals(new QName("entry"))) {
						outputJvmOptions = false;
						continue;
					}
				}
				if (event.isCharacters()) {
					Characters characters = event.asCharacters();
					if (outputJvmOptions & outputJvmOption) {
						options.add(characters.getData());
					}
				}
			}
			
			if (!exitCode.equals("SUCCESS")) {
				System.out.printf("command=%s, exit_code=%s, message=%s\n", command, exitCode, message);
			}
			
		} catch (XMLStreamException e) {
			throw new ServiceUnavailableException();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (XMLStreamException e) { }
			}
		}
		
		return options;
	}
	
	public void addJvmOption(String option) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(UriBuilder.fromUri(uri).path(JVM_OPTOINS));
		Response response = target.request().header(X_REQUESTED_BY, X_REQUESTED_BY_VALUE).accept(APPLICATION_JSON).put(Entity.entity(option, APPLICATION_FORM_URLENCODED));
		System.out.println("[add] HTTP " + response.getStatus());
	}
	
	public void removeJvmOption(String option) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(UriBuilder.fromUri(uri).path(JVM_OPTOINS));
		Response response = target.queryParam(option, "").request().header(X_REQUESTED_BY, X_REQUESTED_BY_VALUE).accept(APPLICATION_JSON).delete();
		System.out.println("[remove] HTTP " + response.getStatus());
	}
}
