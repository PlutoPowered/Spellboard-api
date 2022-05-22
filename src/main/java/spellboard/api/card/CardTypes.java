package spellboard.api.card;

import spellboard.api.Spellboard;

public interface CardTypes {

    CardType SPELL = new SimpleCardType(Spellboard.ID, "spell");
    CardType UNIT = new SimpleCardType(Spellboard.ID, "unit");
    CardType TERRITORY = new SimpleCardType(Spellboard.ID, "territory");

}
