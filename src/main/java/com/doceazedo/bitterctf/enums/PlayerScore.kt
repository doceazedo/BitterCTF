package com.doceazedo.bitterctf.enums

enum class PlayerScore(val points: Int) {
    PICK_FLAG(5),
    CAPTURE_FLAG(20),
    KILL_ENEMY(10),
    RECOVER_FLAG(5)
}