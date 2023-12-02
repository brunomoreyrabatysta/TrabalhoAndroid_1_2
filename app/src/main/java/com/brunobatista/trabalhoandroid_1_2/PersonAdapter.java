package com.brunobatista.trabalhoandroid_1_2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonAdapter  extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private List<Person> personList;
    private IOnItemClickListener listener;

    public PersonAdapter(List<Person> personList, IOnItemClickListener listener) {
        this.personList = personList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.edtPersonId.setText(Integer.toString(person.getId()));
        holder.edtName.setText(person.getName());
        holder.edtEmail.setText(person.getEmail());
        holder.edtPhone.setText(person.getPhone());

        holder.btnEditPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        });

        holder.btnDeletePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public interface IOnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView edtPersonId, edtName, edtEmail, edtPhone;
        Button btnEditPerson, btnDeletePerson;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            edtPersonId = itemView.findViewById(R.id.edtPersonId);
            edtName = itemView.findViewById(R.id.edtPersonName);
            edtEmail = itemView.findViewById(R.id.edtPersonEmail);
            edtPhone = itemView.findViewById(R.id.edtPersonPhone);

            btnEditPerson = itemView.findViewById(R.id.bnUpdatePerson);
            btnDeletePerson = itemView.findViewById(R.id.btnDeletePerson);
        }
    }
}
