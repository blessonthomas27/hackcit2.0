package com.example.hackcit20.dataclass

data class ViewType(val viewtype:Int){
    lateinit var data:List<Category>
    lateinit var dataa:Category
    lateinit var ImaguRL: List<String>
    constructor(viewtype: Int, data:List<Category>):this(viewtype){
        this.data=data
    }
    constructor(viewtype: Int,ImaguRL:List<String>,s:String):this(viewtype){
        this.ImaguRL=ImaguRL
    }
    constructor(viewtype: Int, data:Category,s:String,ss:String):this(viewtype){
        this.dataa=data
    }
}