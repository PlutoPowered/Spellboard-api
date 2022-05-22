package spellboard.api.registry;

import spellboard.api.Spellboard;

import java.util.Optional;

public class GameRegistry extends SimpleRegistry<Registry> {

    public GameRegistry() {
        super(Registry.class, Spellboard.ID, "game_registry");
        register(this);
    }

    public <T extends Registrable> Optional<Registry<T>> registryFor(Class<T> type) {
        return (Optional) this.values().stream().filter(r -> r.type().isAssignableFrom(type)).findFirst();
    }

    public <T extends Registrable> Registry<T> getOrCreateRegistry(Class<T> type, String plugin, String id) {
        return registryFor(type).orElseGet(() -> createRegistry(type, plugin, id));
    }

    public <T extends Registrable> Registry<T> createRegistry(Class<T> type, String plugin, String id) {
        Registry<T> registry = new SimpleRegistry<>(type, plugin, id);
        register(registry);
        return registry;
    }

}
