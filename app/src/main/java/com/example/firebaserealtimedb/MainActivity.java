package com.example.firebaserealtimedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    EditText name,id,about,branch;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.edt_txt_name);
        id=findViewById(R.id.edt_txt_id);
        branch=findViewById(R.id.edt_txt_branch);
        about=findViewById(R.id.noemm);
        register=findViewById(R.id.btn_register);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String getName=name.getText().toString();
                String getid=id.getText().toString();
                String getabout=about.getText().toString();
                String getbranch=branch.getText().toString();

                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("name",getName);
                hashMap.put("Id",getid);
                hashMap.put("Branch",getbranch);
                hashMap.put("About",getabout);
                databaseReference.child("Student")
                        .setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });




            }
        });
    }
}