package com.mohssenteck.moamaochistan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class LevelsActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b); setContentView(R.layout.activity_levels);
        LinearLayout box = findViewById(R.id.levelsContainer);
        for (int i=0; i<GameData.ITEMS.length; i++) {
            final int level=i;
            Button btn = new Button(this);
            btn.setText("معمای شماره " + (i+1) + (isUnlocked(i) ? "  ✓" : "  🔒"));
            btn.setGravity(Gravity.CENTER);
            btn.setOnClickListener(v -> {
                if (isUnlocked(level)) {
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("level", level); startActivity(intent);
                } else android.widget.Toast.makeText(this, "اول معمای قبلی را حل کن!", android.widget.Toast.LENGTH_SHORT).show();
            });
            box.addView(btn, new LinearLayout.LayoutParams(-1, -2));
        }
    }
    private boolean isUnlocked(int i) { return i==0 || getSharedPreferences("game", MODE_PRIVATE).getBoolean("solved_"+(i-1),false); }
}
