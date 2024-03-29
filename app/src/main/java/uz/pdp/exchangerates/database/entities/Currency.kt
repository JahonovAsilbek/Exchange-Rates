package uz.pdp.exchangerates.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "currency")
class Currency : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var cb_price: String? = null
    var code: String? = null
    var date: String? = null
    var nbu_buy_price: String? = null
    var nbu_cell_price: String? = null
    var title: String? = null

    constructor()

    @Ignore
    constructor(
        id: Int?,
        cb_price: String?,
        code: String?,
        date: String?,
        nbu_buy_price: String?,
        nbu_cell_price: String?,
        title: String?
    ) {
        this.id = id
        this.cb_price = cb_price
        this.code = code
        this.date = date
        this.nbu_buy_price = nbu_buy_price
        this.nbu_cell_price = nbu_cell_price
        this.title = title
    }

    @Ignore
    constructor(
        cb_price: String?,
        code: String?,
        date: String?,
        nbu_buy_price: String?,
        nbu_cell_price: String?,
        title: String?
    ) {
        this.cb_price = cb_price
        this.code = code
        this.date = date
        this.nbu_buy_price = nbu_buy_price
        this.nbu_cell_price = nbu_cell_price
        this.title = title
    }
}