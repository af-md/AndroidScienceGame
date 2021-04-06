package com.example.cellgame.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cellgame.PlayerViewModel;
import com.example.cellgame.R;
import com.example.cellgame.model.Player;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IntructionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IntructionsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private NavController navController;
    private Button playButton;
    private EditText userName;

    // player data
    PlayerViewModel playerViewModel;
    Player player;

    public IntructionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IntructionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IntructionsFragment newInstance(String param1, String param2) {
        IntructionsFragment fragment = new IntructionsFragment();
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
        return inflater.inflate(R.layout.fragment_intructions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        playButton = view.findViewById(R.id.instruction_play_button);

        userName = view.findViewById(R.id.editTextTextPersonName);

        playerViewModel = new ViewModelProvider(requireActivity()).get(PlayerViewModel.class);
        player = playerViewModel.getMyModel().getValue();

        // Moves to game fragment
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().trim().equals("")){
                    userName.setError("please enter your name");
                }
                else {
                    player.setName(userName.getText().toString());
                    navController.navigate(R.id.action_intructionsFragment_to_gameFragment);
                }

            }
        });

    }
}