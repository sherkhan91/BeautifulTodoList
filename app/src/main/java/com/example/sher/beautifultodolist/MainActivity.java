package com.example.sher.beautifultodolist;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.ContextMenu;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    SQLiteDatabase db;
//    EditText editsearchname, editempname, editempmail, editempsalary;
//    Button Add, Delete, Modify, Vieww, Search;

    ListView listView;
    List<String> listItems;
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;
     static int clickCounter =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("todolist",Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS todolist("+"Id INTEGER PRIMARY KEY AUTOINCREMENT,Details VARHCAR);");

        listItems = new LinkedList<>();
        listView = (ListView) findViewById(R.id.listView);



        listItems.add("item A");
        listItems.add("item B");
        listItems.add("item C");
        listItems.add("item A");
        listItems.add("item B");
        listItems.add("item C");

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        listView.setAdapter(adapter);

        showItems();

        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                msg(" " +String.valueOf(position));
            }

        });


        registerForContextMenu(listView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Please Enter Details..");
               final View view1 =  getLayoutInflater().inflate(R.layout.add_popup,null);
                alertDialog.setView(view1);
                alertDialog.setPositiveButton("Done!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        StringBuffer buffer =  new StringBuffer();
                        EditText editText = (EditText)view1.findViewById(R.id.addEditText);
                        buffer.append(editText.getText().toString());
                        db.execSQL("INSERT INTO todolist(Details)VALUES('"+buffer+"');");

                        listItems.clear();
                        adapter.notifyDataSetChanged();
                        showItems();

                        dialogInterface.dismiss();

                    }
                });
                AlertDialog alert =  alertDialog.create();

                alert.show();
                ((Button)alert.findViewById(android.R.id.button1)).setBackgroundColor(getResources().getColor(R.color.colorRoyalBlue));



            }
        });


      //  fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorRoyalBlue)));

//
//        // Create database, EmployeeDB database name
//        db= openOrCreateDatabase("EmployeeDB",Context.MODE_PRIVATE,null);
//        // Create table Employee
//        db.execSQL("CREATE TABLE IF NOT EXISTS Employee(" +
//                "EmpId INTEGER PRIMARY KEY AUTOINCREMENT, EmpName VARCHAR, EmpMail VARCHAR,EmpSalary VARCHAR);");
////
//        editsearchname = (EditText) findViewById(R.id.edtemployeename);
//        editempname = (EditText) findViewById(R.id.editText);
//        editempmail = (EditText) findViewById(R.id.editText2);
//        editempsalary = (EditText) findViewById(R.id.editText3);
//
//        Add = (Button) findViewById(R.id.btnsave);
//        Delete = (Button) findViewById(R.id.btndel);
//        Modify = (Button) findViewById(R.id.btnupdate);
//        Vieww = (Button) findViewById(R.id.btnselect);
//        Search = (Button) findViewById(R.id.btnselectperticular);
//
//        Add.setOnClickListener(this);
//        Delete.setOnClickListener(this);
//        Modify.setOnClickListener(this);
//        Vieww.setOnClickListener(this);
//        Search.setOnClickListener(this);

    } // End of OnCreate

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);
        if(view.getId()==R.id.listView){
            menu.setHeaderTitle("Delete Option");
            menu.add(0,0,0,"Delete");

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==0){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            int position = info.position;

            Cursor cursor  = db.rawQuery("SELECT * FROM todolist",null);
            if(cursor.getCount()==0)
                return true;
            if(cursor.moveToPosition(position)){
                String rowId = cursor.getString(0);
                db.execSQL("DELETE FROM todolist WHERE Id = '"+rowId+"'");
            }

            showItems();

            return true;

            //Toast.makeText(MainActivity.this,"add Selected Item: "+item.getTitle()+" "+item.getItemId(),Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void showItems(){
        listItems.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM todolist",null);
        if(cursor.getCount()==0)
            return;
        while(cursor.moveToNext()){

            listItems.add(cursor.getString(1));
        }
        adapter.notifyDataSetChanged();
    }


    public void msg(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onClick(View v){
//        if(v.getId()==R.id.btnsave){
//            // code for save data
//            if(editempname.getText().toString().trim().length()==0||
//                    editempmail.getText().toString().trim().length()==0||
//                    editempsalary.getText().toString().trim().length()==0){
//                msg("Please enter all values");
//                return;
//            } else{
//
//                db.execSQL("INSERT INTO Employee(EmpName,EmpMail,EmpSalary)VALUES('"+ editempname.getText()+"','"+ editempmail.getText()+ "','"+editempsalary.getText()+"');");
//                msg("Record Added");
//            }
//
//        } else if(v.getId()==R.id.btnupdate){
//            // code for update data
//            if(editsearchname.getText().toString().trim().length()==0){
//                msg("Enter Employee Name");
//                return;
//            }
//            Cursor c = db.rawQuery("SELECT * FROM Employee WHERE EmpName='"+editsearchname.getText()+"'",null);
//            Log.d("check","didn't came here");
//            if(c.moveToFirst()){
//                db.execSQL("UPDATE Employee SET EmpName = '"+editempname.getText()+"',EmpMail='"+editempmail.getText()+"',EmpSalary='"+editempsalary.getText()+"' WHERE EmpName='"+editsearchname.getText()+"'");
//                msg("Record Modified");
//
//            } else{
//                msg("Invalid employee name");
//            }
//        } else if(v.getId()==R.id.btndel){
//            // code for delete data
//            if(editsearchname.getText().toString().trim().length()==0){
//                msg("please enter employee name");
//                return;
//            }
//            Cursor c  = db.rawQuery("SELECT * FROM Employee WHERE EmpName='"+editsearchname.getText()+"'",null);
//            if(c.moveToFirst()){
//                db.execSQL("DELETE FROM Employee WHERE EmpName = '"+editsearchname.getText()+"'");
//                msg("Record Deleted");
//            } else{
//                msg("Invalid, EmployeeName");
//            }
//        }
//
//        else if(v.getId()==R.id.btnselect){
//            // code for select all data
//            Cursor c = db.rawQuery("SELECT * FROM Employee",null);
//            if(c.getCount()==0){
//                msg("No record found");
//                return;
//            }
//            StringBuffer buffer =  new StringBuffer();
//            while(c.moveToNext()){
//                buffer.append("Employee Name: "+c.getString(1)+"\n");
//                buffer.append("Employee Mail: "+c.getString(2)+"\n");
//                buffer.append("Employee Salary: "+c.getString(3)+"\n\n");
//
//            }
//            msg(buffer.toString());
//
//        } else if(v.getId()==R.id.btnselectperticular){
//            // code for select particular data
//            if(editsearchname.getText().toString().trim().length()==0){
//                msg("Enter Employee Name");
//                return;
//            }
//            Cursor c =  db.rawQuery("SELECT * FROM Employee WHERE EmpName='"+editsearchname.getText()+"'",null);
//            if(c.moveToFirst()){
//                editempname.setText(c.getString(1));
//                editempmail.setText(c.getString(2));
//                editempsalary.setText(c.getString(3));
//
//            }
//            else
//            {
//                msg("Invalid employee name");
//            }
//        }
//  }
//



}
