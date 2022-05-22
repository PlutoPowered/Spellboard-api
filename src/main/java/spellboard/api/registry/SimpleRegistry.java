package spellboard.api.registry;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SimpleRegistry<T extends Registrable> implements Registry<T> {
    private Map<String, T> vals = new ConcurrentHashMap<>();
    private Class<T> type;
    private String plugin;
    private String id;

    public SimpleRegistry(Class<T> type, String plugin, String id) {
        this.type = type;
        this.id = id;
        this.plugin = plugin;
    }

    @Override
    public boolean has(Class<? extends T> type) {
        return vals.values().stream().anyMatch(type::isInstance);
    }

    @Override
    public boolean has(String plugin, String id) {
        return vals.containsKey(plugin + ":" + id);
    }

    @Override
    public T get(String plugin, String id) {
        return vals.get(plugin + ":" + id);
    }

    @Override
    public <E extends T> E get(Class<E> type) {
        return (E) vals.values().stream().filter(type::isInstance).findFirst().orElse(null);
    }

    @Override
    public Class<T> type() {
        return this.type;
    }

    @Override
    public void register(T... values) {
        for (T t : values) {
            vals.put(t.id(), t);
        }
    }

    @Override
    public <E extends T> Collection<E> values(Class<E> type) {
        return (Collection<E>) vals.values().stream().filter(type::isInstance).collect(Collectors.toList());
    }

    @Override
    public Collection<T> values() {
        return vals.values();
    }

    @Override
    public int size() {
        return vals.size();
    }

    @Override
    public String plugin() {
        return this.plugin;
    }

    @Override
    public String id() {
        return this.id;
    }
}
