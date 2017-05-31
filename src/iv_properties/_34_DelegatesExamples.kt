package iv_properties

import util.TODO
import util.doc34
import kotlin.reflect.KProperty

class LazyPropertyUsingDelegates(val initializer: () -> Int) {
    val lazyValue: Int by Delegate(initializer)

}

class Delegate(val f: () -> Int) {
    var value: Int? = null
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        value = value ?: f()
        return value!!
    }
}


fun todoTask34(): Lazy<Int> = TODO(
    """
        Task 34.
        Read about delegated properties and make the property lazy by using delegates.
    """,
    documentation = doc34()
)
