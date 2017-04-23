package daovudat.finalproject;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Dao Vu Dat on 8/29/2016.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    Context context;

    public NewsAdapter (Context context)
    {
        super(context,0);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = convertView;
        if (rootView == null) {
            rootView = LayoutInflater.from(context).inflate(R.layout.row_news, null);
        }

        News news = getItem(position);
        if (news != null) {
            ((TextView)rootView.findViewById(R.id.text_view_title)).setText(news.getTitle());
            ((TextView)rootView.findViewById(R.id.text_view_title)).setTextColor(Color.BLACK);
        }

        return rootView;
    }
}