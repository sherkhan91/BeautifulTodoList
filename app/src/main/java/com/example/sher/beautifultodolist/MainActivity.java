package com.example.sher.beautifultodolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.IconCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ContextMenu;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<String> listItems;
    ItemArrayAdapter adapter;
    //ArrayAdapter<String> adapter;
    SQLiteDatabase db;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;

    public boolean errorFlag = true;

    public ImageView cameraImage;
    String IMAGE_DIRECTORY = "/demonuts";
    int GALLERY=1, CAMERA=2;

    String themeString;
    FloatingActionButton fab;
    View headerView;
    LinearLayout header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        themeString = preferences.getString("Theme","");
        changeThemeStart();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationItemClicks();


        db = openOrCreateDatabase("todolist",Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS todolist2("+"Id INTEGER PRIMARY KEY AUTOINCREMENT,Details VARHCAR,Done INTEGER);");

        listItems = new LinkedList<>();
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ItemArrayAdapter(this,R.layout.list_item,listItems);
        listView.setAdapter(adapter);
        changeListBackground();
        showItems();

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                listView.getItemAtPosition(position);
//                Object object = listView.getItemAtPosition(position);
//
//
//                msg(" " +String.valueOf(position));            }
//
//        });

        registerForContextMenu(listView);

        headerView = navigationView.getHeaderView(0);
        header = (LinearLayout) headerView.findViewById(R.id.nav_header_layout);




        cameraImage = (CircleImageView) headerView.findViewById(R.id.profileImage);
        showPicture();
        cameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });






        fab = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButtonClick();

        headerColorChange();

    } // End of OnCreate



    public void changeListBackground(){

        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String imageName =  sharedPreferences.getString("Image","");

        if(imageName.equalsIgnoreCase("image1")) {
            listView.setBackground(getResources().getDrawable(R.drawable.image1));
        }else if(imageName.equalsIgnoreCase("image2")){
            listView.setBackground(getResources().getDrawable(R.drawable.image2));
        }else if(imageName.equalsIgnoreCase("image3")){
            listView.setBackground(getResources().getDrawable(R.drawable.image3));
        }
    }

    public void changeThemeStart(){

        if(themeString.equalsIgnoreCase("Green Theme"))
            setTheme(R.style.green);
        else if(themeString.equalsIgnoreCase("Yellow Theme"))
            setTheme(R.style.yellow);
        else if(themeString.equalsIgnoreCase("Dark brown Theme"))
            setTheme(R.style.darkbrown);
        else
            setTheme(R.style.AppTheme);
    }

    public void headerColorChange(){

        if(themeString.equalsIgnoreCase("Green Theme")) {
            navigationView.getMenu().findItem(R.id.theme).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.changeBackground).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.help).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.jokeitem).setCheckable(false).setChecked(true);
            header.setBackground(getResources().getDrawable(R.color.colorPrimary2));

        } else if(themeString.equalsIgnoreCase("Yellow Theme")){
            navigationView.getMenu().findItem(R.id.theme).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.changeBackground).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.help).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.jokeitem).setCheckable(false).setChecked(true);
            header.setBackground(getResources().getDrawable(R.color.goldenYellow));

        }
        else if(themeString.equalsIgnoreCase("Dark brown Theme")){
            navigationView.getMenu().findItem(R.id.theme).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.changeBackground).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.help).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.jokeitem).setCheckable(false).setChecked(true);
            header.setBackground(getResources().getDrawable(R.color.goldenBrown));

        } else{
            navigationView.getMenu().findItem(R.id.theme).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.changeBackground).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.help).setCheckable(false).setChecked(true);
            navigationView.getMenu().findItem(R.id.jokeitem).setCheckable(false).setChecked(true);
            header.setBackground(getResources().getDrawable(R.color.colorRoyalBlue));


        }

    }


    public void floatingActionButtonClick(){
        if(themeString.equalsIgnoreCase("Green Theme"))
            fab.setImageResource(R.drawable.green_big);
        else if(themeString.equalsIgnoreCase("Yellow Theme"))
            fab.setImageResource(R.drawable.yellow_big);
        else if(themeString.equalsIgnoreCase("Dark brown Theme"))
            fab.setImageResource(R.drawable.goldenbrown_big);
        else
            fab.setImageResource(R.drawable.blue_big);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Please write details..");
                final View view1 =  getLayoutInflater().inflate(R.layout.add_popup,null);
                alertDialog.setView(view1);
                alertDialog.setPositiveButton("Done!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuffer buffer =  new StringBuffer();
                        EditText editText = (EditText)view1.findViewById(R.id.addEditText);
                        buffer.append(editText.getText().toString());
                        if(TextUtils.isEmpty(buffer)){
                            msg("Sorry, the writing pad is empty!");
                            errorFlag = true;
                        } else {

                            db.execSQL("INSERT INTO todolist2(Details,Done)VALUES('" + buffer + "',0);");
                            msg("Record saved successfully.");
                            listItems.clear();
                            adapter.notifyDataSetChanged();
                            showItems();

                            errorFlag = false;
                            dialogInterface.dismiss();
                        }
                    }
                });
                alertDialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface, int i){
                        errorFlag = false;
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setCancelable(false);
                final AlertDialog alert =  alertDialog.create();

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(40,0,0,0);
                alert.show();
                ((Button)alert.findViewById(android.R.id.button1)).setBackground(getResources().getDrawable(R.drawable.round_border));
                ((Button)alert.findViewById(android.R.id.button1)).setTextColor(getResources().getColor(android.R.color.white));
                ((Button)alert.findViewById(android.R.id.button1)).setLayoutParams(params);
                ((Button)alert.findViewById(android.R.id.button2)).setBackground(getResources().getDrawable(R.drawable.round_border));
                ((Button)alert.findViewById(android.R.id.button2)).setTextColor(getResources().getColor(android.R.color.white));

                alert.setOnDismissListener(new DialogInterface.OnDismissListener(){
                    @Override
                    public void onDismiss(DialogInterface dialogInterface){
                        // if the error flag is true dialog will show again
                        if(errorFlag){
                            alert.show();
                        }else{
                            return;
                        }
                    }
                });

            }
        });   // Floating action button Ends here

    }


    public void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select Photo from Gallery",
                "Capture photo from camera"
        };
        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        choosePhotoFromGallery();
                        break;
                    case 1:
                        takePhotoFromCamera();
                        break;
                }
            }
        });

        pictureDialog.show();
    }

    public void choosePhotoFromGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    public void takePhotoFromCamera(){
        Intent intent =  new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }


    public void showPicture(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String profileBitmapString = sharedPreferences.getString("profileImage"," ");
        Bitmap profilePicBitmap = stringToBitmap(profileBitmapString);
        cameraImage.setImageBitmap(profilePicBitmap);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode ==this.RESULT_CANCELED){
            return;
        }
        if(requestCode==GALLERY){
            if(data!=null){
                Uri contentURI = data.getData();
                try{
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),contentURI);
                   // String path = saveImage(bitmap);
                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    cameraImage.setImageBitmap(bitmap);
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("profileImage",bitmapToString(bitmap));
                    editor.apply();
                    editor.commit();

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        } else if(requestCode==CAMERA){
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            cameraImage.setImageBitmap(thumbnail);
            //saveImage(thumbnail);
            SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("profileImage",bitmapToString(thumbnail));
            editor.apply();
            editor.commit();

           // Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();

        }
    }


    public String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String stringImage = Base64.encodeToString(bytes, Base64.DEFAULT);
        return stringImage;
    }

    public Bitmap stringToBitmap(String stringImage){

        try {
            byte[] encodeByte = Base64.decode(stringImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch (Exception e){
            e.getMessage();
            return null;
        }
    }


    public String saveImage(Bitmap bitmap){
        ByteArrayOutputStream bytes =  new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,90,bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory()+IMAGE_DIRECTORY
        );
        // have the object build the directory structure if needed
        if(!wallpaperDirectory.exists()){
            wallpaperDirectory.mkdirs();
        }
        try{
            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis()+".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[] {f.getPath()},
                    new String[]{"image/jpeg"},null
                    );
            fo.close();
            Log.d("check","filed saved"+f.getAbsolutePath());
            return f.getAbsolutePath();
        }catch (IOException io){
            io.printStackTrace();
        }
        return  "";
    }


    // This function handle the navigation drawer clicks
    public void navigationItemClicks() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.theme:
                        msg("Theme button clicked");
