package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> tasks;
    private OnTaskDeleteListener onTaskDeleteListener;
    public interface OnTaskDeleteListener {
        void onDeleteClick(int position);
    }

    public TaskAdapter(ArrayList<Task> tasks,OnTaskDeleteListener onTaskDeleteListener){

        this.tasks=tasks;
        this.onTaskDeleteListener=onTaskDeleteListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new TaskViewHolder(view, onTaskDeleteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        Task task = tasks.get(position);
        holder.textViewTaskDescription.setText(task.getTaskDescription());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTaskDescription;
        private Button DeleteTask;
        public TaskViewHolder(@NonNull View itemView ,OnTaskDeleteListener onTaskDeleteListener) {
            super(itemView);
            textViewTaskDescription = itemView.findViewById(R.id.textViewTaskDescription);
            DeleteTask=itemView.findViewById(R.id.taskDeleteButton);

            DeleteTask.setOnClickListener(v->{

                if (onTaskDeleteListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onTaskDeleteListener.onDeleteClick(position);
                    }
                }
            });
        }
    }
}
