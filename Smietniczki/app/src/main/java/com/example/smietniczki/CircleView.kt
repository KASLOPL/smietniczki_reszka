import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

class CircleView(context: Context, private var circleColor: Int) : View(context) {

    private val paint = Paint()

    init {
        paint.isAntiAlias = true // Wygładzanie krawędzi
        paint.color = circleColor  // Ustawiamy początkowy kolor koła
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Obliczamy promień koła, aby było dopasowane do rozmiaru widoku
        val radius = Math.min(width, height) / 2f
        val cx = width / 2f // Środek koła w poziomie
        val cy = height / 2f // Środek koła w pionie

        // Rysowanie koła
        canvas.drawCircle(cx, cy, radius, paint)
    }

    // Funkcja umożliwiająca zmianę koloru koła w dowolnym momencie
    fun setCircleColor(color: Int) {
        paint.color = color
        invalidate()  // Odświeżamy widok, aby narysować go ponownie z nowym kolorem
    }
}
