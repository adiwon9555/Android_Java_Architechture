package com.example.architectureexample.adaptar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.architectureexample.R;
import com.example.architectureexample.entity.Note;


public class NoteAdaptar extends ListAdapter<Note,NoteAdaptar.NoteHolder> {
    private ItemClickListener listener;

    public NoteAdaptar() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return  oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.text_View_Priority.setText(String.valueOf(currentNote.getPriority()));
        holder.text_View_Description.setText(currentNote.getDescription());
        holder.text_View_Title.setText(currentNote.getTitle());
    }


    public Note getNoteOnPosition(int position){
        return getItem(position);
    }

    public class NoteHolder extends RecyclerView.ViewHolder{
        private TextView text_View_Priority;
        private TextView text_View_Title;
        private TextView text_View_Description;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            text_View_Title = itemView.findViewById(R.id.text_View_Title);
            text_View_Description = itemView.findViewById(R.id.text_View_Description);
            text_View_Priority = itemView.findViewById(R.id.text_View_Priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }
    public interface ItemClickListener{
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(ItemClickListener listener ){
        this.listener = listener;

    }

}
