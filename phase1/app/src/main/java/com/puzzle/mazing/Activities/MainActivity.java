package com.puzzle.mazing.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.puzzle.mazing.DataAccess.UserManager;
import com.puzzle.mazing.Exceptions.IncorrectCredentialException;
import com.puzzle.mazing.R;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

  private TextView httpTextView;

  private UserManager userManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    httpTextView = findViewById(R.id.text_view_result);


  }
}
