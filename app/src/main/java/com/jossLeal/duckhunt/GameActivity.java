package com.jossLeal.duckhunt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
	boolean gameOver = false;
	
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
		new CountDownTimer(60000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				long segundosRestantes = millisUntilFinished / 1000;
				tvTimer.setText(segundosRestantes + "s");
				moveDuck();
			}
			
			@Override
			public void onFinish() {
				gameOver = true;
				ivDuck.setEnabled(false);
				tvTimer.setText("0s");
				mostrarDialogGameOver();
			}
		}.start();
	}
	
	private void mostrarDialogGameOver() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Has conseguido cazar: "+counter+" patos.").setTitle("Game Over");
		builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
			
			}
		});
		builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				GameActivity.this.finish();
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
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
				if(!gameOver) {
					ivDuck.setEnabled(false);
					counter++;
					tvCounterDucks.setText(String.valueOf(counter));
					ivDuck.setImageResource(R.drawable.duck_clicked);
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							ivDuck.setImageResource(R.drawable.duck);
							ivDuck.setEnabled(true);
							moveDuck();
						}
					}, 500);
					
				}
			}
		});
	}
	
	private void moveDuck() {
		int min = 0;
		int maximoX = anchoPantalla - ivDuck.getWidth();
		int maximoY = altoPantalla - ivDuck.getHeight();
		int randomX = aleatorio.nextInt(((maximoX - min) + 1));
		int randomY = aleatorio.nextInt(((maximoY - min) + 1));
		ivDuck.setX(randomX);
		ivDuck.setY(randomY);
	}
}