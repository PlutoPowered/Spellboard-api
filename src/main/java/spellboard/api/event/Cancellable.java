package spellboard.api.event;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean cancelled);

    default void ifPermitted(Runnable action) {
        if (!isCancelled()) action.run();
    }

}
