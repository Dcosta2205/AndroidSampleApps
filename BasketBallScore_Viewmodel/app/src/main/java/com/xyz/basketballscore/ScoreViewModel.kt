package com.xyz.basketballscore

import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    private var trojansScore: Int = 0
    private var phoenixScore: Int = 0

    fun updateTrojansScore(score: Int) {
        trojansScore += score
    }

    fun updatePhoenixScore(score: Int) {
        phoenixScore += score
    }

    fun getTrojansScore(): Int {
        return trojansScore
    }

    fun getPhoenixScore(): Int {
        return phoenixScore
    }
}