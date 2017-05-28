package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) {
    operator fun  compareTo(date2: MyDate): Int =
        listOf(year.compareTo(date2.year),
               month.compareTo(date2.month),
               dayOfMonth.compareTo(date2.dayOfMonth)).fold(0, { acc, e ->
            if (acc == 0) e else acc
        })

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate =
    addTimeIntervals(timeInterval, 1)

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate =
    addTimeIntervals(repeatedTimeInterval.ti, repeatedTimeInterval.n)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;
    operator fun  times(i: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, i)
}

data class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    private var current = start

    operator fun contains(date: MyDate): Boolean {
       return date >= start && date <= endInclusive
    }

    operator fun  iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
        override fun hasNext(): Boolean {
            return current <= endInclusive
        }

        override fun next(): MyDate {
            val result = current
            current = current.nextDay()
            return result
        }
    }
}
