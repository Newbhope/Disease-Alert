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
public class NewsDeviceListAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    public NewsDeviceListAdapter(Context context) {
        //Log.e("shreyans", "ledevice");
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
                holder.nameTextView.setText("Mumps Outbreak in Champaign, Illinois");
                holder.phoneTextView.setText("23 Affected");
                //holder.emailTextView.setText("uh");
                holder.doctorImageView.setImageResource(R.drawable.uiuc);
            }
            else if(position == 1){
                holder.nameTextView.setText("Measles Outbreak in Chicago, Illinois");
                holder.phoneTextView.setText("15 Affected");
                //holder.emailTextView.setText("test");
                holder.doctorImageView.setImageResource(R.drawable.measles);
            }
            else if(position == 2){
                holder.nameTextView.setText("Mumps Outbreak in Cupertino, California");
                holder.phoneTextView.setText("2 Affected");
                //holder.emailTextView.setText("Email: jsmith@gmail.com");
                holder.doctorImageView.setImageResource(R.drawable.cupertino);
            }
            else if(position == 3){
                holder.nameTextView.setText("Measles Outbreak in Casper, Wyoming");
                holder.phoneTextView.setText("5 Affected");
                holder.doctorImageView.setImageResource(R.drawable.wyoming);
            }
            else if(position == 4){
                holder.nameTextView.setText("Mumps Outbreak in Montpelier, Vermont");
                holder.phoneTextView.setText("3 Affected");
                holder.doctorImageView.setImageResource(R.drawable.vermont);
            }
            else if(position == 5){

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