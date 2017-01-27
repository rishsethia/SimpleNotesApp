package com.example.rishabh.simplenotesapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNotesActivity extends AppCompatActivity {



    Context mContext;



    @BindView(R.id.note_title_edittext)
    EditText titleView;

    @BindView(R.id.add_note_text_edittext)
    EditText textView;



    @BindView(R.id.add_note_coordinatorLayout)
    CoordinatorLayout myLayout;
    String noteTitle;
    String noteText;
    Calendar calendar;
    FirebaseAnalytics mAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        mContext = this;
        ButterKnife.bind(this);

         calendar = Calendar.getInstance();


        // analytics and transitions
//
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(mContext);
//
//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Transition amEnter = new Slide();
//            Transition amReturn = new Slide();
//
//            amEnter.setDuration(250);
//            amReturn.setDuration(250);
//
//            amEnter.excludeTarget(android.R.id.statusBarBackground, true);
//            amEnter.excludeTarget(android.R.id.navigationBarBackground, true);
//
//            amReturn.excludeTarget(android.R.id.statusBarBackground, true);
//            amReturn.excludeTarget(android.R.id.navigationBarBackground, true);
//
//            getWindow().setEnterTransition(amEnter);
//            getWindow().setReturnTransition(amReturn);



    }




    public void closingDialogFunction(View view) {
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.add_note_quit_dialog_text))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.dialog_add_quit_positive_button),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AddNotesActivity.this.finish();
                            }
                        })
                .setNegativeButton(getString(R.string.dialog_negative_button), null)
                .show();
    }

    public void saveNote(View view) {

        noteTitle = titleView.getText().toString();
        noteText = textView.getText().toString();

        if (noteTitle.equals("") && noteText.equals("")) {
            Snackbar.make(myLayout, getString(R.string
                    .alert_dialog_note_blank), Snackbar.LENGTH_SHORT).show();

            return;
        }


        calendar.setTimeInMillis(System.currentTimeMillis());


        new SaveNoteTask().execute(new NoteProperties(
                mContext,noteText,noteTitle,calendar.getTimeInMillis()
        ));

        Bundle bundle = new Bundle();

        bundle.putInt("length_frequency",noteText.length() );


//        mAnalytics.logEvent("add_medicine", bundle);


        AddNotesActivity.this.finish();
    }
}
