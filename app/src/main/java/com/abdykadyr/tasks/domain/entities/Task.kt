package com.abdykadyr.tasks.domain.entities

data class Task(

    //base tasks
    val description: String,
    val category: String,
    val creationTime: Int,
    val finishingTime: Int? = null,
    //recurred tasks
    val countOfRepeats: Int = BASE_REPEATS_COUNT,
    var countOfRepeatsDone: Int = NO_REPEATS,
    //timed tasks
    val plannedTime: Int? = null,
    var spentTime: Int? = null,

    val id: Int = ID_UNDEFINED

) {

    companion object {
        private const val BASE_REPEATS_COUNT = 1
        private const val NO_REPEATS = 0
        private const val ID_UNDEFINED = 0
    }

}