package com.xing.binge.util

//extension function
fun Set<String>?.search(lowerCasedSearchText: String): Pair<Boolean,String?> {
    //split into a list based on found spaces
    val wordList = lowerCasedSearchText.split("\\s+".toRegex())

    //if the 2 words don't match then only care about finding a match with the first one
    fun findTwoWordMatch(): String? {
        var result = this?.firstOrNull { s: String -> s == (wordList.first() + " " + wordList.elementAtOrNull(1)) }
        if (result.isNullOrEmpty()) result = this?.firstOrNull { s: String -> s == wordList.first() }
        return result
    }

    fun findMatchingGenre(): Pair<Boolean,String?> {
        val genre = when {
            wordList.size > 1 -> findTwoWordMatch()
            else -> this?.firstOrNull { s: String ->  s == wordList.elementAtOrNull(0)}
        }
        //build a pair as (a match was found, string that matches)
        return Pair(!genre.isNullOrEmpty(),genre)
    }
    //init
    return findMatchingGenre()
}