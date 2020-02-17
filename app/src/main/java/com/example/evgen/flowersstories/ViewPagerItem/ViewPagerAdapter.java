package com.example.evgen.flowersstories.ViewPagerItem;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.evgen.flowersstories.R;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private final String TAG = "Logg";
    private Context context;

    Activity activity;
    LayoutInflater inflater;
    ArrayList<ProductModel> flowerList;



    public ViewPagerAdapter(Activity activity, ArrayList<ProductModel> flowerList) {
        this.activity = activity;
        this.flowerList = flowerList;
    }


    @Override
    public int getCount() {
    //    if (flowerList == null)
     //       return 0;
        return flowerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.item_viewpager, container, false);

        TextView textNameView = itemView.findViewById(R.id.nameView);
        textNameView.setText(flowerList.get(position).getName());

        TextView textViewDescription = itemView.findViewById(R.id.DescriptionView);
        textViewDescription.setText(flowerList.get(position).getDescription());

        TextView textViewInStock = itemView.findViewById(R.id.InStockView);
        textViewInStock.setText("В наличии " + flowerList.get(position).getIn_Stock());

        TextView priceView = itemView.findViewById(R.id.priceView);
        priceView.setText(flowerList.get(position).getPriceID().toString() + " руб.");

        TextView invNumberFlowers = itemView.findViewById(R.id.getInvNumberFlowers);
        invNumberFlowers.setText(flowerList.get(position).getInvNumberFlowers());

        ImageView image = itemView.findViewById(R.id.imageView);

        try {
            Glide.with(activity.getApplicationContext())
                    .load(flowerList.get(position).getImages())
                    .thumbnail(0.5f)
            //        .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_launcher)
                 //   .override(800, 850)
                    .error(R.mipmap.ic_launcher)
                  //  .centerCrop()
                  //  .circleCrop()
                    .into(image);

        } catch (Exception ex) {
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
