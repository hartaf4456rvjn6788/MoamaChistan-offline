package com.mohssenteck.moamaochistan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b); setContentView(R.layout.activity_main);
        findViewById(R.id.btnPlay).setOnClickListener(v -> startActivity(new Intent(this, LevelsActivity.class)));
        findViewById(R.id.btnShop).setOnClickListener(v -> startActivity(new Intent(this, ShopActivity.class)));
        findViewById(R.id.btnSettings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        updateCoins();
    }
    @Override protected void onResume() { super.onResume(); updateCoins(); }
    private void updateCoins() { ((TextView)findViewById(R.id.tvCoins)).setText("🪙 سکه: " + getSharedPreferences("game", MODE_PRIVATE).getInt("coins",50)); }
}
