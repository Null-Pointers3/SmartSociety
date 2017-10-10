package com.example.ribhav.smartsociety;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.ribhav.smartsociety.Adapter.MenuAdapter;
import com.example.ribhav.smartsociety.ResourceClasses.MenuItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        ArrayList<MenuItem> menuItems=getArrayList();
        MenuAdapter  menuadapter=new MenuAdapter(menuItems);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(menuadapter);
        mAuth = FirebaseAuth.getInstance();

    }

    private ArrayList<MenuItem> getArrayList() {
        ArrayList<MenuItem> menuitems=new ArrayList<>();
        menuitems.add(new MenuItem(R.drawable.utilities));
        menuitems.add(new MenuItem(R.drawable.utilities));
        menuitems.add(new MenuItem(R.drawable.utilities));
        return menuitems;
    }
    public void SignOut() {
        mAuth.signOut();
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int itemid = item.getItemId();
        switch (itemid){
            case R.id.help: SignOut();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
