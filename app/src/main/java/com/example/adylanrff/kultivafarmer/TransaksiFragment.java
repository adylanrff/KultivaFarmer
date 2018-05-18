package com.example.adylanrff.kultivafarmer;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

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
import java.util.Map;
import java.util.function.Consumer;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransaksiFragment extends Fragment {

    private List<Transaction> transactions;
    private TransactionAdapter transactionAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    public TransaksiFragment() {
        // Required empty public constructor
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        transactions = new ArrayList<>();
        loadTransaction();
        View view = inflater.inflate(R.layout.fragment_transaksi, container, false);
        recyclerView = view.findViewById(R.id.transaction_rv);

        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void initializeAdapter(){
        transactionAdapter = new TransactionAdapter(Glide.with(this),transactions);
        recyclerView.setAdapter(transactionAdapter);
    }

    private void loadTransaction(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://168.235.103.57:5000/order/get_orderline_petani";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("order_lines");
                    for (int i = 0 ; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        double volume = jsonObject.getDouble("qty");
                        int status = jsonObject.getInt("status");

                        JSONObject customerJson = jsonObject.getJSONObject("customer");
                        int customer_id = customerJson.getInt("id");
                        String first_name = customerJson.getString("first_name");
                        String last_name = customerJson.getString("last_name");
                        String address = customerJson.getString("address");

                        Customer customer = new Customer(customer_id, first_name, last_name, address);

                        Transaction transaction = new Transaction(id,customer, volume,status);
                        transactions.add(transaction);

                        Log.d("a",""+transactions.get(0).getId());
                    }
                    initializeAdapter();

                } catch (JSONException e){
                    Log.e("error", "JSON ERROR");
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
