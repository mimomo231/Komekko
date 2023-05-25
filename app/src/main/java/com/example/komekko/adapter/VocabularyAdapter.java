package com.example.komekko.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.komekko.R;
import com.example.komekko.VocabularyActivity;
import com.example.komekko.dal.Sqlite;
import com.example.komekko.model.Lesson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder>{
    private Context context;

    private List<Lesson> mlist;

    private VocabularyItemListener mVocabularyItem;
    List<Integer> num;

    public List<Integer> getNum() {
        return num;
    }

    public void setNum(List<Integer> num) {
        this.num = num;
    }

    public void setMlist(List<Lesson> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    public VocabularyAdapter(Context context) {
        this.context = context;
        mlist = new ArrayList<>();
        num = new ArrayList<>();
    }
    //lấy danh sách được lọc ra
    public void filterList(List<Lesson> filterlist){
        mlist = filterlist;
        notifyDataSetChanged();
    }

    public void setmVocabularyItem(VocabularyItemListener mVocabularyItem) {
        this.mVocabularyItem = mVocabularyItem;
    }
    public void add(Lesson lesson) {
        mlist.add(lesson);
        notifyDataSetChanged();
    }
    // lấy ra item trong tại position
    public Lesson getItem(int position){
        return mlist.get(position);

    }

    @NonNull
    @Override
    public VocabularyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new VocabularyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyViewHolder holder, int position) {
        Lesson lesson = mlist.get(position);
        if(lesson == null)
            return;
        holder.img.setImageResource(R.drawable.meo1);
        holder.txtName.setText(lesson.getName());
        if (num.size() > 0 && num.get(position) > 0) {
            String text = (num.get(position)+8)+"/12";
            holder.tvNumber.setText(text);
        }
        else{
            holder.tvNumber.setText("0/12");
        }

    }

    @Override
    public int getItemCount() {
        if (mlist != null)
            return mlist.size();
        return 0;
    }

    public class VocabularyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView txtName, tvNumber;
        public VocabularyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txtName = itemView.findViewById(R.id.txtName);
            tvNumber = itemView.findViewById(R.id.txtNumber);
            //item được bắt sự kiện từng item
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mVocabularyItem != null){
                mVocabularyItem.onItemClick(v,getAdapterPosition());
            }
        }
    }
    // bắt sự kiện click chuột item trong RecyclerView
    public interface VocabularyItemListener {
        void onItemClick(View view, int position);
    }
}
