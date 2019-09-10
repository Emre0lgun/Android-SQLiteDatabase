package com.example.deneme2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Veritabanı extends SQLiteOpenHelper {
    private static final String TABLE_NAME="Personel"; //Tablo Adı
    private static final String DATABASE_NAME="deneme";  //Veritabanı Adı
    private static final int DATABASE_VERSION=1;
    private static final String PERSONEL_ADI="PersonelAdı";
    private static final String PERSONEL_SOYAD="PersonelSoyad";


    public Veritabanı(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION); }; //Kurucu metot ile veritabanımızı oluşturduk.
    SQLiteDatabase sqLiteDatabase;

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT,"+PERSONEL_ADI+" TEXT,"+PERSONEL_SOYAD+" TEXT);");
        //Burada çalışma anında veritabanımızın içine tabloyu oluşturduk ve değerleri belirledik.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE İF EXISTS "+TABLE_NAME);
        this.onCreate(db);//onCreate fonksiyonu çağrılarak oluşturduğumuz veritabanı tablosunda güncelleme yaptık.
    }
    public void veriSil(String kadi){
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        db.delete(TABLE_NAME,PERSONEL_ADI + "=?",new String[]{String.valueOf(kadi)});//personel adı girilerek veri silme işlemi yapıyoruz.
        db.close();//veritabanı kapatma işlemi
    }
    public void veriEkle(String ad,String soyad){
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        ContentValues cv=new ContentValues();//contentValues sınıfından bir nesne oluşturuyoruz.ContentValues değerleri tutmamızı sağlıyor.
        cv.put(PERSONEL_ADI,ad.trim());//Ad alıyoruz.
        cv.put(PERSONEL_SOYAD,soyad.trim());//Soyad alıyoruz.
        long r=db.insert(TABLE_NAME,null,cv);//Tabloya ekleme yaptığımız fonksiyon.
        if(r>-1) {
            Log.i("tag", "İşlem Başarılı");//ekleme olması durumunda bu çıktıyı verir.
        }
        else
            Log.e("tag","İşlem Başarısız");//ekleme olmaması durumunda bu çıktıyı verir.
        db.close();//Veritabanı kapatma işlemi
    }
    public List<String> veriListele(){
        List<String> veriler=new ArrayList<String>();//String türünde bir liste oluşturduk.
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        String[] sutunlar={PERSONEL_ADI,PERSONEL_SOYAD};
        Cursor cr=db.query(TABLE_NAME,sutunlar,null,null,null,null,null);//query fonksiyonu ile aldığımız parametreler yoluyla komutu kendi içerisinde yapılandırıyoruz.
        while(cr.moveToNext()){//sırasıyla verileri listelememizi sağlıyor.
            veriler.add(cr.getString(0)+" "+cr.getString(1));

        }
        return veriler;
    }
}