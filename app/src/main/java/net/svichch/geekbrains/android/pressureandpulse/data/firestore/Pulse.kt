package net.svichch.geekbrains.android.pressureandpulse.data.firestore

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Pulse(
    var id: String = UUID.randomUUID().toString(),
    var date: Date = Date(),
    var lowerPressure: Int = 0,
    var upperPressure: Int = 0,
    var pulse: Int = 0
): Parcelable