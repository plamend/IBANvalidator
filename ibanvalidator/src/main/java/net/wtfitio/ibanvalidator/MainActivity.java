package net.wtfitio.ibanvalidator;

import android.app.Activity;

import android.os.Bundle;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by plamend on 12/11/13.
 */
public class MainActivity extends Activity {

    EditText iban;
    Button valid;
    ClipboardManager clipBoard;
    Bundle country;
    Bundle alpha;
    String to_validate;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        iban = (EditText)findViewById(R.id.iban);
        valid=(Button)findViewById(R.id.button_valid);
         clipBoard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
         country = new Bundle();
         alpha = new Bundle();
        country.putInt("BG",22);
        country.putInt("BH",22);
        country.putInt("AL",28);
        country.putInt("AD",24);
        country.putInt("AT",20);
        country.putInt("BH",22);
        country.putInt("BE",16);
        country.putInt("BA",20);
        country.putInt("BR",29);
        country.putInt("BG",22);
        country.putInt("HR",21);
        country.putInt("CY",28);
        country.putInt("CZ",24);
        country.putInt("DK",18);
        country.putInt("EE",20);
        country.putInt("FO",18);
        country.putInt("FI",18);
        country.putInt("FR",27);
        country.putInt("GE",22);
        country.putInt("DE",22);
        country.putInt("GI",23);
        country.putInt("GR",27);
        country.putInt("GL",18);
        country.putInt("HU",28);
        country.putInt("IS",26);
        country.putInt("IE",22);
        country.putInt("IL",23);
        country.putInt("IT",27);
        country.putInt("LV",21);
        country.putInt("LE",28);
        country.putInt("LI",21);
        country.putInt("LT",20);
        country.putInt("LU",20);
        country.putInt("MK",19);
        country.putInt("MT",31);
        country.putInt("MU",30);
        country.putInt("MD",24);
        country.putInt("MC",27);
        country.putInt("ME",22);
        country.putInt("NL",18);
        country.putInt("NO",15);
        country.putInt("PK",24);
        country.putInt("PS",29);
        country.putInt("PL",28);
        country.putInt("PT",25);
        country.putInt("AZ",28);
        country.putInt("RO",24);
        country.putInt("SM",27);
        country.putInt("SA",24);
        country.putInt("RS",22);
        country.putInt("SK",24);
        country.putInt("SI",19);
        country.putInt("ES",24);
        country.putInt("SE",24);
        country.putInt("CH",21);
        country.putInt("TN",24);
        country.putInt("TR",26);
        country.putInt("AE",23);
        country.putInt("GB",22);

        int assci;
        int code = 10;
        for (assci=65; assci <= 90; assci++) {
            alpha.putInt(Character.toString((char) assci),code);
            code++;
        }
        Toast.makeText(getBaseContext(),"Not valid lenght for ",Toast.LENGTH_SHORT).show();
      valid.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              to_validate="";
              String iban_new = iban.getText().toString();
              if (!iban_new.equals("")) {
                String coutry_code = iban_new.substring(0,2);
                  if (iban_new.length()==country.getInt(coutry_code)) {
                      String firs_part = iban_new.substring(0,4);
                      String new_iban_str = iban_new.substring(4)+firs_part;
                      for ( int i = 0; i < new_iban_str.length(); ++i ) {
                          char c = new_iban_str.charAt( i );
                          int anInt = alpha.getInt(String.valueOf(c));
                          if (anInt!=0){

                              Log.v("chars",String.valueOf(anInt));
                              to_validate+=String.valueOf(anInt);
                          }
                          else{
                              Log.v("chars",String.valueOf(c));
                              to_validate+=String.valueOf(c);
                          }
                      }
                      Log.v("new_string",new_iban_str);
                      Log.v("new_string",to_validate);
                      int validate_lenght = to_validate.length();
                      int to_devide = validate_lenght -9;
                      int string_count = Math.round(to_devide%7);
                      Log.v("round",String.valueOf(string_count));
                      String n1 = to_validate.substring(0,9);
                      String ost = String.valueOf(Integer.valueOf(n1)%97);

                      String n2 = to_validate.substring(9,16);
                      String n3 = to_validate.substring(16,24);
                      String n4 = to_validate.substring(24,34);
                      Log.v("n5",String.valueOf(validate_lenght));

                      Log.v("n1",n1);
                      Log.v("n1",ost);
                      Log.v("n2",n2);
                      Log.v("n3",n3);
                      Log.v("n4",n4);
//kk

                  }
                  else{

                      Toast.makeText(getBaseContext(),"Not valid lenght for "+ coutry_code,Toast.LENGTH_SHORT).show();
                  }
              }
              else{
                  Toast.makeText(getBaseContext(),"Enter number!!",Toast.LENGTH_SHORT).show();
              }
          }

          private String valid_math(String str1, String str2) {
              String output="";
              int out = Integer.valueOf(str1.concat(str2));
              output = String.valueOf(out%97);


              return output;
          }


      });



    }
}
