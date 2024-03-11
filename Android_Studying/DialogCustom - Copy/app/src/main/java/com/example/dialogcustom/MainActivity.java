package com.example.dialogcustom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd();

            }
        });

    }

    public void openDialogAdd()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_add_item);
        Button btnAdd = (Button)dialog.findViewById(R.id.btnAdd);
        Button btnCancle = (Button)dialog.findViewById(R.id.btnCancle);
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
                openDiaLogColor();
            }
        });



        dialog.show();

    }

    public void openDiaLogColor()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_gridview_color);
        GridView gridView = (GridView) dialog.findViewById(R.id.grid_color);
        int[] colors = { R.color.black, R.color.blue, R.color.red, R.color.yellow, R.color.purple, R.color.turquoise, R.color.salmon, R.color.coral, R.color.mint, R.color.teal, R.color.green, R.color.light_blue, R.color.gray, R.color.lavender, R.color.pink, R.color.gold, R.color.white };
        Color_Adapter adapter = new Color_Adapter(this,colors);
        gridView.setAdapter(adapter);
        dialog.show();

    }
}