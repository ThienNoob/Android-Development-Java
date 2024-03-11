package com.example.taskpro_signin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Success extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView recyclerView;
    BroadsAdapter broadsAdapter;
    ArrayList<Broads> arrayBroads = new ArrayList<Broads>();
    ImageView imageView;
    int selectedColor = R.color.blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        initView();


    }

    // tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    // Hàm xử lí sự kiện các button trên menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getTitle().toString()) {
            case "Add":
                openDialogAdd();
                break;
            case "Search":
                openDiaLogColor();
                break;
            case "Notifications":
                Toast.makeText(this, "Bạn chọn " + item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(this, "Bạn chọn Home ", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }




    // Khởi tạo các recyclerview ( Bảng và background của nó )
    public void initView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.rv_broads);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(),LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ArrayList<ListTag> arrayListTag1= new ArrayList<ListTag>();
        ArrayList<ListTag> arrayListTag2= new ArrayList<ListTag>();
        ArrayList<ListTag> arrayListTag3= new ArrayList<ListTag>();
        ArrayList<ListTag> arrayListTag4= new ArrayList<ListTag>();
        ArrayList<ListTag> arrayListTag5= new ArrayList<ListTag>();

        ArrayList<String> tagList = new ArrayList<>();
        tagList.add("Lab3- Di động");
        tagList.add("Đồ án");
        tagList.add("Thuyết trình");

        ArrayList<String> tagList2 = new ArrayList<>();
        tagList2.add("Lab4-Nhúng");
        tagList2.add("Đồ án nhúng");

        ArrayList<String> tagList3 = new ArrayList<>();
        tagList3.add("Lab5-Nhúng");
        tagList3.add("Đồ án nhúng");

        ArrayList<String> tagList4 = new ArrayList<>();
        tagList4.add("Lab6-Nhúng");
        tagList4.add("Bảo mật");

        ArrayList<String> tagList5 = new ArrayList<>();
        tagList5.add("Lab7-Nhúng");
        tagList5.add("IoT");




        arrayListTag1.add(new ListTag("ToDo",tagList));
        arrayListTag1.add(new ListTag("Doing",tagList2));
        arrayListTag1.add(new ListTag("Done",tagList2));
        arrayListTag2.add(new ListTag("ToDo", tagList3));
        arrayListTag2.add(new ListTag("Doing", tagList4));
        arrayListTag2.add(new ListTag("Done", tagList5));

        arrayListTag3.add(new ListTag("Lab1", tagList));
        arrayListTag3.add(new ListTag("Lab2", tagList2));
        arrayListTag3.add(new ListTag("Lab3", tagList));

        arrayListTag4.add(new ListTag("Category1", tagList));
        arrayListTag4.add(new ListTag("Category2", tagList2));

        arrayListTag5.add(new ListTag("Tag1", tagList));
        arrayListTag5.add(new ListTag("Tag2", tagList2));

        arrayBroads.add(new Broads(R.color.blue,"Di động",arrayListTag1));
        arrayBroads.add(new Broads(R.color.lavender,"Hệ thống nhúng",arrayListTag2));
        arrayBroads.add(new Broads(R.color.gray,"An toàn mạng",arrayListTag3));
        arrayBroads.add(new Broads(R.drawable.back,"Nhậu",arrayListTag4));
        arrayBroads.add(new Broads(R.drawable.a,"Bóng chuyền",arrayListTag5));

        broadsAdapter = new BroadsAdapter(getApplicationContext(),arrayBroads);
        recyclerView.setAdapter(broadsAdapter);






    }

    // Mở lên dialog sự kiện thêm bảng mới
    public void openDialogAdd()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialoag_add_item);
        Button btnAdd = (Button)dialog.findViewById(R.id.btnAdd);
        Button btnCancle = (Button)dialog.findViewById(R.id.btnCancle);
        imageView = (ImageView) dialog.findViewById(R.id.iv_backcolor);
        EditText nameBroads = (EditText)dialog.findViewById(R.id.broad_name);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraTen(nameBroads.getText().toString()) ) {
                    arrayBroads.add(new Broads(selectedColor, nameBroads.getText().toString(),new ArrayList<ListTag>()));
                    broadsAdapter.notifyDataSetChanged();
                    dialog.cancel();
                }
                else
                    Toast.makeText(Success.this, "Invalid name", Toast.LENGTH_SHORT).show();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLogColor();
            }
        });

        dialog.show();

    }

    // Mở dialog để chọn background color
    public void openDiaLogColor()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_gridview_color);
        GridView gridView = (GridView) dialog.findViewById(R.id.grid_color);
        int[] colors = {
                R.color.black, R.color.blue, R.color.red, R.color.green, R.color.yellow,
                R.color.purple, R.color.orange, R.color.pink, R.color.brown, R.color.cyan,
                R.color.teal, R.color.light_blue, R.color.gray, R.color.gold, R.color.silver,
                R.color.lavender, R.color.turquoise, R.color.mint, R.color.salmon, R.color.coral,
                R.color.violet, R.color.indigo, R.color.magenta, R.color.lime
        };
        Color_Adapter adapter = new Color_Adapter(this,colors);
        gridView.setAdapter(adapter);

        //
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imageView.setImageResource(colors[position]);
                selectedColor=colors[position];
                dialog.cancel();
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