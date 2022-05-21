package spellboard.api.event;

public interface EventManager {

    <T> T post(T event);

    void register(EventHandler<?> handler);

}
