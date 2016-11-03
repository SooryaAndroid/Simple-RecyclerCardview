package com.android.recyclerview1;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;

    RecyclerView recyclerView;

    private List<Contacts> contactsList = new ArrayList<>();

    private ContactsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);


        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading");
        pDialog.show();


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://api.androidhive.info/contacts/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pDialog.dismiss();


                        try{

                            JSONObject json = new JSONObject(response);
                            JSONArray contact = json.getJSONArray("contacts");

                            for (int i=0; i<contact.length(); i++){
                                JSONObject c = contact.getJSONObject(i);

                                      Contacts cont = new Contacts();


                                        String name = c.getString("name");
                                        String email = c.getString("email");

                                        JSONObject phone = c.getJSONObject("phone");
                                        String mobile = phone.getString("mobile");

                                        cont.setNames(name);
                                        cont.setEmails(email);
                                        cont.setPhnos(mobile);

                                   contactsList.add(cont);

                            }

                            mAdapter = new ContactsAdapter(contactsList);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            //recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            //  recycler.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(mAdapter);



                        }catch (Exception e ){
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "error" + error, Toast.LENGTH_SHORT).show();
            }


        });
        queue.add(stringRequest);
    }
}