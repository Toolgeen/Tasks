package com.abdykadyr.tasks.domain.entities

data class Task(

    //base tasks
    val name: String,
    val description: String,
    val category: String,
    val creationTime: Int,
    val deadline: Deadline,
    val finishingTime: Int? = null,
    //recurred tasks
    val countOfRepeats: Int? = BASE_REPEATS_COUNT,
    val countOfRepeatsDone: Int? = NO_REPEATS,
    //timed tasks
    val plannedTime: Int? = null,
    val spentTime: Int? = null,

    val id: Int = ID_UNDEFINED

) {

    companion object {
        private const val BASE_REPEATS_COUNT = 1
        private const val NO_REPEATS = 0
        private const val ID_UNDEFINED = -1
    }

}