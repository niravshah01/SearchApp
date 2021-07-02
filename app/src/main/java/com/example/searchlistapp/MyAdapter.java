package com.example.searchlistapp;

import android.content.Context;
import android.renderscript.Sampler;
import android.renderscript.Sampler.Value;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private List<Bean> beanList;
    private LayoutInflater inflater;
    List<Bean> mValue;
    ValueFilter value;

    public MyAdapter(Context context,List<Bean> beanList){
        this.context = context;
        this.beanList = beanList;
        mValue = beanList;
    }
    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.raw_item,null);
        }
        TextView txtDecimal = convertView.findViewById(R.id.decimal);
        TextView txtbinary = convertView.findViewById(R.id.binary);
        TextView txthexa = convertView.findViewById(R.id.hexa);

        Bean bean = beanList.get(position);

        String decimal = bean.getDecimal();
        String binary = bean.getBinary();
        String hexa = bean.getHexa();

        txtDecimal.setText(decimal);
        txtbinary.setText(binary);
        txthexa.setText(hexa);

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(value == null){
            value = new ValueFilter();
        }
        return value;
    }

    private class ValueFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                ArrayList<Bean> filterlist = new ArrayList<Bean>();
                for(int i=0;i<mValue.size();i++){
                    if((mValue.get(i).getDecimal().toUpperCase()).contains(constraint.toString().toUpperCase())){
                        Bean bean = new Bean(mValue.get(i).getDecimal(),mValue.get(i).getBinary(),mValue.get(i).getHexa());
                        filterlist.add(bean);
                    }
                }
                results.count = filterlist.size();
                results.values = filterlist;
            }
            else{
                results.count = mValue.size();
                results.values = mValue;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            beanList = (ArrayList<Bean>) results.values;
            notifyDataSetChanged();
        }
    }
}
