package com.example.assessmenttask;

import android.app.Dialog;
import android.content.Context;


import android.content.Intent;


import android.net.Uri;
import android.util.Log;

 import android.view.View;
 import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ResponseDialogBox {
    private Dialog dialog;
    private String title, imageURL;

    private ResponseDialogBox() {
    }

    private static final ResponseDialogBox instance = new ResponseDialogBox();

    public static ResponseDialogBox getResponseDialogBoxInstance() {
        return instance;
    }

    public void show(Context context, String title, String imageURL,String successURL )  {
        this.title = title;
        this.imageURL = imageURL;
        dialog = new Dialog(context);

        dialog.setContentView(R.layout.response_dialog_box);
        ImageView imageView = dialog.findViewById(R.id.image);
        Log.d("TAG", this.imageURL);
        Picasso.get().load(imageURL).into(imageView);

        TextView textView = dialog.findViewById(R.id.title);
        textView.setText(title);
        Button success = dialog.findViewById(R.id.success);
        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(successURL));
                context.startActivity(browserIntent);

            }
        });


        dialog.show();


    }



}
