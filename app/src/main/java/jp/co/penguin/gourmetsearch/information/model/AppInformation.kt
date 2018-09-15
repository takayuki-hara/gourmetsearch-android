package jp.co.penguin.gourmetsearch.information.model

data class AppInformation(
        val action: AppInformationAction,
        val title: String,
        val sub: String?,
        val url: String?
)

enum class AppInformationAction() {
    NONE,
    APIKEY,
    URL,
    LICENSE;
}
