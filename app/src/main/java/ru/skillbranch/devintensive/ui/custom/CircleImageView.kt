package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.annotation.Dimension.DP
import androidx.core.graphics.drawable.toBitmap
import ru.skillbranch.devintensive.R


class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ImageView(context, attrs, defStyleAttr) {

    private var borderColor = Color.WHITE
    @Dimension(unit = DP)
    private var borderWidth = convertDpToPixels(2f, context)

//    private lateinit var mask: Bitmap

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CircleImageView)
            borderColor = typedArray.getColor(R.styleable.CircleImageView_cv_borderColor, Color.WHITE)
            borderWidth = typedArray.getDimension(R.styleable.CircleImageView_cv_borderWidth, borderWidth)
            typedArray.recycle()
        }
    }

    val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = borderColor
        strokeWidth = borderWidth
    }

//    val fillPaint = Paint().apply{
//        style = Paint.Style.FILL
//        color = Color.WHITE
//    }

    val justPaint = Paint()

//    val maskPaint = Paint().apply {
//        xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
//    }

//    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
//        mask = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(mask)
//        canvas.drawColor(Color.TRANSPARENT)
//        canvas.drawOval(0f, 0f, w.toFloat(), h.toFloat(), Paint(Color.TRANSPARENT))
//        canvas.drawOval(0f, 0f, w.toFloat(), h.toFloat(), borderPaint)
//    }

    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        canvas?.drawBitmap(mask, 0f, 0f, justPaint)
        canvas?.drawOval( 0f, 0f, width.toFloat(), height.toFloat(), justPaint
            .apply { shader = BitmapShader(drawable.toBitmap(width, height), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT) })
        canvas?.drawOval(0f, 0f, width.toFloat(), height.toFloat(), borderPaint)
    }

    @Dimension
    fun getBorderWidth():Int{
        return convertPixelsToDp(borderWidth, context).toInt()
    }

    fun setBorderWidth(@Dimension dp:Int) {
        borderWidth = convertDpToPixels(dp.toFloat(), context)
        borderPaint.strokeWidth = borderWidth
        invalidate()
    }

    fun getBorderColor():Int {
        return borderColor
    }

    fun setBorderColor(hex:String){
        borderColor = Color.parseColor(hex)
        borderPaint.color = borderColor
        invalidate()
    }

    fun setBorderColor(@ColorRes colorId: Int){
        borderColor = colorId
        borderPaint.color = borderColor
        invalidate()
    }



    fun convertDpToPixels(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun convertPixelsToDp(px: Float, context: Context): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}