package br.com.alisonmoura.hobbit.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.camera.CropImageIntentBuilder;

import java.io.File;

import br.com.alisonmoura.hobbit.Models.Usuario;
import br.com.alisonmoura.hobbit.R;
import br.com.alisonmoura.hobbit.Utils.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.imageViewRegisterActivityUserImage)
    ImageView foto;

    @Bind(R.id.editTextRegisterActivityUserName)
    EditText nome;

    @Bind(R.id.editTextRegisterActivityUserLogin)
    EditText login;

    @Bind(R.id.editTextRegisterActivityUserMail)
    EditText mail;

    Usuario usuarioIntent;

    private static final int CAMERA_REQUEST = 123;
    private static final int CROP_REQUEST = 456;

    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(getIntent().getExtras()!=null){
            if (getIntent().getExtras().containsKey(Constants.EXTRA_USER_FOR_REGISTER_ACTIVITY)) {
                usuarioIntent = (Usuario) getIntent().getExtras().getSerializable(Constants.EXTRA_USER_FOR_REGISTER_ACTIVITY);
                if (usuarioIntent != null) {
                    if(usuarioIntent.getFoto()>0)
                    foto.setImageResource(usuarioIntent.getFoto());
                    nome.setText(usuarioIntent.getNome());
                    login.setText(usuarioIntent.getLogin());
                    mail.setText(usuarioIntent.getEmail());
                } else {
                    foto.setImageResource(R.drawable.default_user_image);
                }
            }
        } else
            foto.setImageResource(R.drawable.default_user_image);

    }

    @OnClick(R.id.imageViewRegisterActivityUserImage)
    public void changeImageProfile() {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCamera, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap imageCapture = (Bitmap) data.getExtras().get("data");
            foto.setImageBitmap(imageCapture);
        }else{
            Toast.makeText(RegisterActivity.this, "Imagem não carregada", Toast.LENGTH_SHORT).show();
        }

    }


//    @OnClick(R.id.imageViewRegisterActivityUserImage)
//    public void changeImageProfile() {
//        File image = new File(getExternalCacheDir(), "teste");
//        imageUri = Uri.fromFile(image);
//        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        startActivityForResult(intentCamera, CAMERA_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        File croppedImage = new File(getFilesDir(),"testeCropped.jpg");
//        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
//            Uri croppedImageUri = Uri.fromFile(croppedImage);
//            CropImageIntentBuilder crop = new CropImageIntentBuilder(200, 200, croppedImageUri);
//            crop.setOutlineCircleColor(0xFF03A9F4);
//            crop.setSourceImage(imageUri);
//            startActivityForResult(crop.getIntent(RegisterActivity.this), CROP_REQUEST);
//        }else if(requestCode == CROP_REQUEST && resultCode == RESULT_OK){
//            foto.setImageBitmap(BitmapFactory.decodeFile(croppedImage.getAbsolutePath()));
//        }else{
//            Toast.makeText(RegisterActivity.this, "Imagem não carregada", Toast.LENGTH_SHORT).show();
//        }
//
//    }

}
