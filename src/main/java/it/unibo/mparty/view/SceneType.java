package it.unibo.mparty.view;

public enum SceneType {
    START("initialScreen"),
    END("finalScreen"),
    SHOP("Shop");

    private String scene;

    private SceneType(final String text){
        this.scene = text;
    }

    public String getSceneName(){
        return this.scene;
    }
}
