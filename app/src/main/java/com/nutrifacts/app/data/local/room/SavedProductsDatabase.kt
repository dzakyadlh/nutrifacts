package com.nutrifacts.app.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nutrifacts.app.data.local.entity.SavedProducts

@Database(entities = [SavedProducts::class], version = 1)
abstract class SavedProductsDatabase : RoomDatabase() {
    abstract fun savedProductsDao(): SavedProductsDao

    companion object {
        @Volatile
        private var INSTANCE: SavedProductsDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): SavedProductsDatabase {
            if (INSTANCE == null) {
                synchronized(SavedProductsDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SavedProductsDatabase::class.java,
                        "saved_products_database"
                    ).build()
                }
            }
            return INSTANCE as SavedProductsDatabase
        }
    }
}