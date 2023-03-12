package com.imit.smallMatfak.ui.paginator

object Paginator {
    fun generatePageGridViewHero(arrayHero: List<Int>, currentPage: Int): List<Int>{
        val itemsPage = 4
        val lastPage = arrayHero.size / itemsPage + 1
        val lastPageRemainder = arrayHero.size % itemsPage
        val listPage: MutableList<Int> = mutableListOf()

        val startItem = if(currentPage != 1) {(currentPage - 1) * itemsPage } else { 0 }

        if(currentPage == lastPage  && lastPageRemainder > 0){
            for(i in startItem until startItem + lastPageRemainder){
                listPage.add(arrayHero[i])
            }
        } else {
            for (i in startItem until startItem + itemsPage){
                listPage.add(arrayHero[i])
            }
        }

        return listPage
    }
}