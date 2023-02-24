data class Cell(val position: Position, var status: CellStatus) {
    fun isAlive(): Boolean {
        return status == CellStatus.LIVE
    }
}
