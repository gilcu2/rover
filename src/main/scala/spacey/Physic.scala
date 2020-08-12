package spacey

case class Vector(x:Int, y: Int) {

	def *(scalar:Int)=Vector(x*scalar,y*scalar)

	def +(v:Vector)=Vector(x+v.x,y+v.y)


}

case class Matrix(x11:Int,x12:Int,x21:Int,x22:Int) {

	def *(v:Vector)=Vector(v.x*x11+v.y*x12,v.x*x21+v.y*x22)

}
