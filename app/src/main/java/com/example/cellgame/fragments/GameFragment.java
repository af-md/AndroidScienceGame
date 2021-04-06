package com.example.cellgame.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.drm.DrmEvent;
import android.drm.DrmManagerClient;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cellgame.PlayerViewModel;
import com.example.cellgame.R;
import com.example.cellgame.model.Player;
import com.example.cellgame.view.GameSurfaceView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    private GameSurfaceView surfaceView;
    TextView healthTextView;
    TextView pointsTextView;

    // player data
    PlayerViewModel playerViewModel;
    Player player;

    NavController navController;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        surfaceView = view.findViewById(R.id.surface_game_view);
        // Get sensor
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                surfaceView.setMove(event.values[0] * 2);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        // Register the event listeners to the sensor
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);


        // move to leaderboard fragment once the game has ended
        navController = Navigation.findNavController(view);
        Observer<Boolean> observeGameEnd = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                navController.navigate(R.id.action_gameFragment_to_leaderBoardFragment);
            }
        };
        surfaceView.setObserveGameEnd(observeGameEnd);

        // set Health Bar
        healthTextView = view.findViewById(R.id.health_text_view);
        healthTextView.setText("5");
        surfaceView.setHealthTextView(healthTextView);

        // set Points bar
        pointsTextView = view.findViewById(R.id.points_text_view);
        pointsTextView.setText("0");
        surfaceView.setPointsTextView(pointsTextView);

        // get live data
        playerViewModel = new ViewModelProvider(requireActivity()).get(PlayerViewModel.class);
        player = playerViewModel.getMyModel().getValue();
        surfaceView.setPlayer(player);
    }

    public GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }
}