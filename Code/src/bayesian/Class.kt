package bayesian

class Class(var label: Int, var mean: Double, var min: Double, var max: Double, var stdv: Double) {

    companion object {
        fun create(input: ArrayList<Entry>, label: Int): Class {
            var mean = 0.0
            var stdv = 0.0
            val denominator = input.size
            val x1Values: ArrayList<Double> = ArrayList()

            // Get mean
            for (entry in input) {
                mean += entry.x1
                x1Values.add(entry.x1)
            }
            mean /= denominator

            // Get min and max
            val min = x1Values.min()
            val max = x1Values.max()

            // Get stdv
            for (value in x1Values) {
                stdv += Math.pow((value - mean), 2.0)
            }
            stdv /= denominator
            stdv = Math.sqrt(stdv)

            return Class(label, mean, min!!, max!!, stdv)
        }
    }

    override fun toString(): String {
        return "Class label: $label, mean: $mean, range: $min ~ $max, stdv: $stdv"
    }
}
