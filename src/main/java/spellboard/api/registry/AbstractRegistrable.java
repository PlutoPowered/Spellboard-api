package spellboard.api.registry;

public class AbstractRegistrable implements Registrable {
    private String plugin;
    private String id;

    public AbstractRegistrable(String plugin, String id) {
        this.plugin = plugin;
        this.id = id;
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
