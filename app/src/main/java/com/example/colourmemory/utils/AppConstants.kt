package com.example.colourmemory.utils

class AppConstants {
    interface Database {
        companion object {
            const val DATABASE_NAME = "COLOUR_MEMORY_DATABASE"
        }

        interface TableName {
            companion object {
                const val PLAYER_SCORE = "PLAYER_SCORE"
            }
        }
    }
}