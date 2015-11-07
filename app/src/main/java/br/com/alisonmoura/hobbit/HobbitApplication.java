package br.com.alisonmoura.hobbit;

import android.app.Application;

import br.com.alisonmoura.hobbit.Migrations.CreateUsuarioMigration;
import se.emilsjolander.sprinkles.Sprinkles;

/**
 * Created by alisonmoura on 03/11/15.
 */
public class HobbitApplication extends Application {

    private Sprinkles sprinkles;

    @Override
    public void onCreate() {
        super.onCreate();
        sprinkles = Sprinkles.init(getApplicationContext());
        runMigrations();
    }

    private void runMigrations(){
        sprinkles.addMigration(new CreateUsuarioMigration());
    }
}
