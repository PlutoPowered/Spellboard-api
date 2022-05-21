package spellboard.api;

import java.util.Optional;

public interface Owned {

    Optional<Player> owner();

}
