package com.example.elika.myapplication999;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int userChioce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> menuItemList = new ArrayList<String>();
        menuItemList.add("Login");
        menuItemList.add("Register");
        menuItemList.add("Library Location");
        menuItemList.add("About");
        menuItemList.add("Add Book");
        menuItemList.add("Return Book");
        menuItemList.add("Loan Book");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item_library_menu,
                R.id.text_view_library_menu_item,
                menuItemList );

        ListView listView = (ListView)this.findViewById(
                R.id.list_view_library_menu_items);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                userChioce = i;
                Intent menuIntent = null;
                if(i == 0)
                {
                    menuIntent = new Intent(MainActivity.this,
                            LoginActivity.class);
                    startActivity(menuIntent);
                }
                else if(i == 1)
                {
                    menuIntent = new Intent(MainActivity.this,
                            RegistrationActivity.class);
                    startActivity(menuIntent);
                }
                else if(i == 2)
                {
                    menuIntent = new Intent(MainActivity.this,
                            LibraryLocationActivity.class);
                    startActivity(menuIntent);
                }
                else if(i == 3)
                {
                    menuIntent = new Intent(MainActivity.this,
                            AboutActivity.class);
                    startActivity(menuIntent);
                }
                else if(i == 4)
                {
                    menuIntent = new Intent(MainActivity.this,
                            AddBookActivity.class);
                    startActivity(menuIntent);
                }
                else if(i == 5)
                {
                    menuIntent = new Intent(MainActivity.this,
                            ReturnBookActivity.class);
                    startActivity(menuIntent);
                }
                else if(i == 6)
                {
                    menuIntent = new Intent(MainActivity.this,
                            LoanBookActivity.class);
                    startActivity(menuIntent);
                }
            }
        });

    }
}
