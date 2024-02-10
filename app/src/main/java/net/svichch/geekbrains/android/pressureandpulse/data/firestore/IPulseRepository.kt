package net.svichch.geekbrains.android.pressureandpulse.data.firestore

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


interface IPulseRepository {

    fun getAllPulse(): Single<List<Pulse>>

    fun addPulse(pulse: Pulse): Completable
    fun deletePulse(id: String): Completable

    fun getChangeObservable(): Observable<List<Pulse>>
}