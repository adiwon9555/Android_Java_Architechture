package com.example.architectureexample.adaptar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.architectureexample.R;
import com.example.architectureexample.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdaptar extends RecyclerView.Adapter<NoteAdaptar.NoteHolder> {
    private ItemClickListener listener;
    private List<Note> notes = new ArrayList<>();
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.text_View_Priority.setText(String.valueOf(currentNote.getPriority()));
        holder.text_View_Description.setText(currentNote.getDescription());
        holder.text_View_Title.setText(currentNote.getTitle());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setAllNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteOnPosition(int position){
        return notes.get(position);
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
                        listener.onItemClick(notes.get(position));
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
