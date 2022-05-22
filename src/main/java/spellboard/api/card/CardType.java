package spellboard.api.card;

import spellboard.api.registry.Registrable;
import spellboard.api.translation.Translation;

public interface CardType extends Registrable {

    Translation name();

}
