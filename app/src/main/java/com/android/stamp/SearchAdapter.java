package com.android.stamp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.Date;
import java.util.List;

public class SearchAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;
    private String userId;
    public SearchAdapter(String userId, List<String> list, Context context){
        this.list = list;
        this.userId = userId;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.listview,null);

            viewHolder = new ViewHolder();
            viewHolder.label = (TextView) convertView.findViewById(R.id.label);
            viewHolder.f_add = (Button) convertView.findViewById(R.id.f_add);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Button add =  (Button) convertView.findViewById(R.id.f_add);

        add.setTag(position);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = Integer.parseInt(view.getTag().toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("친구추가").setMessage(list.get(position) + "님을 친구추가 하시겠습니까?").setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String id = list.get(position);

                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {

                                    }
                                };
                                long now = System.currentTimeMillis();
                                Date date = new Date(now);
                                SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                String getTime = mFormat.format(date);

                                FriendInsert friendInsert = new FriendInsert(userId, id,getTime, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(context);
                                queue.add(friendInsert);

                            }
                        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });
        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.
        viewHolder.label.setText(list.get(position));

        return convertView;
    }

    class ViewHolder{
        public TextView label;
        public Button f_add;
    }
}
