package com.example.pokemon

import android.location.Location

class Pokemon{
    var name:String?=null
    var des:String?=null
    var image:Int?=null
    var power:Double?=null
    var location: Location?=null
    var isCatch:Boolean?=false
    constructor(
        name: String?,
        des: String?,
        image: Int?,
        power: Double?,
        lat : Double?,
        long : Double?,
        isCatch:Boolean?
    ) {
        this.name = name
        this.des = des
        this.image = image
        this.power = power
        this.location=Location(name)
        this.location!!.latitude=lat!!
        this.location!!.longitude=long!!
        this.isCatch=isCatch
    }
}