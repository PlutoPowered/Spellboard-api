package spellboard.api.turn;

public interface EffectStack {

    void post(Effect effect);

    void resolve();

    boolean isEmpty();

}
