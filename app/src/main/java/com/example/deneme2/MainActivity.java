package com.example.deneme2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn,btn1,btn2;
    EditText edt1,edt2;
    ListView listView;
    LinearLayout linearLayout;
    public void init(){
        edt1=(EditText)findViewById(R.id.editText);
        edt2=(EditText)findViewById(R.id.editText2);
        btn=(Button)findViewById(R.id.button);
        btn1=(Button)findViewById(R.id.button2);
        btn2=(Button)findViewById(R.id.button3);
        listView=(ListView)findViewById(R.id.veriler);

    }
    public void buton_click(){

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veritabanı veritabanı=new Veritabanı(MainActivity.this);

                    veritabanı.veriEkle(edt1.getText().toString(),edt2.getText().toString());
                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    edt1.setText("");
                    edt2.setText("");

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veritabanı veritabanı=new Veritabanı(MainActivity.this);
                List<String> vVeriler =veritabanı.veriListele();
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.activity_list_item,android.R.id.text1,vVeriler);
                listView.setAdapter(adapter);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veritabanı veritabanı=new Veritabanı(MainActivity.this);
                veritabanı.veriSil(edt1.getText().toString());
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        buton_click();
    }
}