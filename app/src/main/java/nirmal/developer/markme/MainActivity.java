package nirmal.developer.markme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText ed1,ed2;
    public static final String MyPREFERENCES="My";
    public static final String Name="nameKey";
    public static final String RegNo="regKey";
    String n,r;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        ed1=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);
        sharedPreferences= getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed1.getText().toString().isEmpty()||ed2.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"EmptyField",Toast.LENGTH_LONG).show();
                }
                else
                {
                 String n=ed1.getText().toString();
                 String r=ed2.getText().toString();
                 SharedPreferences.Editor editor = sharedPreferences.edit();

                 editor.putString(Name,n);
                 editor.putString(RegNo,r);
                 editor.apply();
                 Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
                 Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                 startActivity(intent);
                }
            }
        });

    }
}
