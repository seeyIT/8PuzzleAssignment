package com.kornel_ius.a8puzzle

/**
 * Created by Kornel_ius.
 */
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues
import android.util.Log


class MyDBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "8puzzle.db"
        val TABLE_GAMES = "games"

        val COLUMN_ID = "_id"
        val COLUMN_PLAYERNAME = "playername"
        val COLUMN_TIME = "time"
    }


    override fun onCreate(db: SQLiteDatabase) {
        Log.e("create", "databe")
        val CREATE_RESULT_TABLE = ("CREATE TABLE " +
                TABLE_GAMES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PLAYERNAME
                + " TEXT," + COLUMN_TIME + " INTEGER" + ")")
        db.execSQL(CREATE_RESULT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES)
        onCreate(db)
    }

    fun addGame(game: Game) {

        val values = ContentValues()
        values.put(COLUMN_PLAYERNAME, game.playerName)
        values.put(COLUMN_TIME, game.time)

        val db = this.writableDatabase
        db.insert(TABLE_GAMES, null, values)
        db.close()
    }

    fun getGames(): ArrayList<Game> {
        val QUERY = "SELECT * FROM $TABLE_GAMES ORDER BY $COLUMN_TIME ASC"

        var games: ArrayList<Game> = ArrayList()
        val db = this.readableDatabase

        val cursor = db.rawQuery(QUERY, null)
        var playerName: String = ""
        var time: Int = 0

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                playerName = cursor.getString(cursor.getColumnIndex(COLUMN_PLAYERNAME))
                time = cursor.getInt(cursor.getColumnIndex(COLUMN_TIME))

                games.add(Game(playerName, time))
                cursor.moveToNext()
            }
        }
        db.close()

        return games
    }
}
