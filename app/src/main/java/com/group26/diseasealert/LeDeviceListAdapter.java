package com.group26.diseasealert;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Displays basic information.
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class LeDeviceListAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    public LeDeviceListAdapter(Context context) {
        Log.e("shreyans", "ledevice");
        this.inflater = LayoutInflater.from(context);
    }

    public void update()
    {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 5;
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
    public View getView(int position, View view, ViewGroup parent) {
        Log.e("shreyans", "Entered getview");
        view = inflateIfRequired(view, position, parent);
        bind(position, view, null, null, null);
        return view;
    }

    public void bind(int position, View view, String title, String summary, String number) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if(title == null) {
            if(position == 0) {
                holder.nameTextView.setText("Dr. John Pham");
                holder.phoneTextView.setText("(217) 123-4567");
                holder.emailTextView.setText("jp@example.com");
                holder.doctorImageView.setImageResource(R.drawable.doc1);
            }
            else if(position == 1){
                holder.nameTextView.setText("Dr. Shreyans Choudhury");
                holder.phoneTextView.setText("(217) 234-5678");
                holder.emailTextView.setText("sc@example.com");
                holder.doctorImageView.setImageResource(R.drawable.doc2);
            }
            else if(position == 2){
                holder.nameTextView.setText("Dr. Yan Han");
                holder.phoneTextView.setText("(217) 345-6789");
                holder.emailTextView.setText("yh@example.com");
                holder.doctorImageView.setImageResource(R.drawable.doc3);
            }
            else if(position == 3){
                holder.nameTextView.setText("Dr. Sunaya Shivakumar");
                holder.phoneTextView.setText("(217) 456-7890");
                holder.emailTextView.setText("ss@example.com");
                holder.doctorImageView.setImageResource(R.drawable.doc4);
            }
            else if(position == 4){
                holder.nameTextView.setText("Dr. Ambika Dubey");
                holder.phoneTextView.setText("(217) 567-8901");
                holder.emailTextView.setText("ad@example.com");
                holder.doctorImageView.setImageResource(R.drawable.doc5);
            }
            else{
                holder.nameTextView.setText("Dr. Megan Smith");
                holder.phoneTextView.setText("217-419-8343");
                holder.emailTextView.setText("jsmith@gmail.com");
            }
        }
        else{
            holder.nameTextView.setText(title);
            holder.phoneTextView.setText(summary);
            holder.emailTextView.setText(number);
        }
        //TODO change image here
        //holder.doctorImageView.setImageResource();
    }

    private View inflateIfRequired(View view, int position, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.device_item, null);
            view.setTag(new ViewHolder(view));
        }
        return view;
    }

    static class ViewHolder {
        final TextView nameTextView;
        final TextView phoneTextView;
        final TextView emailTextView;
        final ImageView doctorImageView;

        ViewHolder(View view) {
            nameTextView = (TextView) view.findViewWithTag("name");
            phoneTextView = (TextView) view.findViewWithTag("phone");
            emailTextView = (TextView) view.findViewWithTag("email");
            doctorImageView = (ImageView) view.findViewWithTag("doctorImage");
        }
    }
}