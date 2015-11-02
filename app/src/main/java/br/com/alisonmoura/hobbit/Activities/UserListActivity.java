package br.com.alisonmoura.hobbit.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.alisonmoura.hobbit.Adapters.UserListAdapter;
import br.com.alisonmoura.hobbit.Models.Usuario;
import br.com.alisonmoura.hobbit.R;
import br.com.alisonmoura.hobbit.Utils.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;

public class UserListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbarUserListActivity)
    Toolbar toolbar;

    @Bind(R.id.floatingActionButtonUserListActivity)
    FloatingActionButton fab;

    @Bind(R.id.drawerLayoutUserListActivity)
    DrawerLayout drawer;

    @Bind(R.id.navgationViewUserListActivity)
    NavigationView navigationView;

    @Bind(R.id.listViewUserListActivityUserList)
    ListView listView;

    List<Usuario> usuarios;

    private String[] nomes =
            {
                    "Alison Moura",
                    "Nayara Romeiro",
                    "Virmerson Bento",
                    "Cesar Bassani",
                    "Vinicius Souza",
                    "Silvio Chapolin",
                    "Pedro Bispo",
                    "Alison Moura",
                    "Nayara Romeiro",
                    "Virmerson Bento",
                    "Cesar Bassani",
                    "Vinicius Souza",
                    "Silvio Chapolin",
                    "Pedro Bispo",
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        final UserListAdapter adapter = new UserListAdapter(this,getUsuarioList());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Usuario usuarioClicado = adapter.getItem(position);
                Intent i = new Intent(UserListActivity.this, RegisterActivity.class);
                if(usuarioClicado!=null){
                    i.putExtra(Constants.EXTRA_USER_FOR_REGISTER_ACTIVITY, usuarioClicado);
                }
                startActivity(i);


            }
        });

    }

    public List<Usuario> getUsuarioList() {

        usuarios = new ArrayList<>();

        for(String nome:nomes){
            Usuario usuario = new Usuario();
            usuario.setNome(nome);

            usuarios.add(usuario);
        }

        return usuarios;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara_activity_fotos) {
            // Handle the camera action
        } else if (id == R.id.nav_fotos_activity_fotos) {

        } else if (id == R.id.nav_slideshow_activity_fotos) {

        } else if (id == R.id.nav_manage_activity_fotos) {

        } else if (id == R.id.menu_drawer_home) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
