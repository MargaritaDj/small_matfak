package com.imit.smallMatfak.ui.paginator

import com.imit.smallMatfak.model.TaskAnswerStudent

object Paginator {
    var newPageStartTask = -1

    fun generatePageGridViewHero(arrayHero: List<Int>, currentPage: Int): List<Int> {
        val itemsPage = 4
        val lastPage = arrayHero.size / itemsPage + 1
        val lastPageRemainder = arrayHero.size % itemsPage
        val listPage: MutableList<Int> = mutableListOf()

        val startItem = if (currentPage != 1) {
            (currentPage - 1) * itemsPage
        } else {
            0
        }

        if (currentPage == lastPage && lastPageRemainder > 0) {
            for (i in startItem until startItem + lastPageRemainder) {
                listPage.add(arrayHero[i])
            }
        } else {
            for (i in startItem until startItem + itemsPage) {
                listPage.add(arrayHero[i])
            }
        }

        return listPage
    }

    fun generatePageTasksStudent(mapTasks: Map<String, List<TaskAnswerStudent>>, currentPage: Int)
            : Pair<String, List<TaskAnswerStudent>> {
        val itemsPage = 3
        val keys = mapTasks.keys.toList()
        val listPage: MutableList<TaskAnswerStudent> = mutableListOf()
        newPageStartTask = -1

        var currentDate = ""
        var startItem = (currentPage - 1) * itemsPage
        var sum = 0

        for (key in keys) {
            val sizeList = mapTasks[key]?.size!!
            sum += sizeList
            if (sum > startItem) {
                currentDate = key
                for (i in startItem until startItem + itemsPage) {
                    if (sizeList > i && listPage.size != itemsPage) {
                        mapTasks[key]?.get(i)?.let { listPage.add(it) }
                    }
                }
                if (listPage.size != 0 && mapTasks[key]?.get(0) == listPage[0]) {
                    newPageStartTask = 0
                }
                if (listPage.size != itemsPage && newPageStartTask == -1) {
                    newPageStartTask = listPage.size
                    startItem = 0
                } else {
                    break
                }
            } else {
                startItem -= sum
            }
        }

        if (newPageStartTask == -1) {
            currentDate = ""
        }

        return Pair(currentDate, listPage)
    }
}