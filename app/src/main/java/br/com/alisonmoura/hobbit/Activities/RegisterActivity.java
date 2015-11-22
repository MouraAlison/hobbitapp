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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

    @Bind(R.id.editTextRegisterActivityUserPassword)
    EditText senha;

    @Bind(R.id.buttonRegisterActivitySalvar)
    Button btnSalvar;

    Usuario usuarioIntent;

    private static final int CAMERA_REQUEST = 123;
    private static final int CROP_REQUEST = 456;

    private File capture;
    private Uri captureUri;

    private File croped;
    private Uri cropedUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        nome = (EditText) findViewById(R.id.editTextRegisterActivityUserName);
//        foto = (ImageView) findViewById(R.id.imageViewRegisterActivityUserImage);
//        login = (EditText) findViewById(R.id.editTextRegisterActivityUserLogin);
//        mail = (EditText) findViewById(R.id.editTextRegisterActivityUserMail);
//        senha = (EditText) findViewById(R.id.editTextRegisterActivityUserPassword);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(Constants.EXTRA_USER_FOR_REGISTER_ACTIVITY)) {
                usuarioIntent = (Usuario) getIntent().getExtras().getSerializable(Constants.EXTRA_USER_FOR_REGISTER_ACTIVITY);
                if (usuarioIntent != null) {
                    if (usuarioIntent.getFoto() > 0)
                        foto.setImageResource(usuarioIntent.getFoto());
                    else foto.setImageResource(R.drawable.default_user_image);
                    nome.setText(usuarioIntent.getNome());
                    login.setText(usuarioIntent.getLogin());
                    mail.setText(usuarioIntent.getEmail());
                } else {
                    foto.setImageResource(R.drawable.default_user_image);
                }
            } else foto.setImageResource(R.drawable.default_user_image);
        } else foto.setImageResource(R.drawable.default_user_image);

    }

    @OnClick(R.id.buttonRegisterActivitySalvar)
    public void saveUsuario() {
        if (isValidFields()) {
            if (usuarioIntent == null)
                usuarioIntent = new Usuario();
            usuarioIntent.setFoto(R.drawable.default_user_image);
            usuarioIntent.setNome(nome.getText().toString());
            usuarioIntent.setLogin(login.getText().toString());
            usuarioIntent.setSenha(senha.getText().toString());
            usuarioIntent.setEmail(mail.getText().toString());

            //salva o usuario
            usuarioIntent.save();
            usuarioIntent = new Usuario();

            Intent i = new Intent(RegisterActivity.this, UserListActivity.class);
            i.putExtra(Constants.EXTRA_SAVE_SUCESS_FOR_USER_LIST_ACTIVITY, "Usuário salvo com sucesso!");
            startActivity(i);
            finish();

        } else
            //Toast.makeText(RegisterActivity.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
            Snackbar.make(this.btnSalvar, "Preencha todos os campos!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
    }

    @OnClick(R.id.imageViewRegisterActivityUserImage)
    public void changeImageProfile() {
        //Cria um novo arquivo para a foto
        capture = new File(getExternalCacheDir(), "profile.jpg");
        //Armazena o caminho da foto
        captureUri = Uri.fromFile(capture);
        //Intent para acionar a camera app
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Informa, pelo extra, o local onde a foto deve ser salva
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, captureUri);
        //inicia uma Activity com Callback
        startActivityForResult(intentCamera, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            //Criando um arquivo para armazenar a imagem cortada em um local não cache
            croped = new File(getFilesDir(), "croped-profile.jpg");
            cropedUri = Uri.fromFile(croped);
            //Criando um CropImageIntentBuilder e configurando a largura e alturo do corte e o URI onde será salvo a imagem cortada
            CropImageIntentBuilder cropIntent = new CropImageIntentBuilder(200, 200, cropedUri);
            //Setando a cor das linhas de corte
            cropIntent.setOutlineCircleColor(0xFF03A9F4);
            cropIntent.setCircleCrop(true);
            //Setando a URI da imagem a ser cortada
            cropIntent.setSourceImage(captureUri);
            //Start esperando um resultado na Intent do CropImage
            startActivityForResult(cropIntent.getIntent(RegisterActivity.this), CROP_REQUEST);
        } else if (requestCode == CROP_REQUEST && resultCode == RESULT_OK) {
            Bitmap imageCroped = BitmapFactory.decodeFile(croped.getAbsolutePath());
            foto.setImageBitmap(imageCroped);
        } else {
            Toast.makeText(RegisterActivity.this, "Imagem não carregada", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isValidFields() {

        if (foto.getDrawable() == null) {
            return false;
        } else if (TextUtils.isEmpty(nome.getText())) {
            return false;
        } else if (TextUtils.isEmpty(login.getText())) {
            return false;
        } else if (TextUtils.isEmpty(senha.getText())) {
            return false;
        } else if (TextUtils.isEmpty(mail.getText())) {
            return false;
        }
        return true;
    }
}