package com.example.evgen.flowersstories.Cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.evgen.flowersstories.Cart.DB.DBHelper;
import com.example.evgen.flowersstories.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.NumberViewHolderCart>{

    List<Card> arrayListDescription;
    CartActivity cartActivity;

    Context context;

   public static Integer countLinerLayoutOFFandOn = 0;

    public static void countLinerLayoutOffandON(){
            countLinerLayoutOFFandOn = 0;
    }

    public CartAdapter(List<Card> arrayListDescription) {
        this.arrayListDescription = arrayListDescription;
    }

    @Override
    public NumberViewHolderCart onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);

        return new NumberViewHolderCart(v);
    }

    @Override
    public void onBindViewHolder(final NumberViewHolderCart holder, final int position) {

        final CartController cartController = new CartController();

        final Card card = arrayListDescription.get(position);

        DBHelper db = new DBHelper(context);
        final String quantityOfBD = db.getQuantityCount(card.getInvNumber());
        final int countOfBdParse = Integer.parseInt(quantityOfBD);

        final String startPrice = card.getCartPriceStart();
        final int price = Integer.parseInt(startPrice);
        final int sum = price * countOfBdParse;


         holder.cartItem.setText(card.getCartItem());
         holder.cartItem.setTextColor(Color.BLACK);

        if (card.isSelectedcheckBox()){
            holder.cartItem.setTextColor(ContextCompat.getColor(context, R.color.checkBoxYellow));
        }else {
            holder.cartItem.setTextColor(Color.BLACK);
        }

         holder.cartPrice.setText(String.valueOf(sum));
         holder.cartQuantity.setText(quantityOfBD);

         holder.cartCheckBox.setChecked(card.isSelectedcheckBox());
         holder.cartCheckBox.setTag(arrayListDescription.get(position));

        holder.cartDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                arrayListDescription.remove(position);
                notifyDataSetChanged();

                DBHelper db = new DBHelper(context);
                db.deleteCart(card.getInvNumber());

                cartController.buyDeleteOfBD(card.getInvNumber());

                if (card.isSelectedcheckBox()){
                    countLinerLayoutOFFandOn--;
                    cartActivity.onBuy(v);

                }else {
           //         System.out.println("Off ");
                }
            }
        });

         holder.cartCheckBox.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                CheckBox checkBox = (CheckBox)v;
                Card card1 = (Card)checkBox.getTag();

                card1.getCartItem();

                card1.setSelectedcheckBox(checkBox.isChecked());
                arrayListDescription.get(position).setSelectedcheckBox(checkBox.isChecked());

                if(checkBox.isChecked()){

                    CartModelBuy cartModelBuy = new CartModelBuy(card.getInvNumber(),card.getCartItem(),quantityOfBD, startPrice,true);

                    cartController.addItemPlusCheckBoxBuy(cartModelBuy,context);
                    countLinerLayoutOFFandOn++;

                    holder.cartItem.setTextColor(ContextCompat.getColor(context, R.color.checkBoxYellow));
                    cartActivity.onBuy(v);

                    Toast.makeText(context,arrayListDescription.get(position).getCartPrice() + " clicked!" +position, Toast.LENGTH_SHORT).show();

                }else {
                    holder.cartItem.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                    countLinerLayoutOFFandOn--;
                    cartActivity.onBuy(v);

                    CartModelBuy cartModelBuy = new CartModelBuy(card.getInvNumber(),card.getCartItem(),card.getCartQuantity(),card.getCartPrice(),false);
                    cartController.removeItemMinusCheckBoxBuy(cartModelBuy);
                }
            }
        });

        holder.plusCartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(context);
                db.plusCart(card.getInvNumber());
                holder.cartQuantity.setText(db.getQuantityCount(card.getInvNumber()));


                String startPrice = card.getCartPriceStart();
                int count = Integer.parseInt(db.getQuantityCount(card.getInvNumber()));
                int price = Integer.parseInt(startPrice);
                int summ = price * count;

                holder.cartPrice.setText(String.valueOf(summ));

                cartController.buyItemQuantityPlus(card.getInvNumber());

            }
        });

        holder.minusCartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(context);
                db.minusCart(card.getInvNumber());
                holder.cartQuantity.setText(db.getQuantityCount(card.getInvNumber()));
                cartController.buyItemQuantityMinus(card.getInvNumber());

                if(db.getQuantityCount(card.getInvNumber()).equals("0")){

                    arrayListDescription.remove(position);
                    db.deleteCart(card.getInvNumber());
                    notifyDataSetChanged();

                    if(card.isSelectedcheckBox()){
                        countLinerLayoutOFFandOn--;
                        cartActivity.onBuy(v);
                    }

                }else {
                    String startPrice = card.getCartPriceStart();
                    int price = Integer.parseInt(startPrice);
                    int count = Integer.parseInt(db.getQuantityCount(card.getInvNumber()));
                    int summMinus = price*count;

                    holder.cartPrice.setText(String.valueOf(summMinus));
                }
            }
        });

        try {
            Glide.with(context)
                    .load(card.getCartImage())
                    // .circleCrop()
                    // .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.cartImage);

          }catch (Exception ex){
        }
    }

    @Override
    public int getItemCount() {
        return arrayListDescription.size();
    }

    public class NumberViewHolderCart extends RecyclerView.ViewHolder {

        private ImageView cartImage;
        private TextView cartItem;
        private TextView cartPrice;
        private TextView cartQuantity;
        private TextView cartDelete;
        private TextView setQuantity;
        private CardView cardView;

        private Button plusCartQuantity;
        private Button minusCartQuantity;

        private CheckBox cartCheckBox;

        NumberViewHolderCart(View itemView) {
            super(itemView);

         //   cartInvNumber = (TextView)itemView.findViewById(R.id.cart_invnumber);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            cartItem = (TextView)itemView.findViewById(R.id.cart_item);
            cartPrice = (TextView)itemView.findViewById(R.id.cart_price);
            cartImage = (ImageView)itemView.findViewById(R.id.cart_image);
            cartQuantity = (TextView)itemView.findViewById(R.id.cart_quantity);
            cartDelete = (TextView)itemView.findViewById(R.id.deleteCart);
            plusCartQuantity = (Button)itemView.findViewById(R.id.plusCartQuantity);
            minusCartQuantity = (Button)itemView.findViewById(R.id.minusCartQuantity);
            setQuantity = (TextView)itemView.findViewById(R.id.cart_quantity);
            cartCheckBox = (CheckBox)itemView.findViewById(R.id.checkboxtCart);

//            cartQuantity.setText(arrayListDescription.get(getAdapterPosition()).getCartQuantity());
//            cartPrice.setText(String.valueOf(arrayListDescription.get(getAdapterPosition()).getCartPrice()));

//            itemView.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//
//                    int q = getAdapterPosition();
//          //          cartQuantity.setText(arrayListDescription.get(q).getCartQuantity()); // работает
//                    System.out.println("getAdapterPosition()q " + q);
//                    System.out.println("getAdapterPosition()2 " + arrayListDescription.get(q).toString());
//                    Toast.makeText(context, "Element" + q, Toast.LENGTH_SHORT).show();
//                }
  //          });


//            plusCartQuantity.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                            DBHelper db = new DBHelper(context);
//                            db.plusCart(arrayListDescription.get(getAdapterPosition()).getInvNumber());
//                            cartQuantity.setText(db.getQuantityCount(arrayListDescription.get(getAdapterPosition()).getInvNumber()));
//
//
//                            String startPrice = arrayListDescription.get(getAdapterPosition()).getCartPriceStart();
//                            int count = Integer.parseInt(db.getQuantityCount(arrayListDescription);
//                            int price = Integer.parseInt(startPrice);
//                            int summ = price*count;
//
//                            cartPrice.setText(String.valueOf(summ));
//                }
//            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