//                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Theme","green").apply();
                        Intent intent = new Intent(MainActivity.this,ThemeActivity.class);
                        startActivity(intent);
                        return true;

                        //finish();
                    case R.id.changeBackground:
                        msg("Changing background");
                        startActivity(new Intent(MainActivity.this,ChangeBackgroundActivity.class));
                        return true;
                    case R.id.jokeitem:
                        msg("Ready for some jokes");
                        startActivity(new Intent(MainActivity.this, JokeActivity.class));
                        return true;


                    default:
                        return true;
                }

            }
        });
    }

    // This is something related to navigation menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


    // This functions just display the context menu items
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);
        if(view.getId()==R.id.listView){
            menu.setHeaderTitle("Options");
            menu.add(0,0,0,"Delete");
            menu.add(0,1,0,"Edit");
            menu.add(0,2,0,"Done");

        }
    }



    // This function is for context menu onitem selected actions
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==0){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            int position = info.position;
            Cursor cursor  = db.rawQuery("SELECT * FROM todolist2",null);
            if(cursor.getCount()==0)
                return true;
            if(cursor.moveToPosition(position)){
                String rowId = cursor.getString(0);
                db.execSQL("DELETE FROM todolist2 WHERE Id = '"+rowId+"'");
                msg("Record deleted successfully.");
            }
            showItems();
            return true;
        } else if(item.getItemId()==1){ //Edit Option goes here
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            int position = info.position;
            Cursor cursor  = db.rawQuery("SELECT * FROM todolist2",null);
            if(cursor.getCount()==0)
                return true;
            if(cursor.moveToPosition(position)){
                final String rowId = cursor.getString(0);
                String details = cursor.getString(1);

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Please write details..");
                final View view2 =  getLayoutInflater().inflate(R.layout.add_popup,null);
                alertDialog.setView(view2);
                final EditText editText = (EditText) view2.findViewById(R.id.addEditText);
                editText.setText(details);
                alertDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuffer buffer =  new StringBuffer();
                        buffer.append(editText.getText().toString());
                        if(TextUtils.isEmpty(buffer)){
                             db.execSQL("DELETE FROM todolist2 WHERE Id='"+rowId+"'");
                                msg("Nothing left to save, record deleted.");
                        } else {
                            Cursor c = db.rawQuery("SELECT * FROM todolist2 WHERE Id='" + rowId + "'", null);
                            if (c.moveToFirst()) {
                                db.execSQL("UPDATE todolist2 SET Details = '" + editText.getText() + "' WHERE Id='" + rowId + "'");
                                msg("Record modified successfully");
                            }
                            c.close();
                        }
                        listItems.clear();
                        adapter.notifyDataSetChanged();
                        showItems();
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                   public void onClick(DialogInterface dialogInterface,int i){
                       dialogInterface.dismiss();
                   }
                });
                AlertDialog alert =  alertDialog.create();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(40,0,0,0);
                alert.show();
                ((Button)alert.findViewById(android.R.id.button1)).setBackground(getResources().getDrawable(R.drawable.round_border));
                ((Button)alert.findViewById(android.R.id.button1)).setTextColor(getResources().getColor(android.R.color.white));
                ((Button)alert.findViewById(android.R.id.button1)).setLayoutParams(params);
                ((Button)alert.findViewById(android.R.id.button2)).setBackground(getResources().getDrawable(R.drawable.round_border));
                ((Button)alert.findViewById(android.R.id.button2)).setTextColor(getResources().getColor(android.R.color.white));
            }
        } else if(item.getItemId()==2){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            int position = info.position;
            Cursor cursor  = db.rawQuery("SELECT * FROM todolist2",null);
            if(cursor.getCount()==0)
                return true;
            if(cursor.moveToPosition(position)) {
                final String rowIdd = cursor.getString(0);
                String doneText = cursor.getString(2);

                Cursor c = db.rawQuery("SELECT * FROM todolist2 WHERE Id='" + rowIdd + "'", null);
                if (c.moveToFirst()) {
                    db.execSQL("UPDATE todolist2 SET Done = '"+1+"' WHERE Id='" + rowIdd + "'");
                    msg("Congratulations! on successfully completing task.");
                    showItems();
                }


            }

        }
        return true;
    } // ContextMenu OnClickListener Actions end here


    // This function just list the items everytime loaded
    public void showItems(){
        listItems.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM todolist2",null);
        if(cursor.getCount()==0){
            adapter.notifyDataSetChanged();
            return;}
        while(cursor.moveToNext()){
            String detailsWithFlag = cursor.getString(1)+cursor.getString(2);
            listItems.add(detailsWithFlag);

        }
        // experiment area
//        String string1 = "Hello Everyone";
//        SpannableString string2 = new SpannableString(string1);
//        string2.setSpan(new RelativeSizeSpan(2f),0,5,0); // size has been set
//        string2.setSpan(new ForegroundColorSpan(Color.RED),0,5,0); // color
//        listItems.add(string2.toString());
        //experiment area ended


        adapter.notifyDataSetChanged();
    }



    // This function is just for a toast message
    public void msg(String string) {
        Toast toast = Toast.makeText(this, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

}  // End of Class here..
