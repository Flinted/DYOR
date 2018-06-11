package makes.flint.dyor.deck.data

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

/**
 * AssetResponse
 */
class AssetResponse {

    // Properties

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("id")
    lateinit var id: String

    @SerializedName("symbol")
    lateinit var symbol: String

    @SerializedName("rank")
    private lateinit var rank: String

    @SerializedName("price_usd")
    private var priceUSD: String? = null

    @SerializedName("price_btc")
    private var priceBTC: String? = null

    @SerializedName("24h_volume_usd")
    private var volume24hUSD: String? = null

    @SerializedName("market_cap_usd")
    private var marketCapUSD: String? = null

    @SerializedName("available_supply")
    private var availableSupply: String? = null

    @SerializedName("total_supply")
    private var totalSupply: String? = null

    @SerializedName("percent_change_1h")
    var percentChange1H: String? = null

    @SerializedName("percent_change_24h")
    var percentChange24H: String? = null

    @SerializedName("percent_change_7d")
    var percentChange7D: String? = null

    // Overrides

    fun provideRank() = rank.toInt()

    fun providePriceUSD(): BigDecimal? {
        priceUSD ?: return null
        return BigDecimal(priceUSD)
    }

    fun providePriceBTC(): BigDecimal? {
        priceBTC ?: return null
        return BigDecimal(priceBTC)
    }

    fun provideVolume24Hour(): BigDecimal? {
        volume24hUSD ?: return null
        return BigDecimal(volume24hUSD)
    }

    fun provideMarketCapUSD(): BigDecimal? {
        marketCapUSD ?: return null
        return BigDecimal(marketCapUSD)
    }

    fun provideAvailableSupply() = availableSupply

    fun provideTotalSupply() = totalSupply
}
