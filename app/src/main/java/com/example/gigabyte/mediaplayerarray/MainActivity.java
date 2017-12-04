package com.example.gigabyte.mediaplayerarray;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MediaPlayer player;
    private ImageView imagen;
    private boolean isPaused=false;
    private ImageButton btnPrev;
    private ImageButton btnPlayPause;
    private ImageButton btnNext;
    private int cont=0;
    private TextView titulo;
    private Integer[] canciones={R.raw.cancion1,R.raw.cancion2,R.raw.cancion3};
    private Integer[] imagenes={R.drawable.cancion1,R.drawable.cancion2,R.drawable.cancion3};
    private String[] titulos={"Yet set run","Bombing king !!","Boku no Hero Academia OST"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se carga la imagen
        imagen= (ImageView)findViewById(R.id.imagen);
        imagen.setImageResource(imagenes[cont]);
        //Se carga el titlulo
        titulo= (TextView)findViewById(R.id.txtTitulo);
        titulo.setText(titulos[cont]);
        //Se asignan los escuchadores de eventos a los botones
        btnNext= (ImageButton)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnPlayPause= (ImageButton)findViewById(R.id.btnPlayPause);
        btnPlayPause.setOnClickListener(this);
        btnPrev= (ImageButton)findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(this);
        //Asignamos la cancion al MediaPlayer
        player= MediaPlayer.create(this, canciones[cont]);
        player.start();
    }



    @Override
    public void onClick(View v) {
        //Se ha pusado el boton de play
        if(v.getId()==R.id.btnPlayPause){
            if(!isPaused){
                //cambiamos la imagen del boton
                btnPlayPause.setImageResource(R.drawable.ic_pause);
                //se pausa la cancion
                player.pause();
                isPaused=true;
            }else{
                //cambiamos la imagen del boton
                btnPlayPause.setImageResource(R.drawable.ic_play);
                //se inicia la cancion
                player.start();
                isPaused=false;
            }
        }else if(v.getId()==R.id.btnNext){
            //si el contador es == numero de canciones -1, pongo el contador a 0
            if(cont==canciones.length-1){
                cont=0;
            //si el contador es != numero de canciones -1, le sumamos 1 al contador
            }else{
                cont++;
            }
            //cambiamos la cancion
            cambiarCancion();
        //se pulsa el boton de cancion previa
        }else if(v.getId()==R.id.btnPrev){
            //si el contador es == 0, pongo el contador a 0, pongo el contador == numero de canciones -1
            if(cont==0){
                cont=canciones.length-1;
                //si el contador es != 0,  le restamos 1 al contador
            }else{
                cont--;
            }
            //cambiamos la cancion
            cambiarCancion();
        }
    }

    private void cambiarCancion() {
        //si la cancion esta sonando se pausa y se cambia la imagen del boton
        if(player.isPlaying()){
            player.pause();
            isPaused=true;
            btnPlayPause.setImageResource(R.drawable.ic_pause);
        }
        //se cambia la cancion
        player= MediaPlayer.create(this, canciones[cont]);
        //se cambia la imagen
        imagen.setImageResource(imagenes[cont]);
        //se cambia el titulo
        titulo.setText(titulos[cont]);
        //se inicia la cancion
        player.start();
        //se cambia la imagen del boton
        btnPlayPause.setImageResource(R.drawable.ic_play);
        isPaused=false;
    }
}
