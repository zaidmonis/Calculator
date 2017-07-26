package com.example.zaid.calculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    String display="";
    String currentOp= "";
    int count= 0;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.textView2);
        Button b2;
        b2 = (Button) findViewById(R.id.bClear);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(display.length()>=1)
                 display=display.substring(0, display.length()-1);

                updateScreen();
            }
        });
        b2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
               clear();
                updateScreen();
                return true;
            }
        });

    }

    public void updateScreen(){
        tv.setText(display);
    }






   public void onClickNumber(View v){
       Button b = (Button) v;
       display += b.getText();
       updateScreen();
   }





   public void onClickOperator(View v){
       int intIndex1, intIndex2, intIndex3, intIndex4;
       go:
       intIndex1 = display.indexOf("+");
       intIndex2 = display.indexOf("-");
       intIndex3 = display.indexOf("x");
       intIndex4 = display.indexOf("/");
       if(intIndex1!=-1||intIndex2!=-1||intIndex3!=-1||intIndex4!=-1){
           display=display.substring(0, display.length()-1);
       }
       Button b = (Button) v;
       display += b.getText();
       currentOp = b.getText().toString();
       count+=1;
       updateScreen();
   }





   public void clear(){
       display="";
       currentOp="";
   }



    public double operate(String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-":  return Double.valueOf(a) - Double.valueOf(b);
            case "x": return Double.valueOf(a) * Double.valueOf(b);
            case "/":
                try{return Double.valueOf(a) / Double.valueOf(b);}
                catch(ArithmeticException e){
                    Toast.makeText(getApplicationContext(), "Exception" + e, Toast.LENGTH_SHORT).show();
                }
            default: return 0;

        }
    }




   public void onClickEqual(View v){
       Button b = (Button) v;
       try {
           String[] operation = display.split(Pattern.quote(currentOp));

           if (operation.length < 2) return;
           double _result = operate(operation[0], operation[1], currentOp);
           if ((_result % 1) == 0.0) {
               int resu = (int) _result;
               clear();
               tv.setText(display + "\n" + resu);
           }
           else {
               clear();
               tv.setText(display + "\n" + String.valueOf(_result));
           }
       }
       catch(Exception e){
           Toast.makeText(getApplicationContext(), "Exception"+ e, Toast.LENGTH_SHORT).show();
       }





   }







}
