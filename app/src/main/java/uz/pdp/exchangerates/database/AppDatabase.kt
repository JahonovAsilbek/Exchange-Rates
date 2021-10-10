package uz.pdp.exchangerates.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.pdp.exchangerates.database.daos.CurrenyDao
import uz.pdp.exchangerates.database.entities.Currency

@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): CurrenyDao

    companion object {
        @Volatile
        private var database: AppDatabase? = null

        fun init(context: Context) {
            synchronized(this) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "currency.db"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }
    }

    object get {
        fun getDatabase(): AppDatabase = database!!
    }
}