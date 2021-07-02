//dialog
package com.example.camppractice5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4, b5;

    int selectedMenu = 0;
    String menu[] = {"Chicken", "Pizza", "Pasta"};
    boolean checked[] = {true, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button1){
            displayDialog();
        }else if(v.getId() == R.id.button2){
            displayDialog2();
        }else if(v.getId() == R.id.button3){
            displayDialog3();
        }else if(v.getId() == R.id.button4){
            displayDialog4();
        }else if(v.getId() == R.id.button5){
            displayDialog5();
        }
    }

    //기본 대화상자
    private void displayDialog(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("기본대화상자1 ");
        dlg.setMessage("대화상자 메시지입니다.");
        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToast(null);
            }
        });

        dlg.show();
    }

    //라디오버튼 대화상자
    private void displayDialog2(){

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Radio button 대화상자 ");
        dlg.setSingleChoiceItems(menu, selectedMenu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedMenu = which;
            }
        });
        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToast(menu[selectedMenu] + " 이/가 선택되었습니다 !");
            }
        });

        dlg.show();
    }

    //체크박스 대화상자
    private void displayDialog3(){

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Checkbox 대화상자 ");
        dlg.setMultiChoiceItems(menu, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checked[which] = isChecked;
            }
        });
        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String msg2 = "";
                for(int i = 0; i<checked.length; i++)
                    if (checked[i])
                        msg2 = msg2 + " " + menu[i];
                displayToast(msg2 + " 이/가 선택되었습니다 !");

            }
        });

        dlg.show();
    }

    //사용자 정의 대화상자
    private void displayDialog4(){
        View view = View.inflate(this, R.layout.dialog , null);
        //final 넣어줘서 전역변수로 만들어줌
        final EditText editText = view.findViewById(R.id.etMsg);


        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("사용자 정의 대화상자 ");
        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.setView(view);
        dlg.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToast("입력한 메시지: "+ editText.getText().toString());
            }
        });
        dlg.setPositiveButton("Cancel", null);
        dlg.show();
    }

    private void displayDialog5() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string + "/" + year_string);

        Toast.makeText(this, "Date: " + dateMessage,
                Toast.LENGTH_SHORT).show();
    }



    private void displayToast(String msg) {
        if(msg== null) msg = "You pressed OK !";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}