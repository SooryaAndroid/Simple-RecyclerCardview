package com.android.recyclerview1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by arunkumar on 03/11/16.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<Contacts>contactlist;


    public ContactsAdapter(List<Contacts>contactlist){
        this.contactlist = contactlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contacts contact = contactlist.get(position);
        holder.name.setText(contact.getNames());
        holder.email.setText(contact.getEmails());
        holder.phno.setText(contact.getPhnos());
        

    }

    @Override
    public int getItemCount() {
        return contactlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,email,phno;
        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            email = (TextView)itemView.findViewById(R.id.email);
            phno = (TextView)itemView.findViewById(R.id.phno);


        }
    }
}
