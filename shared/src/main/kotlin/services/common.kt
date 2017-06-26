package cz.aipsafe.ksafe.shared.services.common

data class ObjectInfo(val id: Long, val display: String,val defName: String) {
    fun jsonize() = JSONObjectInfo(id.toDouble(), display, defName)
}

data class Property(val name: String, val value: PropertyValue) {
    fun jsonize() = JSONProperty(name, value.jsonize())
}

sealed class PropertyValue {
    fun  jsonize(): JSONPropertyValue = when(this) {
        is StringPropertyValue -> JSONPropertyValue(string=string)
        is DoublePropertyValue -> JSONPropertyValue(double=double)
        NullPropertyValue -> JSONPropertyValue()
    }
}
data class DoublePropertyValue(val double: Double) : PropertyValue()
data class StringPropertyValue(val string: String) : PropertyValue()
object NullPropertyValue : PropertyValue()

data class JSONObjectInfo(val id: Double, val display: String, val defName: String)
fun JSONObjectInfo.normalize() = ObjectInfo(id.toLong(), display, defName)


data class JSONProperty(val name: String, val value: JSONPropertyValue)
fun JSONProperty.normalize() = Property(name, value.normalize())


data class JSONPropertyValue (val string: String? = null, val double: Double? = null)
fun JSONPropertyValue.normalize() = when {
    string != null -> StringPropertyValue(string)
    double != null -> DoublePropertyValue(double)
    else -> NullPropertyValue
}


