package com.example.dmitry.weatherchecker.other

class ApiQuery {
    companion object {
        const val GET_ALL = "SELECT * FROM weatherData"
        const val DELETE_ALL = "DELETE FROM weatherData"
        const val DELETE_ONE = "DELETE FROM weatherData WHERE id = :id"
        const val GET_ONE_BY_ID = "SELECT * FROM weatherData WHERE id=:id"
        const val GET_LAST_DATA = "SELECT * FROM weatherData group by dt order by id DESC LIMIT 1"
        const val GET_10_LAST_DATA = "SELECT * FROM weatherData group by dt order by id DESC LIMIT 10"
        const val GET_TODAY_DATA = "SELECT * FROM weatherData WHERE dt_text > CURRENT_DATE group by dt"
    }
}