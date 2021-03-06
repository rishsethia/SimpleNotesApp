package com.example.rishabh.simplenotesapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.note_title_detail)
    TextView titleView;

    @BindView(R.id.note_text_detail)
    TextView textView;


    Context mContext;



    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        titleView.setText(intent.getStringExtra("noteTitle"));
        textView.setText(intent.getStringExtra("noteText"));


        id = intent.getIntExtra("notesId",0);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Explode detailsEnter = new Explode();
            Slide detailsReturn = new Slide();

            detailsEnter.setDuration(250);
            detailsReturn.setDuration(250);

            detailsEnter.excludeTarget(android.R.id.statusBarBackground, true);
            detailsEnter.excludeTarget(android.R.id.navigationBarBackground, true);
            detailsEnter.excludeTarget(R.id.detail_toolbar, true);

            detailsReturn.excludeTarget(android.R.id.statusBarBackground, true);
            detailsReturn.excludeTarget(android.R.id.navigationBarBackground, true);
            detailsReturn.excludeTarget(R.id.detail_toolbar, true);

            getWindow().setEnterTransition(detailsEnter);
            getWindow().setReturnTransition(detailsReturn);
        }

    }


    public void onDeleteButton(View view) {

        new AlertDialog.Builder(mContext)
                .setPositiveButton(
                        getString(R.string.delete_note_dialog_positive),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new DeleteNoteTask().execute(new ContextAndId(
                                        mContext,id ));

                                finish();
                            }
                        })
                .setNegativeButton(getString(R.string.dialog_delete_negative_button),
                        null)
                .setMessage(getString(R.string
                        .delete_note_alert_dialog))
                .show();
    }



    public void onBack(View view) {
        finish();
    }

}
