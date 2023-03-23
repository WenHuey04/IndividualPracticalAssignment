package my.edu.utar.individualassignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends ArrayAdapter<Color> {
    private Context context;
    private ArrayList<Color> arrColor;
    public ColorAdapter(@NonNull Context context, int resource, @NonNull List<Color> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrColor = new ArrayList<>(objects);
    }

    public void upDate(ArrayList<Color> arr){
        this.arrColor.clear();
        this.arrColor.addAll(arr);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrColor.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_color,null);
        }
        if (arrColor.size()>0){
            Color color = arrColor.get(position);
            ImgCustom imgCustom = convertView.findViewById(R.id.imgObj1);
            imgCustom.setColorFilter(android.graphics.Color.parseColor(color.color));
        }
        return convertView;
    }
}
