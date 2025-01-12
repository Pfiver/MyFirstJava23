package net.patrickpfeifer.dynproxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;

public class ProxyObject<T> implements InvocationHandler {

    private final Class<T> type;
    private final Map<String, String> attributes;
    private final Map<String, Collection<Map<String, String>>> components;

    private ProxyObject(Class<T> type, Map<String, String> attributes, Map<String, Collection<Map<String, String>>> components) {
        this.type = type;
        this.attributes = attributes;
        this.components = components;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var methodName = method.getName();
        if (methodName.startsWith("get")) {
            var propertyName = methodName.substring(4).toLowerCase();
            if (components.containsKey(propertyName)) {
                return components.get(propertyName);
            }
            return attributes.get(propertyName);
        }
        return switch (methodName) {
            case "equals" -> false;
            case "hashCode" -> -1;
            case "toString" -> toString();
            default -> throw new NoSuchMethodError(methodName);
        };
    }

    @Override
    public String toString() {
        return "ProxyObject{" +
                "type=" + type +
                ", attributes=" + attributes +
                ", components=" + components +
                '}';
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> type, Map<String, String> attributes, Map<String, Collection<Map<String, String>>> components) {
        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class[] {type},
                new ProxyObject<T>(type, attributes, components)
        );
    }
}
