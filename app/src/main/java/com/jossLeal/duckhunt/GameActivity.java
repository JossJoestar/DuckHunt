package com.jossLeal.duckhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jossLeal.duckhunt.common.Constantes;

public class GameActivity extends AppCompatActivity {
	TextView tvCounterDucks, tvTimer, tvNick;
	ImageView ivDuck;
	int counter = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		initViewComponets();
		eventos();
	}
	
	private void initViewComponets(){
		tvCounterDucks = findViewById(R.id.textViewCounter);
		tvTimer = findViewById(R.id.textViewTimer);
		tvNick = findViewById(R.id.textViewNick);
		ivDuck = findViewById(R.id.imageViewDuck);
		
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
	private void eventos() {
		ivDuck.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				counter++;
				tvCounterDucks.setText(String.valueOf(counter));
			}
		});
	}
}