package com.example.rishabh.simplenotesapp;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.widget.TextView;

import com.example.rishabh.simplenotesapp.database.NotesColumns;
import com.example.rishabh.simplenotesapp.database.NotesProvider;
import com.example.rishabh.simplenotesapp.recyclerViewHelpers.EmptyRecyclerViewSupport;
import com.example.rishabh.simplenotesapp.recyclerViewHelpers.NotesCursorAdapter;
import com.example.rishabh.simplenotesapp.recyclerViewHelpers.rvClickListener;
import com.facebook.stetho.Stetho;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager
        .LoaderCallbacks<Cursor> {

    private NotesCursorAdapter notesCursorAdapter;

    private static final int CURSOR_LOADER_ID = 0;
    private Cursor mCursor;
    @BindView(R.id.recyler_view)
    EmptyRecyclerViewSupport recyclerView;

    @BindView(R.id.empty_view)
    TextView emptyView;


    Context mContext;
    Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fabAdd);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddNotesActivity.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(mActivity)
                            .toBundle();
                    mContext.startActivity(intent, bundle);
                } else
                    startActivity(intent);
            }
        });

        ButterKnife.bind(this);

        //setting up stetho
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );

        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);


        notesCursorAdapter = new NotesCursorAdapter(mContext,null);
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(notesCursorAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");


        recyclerView.addOnItemTouchListener(new rvClickListener(this, new
                rvClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        mCursor.moveToPosition(position);

                        Intent intent = new Intent(mContext, DetailsActivity.class);
                        intent.putExtra("notesId", mCursor.getInt(NotesColumns
                                .ID_INDEX));

                        intent.putExtra("noteTitle", mCursor.getString(NotesColumns.TITLE_INDEX));
                        intent.putExtra("noteText", mCursor.getString(NotesColumns
                                .TEXT_INDEX));

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(mActivity)
                                    .toBundle();
                            mContext.startActivity(intent, bundle);
                        } else
                            startActivity(intent);
                    }
                }));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition mainExit = new Explode();
            Transition mainReenter = new Slide();
            mainExit.setDuration(250);

            mainExit.excludeTarget(R.id.fabAdd, true);
            mainExit.excludeTarget(android.R.id.statusBarBackground, true);
            mainExit.excludeTarget(android.R.id.navigationBarBackground, true);
            mainExit.excludeTarget(R.id.toolbar_main, true);

            mainReenter.excludeTarget(R.id.fabAdd, true);
            mainReenter.excludeTarget(android.R.id.statusBarBackground, true);
            mainReenter.excludeTarget(android.R.id.navigationBarBackground, true);
            mainReenter.excludeTarget(R.id.toolbar_main, true);

            getWindow().setExitTransition(mainExit);
            getWindow().setReenterTransition(mainReenter);
        }

            updateWidgets(mContext);
    }






    public static final String ACTION_DATA_UPDATED =
            "com.example.rishabh.simplenotesapp.ACTION_DATA_UPDATED";

    public static void updateWidgets(Context context) {
        // Setting the package ensures that only components in our app will receive the broadcast
        Intent updatedIntent = new Intent(ACTION_DATA_UPDATED)
                .setPackage(context.getPackageName());
        context.sendBroadcast(updatedIntent);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return
                new CursorLoader(mContext, NotesProvider.Notes.CONTENT_URI,
                        NotesColumns.ALL_COLUMNS,null,null,null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        notesCursorAdapter.swapCursor(data);
        mCursor = data;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        notesCursorAdapter.swapCursor(null);
    }



}
