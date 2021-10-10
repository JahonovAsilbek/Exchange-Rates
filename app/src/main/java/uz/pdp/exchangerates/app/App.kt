package uz.pdp.exchangerates.app

import android.app.Application
import uz.pdp.exchangerates.database.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }
}