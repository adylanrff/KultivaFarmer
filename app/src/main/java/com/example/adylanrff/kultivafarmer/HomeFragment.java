package com.example.adylanrff.kultivafarmer;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private TextView title;
    private LinearLayoutManager llm;
    private RecyclerView recyclerView;
    private CapabilityAdapter capabilityAdapter;
    private List<Capability> capabilities;
    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        capabilities = new ArrayList<>();
        loadCapabilities();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.crops_rv);
        title = view.findViewById(R.id.title_tv);
        setFonts();
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        return view;

    }

    private void setFonts(){
        AssetManager am = getActivity().getAssets();
        Typeface titleFont = Typeface
                .createFromAsset(am, String.format(Locale.US, "fonts/%s", "TitilliumWeb-Bold.ttf"));
        title.setTypeface(titleFont);
    }

    private void initializeAdapter(){
        capabilityAdapter = new CapabilityAdapter(Glide.with(this),capabilities);
        recyclerView.setAdapter(capabilityAdapter);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

private String getSession(){
        String ret = "";
        try {
            InputStream inputStream = getContext().openFileInput("session");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while( (receiveString = bufferedReader.readLine())!=null){
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("error","file not found");
        } catch (IOException e){
            Log.e("error","IO EXC");

        }

        return ret;
}



    private void loadCapabilities() {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://168.235.103.57:5000/capability/all";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("capabilities");
                    for (int i = 0 ; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        double volume = jsonObject.getDouble("volume");
                        Date startDate = new Date(jsonObject.getString("start_date"));
                        Date endDate = new Date(jsonObject.getString("end_date"));
                        int productId = jsonObject.getInt("product_id");
                        String productName = jsonObject.getString("product_name");
                        String productImage = jsonObject.getString("product_image");
                        String productPrice = jsonObject.getString("product_price");

                        Capability capability = new Capability(id,volume,startDate,endDate,productId,productName,productImage,productPrice);
                        capabilities.add(capability);
                    }
                    initializeAdapter();

                } catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                HashMap<String, String> header = new HashMap<>();
                header.put("Auth", getSession());

                return header;
            }
        };

        queue.add(request);
    }


}
