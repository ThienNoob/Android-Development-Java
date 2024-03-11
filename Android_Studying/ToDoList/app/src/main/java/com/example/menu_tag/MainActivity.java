package com.example.menu_tag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_list_tag;
    AdapterListTag listTagAdapter;
    ArrayList<ListTag> arrayListTag = new ArrayList<ListTag>();

    public void SetArrayListTag()
    {
        ArrayList<String> tagList = new ArrayList<>();
        tagList.add("Lab3-ATM");
        tagList.add("Project");
        tagList.add("hehe");

        ArrayList<String> tagList2 = new ArrayList<>();
        tagList2.add("Lab4-ATM");
        tagList2.add("Project2");
        tagList2.add("câcca");


        arrayListTag.add(new ListTag("ToDo",tagList));
        arrayListTag.add(new ListTag("Doing",tagList2));
        arrayListTag.add(new ListTag("Done",tagList2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetArrayListTag();
        initViewListTag(arrayListTag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_tag, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void initViewListTag(ArrayList<ListTag> arrayListTag)
    {
        rv_list_tag = (RecyclerView) findViewById(R.id.rv_list_tag);
        rv_list_tag.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rv_list_tag.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(rv_list_tag.getContext(),LinearLayoutManager.HORIZONTAL);
        rv_list_tag.setItemAnimator(new DefaultItemAnimator());
        rv_list_tag.setItemAnimator(new DefaultItemAnimator());

        listTagAdapter = new AdapterListTag(getApplicationContext(),arrayListTag);
        rv_list_tag.setAdapter(listTagAdapter);

    }

    // Hàm xử lí sự kiện các button trên menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getTitle().toString())
        {
            case "Add":
                openDialogAddListTag();
                break;
            default:
                Toast.makeText(this, "Bạn chọn Home ", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Thêm list tag mới
    public void openDialogAddListTag()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_add_listtag);
        Button btnAdd = (Button)dialog.findViewById(R.id.btnAdd);
        Button btnCancle = (Button)dialog.findViewById(R.id.btnCancle);
        EditText et_nameListTag = (EditText)dialog.findViewById(R.id.et_nameListTag);
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
                    arrayListTag.add(new ListTag(et_nameListTag.getText().toString(),new ArrayList<>()));
                    listTagAdapter.notifyDataSetChanged();
                    dialog.cancel();
                }
                else
                    Toast.makeText(MainActivity.this, "Invalid name", Toast.LENGTH_SHORT).show();
            }
        });



        dialog.show();

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