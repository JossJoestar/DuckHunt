package com.jossLeal.duckhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jossLeal.duckhunt.common.Constantes;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
	TextView tvCounterDucks, tvTimer, tvNick;
	ImageView ivDuck;
	int counter = 0;
	int anchoPantalla;
	int altoPantalla;
	Random aleatorio;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		initViewComponets();
		eventos();
		initPantalla();
		initCuentaAtras();
	}
	
	private void initCuentaAtras() {
		new CountDownTimer(25000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				long segundosRestantes = millisUntilFinished / 1000;
				tvTimer.setText(segundosRestantes + "s");
				moveDuck();
			}
			
			@Override
			public void onFinish() {
				tvTimer.setText("0s");
				Toast mensaje = Toast.makeText(getApplicationContext(),"Se ha terminado el juego tu puntuaje: "+tvCounterDucks.getText(), Toast.LENGTH_LONG);
				mensaje.show();
				ivDuck.setEnabled(false);
			}
		}.start();
	}
	
	private void initPantalla() {
		// 1.- Obtener el tamaño de la pantalla del dispositivo en el que estamos ejecutando la app
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		anchoPantalla = size.x;
		altoPantalla = size.y;
		Log.d("ancho", String.valueOf(anchoPantalla));
		Log.d("alto", String.valueOf(altoPantalla));
		
		// 2.- Inicializamos el objeto  para  genera numeros aleatorios
		aleatorio = new Random();
	}
	
	private void initViewComponets() {
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
				ivDuck.setEnabled(false);
				counter++;
				tvCounterDucks.setText(String.valueOf(counter));
				ivDuck.setImageResource(R.drawable.duck_clicked);
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						ivDuck.setImageResource(R.drawable.duck);
						moveDuck();
						ivDuck.setEnabled(true);
					}
				}, 500);
			}
		});
	}
	
	private void moveDuck() {
		int min = 0;
		int maximoX = anchoPantalla - ivDuck.getWidth();
		int maximoY = altoPantalla - ivDuck.getHeight();
		//Generamos 2 numeros aleatorios, uno para la cordenara X y otro para Y
		int randomX = aleatorio.nextInt(((maximoX - min) + 1));
		int randomY = aleatorio.nextInt(((maximoY - min) + 1));
		
		//Utilizamos los numeros aleatorios para mover el pato a esa posicion
		ivDuck.setX(randomX);
		ivDuck.setY(randomY);
	}
}