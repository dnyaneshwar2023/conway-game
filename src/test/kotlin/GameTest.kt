import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GameTest {
    @Test
    fun `it should return the next generation given all cells are alive in current generation`() {
        val cells = ArrayList<ArrayList<Cell>>()
        val rows = 5
        val cols = 5
        for(i in 0 until rows) {
            cells.add(ArrayList())
        }

        val expectedLiveCells: MutableList<Cell> = mutableListOf()

        for(rowNumber in 0 until rows) {
            for(colNumber in 0 until  cols) {
                val cell = Cell(Position(rowNumber, colNumber), CellStatus.LIVE)
                cells[rowNumber].add(cell)

                if(rowNumber == 0 || rowNumber == rows - 1 || colNumber == 0 || colNumber == cols - 1) {
                    expectedLiveCells.add(cell)
                }
            }
        }

        val nextGeneration = Game(cells).getNextGeneration()

        val actualLiveCells = filterLiveCells(nextGeneration)

        assertTrue(expectedLiveCells.containsAll(actualLiveCells))

    }

    private fun filterLiveCells(nextGeneration: ArrayList<ArrayList<Cell>>): MutableList<Cell> {
        val liveCells = mutableListOf<Cell>()
        for(row in 0 until nextGeneration.size) {
            liveCells.addAll(nextGeneration[row].filter { it.isAlive() })
        }
        return liveCells
    }
}
