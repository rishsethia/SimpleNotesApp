package com.example.rishabh.simplenotesapp;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.rishabh.simplenotesapp.database.NotesProvider;

/**
 * Created by Rishabh on 1/26/17.
 */
public class DeleteNoteTask extends AsyncTask<ContextAndId,Void,Context> {


    @Override
    protected Context doInBackground(ContextAndId... params) {

        Context context = params[0].getmContext();
        int noteId = params[0].getId();

        ContentResolver cr = context.getContentResolver();

        cr.delete(NotesProvider.Notes.withId(noteId),null,null);
        MainActivity.updateWidgets(context);
        return context;
    }

    @Override
    protected void onPostExecute(Context context) {
        if (context != null)
            Toast.makeText(context, context.getString(R.string.delete_note_toast), Toast
                    .LENGTH_SHORT).show();
    }
}
