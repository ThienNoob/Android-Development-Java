package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView lv_task;
    ArrayList<Task> arrayList;
    TaskAdapter taskAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_task = (ListView)findViewById(R.id.lv_task);
        arrayList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this,R.layout.task_item,arrayList);
        lv_task.setAdapter(taskAdapter);

        // tạo database ghi chú
        database= new Database(this,"ghichu.sqlite",null,1);

        // tạo bảng
        database.QueryData("CREATE TABLE IF NOT EXISTS LIST_TASK(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(200))");

        // insert DATA
        database.QueryData("INSERT INTO LIST_TASK VALUES(null,'lam bai tap')");
        database.QueryData("INSERT INTO LIST_TASK VALUES(null,'androi')");

        //lấy data
        getTask();



    }

    void getTask()
    {
        arrayList.clear();
        Cursor dataListTask = database.GetData("SELECT * FROM LIST_TASK");
        while (dataListTask.moveToNext())
        {
            arrayList.add(new Task(dataListTask.getInt(0),dataListTask.getString(1)));
            taskAdapter.notifyDataSetChanged();
        }

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Hàm xử lí sự kiện các button trên menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getTitle().toString())
        {
            case "Add":
                openDialogAddTask();
                break;
            default:
                Toast.makeText(this, "Bạn chọn Home ", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    public void openDialogAddTask()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_addtask);
        Button btnAdd = (Button)dialog.findViewById(R.id.btnAdd);
        Button btnCancle = (Button)dialog.findViewById(R.id.btnCancle);
        EditText et_nameListTag = (EditText)dialog.findViewById(R.id.et_add_Task);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraTen(et_nameListTag.getText().toString()) ) {
                    database.QueryData("INSERT INTO LIST_TASK VALUES(null,'"+ et_nameListTag.getText().toString() + "')");
                    getTask();
                    dialog.dismiss();
                }
                else
                    Toast.makeText(MainActivity.this, "Invalid name", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();

    }

    void DeleteTask(int id)
    {
        database.QueryData("DELETE FROM LIST_TASK WHERE ID ='"+id +"' ");
    }



    public static boolean kiemTraTen(String ten) {
        // Kiểm tra xem chuỗi không rỗng
        if (ten == null || ten.isEmpty()) {
            return false;
        }

        // Kiểm tra xem chuỗi chỉ chứa các ký tự chữ cái và khoảng trắng
        for (char kyTu : ten.toCharArray()) {
            if (!Character.isLetter(kyTu) && kyTu != ' ') {
                return false;
            }
        }

        return true;
    }



}