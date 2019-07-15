package com.example.sports;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sports.Adapter.CourseAdapter;
import com.example.sports.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    String URL_DATA="https://www.simplifiedcoding.net/wp-json/wp/v2/posts";
    RequestQueue requestQueue;
    List<User>user;
    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        frameLayout=findViewById(R.id.framelayout);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));

        user=new ArrayList<>();
        loadurl();
    }
    public void loadurl(){
        JsonArrayRequest stringRequest=new JsonArrayRequest(URL_DATA, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                getvalue(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void getvalue(JSONArray array){
        for(int i=0;i<array.length();i++){
            com.example.sports.Model.User userlist = new User();
            JSONObject jsonObject=null;
            try{
                jsonObject=array.getJSONObject(i);
                JSONObject titleobj= jsonObject.getJSONObject("title");
                String render=titleobj.getString("rendered");
                userlist.setTitle(render);
                JSONObject descobj=jsonObject.getJSONObject("content");
                String render1=descobj.getString("rendered");
                userlist.setDescription(render1);
            }catch(JSONException e){
                e.printStackTrace();
            }
            user.add(userlist);
            adapter=new CourseAdapter(user,this);
            recyclerView.setAdapter(adapter);

        }

    }
}
