package controller;

import model.Gimnasio;

/** Comparte el gimnasio entre las ventanas mientras la aplicación está abierta. */
public final class AppContext {
    public static final Gimnasio GIMNASIO = new Gimnasio();

    /** Evita crear instancias del contexto compartido. */
    private AppContext() { }
}
