package spellboard.api.translation;

import java.util.HashMap;
import java.util.Map;

public class Translation {
    private static Map<String, Translation> translations = new HashMap<>();
    private String id;

    private Translation(String id) {
        this.id = id;
    }

    public static Translation of(String id) {
        return translations.computeIfAbsent(id, Translation::new);
    }

    public String id() {
        return this.id;
    }

}
