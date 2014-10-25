package ga.finappsparty.com.gather.Activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ga.finappsparty.com.gather.Object.Botes;
import ga.finappsparty.com.gather.R;

/**
 * Created by ulidev on 25/10/14.
 */
public class Fragment_Botes extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_list, container, false);
        return rootView;

       /* ArrayList<Botes> arrayEsquema = new ArrayList<Botes>();
        ArrayAdapter<SimpleListAdapter> arrayAdapter;
        arrayAdapter = new ArrayAdapter<SimpleListAdapter>(getActivity(), R.layout.list_item, arrayEsquema);*/
    }

    public class SimpleListAdapter extends ArrayAdapter<Botes> {

        private ArrayList<Botes> mList;
        private LayoutInflater inflater;
        private Context context;

        public SimpleListAdapter(Context context, int layoutResources, ArrayList<Botes> arrayList) {
            super(context, layoutResources, arrayList);
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Botes getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            return convertView;
        }
    }
}
