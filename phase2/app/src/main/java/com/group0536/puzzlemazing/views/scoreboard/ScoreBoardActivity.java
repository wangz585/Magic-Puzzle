package com.group0536.puzzlemazing.views.scoreboard;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.scoreboard.ScoreBoardActionCreator;
import com.group0536.puzzlemazing.stores.scoreboard.ScoreBoardStore;
import com.group0536.puzzlemazing.views.FluxActivity;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardActivity extends FluxActivity implements AdapterView.OnItemSelectedListener {
    private ScoreBoardStore store;
    private Button btnBackToMain;
    private Spinner spinnerScoreType;
    private List<List<TextView>> usersWithScores;
    private ScoreBoardActionCreator actionCreator;
    private TextView tableScoreType;

    @Override
    protected void initFluxComponents() {
        store = ScoreBoardStore.getInstance(dispatcher);
        setContentView(R.layout.activity_score_board);
        actionCreator = new ScoreBoardActionCreator(dispatcher);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
    }

    private void bindViews() {
        initializeBackToMain();
        initializeScoreType();
        initializeUserWithScores();
        initializeTableScoreType();
    }

    private void initializeTableScoreType() {
        tableScoreType = findViewById(R.id.txtScoreBoardScoreType);
    }

    private void initializeUserWithScores() {
        usersWithScores = new ArrayList<>();
        for(int row = 0; row < 3; row++){
            List<TextView> currentRow = new ArrayList<>();
            String btnId = "txtUser" + (row + 1);
            int resName = getResources().getIdentifier(btnId+"Name", "id", getPackageName());
            int resScore = getResources().getIdentifier(btnId+"Score", "id", getPackageName());
            currentRow.add((TextView) findViewById(resName));
            currentRow.add((TextView) findViewById(resScore));
            usersWithScores.add(currentRow);
        }
    }

    private void initializeScoreType() {
        spinnerScoreType = findViewById(R.id.spinScoreType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.scoreTypesForRank, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerScoreType.setAdapter(adapter);
        spinnerScoreType.setOnItemSelectedListener(this);
    }

    private void initializeBackToMain() {
        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                When this button is clicked, got to he main page
//                startActivity(new Intent());
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String scoreType = adapterView.getItemAtPosition(i).toString();
        actionCreator.chooseScoreType(scoreType);
        upDateUI(scoreType);
    }

    private void upDateUI(String scoreType) {
//        List<List> usersWithScoresData = store.getUsersWithScores();
//        for(int row = 0; row < 3; row++){
//            List currentUser = usersWithScoresData.get(row);
//            usersWithScores.get(row).get(0).setText((String) currentUser.get(0));
//            usersWithScores.get(row).get(1).setText((Integer) currentUser.get(1));
//        }
        String txtIdScoreType = scoreType.substring(8);
        tableScoreType.setText(txtIdScoreType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
