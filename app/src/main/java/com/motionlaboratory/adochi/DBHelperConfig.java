package com.motionlaboratory.adochi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by naofal on 3/9/2017.
 */

public class DBHelperConfig extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Adochi.db";
    public static final String TABLE_NAME_1 = "User_table";
    public static final String NO_KTP = "NO_KTP";
    public static final String NAMA = "NAMA";
    public static final String EMAIL = "EMAIL";
    public static final String ALAMAT = "ALAMAT";
    public static final String PASS = "PASSWORD";

    public static final String TABLE_NAME_2 = "PANTI_table";
    public static final String ID_PANTI = "ID_PANTI";
    public static final String NAMA_PANTI = "NAMA_PANTI";
    public static final String ALAMAT_P = "ALAMAT_P";
    public static final String KONTAK_P = "KONTAK";
    public static final String EMAIL_P = "EMAIL";

    public static final String TABLE_NAME_3 = "Adopsi_table";
    public static final String ID_ADOPSI = "ID_ADOPSI";
    public static final String A_NO_KTP = "NO_KTP";
    public static final String A_NAMA_USER = "NAMA";
    public static final String A_ID_PANTI = "ID_PANTI";
    public static final String A_NAMA_PANTI = "NAMA_PANTI";
    public static final String A_NAMA_ANAK = "NAMA_ANAK";

    public static final String TABLE_NAME_4 = "Donasi_table";
    public static final String ID_DONASI = "ID_DONASI";
    public static final String EMAIL_DONATUR = "EMAIL";
    public static final String NOMINAL = "NOMINAL";
    public static final String KATEGORI = "KATEGORI";

    public static final String TABLE_NAME_5 = "Anak_table";
    public static final String ID_ANAK = "ID_ANAK";
    public static final String NAMA_ANAK = "NAMA";
    public static final String ASAL_ANAK = "ASAL";
    public static final String GENDER_ANAK = "GENDER";
    public static final String UMUR = "UMUR";
    public static final String GAMBAR_ANAK = "GAMBAR_ANAK";

    private static final String TABLE_NAME_6 = "Login_table";
    public static final String EMAIL_LOGIN = "EMAIL";

    public DBHelperConfig(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME_1 +
                " ( " +
                "NO_KTP INTEGER PRIMARY KEY," +
                " NAMA TEXT, " +
                " EMAIL TEXT, " +
                " ALAMAT TEXT,"+
                " PASSWORD TEXT" +
                ");"

        );
//                " create table " + TABLE_NAME_2 +  " ( " +
//                "ID_PANTI INTEGER PRIMARY KEY AUTOINCREMENT," +
//                " NAMA_PANTI TEXT, " +
//                " ALAMAT TEXT, " +
//                "KONTAK TEXT, " +
//                "EMAIL TEXT " +
//                "); " +
//
//                " create table " + TABLE_NAME_3 +  " ( " +
//                "ID_ADOPSI INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "NO_KTP TEXT, " +
//                "ID_PANTAI INT"+
//                " NAMA_PANTI, " +
//                "NAMA_ANAK  "+
//                "); " +

        sqLiteDatabase.execSQL(" create table " + TABLE_NAME_4 +  " ( " +
                "ID_DONASI INTEGER PRIMARY KEY AUTOINCREMENT," +
                "EMAIL TEXT, " +
                "NOMINAL INT,"+
                "KATEGORI TEXT" +
                "); "
        );

        sqLiteDatabase.execSQL(" create table "+ TABLE_NAME_5 +" ( " +
                "ID_ANAK INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAMA TEXT, " +
                "UMUR INT, "+
                "GENDER TEXT, " +
                "ASAL TEXT, " +
                "GAMBAR_ANAK INT " +
                "); "
        );

        sqLiteDatabase.execSQL(" create table "+ TABLE_NAME_6 +" ( " +
                "ID_LOGIN INTEGER PRIMARY KEY AUTOINCREMENT," +
                "EMAIL TEXT " +
                "); "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST" + TABLE_NAME_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST" + TABLE_NAME_2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST" + TABLE_NAME_3);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST" + TABLE_NAME_4);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST" + TABLE_NAME_5);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String no_ktp, String nama, String email, String pass, String alamat) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NO_KTP, no_ktp);
        contentValues.put(NAMA, nama);
        contentValues.put(EMAIL, email);
        contentValues.put(ALAMAT, alamat);
        contentValues.put(PASS, pass);
        long result = sqLiteDatabase.insert(TABLE_NAME_1, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertLogin(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL_LOGIN, email);
        long result = sqLiteDatabase.insert(TABLE_NAME_6, null, contentValues);;

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }



    public boolean insertData(String nama_panti, String alamat_p, String kontak_p, String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA_PANTI, nama_panti);
        contentValues.put(EMAIL_P, email);
        contentValues.put(ALAMAT_P, alamat_p);
        contentValues.put(KONTAK_P, kontak_p);

        long result = sqLiteDatabase.insert(TABLE_NAME_2, null, contentValues);;
        sqLiteDatabase.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertDataDonasi(String email, int nominal, String kategori){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL_DONATUR, email);
        contentValues.put(NOMINAL, nominal);
        contentValues.put(KATEGORI, kategori);

        long result = sqLiteDatabase.insert(TABLE_NAME_4, null, contentValues);;

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertDataAnak(String nama, int usia, String gender, String asal, int gmb){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA_ANAK, nama);
        contentValues.put(UMUR, usia);
        contentValues.put(GENDER_ANAK, gender);
        contentValues.put(ASAL_ANAK, asal);
        contentValues.put(GAMBAR_ANAK, gmb);

        long result = sqLiteDatabase.insert(TABLE_NAME_5, null, contentValues);;

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Integer deleteLogin(String email){
        SQLiteDatabase sql = this.getWritableDatabase();
        return sql.delete(TABLE_NAME_6,"ID_ANAK = ?",new String[]{email});
    }

}