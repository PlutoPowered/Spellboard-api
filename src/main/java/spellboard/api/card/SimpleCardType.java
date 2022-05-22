package spellboard.api.card;

import spellboard.api.registry.AbstractRegistrable;
import spellboard.api.translation.Translation;

public class SimpleCardType extends AbstractRegistrable implements CardType {
    private Translation translation;

    public SimpleCardType(String plugin, String id) {
        super(plugin, id);
        this.translation = Translation.of("card.type." + id);
    }

    @Override
    public Translation name() {
        return this.translation;
    }

}
