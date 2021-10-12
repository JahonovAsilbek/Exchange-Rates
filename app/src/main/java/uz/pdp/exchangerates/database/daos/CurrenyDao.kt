package uz.pdp.exchangerates.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.pdp.exchangerates.database.entities.Currency

@Dao
interface CurrenyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurency(currency: Currency)

    @Query("select * from currency where id=:id order by date")
    fun getCurrencyHistory(id: Int): List<Currency>

    @Query("select * from currency where date=:date and code=:code")
    fun getCurrencyByDate(date: String, code: String): List<Currency>

    @Query("select * from currency where date<>:date and code=:code")
    fun getCurrencyByDateRv(date: String, code: String): List<Currency>

}