package daovudat.finalproject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Nutrition extends AppCompatActivity {
    private String rssUrl = "http://www.self.com/feed/nutrition/";
    private ListView listView;
    private ArrayList listNews;
    private NewsAdapter adapter;
    private ProgressDialog progressDialog;
    private TextView nutrition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);


        listView = (ListView)findViewById(R.id.list_view);
        listNews = new ArrayList();
        adapter = new NewsAdapter(this);
        listView.setAdapter(adapter);
        nutrition = (TextView)findViewById(R.id.Nutrition);
        nutrition.setTypeface(EasyFonts.captureIt(this));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news = adapter.getItem(i);
                Intent intent = new Intent(getApplicationContext(), Detail.class);
                intent.putExtra("link", news.getLink());
                startActivity(intent);
                Log.d("link", news.getLink());
            }
        });

        showLoadingDialog("Loading data");
        new XMLReader().execute(rssUrl);
    }


    private class XMLReader extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            listNews = new ArrayList();

            XMLDOMParser parser = new XMLDOMParser();
            Document doc = parser.getDocument(s);
            NodeList nodeList = doc.getElementsByTagName("item");
            for (int i = 0; i < nodeList.getLength(); i++) {
                News news = new News();
                Element e = (Element)nodeList.item(i);

                NodeList title = e.getElementsByTagName("title");
                Element titleElement = (Element)title.item(0);
                news.setTitle(titleElement.getFirstChild().getNodeValue());

                NodeList link = e.getElementsByTagName("link");
                Element linkElement = (Element)link.item(0);
                news.setLink(linkElement.getFirstChild().getNodeValue());

                listNews.add(news);
            }
            adapter.addAll(listNews);
            progressDialog.dismiss();
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            result = getXmlFromUrl(strings[0]);
            return result;
        }
    }

    private String getXmlFromUrl(String string) {
        String result = "";

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(string);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private void showLoadingDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

}

