package controller;

import model.Gimnasio;

/**
 *  Comparte el gimnasio entre las ventanas que se abren mientras la aplicación está en ejecución.
 * */
public final class AppContext {
    public static final Gimnasio GIMNASIO = new Gimnasio();

    /** Evita que se creen instancias de la ventana compartida. */
    private AppContext() { }
}
