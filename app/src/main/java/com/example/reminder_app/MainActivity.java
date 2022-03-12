package com.example.reminder_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
@SuppressWarnings("unchecked")

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdaptaer;
    private ListView listview;
    private FloatingActionButton button;
    private DatePickerDialog datePickerDialog;
    Button bgbutton, today, someday, dark;
     DBHelper DB;


    public void addItem(String list) {
        EditText input = findViewById(R.id.editTextTextPersonName);

        if((list.equals(" "))){
            Toast.makeText(getApplicationContext(),"Please Enter a Text", Toast.LENGTH_LONG).show();


        }else{
            itemsAdaptaer.add(list);
            input.setText(" ");
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        EditText editText = findViewById(R.id.editTextTextPersonName);

        FloatingActionButton submit = findViewById(R.id.floatingActionButton);
        DB = new DBHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String list = editText.getText().toString();

                Boolean checkinsertdata = DB.insertlist(list);

                if (checkinsertdata == true){
                    Toast.makeText(MainActivity.this,"List Added",Toast.LENGTH_SHORT).show();
                }
            else{
                    Toast.makeText(MainActivity.this,"List Not Added Technical Issue",Toast.LENGTH_SHORT).show();

                }
                Cursor res = DB.getdata();
                if(res.getCount() == 0) {


                }
                else {
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    Toast.makeText(MainActivity.this,res.getString(0),Toast.LENGTH_SHORT).show();

                    addItem(res.getString(0));
                }
                }
            }
        });



        final ConstraintLayout constraintLayout;
        listview =findViewById(R.id.listView);

        EditText input = findViewById(R.id.editTextTextPersonName);

        final ListView list  = findViewById(R.id.listView);

        button = findViewById(R.id.floatingActionButton);

        today = findViewById(R.id.button);

        initDatePicker();
        someday = findViewById(R.id.button3);
        someday.setText(getTodaysDate());

        bgbutton =  findViewById(R.id.bgchange);

        constraintLayout = findViewById(R.id.editText);

        dark = findViewById(R.id.dark);


        bgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                constraintLayout.setBackgroundResource(R.color.white);
                button.setBackgroundResource(R.color.white);
                bgbutton.setBackgroundResource(R.color.white);
                listview.setBackgroundResource(R.color.black);

                today.setBackgroundResource(R.color.white);
                someday.setBackgroundResource(R.color.white);
                input.setBackgroundResource(R.color.white);
                input.setTextColor(Color.parseColor("#FF000000"));
                input.setBackground(getResources().getDrawable(R.drawable.my_borfer_for_light));
//                listview.setBackground(getResources().getDrawable(R.drawable.my_borfer_for_light));

            }
        });


        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constraintLayout.setBackgroundResource(R.color.black);
                button.setBackgroundResource(R.color.white);
                bgbutton.setBackgroundResource(R.color.white);
                listview.setBackgroundResource(R.color.black);
                today.setBackgroundResource(R.color.white);
                someday.setBackgroundResource(R.color.white);
                input.setBackgroundResource(R.color.black);
                input.setTextColor(Color.parseColor("#FFFFFFFF"));
                input.setBackground(getResources().getDrawable(R.drawable.my_border));
                listview.setBackground(getResources().getDrawable(R.drawable.my_border));

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem("");
            }
        });

        items =  new ArrayList<>();

        itemsAdaptaer = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, items);
        listview.setAdapter(itemsAdaptaer);
        setupListViewListener();

    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                someday.setText(date);
                opensomeDay(date);

            }



        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener,year,month,day);

    }
    private void opensomeDay(String date) {
        Intent intent;
        intent = new Intent(this, someDay.class);
        intent.putExtra("Date",date);
        startActivity(intent);
    }
            private String makeDateString(int day, int month, int year) {
                return getMonthFormat(month) + " "  + day + " "+ year;
            }

            private String getMonthFormat(int month) {
                if(month == 1)
                    return "JAN";
                if(month == 2)
                    return "FEB";
                if(month == 3)
                    return "MAR";
                if(month == 4)
                    return "APR";
                if(month == 5)
                    return "MAY";
                if(month == 6)
                    return "JUN";
                if(month == 7)
                    return "JUL";
                if(month == 8)
                    return "AUG";
                if(month == 9)
                    return "SEP";
                if(month == 10)
                    return "OCT";
                if(month == 11)
                    return "NOV";
                if(month == 12)
                    return "DEC";
                return "JAN";

            }



    private void setupListViewListener() {
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Items Removed", Toast.LENGTH_LONG).show();

                items.remove(i);

                itemsAdaptaer.notifyDataSetChanged();
                return true;
                    }
        });

    }



    public void opendatepicker(View view) {
        datePickerDialog.show();
    }
}