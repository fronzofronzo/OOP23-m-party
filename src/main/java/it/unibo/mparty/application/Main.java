package it.unibo.mparty.application;

import it.unibo.mparty.view.GameViewImpl;
import javafx.application.Application;

public class Main {

    private Main(){

    }

    public static void main(final String[] args){
        Application.launch(GameViewImpl.class);
    }
}
