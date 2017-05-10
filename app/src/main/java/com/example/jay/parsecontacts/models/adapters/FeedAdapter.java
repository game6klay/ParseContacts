package com.example.jay.parsecontacts.models.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jay.parsecontacts.R;
import com.example.jay.parsecontacts.controllers.Controller;
import com.example.jay.parsecontacts.models.pojo.Contacts;
import com.example.jay.parsecontacts.models.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jay on 5/8/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.Holder> {

    public static String TAG = FeedAdapter.class.getSimpleName();
    private List<Contacts> contactsList;
    private Controller.FeedCallbackListener mListener;

    public FeedAdapter(Controller.FeedCallbackListener listener,
                       List<Contacts> list) {
        this.mListener = listener;
        contactsList = list;
    }

    public void addItem(Contacts contact) {
        //List<Result> list = mItems.getResult();
        contactsList.add(contact);
        notifyDataSetChanged();
    }

    public void addItems(List<Contacts> mList) {
        //List<Result> mResults = mItems.getResult();
        contactsList.addAll(mList);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        //List<Result> mResultList = mItems.getResult();

        Contacts mContact = contactsList.get(position);
        holder.mId.setText(mContact.getId());
        holder.mName.setText(mContact.getName());
        holder.mEmail.setText(mContact.getId());
        holder.mGender.setText(mContact.getId());

        Picasso.with(holder.itemView.getContext())
                .load(Constants.PHOTO_URL)
                .centerCrop()
                .fit()
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.id)
        public TextView mId;

        @BindView(R.id.name)
        public TextView mName;

        @BindView(R.id.email)
        public TextView mEmail;

        @BindView(R.id.gender)
        public TextView mGender;

        @BindView(R.id.image)
        public ImageView mImage;

        @BindView(R.id.cont_item_root)
        public CardView mContainer;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition());
        }
    }


}
