package br.com.alisonmoura.hobbit.Migrations;

import android.database.sqlite.SQLiteDatabase;

import se.emilsjolander.sprinkles.Migration;

/**
 * Created by alisonmoura on 03/11/15.
 */
public class CreateUsuarioMigration extends Migration {

    @Override
    protected void doMigration(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Usuario(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "email TEXT," +
                "login TEXT," +
                "senha TEXT," +
                "foto INTEGER" +
                ")");
    }
}
