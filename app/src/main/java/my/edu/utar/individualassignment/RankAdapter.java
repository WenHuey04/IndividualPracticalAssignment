package my.edu.utar.individualassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import my.edu.utar.individualassignment.R;
import my.edu.utar.individualassignment.Rank;

public class RankAdapter extends BaseAdapter {

    private LinkedList<Rank> rankData;
    private Context rankContext;

    public RankAdapter(LinkedList<Rank> rankData, Context rankContext) {
        this.rankData = rankData;
        this.rankContext = rankContext;
    }

    @Override
    public int getCount() {
        return rankData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(rankContext).inflate(R.layout.ranking_data,null);
        TextView userID = convertView.findViewById(R.id.userID);
        TextView userName = convertView.findViewById(R.id.userName);
        TextView userLevel = convertView.findViewById(R.id.userLevel);
        userID.setText(rankData.get(position).getId());
        userName.setText(rankData.get(position).getName());
        userLevel.setText(rankData.get(position).getLevel());
        return convertView;
    }
}
