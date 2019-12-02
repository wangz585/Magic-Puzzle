package com.group0536.puzzlemazing.views.global;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.global.ScoreBoardActionCreator;
import com.group0536.puzzlemazing.stores.global.ScoreBoardStore;
import com.group0536.puzzlemazing.views.FluxActivity;
import com.group0536.puzzlemazing.views.menu.MenuActivity;

import java.util.List;

public class ScoreBoardActivity extends FluxActivity implements AdapterView.OnItemSelectedListener {
    private ScoreBoardStore store;
    private Button btnBackToMain;
    private Spinner spinnerScoreType;
    private ScoreBoardActionCreator actionCreator;
    private TextView tableScoreType;
    private TableLayout table;

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
        initializeTableScoreType();
        initializeTable();
    }

    private void initializeTable() {
        table = findViewById(R.id.tableUserScores);
    }

    private void initializeTableScoreType() {
        tableScoreType = findViewById(R.id.txtScoreBoardScoreType);
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
                Intent intent = new Intent(ScoreBoardActivity.this,
                        MenuActivity.class);
                startActivity(intent);
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
        table.removeViews(1, table.getChildCount()-1);
        List<List> usersWithScoresData = store.getUsersWithScores();
//        for(int row = 0; row < 3; row++){
//            List currentUser = usersWithScoresData.get(row);
//            usersWithScores.get(row).get(0).setText((String) currentUser.get(0));
//            usersWithScores.get(row).get(1).setText((Integer) currentUser.get(1));
//        }
//        for(int i = 0; i < usersWithScoresData.size(); i++){
//            TableRow row = new TableRow(this);
//            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
//            lp.weight=1f;
//            row.setLayoutParams(lp);
//            row.setWeightSum(2);
//            TextView userName = new TextView(this);
//            userName.setGravity(Gravity.CENTER);
//            userName.setText((String) usersWithScoresData.get(i).get(0));
//            userName.setWidth(10);
//            userName.setLayoutParams(lp);
//            TextView userScore = new TextView(this);
//            userScore.setGravity(Gravity.CENTER);
//            userScore.setText(String.valueOf(usersWithScoresData.get(i).get(1)));
//            userScore.setLayoutParams(lp);
//            row.addView(userName);
//            row.addView(userScore);
//            table.addView(row);
//
//        }
        String txtIdScoreType = scoreType.substring(8);
        tableScoreType.setText(txtIdScoreType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
