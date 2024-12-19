package com.bongbong.coupleapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.bongbong.coupleapp.calendar.schedule.ScheduleData
import java.time.LocalDate
import java.time.LocalTime

class SQLiteDBHelper(
    val context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "bongbongCoupleApp"

        const val TABLE_NAME = "schedule"
        const val UID = "UID"

        const val START_DATE = "startDate"
        const val END_DATE = "endDate"
        const val START_TIME = "startTime"
        const val END_TIME = "endTime"
        const val NAME = "name"
        const val CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var createSql: String = "CREATE TABLE IF NOT EXISTS " +
                "$TABLE_NAME ($UID integer primary key autoincrement," +
                "$START_DATE text, $END_DATE text, $START_TIME text, $END_TIME text, $NAME text, $CONTENT text);"

        db?.execSQL(createSql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS $TABLE_NAME"

        db?.execSQL(sql)
        onCreate(db)
    }

    fun getScheduleData(date: LocalDate): List<ScheduleData> {

        val schedules = ArrayList<ScheduleData>()

        val startRange = date.withDayOfMonth(1).toString()
        val endRange = date.withDayOfMonth(date.lengthOfMonth()).toString()

        val selectQueryHandler = "select * FROM ($START_DATE between $startRange and $endRange) or ($END_DATE between $startRange and $endRange) or ($START_DATE < $startRange and $END_DATE > $startRange) "

        val db = readableDatabase

        val cursor = db.rawQuery(selectQueryHandler, null)

        if(cursor.moveToFirst()) {
            do {

                val uid = cursor.getInt(cursor.getColumnIndex(UID))
                val startDate = LocalDate.parse(cursor.getString(cursor.getColumnIndex(START_DATE)))
                val endDate = LocalDate.parse(cursor.getString(cursor.getColumnIndex(END_DATE)))
                val startTime = LocalTime.parse(cursor.getString(cursor.getColumnIndex(START_TIME)))
                val endTime = LocalTime.parse(cursor.getString(cursor.getColumnIndex(END_TIME)))
                val name = cursor.getString(cursor.getColumnIndex(NAME))
                val content = cursor.getString(cursor.getColumnIndex(CONTENT))

                val schedule = ScheduleData(uid, startDate, endDate, startTime, endTime, name, content)
                schedules.add(schedule)
            } while (cursor.moveToNext())
        }

        return schedules
    }

    fun insertSchedule(startDate: LocalDate,
                       endDate: LocalDate,
                       startTime: LocalTime,
                       endTime: LocalTime,
                       name: String,
                       content: String) {

        val db = writableDatabase
        val values = ContentValues()
        values.put(UID, 0)
        values.put(START_DATE, startDate.toString())
        values.put(END_DATE, endDate.toString())
        values.put(START_TIME, startTime.toString())
        values.put(END_TIME, endTime.toString())

        values.put(NAME, name)
        values.put(CONTENT, content)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateSchedule(scgeduleData: ScheduleData) {

        val db = writableDatabase
        val values = ContentValues()
        values.put(START_DATE, scgeduleData.startDate.toString())
        values.put(END_DATE, scgeduleData.endDate.toString())
        values.put(START_TIME, scgeduleData.startTime.toString())
        values.put(END_TIME, scgeduleData.endTime.toString())

        values.put(NAME, scgeduleData.name)
        values.put(CONTENT, scgeduleData.content)

        db.update(TABLE_NAME, values,  "$UID=?", arrayOf("${scgeduleData.uid}"))
        db.close()
    }

    fun deleteSchedule(uid: Int) {
        val db = writableDatabase

        db.delete(TABLE_NAME, "$UID=?", arrayOf("$uid"))
        db.close()
    }
}