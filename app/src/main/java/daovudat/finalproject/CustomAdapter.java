package daovudat.finalproject;

/**
 * Created by Dao Vu Dat on 8/30/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import daovudat.finalproject.fragment.ItemObject;

public class CustomAdapter extends BaseAdapter {

    private LayoutInflater layoutinflater;
    private List<ItemObject> listStorage;
    private Context context;

    public CustomAdapter(Context context, List<ItemObject> customizedListView) {
        this.context = context;
        layoutinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.custom_adapter, parent, false);
            listViewHolder.screenShot = (ImageView)convertView.findViewById(R.id.screen_shot);
            listViewHolder.musicName = (TextView)convertView.findViewById(R.id.ExeName);
            listViewHolder.musicAuthor = (TextView)convertView.findViewById(R.id.Description);

            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.screenShot.setImageResource(listStorage.get(position).getScreenShot());
        listViewHolder.musicName.setText(listStorage.get(position).getMusicName());
        listViewHolder.musicAuthor.setText(listStorage.get(position).getMusicAuthor());

        return convertView;
    }

    static class ViewHolder{
        ImageView screenShot;
        TextView musicName;
        TextView musicAuthor;
    }
}