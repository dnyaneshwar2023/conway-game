class Game(private val cells: ArrayList<ArrayList<Cell>>) {

    fun getNextGeneration(): ArrayList<java.util.ArrayList<Cell>> {
        val nextGeneration = ArrayList<ArrayList<Cell>>()

        for (rowNumber in 0 until cells.size) {
            nextGeneration.add(ArrayList())

            cells[rowNumber].forEach { cell ->
                nextGeneration[rowNumber].add(
                    Cell(
                        Position(
                            cell.position.xCoordinate,
                            cell.position.yCoordinate
                        ), cell.status
                    )
                )
            }

        }

        for (rowNumber in 0 until cells.size) {
            for (columnNumber in 0 until cells[rowNumber].size) {
                val cell = nextGeneration[rowNumber][columnNumber]
                val liveNeighboursCount = getLiveNeighbours(cell)

                if (cell.isAlive()) {
                    if (liveNeighboursCount < 2 || liveNeighboursCount > 3) {
                        cell.status = CellStatus.DEAD
                    }
                } else {
                    if (liveNeighboursCount == 3) {
                        cell.status = CellStatus.LIVE
                    }
                }

            }
        }

        return nextGeneration
    }

    private fun areVerticesWithinBoundry(x: Int, y: Int): Boolean {
        return x >= 0 && x < cells.size && y >= 0 && y < cells[0].size
    }

    private fun getLiveNeighbours(cell: Cell): Int {
        val directionX = listOf(0, 0, 1, -1, 1, -1, 1, -1)
        val directionY = listOf(1, -1, 0, 0, 1, -1, -1, 1)
        var count = 0

        for (index in directionX.indices) {
            val xCoordinateOfNeighbour = cell.position.xCoordinate + directionX[index]
            val yCoordinateOfNeighbour = cell.position.yCoordinate + directionY[index]

            if (areVerticesWithinBoundry(xCoordinateOfNeighbour, yCoordinateOfNeighbour)) {
                if (cells[xCoordinateOfNeighbour][yCoordinateOfNeighbour].isAlive()) {
                    count++
                }
            }
        }
        return count
    }
}
