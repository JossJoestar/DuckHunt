package com.jossLeal.duckhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.jossLeal.duckhunt.common.Constantes;

public class GameActivity extends AppCompatActivity {
	TextView tvCounterDucks, tvTimer, tvNick;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		tvCounterDucks = findViewById(R.id.textViewCounter);
		tvTimer = findViewById(R.id.textViewTimer);
		tvNick = findViewById(R.id.textViewNick);
		
		// Asignar Fuentes personalizadas
		Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
		tvCounterDucks.setTypeface(typeface);
		tvTimer.setTypeface(typeface);
		tvNick.setTypeface(typeface);
		
		//Extras: Obtener Nombre y setear
		Bundle extras = getIntent().getExtras();
		String nick = extras.getString(Constantes.EXTRA_NICK);
		tvNick.setText(nick);
	}
}