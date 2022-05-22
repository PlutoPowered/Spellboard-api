package spellboard.api;

import spellboard.api.event.EventManager;
import spellboard.api.registry.GameRegistry;

public interface Game {

    EventManager events();

    GameRegistry registry();

}
