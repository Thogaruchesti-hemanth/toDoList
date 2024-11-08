package com.example.todolist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskDeleteListener {

    private EditText editTextTask;
    private Button addTaskButton;

    private ArrayList<Task> taskList;

    private TaskAdapter taskAdapter;

    private RecyclerView recyclerView;

    private static final String TASK_LIST_KEY = "task_list";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextTask=findViewById(R.id.editTextText2);
        addTaskButton=findViewById(R.id.button2);
        recyclerView=findViewById(R.id.recyclerView);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        addTaskButton.setOnClickListener(v->addTask());

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loadTasks();  // Load saved tasks when the app starts
    }

    private void loadTasks() {
            Set<String> taskSet = sharedPreferences.getStringSet(TASK_LIST_KEY, new HashSet<>());

            for (String taskDescription : taskSet) {
                taskList.add(new Task(taskDescription));
            }

            taskAdapter.notifyDataSetChanged();

    }

    private void addTask() {
        String taskDescription = editTextTask.getText().toString().trim();
        if (!taskDescription.isEmpty()) {
            taskList.add(new Task(taskDescription));
            taskAdapter.notifyItemInserted(taskList.size() - 1);
            editTextTask.setText("");
            saveTasks();
        }
    }

    private void saveTasks() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> taskSet = new HashSet<>();

        for (Task task : taskList) {
            taskSet.add(task.getTaskDescription());
        }

        editor.putStringSet(TASK_LIST_KEY, taskSet);
        editor.apply();
    }

    @Override
        public void onDeleteClick(int position) {
            taskList.remove(position);
            taskAdapter.notifyItemRemoved(position);
        }


}
