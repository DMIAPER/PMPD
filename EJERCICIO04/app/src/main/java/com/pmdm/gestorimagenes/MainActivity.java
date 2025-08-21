/*
 * Aplicación para mostar u oculatar imágenes.
 *
 * El botón para cambiar de imgane, debe poder se deshabilitado y habilitado al antojo del usuario
 * mediante un checkbox.
 * Tabién de sede poder oculat y mostar las imágenes según desee el usuario.
 *
 * Versión: 1.0
 * Dev @dmiaper (Diógenes Miaja Pérez)
 */

package com.pmdm.gestorimagenes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //variables para utilizar los obtjetos
    Button boton;
    ImageView imagen;
    RadioGroup grupoRadio;
    RadioButton idrb;
    CheckBox deshabilitar;
    TextView descripcion;
    //Lista para almacenar la dirección de la imagen.
    List<Integer> listImg = new ArrayList<>();
    //list para aladd(
    List<String> listDes = new ArrayList<>();

    //función para cambiar entre la imágenes.
    public void cambiarImagen(@NonNull ImageView img) {

        //bucle switch para cambiar las imagenes.
        switch (img.getTag().toString()) {
            case "R.drawable.imge_1":
                //se indica la nueva imagen
                imagen.setImageResource(listImg.get(1));
                //se indica el nueve Tag
                imagen.setTag("R.drawable.imge_2");
                //se cambia la descripción de la imagen
                imagen.setContentDescription(listDes.get(1));
                //se añade la nueva descripción para la imagen.
                descripcion.setText(listDes.get(1));
                break;
            case "R.drawable.imge_2":
                //se indica la nueva imagen
                imagen.setImageResource(listImg.get(2));
                //se indica el nueve Tag
                imagen.setTag("R.drawable.imge_3");
                //se cambia la descripción de la imagen
                imagen.setContentDescription(listDes.get(2));
                //se añade la nueva descripción para la imagen.
                descripcion.setText(listDes.get(2));
                break;
            case "R.drawable.imge_3":
                //se indica la nueva imagen
                imagen.setImageResource(listImg.get(3));
                //se indica el nueve Tag
                imagen.setTag("R.drawable.imge_4");
                //se cambia la descripción de la imagen
                imagen.setContentDescription(listDes.get(3));
                //se añade la nueva descripción para la imagen.
                descripcion.setText(listDes.get(3));
                break;
            case "R.drawable.imge_4":
                //se indica la nueva imagen
                imagen.setImageResource(listImg.get(4));
                //se indica el nueve Tag
                imagen.setTag("R.drawable.imge_5");
                //se cambia la descripción de la imagen
                imagen.setContentDescription(listDes.get(5));
                //se añade la nueva descripción para la imagen.
                descripcion.setText(listDes.get(5));
                break;
            case "R.drawable.imge_5":
                //se indica la nueva imagen
                imagen.setImageResource(listImg.get(5));
                //se indica el nueve Tag
                imagen.setTag("R.drawable.imge_6");
                //se cambia la descripción de la imagen
                imagen.setContentDescription(listDes.get(5));
                //se añade la nueva descripción para la imagen.
                descripcion.setText(listDes.get(5));
                break;
            case "R.drawable.imge_6":
                //se indica la nueva imagen
                imagen.setImageResource(listImg.get(6));
                //se indica el nueve Tag
                imagen.setTag("R.drawable.imge_7");
                //se cambia la descripción de la imagen
                imagen.setContentDescription(listDes.get(6));
                //se añade la nueva descripción para la imagen.
                descripcion.setText(listDes.get(6));
                break;
            case "R.drawable.imge_7":
                //se indica la nueva imagen
                imagen.setImageResource(listImg.get(7));
                //se indica el nueve Tag
                imagen.setTag("R.drawable.imge_8");
                //se cambia la descripción de la imagen
                imagen.setContentDescription(listDes.get(7));
                //se añade la nueva descripción para la imagen.
                descripcion.setText(listDes.get(7));
                break;
            case "R.drawable.imge_8":
                //se indica la nueva imagen
                imagen.setImageResource(listImg.get(0));
                //se indica el nueve Tag
                imagen.setTag("R.drawable.imge_1");
                //se cambia la descripción de la imagen
                imagen.setContentDescription(listDes.get(0));
                //se añade la nueva descripción para la imagen.
                descripcion.setText(listDes.get(0));
                break;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //se agregan las referenccias de las imagenes
        listImg.add(R.drawable.imge_1);
        listImg.add(R.drawable.imge_2);
        listImg.add(R.drawable.imge_3);
        listImg.add(R.drawable.imge_4);
        listImg.add(R.drawable.imge_5);
        listImg.add(R.drawable.imge_6);
        listImg.add(R.drawable.imge_7);
        listImg.add(R.drawable.imge_8);

        //se agregan las referencias a los string de descripción
        listDes.add(getString(R.string.descripcion1));
        listDes.add(getString(R.string.descripcion2));
        listDes.add(getString(R.string.descripcion3));
        listDes.add(getString(R.string.descripcion4));
        listDes.add(getString(R.string.descripcion5));
        listDes.add(getString(R.string.descripcion6));
        listDes.add(getString(R.string.descripcion7));
        listDes.add(getString(R.string.descripcion8));

        //se declaran los objetos del layout
        boton = findViewById(R.id.btCambiar);
        imagen = findViewById(R.id.imageView);
        grupoRadio = findViewById(R.id.rbGrupo);
        deshabilitar = findViewById(R.id.cbDeshabilitar);
        descripcion = findViewById(R.id.tvDescripcion);

        //evento de escucha para cuando se pulsa el botón "Cambiar imagen"
        boton.setOnClickListener(v->{
            //si el checkbox esta desactivado se podrá pulsar el botón.
            if(!deshabilitar.isChecked()){
                cambiarImagen(imagen);
            }
        });

        //evento que se ejecuta cuando se cambie entre los radionutton.
        grupoRadio.setOnCheckedChangeListener((group, checkedId) ->{
            //se obtiene el id del radiobutton seleccionado
            idrb = findViewById(checkedId);
            //condicional para mostrar u ocultar la imagen
            if(idrb.getTag().toString().equals("ocultar")){
                //si se ha seleccionado el radiobutton con el tag ocultar
                //se oculta la imagen pero se mantiene el espacio que ocupa
                imagen.setVisibility(View.INVISIBLE);
            }else if(idrb.getTag().toString().equals("mostrar")){
                //si se ha seleccionado el radiobutton con el tag mostrar
                //se se vuelve a mostrar la imagen
                imagen.setVisibility(View.VISIBLE);
            }
        });


    }
}