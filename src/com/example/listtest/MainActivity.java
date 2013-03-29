package com.example.listtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final int HDR_POS1 = 0;
    public static final int HDR_POS2 = 5;
    public static final String[] LIST = { "Characters", "The Monkey priest",
        "Goat", "The beast of craggy island", "Chris", "Episodes",
        "Are you right there Fr. Ted", "Speed 3", "Hell", "A Christmassy Ted" };
    public static final String[] SUBTEXTS = { null, "He was fond of pulling books off shelves",
        "Made famous by the tunnel of goats", "Claws as big as cups and four arses", "Stressed out sheep", null,
        "Not a racist", "Is there anything to be said for another mass", "Off to get some heroin", "Toddy tod tod. There you are now Todd." };

    private static final Integer LIST_HEADER = 0;
    private static final Integer LIST_ITEM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView)findViewById(R.id.listView1);
        lv.setAdapter(new MyListAdapter(this));
    }

    private class MyListAdapter extends BaseAdapter {
        public MyListAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return LIST.length;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
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

            String headerText = getHeader(position);
            if(headerText != null) {

                View item = convertView;
                if(convertView == null || convertView.getTag() == LIST_ITEM) {

                    item = LayoutInflater.from(mContext).inflate(
                            R.layout.lv_header_layout, parent, false);
                    item.setTag(LIST_HEADER);

                }

                TextView headerTextView = (TextView)item.findViewById(R.id.lv_list_hdr);
                headerTextView.setText(headerText);
                return item;
            }

            View item = convertView;
            if(convertView == null || convertView.getTag() == LIST_HEADER) {
                item = LayoutInflater.from(mContext).inflate(
                        R.layout.lv_layout, parent, false);
                item.setTag(LIST_ITEM);
            }

            TextView header = (TextView)item.findViewById(R.id.lv_item_header);
            header.setText(LIST[position % LIST.length]);

            TextView subtext = (TextView)item.findViewById(R.id.lv_item_subtext);
            subtext.setText(SUBTEXTS[position % SUBTEXTS.length]);

            //Set last divider in a sublist invisible
            View divider = item.findViewById(R.id.item_separator);
            if(position == HDR_POS2 -1) {
                divider.setVisibility(View.INVISIBLE);
            }


            return item;
        }

        private String getHeader(int position) {

            if(position == HDR_POS1  || position == HDR_POS2) {
                return LIST[position];
            }

            return null;
        }

        private final Context mContext;
    }

}
