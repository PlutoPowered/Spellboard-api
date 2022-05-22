package spellboard.api.registry;

import java.util.Collection;
import java.util.Iterator;

public interface Registry<T extends Registrable> extends Registrable, Iterable<T> {

    boolean has(Class<? extends T> type);

    boolean has(String plugin, String id);

    T get(String plugin, String id);

    <E extends T> E get(Class<E> type);

    Class<T> type();

    void register(T... values);

    <E extends T> Collection<E> values(Class<E> type);

    Collection<T> values();

    int size();

    @Override
    default Iterator<T> iterator() {
        return values().iterator();
    }
}
