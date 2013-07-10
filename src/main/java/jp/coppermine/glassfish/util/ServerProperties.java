package jp.coppermine.glassfish.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import jp.coppermine.glassfish.GlassFish;
import jp.coppermine.glassfish.management.JavaConfig;

public class ServerProperties implements Map<Object, Object> {

    private JavaConfig javaConfig;

    public ServerProperties(String host, int port) {
        javaConfig = GlassFish.server(host, port).manager().configs().serverConfig().javaConfig();
    }

    public String getProperty(String key) {
        System.out.println(key);
        for (String option : javaConfig.getJvmOptions()) {
            String prefix = String.format("-D%s=", key);
            if (option.startsWith(prefix)) {
                return option.substring(prefix.length());
            }
        }
        return null;
    }

    public String getProperty(String key, String defaultValue) {
        return getProperty(key) == null ? defaultValue : getProperty(key);
    }

    public Object setProperty(String key, String value) {
        String oldValue = getProperty(key);
        if (oldValue != null) {
            javaConfig.removeJvmOption(String.format("-D%s=%s", key, oldValue));
        }
        javaConfig.addJvmOption(String.format("-D%s=%s", key, value));
        return oldValue;
    }

    public Set<String> stringPropertyNames() {
        return null;
    }

    @Override
    public int size() {
        return javaConfig.getJvmOptions().size();
    }

    @Override
    public boolean isEmpty() {
        return javaConfig.getJvmOptions().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get(Object key) {
        return getProperty(key.toString());
    }

    @Override
    public Object put(Object key, Object value) {
        return setProperty(key.toString(), value.toString());
    }

    @Override
    public Object remove(Object key) {
        String value = getProperty(key.toString());
        if (value != null) {
            javaConfig.removeJvmOption(String.format("-D%s=%s", key, value));
        }
        return value;
    }

    @Override
    public void putAll(Map<? extends Object, ? extends Object> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Object> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Object> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<java.util.Map.Entry<Object, Object>> entrySet() {
        throw new UnsupportedOperationException();
    }

}
