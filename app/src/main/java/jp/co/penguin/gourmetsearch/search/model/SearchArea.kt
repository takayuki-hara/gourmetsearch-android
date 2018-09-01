package jp.co.penguin.gourmetsearch.search.model

class AreaManager {
    enum class SearchArea(val rawValue :Int) {
        TOKYO(0),
        KYOTO(1),
        OSAKA(2),
        FUKUSHIMA(3),
        FUKUOKA(4),
        HOKKAIDO(5),
        OKINAWA(6);
    }

    fun allData(): Array<String> {
        val values = SearchArea.values()
        val array = Array(values.count(), { i -> name(values[i]) })
        return array
    }

    fun name(area: AreaManager.SearchArea): String {
        when(area) {
            SearchArea.TOKYO -> return "東京"
            SearchArea.KYOTO -> return "京都"
            SearchArea.OSAKA -> return "大阪"
            SearchArea.FUKUSHIMA -> return "福島"
            SearchArea.FUKUOKA -> return "福岡"
            SearchArea.HOKKAIDO -> return "北海道"
            SearchArea.OKINAWA -> return "沖縄"
            else -> return "その他"
        }
    }

    fun code(area: AreaManager.SearchArea): String {
        when(area) {
            SearchArea.TOKYO -> return "Z011"
            SearchArea.KYOTO -> return "Z022"
            SearchArea.OSAKA -> return "Z023"
            SearchArea.FUKUSHIMA -> return "Z056"
            SearchArea.FUKUOKA -> return "Z091"
            SearchArea.HOKKAIDO -> return "Z041"
            SearchArea.OKINAWA -> return "Z098"
            else -> return "その他"
        }
    }

}
