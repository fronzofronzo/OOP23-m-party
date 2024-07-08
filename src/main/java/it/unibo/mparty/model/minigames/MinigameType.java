package it.unibo.mparty.model.minigames;

public enum MinigameType {

    SINGLE_PLAYER("singlePlayerMinigame"),
    MULTI_PLAYER("multiPlayerMinigame");

    private final String type;

    MinigameType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
