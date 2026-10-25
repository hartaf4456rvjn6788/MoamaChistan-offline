package com.mohssenteck.moamaochistan;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class ShopActivity extends AppCompatActivity {
 @Override protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_shop);
 findViewById(R.id.btnFreeCoins).setOnClickListener(v->{android.content.SharedPreferences p=getSharedPreferences("game",MODE_PRIVATE);p.edit().putInt("coins",p.getInt("coins",50)+50).apply();Toast.makeText(this,"۵۰ سکه به موجودی اضافه شد 🎁",Toast.LENGTH_SHORT).show();});
 }
}
