package com.example.cellgame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cellgame.model.Player;

public class PlayerViewModel extends ViewModel {
    private final MutableLiveData<Player> playerModel;

    public PlayerViewModel() {
        this.playerModel = new MutableLiveData<Player>();
        this.playerModel.setValue(new Player());
    }

    /**
     * @return returns the created player that the fragments will be interacting with.
     */
    public LiveData<Player> getMyModel() {
        return playerModel;
    }
}
