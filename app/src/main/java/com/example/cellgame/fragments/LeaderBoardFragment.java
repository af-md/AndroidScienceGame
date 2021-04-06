package com.example.cellgame.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.cellgame.PlayerViewModel;
import com.example.cellgame.R;
import com.example.cellgame.model.Player;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeaderBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeaderBoardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeaderBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeaderBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeaderBoardFragment newInstance(String param1, String param2) {
        LeaderBoardFragment fragment = new LeaderBoardFragment();
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


    Button retryButton;
    Button exitButton;
    NavController navController;

    // player data
    PlayerViewModel playerViewModel;
    Player player;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TableLayout leaderBoard = view.findViewById(R.id.ledear_board);

        navController = Navigation.findNavController(view);
        // button
        retryButton = view.findViewById(R.id.ledearboard_button_retry);
        exitButton = view.findViewById(R.id.ledearboard_button_exit);

        // navigate to game for retry
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_leaderBoardFragment_to_gameFragment);
            }
        });

        // quit the application
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                System.exit(0);
            }
        });

        // get live data
        playerViewModel = new ViewModelProvider(requireActivity()).get(PlayerViewModel.class);
        player = playerViewModel.getMyModel().getValue();

        populateLeaderBoard(leaderBoard);

    }

    private void populateLeaderBoard(TableLayout leaderBoard) {

        ArrayList<Player> playersData = populatePlayers();

        for (Player data: playersData){

            // create the row
            TableRow tableRow = new TableRow(getContext());

            // create views to sit in the row
            TextView textViewName = new TextView(getContext());
            TextView textViewScore = new TextView(getContext());

            // populate row
            textViewName.setText(data.getName());
            textViewScore.setText(data.getScore());

            // set padding
            textViewName.setPadding(60,0,0,0);
            textViewScore.setPadding(300,0,0,0);

            tableRow.addView(textViewName);
            tableRow.addView(textViewScore);

            leaderBoard.addView(tableRow);
        }
    }

    private ArrayList<Player> populatePlayers() {
        ArrayList<Player> playersData = new ArrayList<Player>();
        playersData.add( new Player("Ali", "4"));
        playersData.add( new Player("Jonny", "3"));
        playersData.add( new Player("Dario", "7"));
        playersData.add(player);
        return playersData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leader_board, container, false);
    }
}