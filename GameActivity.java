package com.mohssenteck.moamaochistan;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.text.Normalizer;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    private int level;
    private android.content.SharedPreferences prefs;
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b); setContentView(R.layout.activity_game);
        prefs=getSharedPreferences("game",MODE_PRIVATE);
        level=Math.max(0,Math.min(getIntent().getIntExtra("level",0),GameData.ITEMS.length-1));
        TextView q=findViewById(R.id.tvQuestion); TextView coins=findViewById(R.id.tvGameCoins);
        EditText answer=findViewById(R.id.etAnswer);
        q.setText("معمای "+(level+1)+"\n\n"+GameData.ITEMS[level][0]);
        refreshCoins(coins);
        findViewById(R.id.btnSubmit).setOnClickListener(v -> {
            String typed=clean(answer.getText().toString()), correct=clean(GameData.ITEMS[level][1]);
            if (typed.equals(correct)) {
                boolean already=prefs.getBoolean("solved_"+level,false);
                if (!already) prefs.edit().putBoolean("solved_"+level,true).putInt("coins",prefs.getInt("coins",50)+10).apply();
                Toast.makeText(this, already ? "این معما قبلاً حل شده!" : "آفرین! ۱۰ سکه گرفتی 🎉",Toast.LENGTH_LONG).show();
                finish();
            } else Toast.makeText(this,"جواب درست نیست؛ دوباره تلاش کن.",Toast.LENGTH_SHORT).show();
        });
        findViewById(R.id.btnHint).setOnClickListener(v -> {
            int c=prefs.getInt("coins",50);
            if(c>=20){prefs.edit().putInt("coins",c-20).apply(); refreshCoins(coins); Toast.makeText(this,GameData.ITEMS[level][2],Toast.LENGTH_LONG).show();}
            else Toast.makeText(this,"سکه کافی نداری.",Toast.LENGTH_SHORT).show();
        });
    }
    private void refreshCoins(TextView v){v.setText("🪙 سکه: "+prefs.getInt("coins",50));}
    private String clean(String s){return Normalizer.normalize(s.trim().toLowerCase(new Locale("fa")),Normalizer.Form.NFC).replace("ي","ی").replace("ك","ک").replaceAll("\\s+","");}
}
