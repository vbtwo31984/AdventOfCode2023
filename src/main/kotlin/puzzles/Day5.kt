package puzzles

class Day5 : Puzzle(5) {
    val seeds = parseSeeds()
    private val seedToSoil = getMappings(3)
    private val soilToFertilizer = getMappings(5 + seedToSoil.size)
    private val fertilizerToWater = getMappings(7 + seedToSoil.size + soilToFertilizer.size)
    private val waterToLight = getMappings(9 + seedToSoil.size + soilToFertilizer.size + fertilizerToWater.size)
    private val lightToTemperature = getMappings(11 + seedToSoil.size + soilToFertilizer.size + fertilizerToWater.size + waterToLight.size)
    private val temperatureToHumidity = getMappings(13 + seedToSoil.size + soilToFertilizer.size + fertilizerToWater.size + waterToLight.size + lightToTemperature.size)
    private val humidityToLocation = getMappings(15 + seedToSoil.size + soilToFertilizer.size + fertilizerToWater.size + waterToLight.size + lightToTemperature.size + temperatureToHumidity.size)

    val allMappings = listOf(seedToSoil, soilToFertilizer, fertilizerToWater, waterToLight, lightToTemperature, temperatureToHumidity, humidityToLocation)

    override fun partOne() {
        val min = seeds.map { seed ->
            allMappings.fold(seed) { acc, mappings ->
                getDestination(acc, mappings)
            }
        }.min()
        println("Part 1: $min")
    }

    override fun partTwo() {
        TODO("Not yet implemented")
    }

    fun parseSeeds(): List<Long> {
        val seeds = input[0].split(' ').drop(1)
        return seeds.map { it.toLong() }
    }

    fun getMappings(startLine: Int): List<Mapping> {
        var index = startLine
        val mappings = mutableListOf<Mapping>()
        do {
            val line = input[index++]
            if(line.isNotBlank()) {
                mappings.add(Mapping(line))
            }
        } while (line.isNotBlank())
        return mappings
    }

    fun getDestination(num: Long, mappings: List<Mapping>): Long {
        mappings.forEach {
            if (it.inRange(num)) {
                return it.getDestination(num)
            }
        }
        return num
    }
}

class Mapping(line: String) {
    private val destinationStart: Long
    private val sourceStart: Long
    private val rangeLength: Long

    init {
        val split = line.split(' ')
        destinationStart = split[0].toLong()
        sourceStart = split[1].toLong()
        rangeLength = split[2].toLong()
    }

    fun inRange(num: Long): Boolean {
        return num >= sourceStart && num <= sourceStart + rangeLength
    }

    fun getDestination(num: Long): Long {
        val diff = num - sourceStart
        return destinationStart + diff
    }
}