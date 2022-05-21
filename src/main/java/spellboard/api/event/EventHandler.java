package spellboard.api.event;

public interface EventHandler<T> {

    EventPriority priority();

    Class<T> event();

    void handle(T event) throws Throwable;

    interface Consumer<V> {

        void accept(V value) throws Throwable;

    }
}
