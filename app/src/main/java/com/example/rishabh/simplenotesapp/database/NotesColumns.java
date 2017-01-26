package com.example.rishabh.simplenotesapp.database;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.Unique;

/**
 * Created by Rishabh on 1/26/17.
 */


public class NotesColumns {


    @DataType(DataType.Type.INTEGER)
    @PrimaryKey
    @Unique
    @AutoIncrement
    public static final String _ID = "_id";


    //Name of the medication
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String TITLE = "title";

    //Dosage for the medication
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String TEXT = "text";


    @DataType(DataType.Type.REAL)
    @NotNull
    public static final String TIME_IN_MILLIS = "millis";



    //Columns array for faster query buildup
    public static final String[] ALL_COLUMNS = {
            _ID,
            TITLE,
            TEXT,
            TIME_IN_MILLIS,

    };

    //Columns Indices
    public static final int ID_INDEX = 0;
    public static final int TITLE_INDEX = 1;
    public static final int TEXT_INDEX = 2;
    public static final int TIME_IN_MILLIS_INDEX = 3;


}
